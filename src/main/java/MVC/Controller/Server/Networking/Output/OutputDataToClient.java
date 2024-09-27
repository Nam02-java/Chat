package MVC.Controller.Server.Networking.Output;


import MVC.Model.Data;
import MVC.Service.InterfaceService.IO.SocketDataOutput;

import java.io.IOException;
import java.net.Socket;

public class OutputDataToClient {

    private SocketDataOutput socketDataOutput;

    public OutputDataToClient(SocketDataOutput socketDataOutput) {
        this.socketDataOutput = socketDataOutput;
    }

    public void sendData(Socket clientSocket, String message) throws IOException {
        for (Socket socket : Data.getClientSockets()) {
            if (socket != clientSocket) {
                socketDataOutput.sendData(socket, message);
            }
        }
    }
}
