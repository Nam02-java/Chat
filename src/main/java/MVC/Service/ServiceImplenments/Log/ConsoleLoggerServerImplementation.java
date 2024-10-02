package MVC.Service.ServiceImplenments.Log;

import MVC.Service.InterfaceService.Log.ConsoleLoggerServer;

import java.io.*;

public class ConsoleLoggerServerImplementation implements ConsoleLoggerServer {
    @Override
    public void save() throws FileNotFoundException {
        FileOutputStream logFile = new FileOutputStream("E:\\Test.txt", true);

        PrintStream consoleOut = System.out;

        PrintStream fileOut = new PrintStream(logFile);

        PrintStream combinedOut = new PrintStream(new OutputStream() {
            private StringBuilder buffer = new StringBuilder();

            @Override
            public void write(int b) throws IOException {
                char c = (char) b;

                buffer.append(c);

                if (c == '\n') {
                    String line = buffer.toString();

                    consoleOut.print(line);

                    if (!isSystemMessage(line)) {
                        fileOut.print(line);
                    }

                    buffer.setLength(0);
                }
            }
        });

        System.setOut(combinedOut);

    }
    private static boolean isSystemMessage(String message) {
        return message.contains("New client connected");
    }
}
