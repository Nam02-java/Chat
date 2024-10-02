package MVC.Model;

import java.net.Socket;
import java.util.ArrayList;

public class Data {
    private final static ArrayList<Socket> clientSockets = new ArrayList<>();

    private static ArrayList<Socket> clientSocketLoadingHistory = new ArrayList<>();
    private final static int PORT = 8080;
    private final static String filePath = "E:\\Test.txt";
    private int startLine = 1;


    public static ArrayList<Socket> getClientSockets() {
        return clientSockets;
    }

    public static ArrayList<Socket> getClientSocketLoadingHistory() {
        return clientSocketLoadingHistory;
    }

    public static int getPORT() {
        return PORT;
    }

    public static String getFilePath() {
        return filePath;
    }

    public int getStartLine() {
        return startLine;
    }

    public void setStartLine(int startLine) {
        this.startLine = startLine;
    }
}
