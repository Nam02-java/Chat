package MVC;


import MVC.Controller.Server.ServerManager;
import MVC.Service.InterfaceService.Log.ConsoleLoggerServer;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;

public class StartServer {
    public static void main(String[] args) throws IOException {
//        // Tạo file log để lưu trữ lịch sử tin nhắn
//        FileOutputStream logFile = new FileOutputStream("E:\\Test.txt", true); // true để ghi thêm vào file
//
//        // Lưu output hiện tại (console) lại
//        PrintStream consoleOut = System.out;
//
//        // Tạo đối tượng PrintStream để ghi ra cả console và file
//        PrintStream fileOut = new PrintStream(logFile);
//
//        // Tạo PrintStream kết hợp cả console và file
//        PrintStream combinedOut = new PrintStream(new OutputStream() {
//            private StringBuilder buffer = new StringBuilder();
//
//            @Override
//            public void write(int b) throws IOException {
//                // Chuyển b thành một ký tự
//                char c = (char) b;
//
//                // Ghi vào bộ đệm (buffer) tạm thời
//                buffer.append(c);
//
//                // Kiểm tra nếu gặp ký tự xuống dòng (nghĩa là một dòng đã hoàn thành)
//                if (c == '\n') {
//                    String line = buffer.toString();
//
//                    // Ghi ra console luôn luôn
//                    consoleOut.print(line);
//
//                    // Ghi vào file chỉ khi không phải dòng thông báo hệ thống
//                    if (!isSystemMessage(line)) {
//                        fileOut.print(line);
//                    }
//
//                    // Xóa buffer sau khi xử lý dòng
//                    buffer.setLength(0);
//                }
//            }
//        });
//
//        // Chuyển hướng output từ System.out sang đối tượng combinedOut
//        System.setOut(combinedOut);
//
//        // Khởi tạo và chạy server như bình thường
//        ServerManager serverManager = new ServerManager();
//        serverManager.initializeServer();
//    }
//
//    // Hàm kiểm tra xem tin nhắn có phải là thông báo hệ thống không
//    private static boolean isSystemMessage(String message) {
//        return message.contains("New client connected");
//    }
        ServerManager serverManager = new ServerManager();
        serverManager.initializeServer();
    }
}