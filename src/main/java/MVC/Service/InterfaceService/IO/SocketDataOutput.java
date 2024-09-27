package MVC.Service.InterfaceService.IO;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public interface SocketDataOutput {

    /**
     * new update from interface (java 8)
     * defauld void method
     * https://www.oracle.com/webfolder/technetwork/tutorials/obe/java/JavaSE8DefaultMethods/JavaSE8DefaultMethods.html
     * @param socket
     * @param message
     * @throws IOException
     */
    default void sendData(Socket socket, String message) throws IOException {
        DataOutputStream outToClient = new DataOutputStream(socket.getOutputStream());
        outToClient.writeBytes(message + "\n");
    }
}
