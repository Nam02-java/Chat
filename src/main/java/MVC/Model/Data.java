package MVC.Model;

import java.net.Socket;
import java.util.ArrayList;

public class Data {

    private final static ArrayList<Socket> clientSockets = new ArrayList<>();

    private final static int PORT = 8080;

    public static ArrayList<Socket> getClientSockets() {
        return clientSockets;
    }

    public static int getPORT() {
        return PORT;
    }
}
