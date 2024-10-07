package MVC.Controller.Server.Networking.Output;

import MVC.Model.Data;
import MVC.Service.InterfaceService.IO.SocketDataOutput;
import MVC.Service.InterfaceService.Log.ReadLogServer;

import java.io.BufferedReader;
import java.io.IOException;
import java.net.Socket;
import java.util.List;

public class OutputDataToClient {
    private SocketDataOutput socketDataOutput;
    private ReadLogServer readLogServer;
    private Data data;

    public OutputDataToClient(SocketDataOutput socketDataOutput, ReadLogServer readLogServer, Data data) {
        this.socketDataOutput = socketDataOutput;
        this.readLogServer = readLogServer;
        this.data = data;
    }
    public void sendData(Socket clientSocket, BufferedReader inFromClient) {
        String messageFromClient;

        try {
            while ((messageFromClient = inFromClient.readLine()) != null) {

                System.out.println(messageFromClient);

                if (messageFromClient.contains("- request history data")) {
                    List<String> listChatHistory = readLogServer.read(data);
                    for (String message : listChatHistory) {
                        socketDataOutput.sendData(clientSocket, "Old message ( " + message + " ) - total message " + listChatHistory.size());
                    }

                } else {
                    for (Socket socket : Data.getClientSockets()) {
                        if (socket != clientSocket) {
                            socketDataOutput.sendData(socket, messageFromClient);
                        }
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
