package MVC.Controller.Server;

import MVC.Controller.Server.Config.ClientConnectionHandler;
import MVC.Controller.Server.Config.PortConfig;
import MVC.Controller.Server.Networking.IOManager;
import MVC.Controller.Server.Networking.Input.InputDataFromClient;
import MVC.Controller.Server.Networking.Output.OutputDataToClient;
import MVC.Service.ServiceImplenments.IO.SocketDataOutputImplementation;
import MVC.Service.ServiceImplenments.IO.SocketInputReaderImplementation;
import MVC.Service.ServiceImplenments.Initialization.ServerInitializationImplementation;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerManager {

    private PortConfig portConfig;
    private ClientConnectionHandler clientConnectionHandler;
    private InputDataFromClient inputDataFromClient;
    private OutputDataToClient outputDataToClient;

    public ServerManager() throws IOException {
        this.portConfig = new PortConfig(new ServerInitializationImplementation());
        this.clientConnectionHandler = new ClientConnectionHandler();
        inputDataFromClient = new InputDataFromClient(new SocketInputReaderImplementation());
        outputDataToClient = new OutputDataToClient(new SocketDataOutputImplementation());
    }

    public void initializeServer() throws IOException {
        ServerSocket serverSocket = portConfig.configure();
        while (true) {
            Socket clientSocket = clientConnectionHandler.waitForClients(serverSocket);
            new Thread(new IOManager(clientSocket, inputDataFromClient, outputDataToClient)).start();
        }
    }
}
