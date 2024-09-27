package MVC.Controller.Server.Config;

import MVC.Controller.Server.Networking.IOManager;
import MVC.Controller.Server.Networking.Input.InputDataFromClient;
import MVC.Controller.Server.Networking.Output.OutputDataToClient;
import MVC.Model.Data;
import MVC.Service.ServiceImplenments.IO.SocketDataOutputImplementation;
import MVC.Service.ServiceImplenments.IO.SocketInputReaderImplementation;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class ClientConnectionHandler {
    private InputDataFromClient inputDataFromClient;
    private OutputDataToClient outputDataToClient;
    private Socket clientSocket;

    public ClientConnectionHandler() {
        outputDataToClient = new OutputDataToClient(new SocketDataOutputImplementation());
    }

    public void waitForClients(ServerSocket serverSocket) throws IOException {
        while (true) {
            clientSocket = serverSocket.accept();

            this.inputDataFromClient = new InputDataFromClient(new SocketInputReaderImplementation(), outputDataToClient);

            Data.getClientSockets().add(clientSocket);
            System.out.println("New client connected : " + clientSocket.getInetAddress());


            new Thread(new IOManager(clientSocket, inputDataFromClient)).start();
        }
    }
}
