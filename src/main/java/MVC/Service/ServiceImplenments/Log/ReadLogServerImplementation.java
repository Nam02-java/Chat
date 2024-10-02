package MVC.Service.ServiceImplenments.Log;

import MVC.Model.Data;
import MVC.Service.InterfaceService.Log.ReadLogServer;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.List;

public class ReadLogServerImplementation implements ReadLogServer {

    private List<String> list;


    public ReadLogServerImplementation() {
        this.list = new ArrayList<>();
    }


    @Override
    public List<String> read(Data data) {
        list.clear();
        try (RandomAccessFile raf = new RandomAccessFile(Data.getFilePath(), "r")) {
            String line;
            int currentLine = 0;

            while ((line = raf.readLine()) != null) {
                currentLine++;

                if (currentLine >= data.getStartLine() && currentLine < data.getStartLine() + 5) {
                    list.add(line);
                }

                if (currentLine >= data.getStartLine() + 5) {
                    break;
                }
            }

            if (currentLine < data.getStartLine()) {
            } else if (currentLine < data.getStartLine() + 5) {
            }

            data.setStartLine(data.getStartLine() + 5);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return list;
    }
}
