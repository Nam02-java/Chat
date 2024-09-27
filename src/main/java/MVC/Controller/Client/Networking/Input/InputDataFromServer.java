package MVC.Controller.Client.Networking.Input;

import MVC.Service.InterfaceService.IO.SocketInputReader;

import java.io.BufferedReader;
import java.io.IOException;
import java.net.Socket;

public class InputDataFromServer {

    private BufferedReader inFromServer;
    private SocketInputReader socketInputReader;

    public InputDataFromServer(SocketInputReader socketInputReader) {
        this.socketInputReader = socketInputReader;
    }

    public void receiveData(Socket socket) throws IOException {
        inFromServer = socketInputReader.getData(socket);
        new Thread(() -> {
            try {
                String messageFromServer;
                while ((messageFromServer = inFromServer.readLine()) != null) {
                    System.out.println("Received: " + messageFromServer);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }).start();
    }
}
