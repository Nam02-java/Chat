package MVC.Controller.Client.Networking.Input;

import MVC.Model.Data;
import MVC.Service.InterfaceService.File.ParseFile;
import MVC.Service.InterfaceService.String.ParseString;
import MVC.Service.LazySingleton.UserName.UserNameManager;
import MVC.Service.InterfaceService.IO.SocketInputReader;
import MVC.Service.ServiceImplenments.File.ParseFileImplementation;
import MVC.Service.ServiceImplenments.String.ParseStringImplementation;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.net.Socket;

public class InputDataFromServer {
    private BufferedReader inFromServer;
    private SocketInputReader socketInputReader;
    private ParseString parseString;

    private ParseFile parseFile;

    public InputDataFromServer(SocketInputReader socketInputReader) {
        this.socketInputReader = socketInputReader;
        this.parseString = new ParseStringImplementation();
        this.parseFile = new ParseFileImplementation();
    }

    public void receiveData(Socket socket) throws IOException {
        inFromServer = socketInputReader.getData(socket);
        new Thread(() -> {
            try {
                String messageFromServer;
                boolean flag = false;
                while ((messageFromServer = inFromServer.readLine()) != null) {

                    int biggestID = parseFile.getBiggestID(new File(Data.getFilePath()));
                    int serverCurrentMessageID = parseString.getIDFromHistoryMessage(messageFromServer);

                    if (serverCurrentMessageID < biggestID) {
                        flag = true;

                    } else {
                        if (flag = true) {
                            continue;
                        }
                        flag = false;
                    }

                    if (messageFromServer.contains(UserNameManager.getInstance().getUsername())) {
                        String userName = UserNameManager.getInstance().getUsername();
                        messageFromServer = messageFromServer.replaceFirst(userName + " : ", "");
                    }
                    Thread.sleep(1000);
                    System.out.println(messageFromServer);

                }
            } catch (IOException e) {
                e.printStackTrace();
                throw new RuntimeException(e);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }).start();
    }
}

//  int biggestID = parseFile.getBiggestID(new File(Data.getFilePath()));
//                    if (messageFromServer.contains("Old message")) {
//                        flag = true;
//                        serverCurrentMessageID = parseString.getIDFromHistoryMessage(messageFromServer);
//                    } else {
//                        if (flag == true) {
//                            continue;
//                        }
//                        flag = false;
//                        serverCurrentMessageID = biggestID;
//                    }
//                    if (serverCurrentMessageID < biggestID) {
//                        flag = false;
//
//                        if (messageFromServer.contains(UserNameManager.getInstance().getUsername())) {
//                            String userName = UserNameManager.getInstance().getUsername();
//                            messageFromServer = messageFromServer.replaceFirst(userName + " : ", "");
//                        }
//                    }
//                    if (messageFromServer.contains(UserNameManager.getInstance().getUsername())) {
//                        String userName = UserNameManager.getInstance().getUsername();
//                        messageFromServer = messageFromServer.replaceFirst(userName + " : ", "");
//                    }
//                    System.out.println(messageFromServer);