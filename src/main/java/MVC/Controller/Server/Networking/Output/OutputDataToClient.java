package MVC.Controller.Server.Networking.Output;

import MVC.Model.Data;
import MVC.Service.InterfaceService.IO.SocketDataOutput;

import java.io.BufferedReader;
import java.io.IOException;
import java.net.Socket;

public class OutputDataToClient {

    private SocketDataOutput socketDataOutput;

    public OutputDataToClient(SocketDataOutput socketDataOutput) {
        this.socketDataOutput = socketDataOutput;
    }

    public void sendData(Socket clientSocket, BufferedReader inFromClient) {
        String messageFromClient;

        try {
            while ((messageFromClient = inFromClient.readLine()) != null) {
                System.out.println(messageFromClient);
                for (Socket socket : Data.getClientSockets()) {
                    if (socket != clientSocket) {
                        socketDataOutput.sendData(socket, messageFromClient);
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
