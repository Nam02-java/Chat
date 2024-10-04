package MVC.Controller.Client.Networking.Input;

import MVC.Service.Enum.HistoryDataState;
import MVC.Service.InterfaceService.String.ParseString;
import MVC.Service.LazySingleton.HistoryData.HistoryDataManager;
import MVC.Service.LazySingleton.UserName.UserNameManager;
import MVC.Service.InterfaceService.IO.SocketInputReader;
import MVC.Service.ServiceImplenments.String.ParseStringImplementation;

import java.io.BufferedReader;
import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class InputDataFromServer {
    private BufferedReader inFromServer;
    private SocketInputReader socketInputReader;
    private ParseString parseString;

    public InputDataFromServer(SocketInputReader socketInputReader) {
        this.socketInputReader = socketInputReader;
        this.parseString = new ParseStringImplementation();
    }

    public void receiveData(Socket socket) throws IOException {
        inFromServer = socketInputReader.getData(socket);
        new Thread(() -> {
            try {
                String messageFromServer;
                List<String> list = new ArrayList<>();
                int sizeHistoryData = 0;
                while ((messageFromServer = inFromServer.readLine()) != null) {
                    if (HistoryDataManager.getInstance().getHistoryDataState().equals(HistoryDataState.LOADING)) {

                        sizeHistoryData = parseString.getSize(messageFromServer, " - total message ");

                        messageFromServer = parseString.removeText(messageFromServer, " - total message ");

                        if (messageFromServer.contains(UserNameManager.getInstance().getUsername())) {
                            String userName = UserNameManager.getInstance().getUsername();
                            messageFromServer = messageFromServer.replaceFirst(userName + " : ", "");
                        }

                        list.add(messageFromServer);
                        if (list.size() == sizeHistoryData) {
                            for (int i = 0; i < list.size(); i++) {
                                Thread.sleep(1000);
                                System.out.println(list.get(i));
                            }
                            HistoryDataManager.getInstance().setHistoryDataState(HistoryDataState.NO_LOADING);
                            list.clear();
                        }
                    } else if (HistoryDataManager.getInstance().getHistoryDataState().equals(HistoryDataState.NO_LOADING)) {
                        if (list.isEmpty()) {
                            if (messageFromServer.contains(UserNameManager.getInstance().getUsername())) {
                                String userName = UserNameManager.getInstance().getUsername();
                                messageFromServer = messageFromServer.replaceFirst(userName + " : ", "");
                            }
                            System.out.println(messageFromServer);
                        }
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
