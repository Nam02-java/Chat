package MVC.Controller.Server;

import MVC.Controller.Server.Config.ClientConnectionHandler;
import MVC.Controller.Server.Config.PortConfig;
import MVC.Service.ServiceImplenments.Initialization.ServerInitializationImplementation;

import java.io.IOException;
import java.net.ServerSocket;

public class ServerManager {

    /**
     * warning !
     * this is port config of server
     */
    private PortConfig portConfig;
    private ClientConnectionHandler clientConnectionHandler;

    public ServerManager() {
        this.portConfig = new PortConfig(new ServerInitializationImplementation());
        this.clientConnectionHandler = new ClientConnectionHandler();
    }

    public void initializeServer() throws IOException {
        ServerSocket serverSocket = portConfig.configure();
        clientConnectionHandler.waitForClients(serverSocket);
    }
}
