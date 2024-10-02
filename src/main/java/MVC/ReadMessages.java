package MVC;

import java.io.IOException;
import java.io.RandomAccessFile;

public class ReadMessages {

    public static void main(String[] args) {
        readMessagesFromFile("E:\\Test.txt", 6); // Đọc từ dòng thứ 5
    }

    public static void readMessagesFromFile(String filePath, int startLine) {
        try (RandomAccessFile raf = new RandomAccessFile(filePath, "r")) {
            String line;
            int currentLine = 0;

            // Duyệt qua từng dòng trong file
            while ((line = raf.readLine()) != null) {
                currentLine++;

                // Kiểm tra nếu đang ở dòng bắt đầu
                if (currentLine >= startLine && currentLine < startLine + 5) {
                    System.out.println("Dòng thứ " + currentLine + ": " + line);
                }

                // Dừng đọc nếu đã đọc đủ 5 dòng sau dòng bắt đầu
                if (currentLine >= startLine + 5) {
                    break;
                }
            }

            // Nếu dòng hiện tại nhỏ hơn dòng bắt đầu, thông báo không đủ dòng
            if (currentLine < startLine) {
                System.out.println("File không có đủ dòng để đọc từ dòng " + startLine);
            } else if (currentLine < startLine + 5) {
                System.out.println("File không có đủ dòng để đọc từ dòng " + startLine + " tới dòng " + (startLine + 4));
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

