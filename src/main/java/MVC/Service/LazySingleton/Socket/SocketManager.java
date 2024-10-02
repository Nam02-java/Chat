package MVC.Service.LazySingleton.Socket;

import java.net.Socket;

public class SocketManager {
    private static SocketManager instance;
    private Socket socket;

    // Private constructor to prevent instantiation
    private SocketManager() {
    }

    // Synchronized method to control simultaneous access
    public static synchronized SocketManager getInstance() {
        if (instance == null) {
            instance = new SocketManager();
        }
        return instance;
    }

    // Method to set the socket
    public void setSocket(Socket socket) {
        this.socket = socket;
    }

    // Method to get the socket
    public Socket getSocket() {
        return socket;
    }

    // Method to remove or close the socket
    public void removeSocket() {
        if (this.socket != null && !this.socket.isClosed()) {
            try {
                this.socket.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        this.socket = null;
    }
}
