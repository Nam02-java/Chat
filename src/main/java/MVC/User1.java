package MVC;

import MVC.Controller.Client.ClientManager;
import java.io.IOException;

public class User1 {
    public static void main(String[] args) throws IOException {
        ClientManager clientManager = new ClientManager();
        clientManager.initializeClient();
    }
}
