package MVC.Service.ServiceImplenments.Log;

import MVC.Model.Data;
import MVC.Service.InterfaceService.Log.ConsoleLoggerServer;

import java.io.*;

public class ConsoleLoggerServerImplementation implements ConsoleLoggerServer {
    @Override
    public void save() throws FileNotFoundException {
        FileOutputStream logFile = new FileOutputStream(Data.getFilePath(), true);

        PrintStream consoleOut = System.out;

        PrintStream fileOut = new PrintStream(logFile);

        PrintStream combinedOut = new PrintStream(new OutputStream() {
            private StringBuilder buffer = new StringBuilder();

            @Override
            public void write(int b) {
                char c = (char) b;

                buffer.append(c);

                if (c == '\n') {
                    String line = buffer.toString();

                    consoleOut.print(line);

                    if (!isSystemMessageConnected(line) &&
                            !isSystemMessageHistory(line) &&
                            !isSystemMessageRequestHistory(line)) {
                        fileOut.print(line);
                    }

                    buffer.setLength(0);
                }
            }
        });

        System.setOut(combinedOut);
    }

    private static boolean isSystemMessageConnected(String message) {
        return message.contains("New client connected");
    }

    private static boolean isSystemMessageHistory(String message) {
        return message.contains("Old message");
    }

    private static boolean isSystemMessageRequestHistory(String message) {
        return message.contains("- request history data");
    }
}
