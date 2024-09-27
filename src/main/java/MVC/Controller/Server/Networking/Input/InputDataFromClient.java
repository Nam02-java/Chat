package MVC.Controller.Server.Networking.Input;

import MVC.Controller.Server.Networking.Output.OutputDataToClient;
import MVC.Service.InterfaceService.IO.SocketInputReader;

import java.io.BufferedReader;
import java.io.IOException;
import java.net.Socket;

public class InputDataFromClient {
    private BufferedReader inFromClient;
    private SocketInputReader socketInputReader;
    private OutputDataToClient outputDataToClient;


    public InputDataFromClient(SocketInputReader socketInputReader, OutputDataToClient outputDataToClient) {
        this.socketInputReader = socketInputReader;
        this.outputDataToClient = outputDataToClient;
    }

    public void receiveData(Socket socket) throws IOException {
        inFromClient = socketInputReader.getData(socket);

        String messageFromClient;
        try {
            while ((messageFromClient = inFromClient.readLine()) != null) {
                System.out.println("Received to server : " + messageFromClient);
                outputDataToClient.sendData(socket, messageFromClient);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
