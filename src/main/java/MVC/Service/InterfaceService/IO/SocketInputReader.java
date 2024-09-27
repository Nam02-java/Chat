package MVC.Service.InterfaceService.IO;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

public interface SocketInputReader {

    /**
     * new update from interface (java 8)
     * defauld void method
     * https://www.oracle.com/webfolder/technetwork/tutorials/obe/java/JavaSE8DefaultMethods/JavaSE8DefaultMethods.html
     * @param socket
     * @return
     * @throws IOException
     */
    default BufferedReader getData(Socket socket) throws IOException {
        return new BufferedReader(new InputStreamReader(socket.getInputStream()));
    }
}
