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
                    Data.getClientSocketLoadingHistory().add(clientSocket);
                    List<String> listChatHistory = readLogServer.read(data);
                    for (String message : listChatHistory) {
                        Thread.sleep(1000);
                        socketDataOutput.sendData(clientSocket, message);
                    }
                    Data.getClientSocketLoadingHistory().remove(clientSocket);

                } else {
                    System.out.println(Data.getClientSocketLoadingHistory().size());
                    while (Data.getClientSocketLoadingHistory().isEmpty()) {
                        for (Socket socket : Data.getClientSockets()) {
                            if (socket != clientSocket) {
                                if (Data.getClientSocketLoadingHistory().contains(socket)) {

                                }
                                socketDataOutput.sendData(socket, messageFromClient);
                            }
                        }
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
