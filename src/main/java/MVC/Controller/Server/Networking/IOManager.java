package MVC.Controller.Server.Networking;

import MVC.Controller.Server.Networking.Input.InputDataFromClient;

import java.io.IOException;
import java.net.Socket;

public class IOManager implements Runnable {
    private Socket socket;
    private InputDataFromClient inputDataFromClient;

    public IOManager(Socket socket, InputDataFromClient inputDataFromClient) {
        this.socket = socket;
        this.inputDataFromClient = inputDataFromClient;
    }

    @Override
    public void run() {
        try {
            inputDataFromClient.receiveData(socket);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
