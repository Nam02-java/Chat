package MVC.Controller.Client.Networking.Input;

import MVC.Service.LazySingleton.UserName.UserNameManager;
import MVC.Service.InterfaceService.IO.SocketInputReader;

import java.io.BufferedReader;
import java.io.IOException;
import java.net.Socket;

public class InputDataFromServer {

    private BufferedReader inFromServer;
    private SocketInputReader socketInputReader;

    public InputDataFromServer(SocketInputReader socketInputReader) {
        this.socketInputReader = socketInputReader;
    }

    public void receiveData(Socket socket) throws IOException {
        inFromServer = socketInputReader.getData(socket);
        new Thread(() -> {
            try {
                String messageFromServer;
                while ((messageFromServer = inFromServer.readLine()) != null) {
                    if(messageFromServer.contains(UserNameManager.getInstance().getUsername())){
                        String userName = UserNameManager.getInstance().getUsername();
                        messageFromServer = messageFromServer.replaceFirst(userName + " : ", "");
                    }
                    System.out.println(messageFromServer);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }).start();
    }
}
