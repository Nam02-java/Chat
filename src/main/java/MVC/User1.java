package MVC;

import MVC.Controller.Client.ClientManager;
import MVC.Service.LazySingleton.UserName.UserNameManager;

import java.io.IOException;

public class User1 {
    public static void main(String[] args) throws IOException {
        UserNameManager.getInstance().setUsername("Nam02");

        ClientManager clientManager = new ClientManager();
        clientManager.initializeClient();

    }
}
