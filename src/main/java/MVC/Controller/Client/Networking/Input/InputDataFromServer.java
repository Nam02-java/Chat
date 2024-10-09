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
import java.util.ArrayList;
import java.util.List;

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
                List<String> historyDataList = new ArrayList<>();
                int sizeHistoryData = 0, serverHistoryMessageID = 0;

                while ((messageFromServer = inFromServer.readLine()) != null) {
                    int biggestID = parseFile.getBiggestID(new File(Data.getFilePath()));
                    if (messageFromServer.contains("Old message")) {
                        serverHistoryMessageID = parseString.getIDFromHistoryMessage(messageFromServer);
                        if (serverHistoryMessageID == biggestID) {
                            serverHistoryMessageID = biggestID - 1;
                        }
                    } else {
                        serverHistoryMessageID = biggestID;
                    }

                    if (serverHistoryMessageID < biggestID) {
                        sizeHistoryData = parseString.getSize(messageFromServer, " - total message ");
                        messageFromServer = parseString.removeText(messageFromServer, " - total message ");
                        if (messageFromServer.contains(UserNameManager.getInstance().getUsername())) {
                            String userName = UserNameManager.getInstance().getUsername();
                            messageFromServer = messageFromServer.replaceFirst(userName + " : ", "");
                        }
                        historyDataList.add(messageFromServer);
                        if (historyDataList.size() == sizeHistoryData) {
                            for (int i = 0; i < historyDataList.size(); i++) {
                                Thread.sleep(1000);
                                System.out.println(historyDataList.get(i));
                            }
                            historyDataList.clear();
                        }
                    } else if (historyDataList.isEmpty()) {
                        if (messageFromServer.contains(UserNameManager.getInstance().getUsername())) {
                            String userName = UserNameManager.getInstance().getUsername();
                            messageFromServer = messageFromServer.replaceFirst(userName + " : ", "");
                        }
                        System.out.println(messageFromServer);
                    }
                }


            } catch (IOException e) {
                e.printStackTrace();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }).start();
    }
}
