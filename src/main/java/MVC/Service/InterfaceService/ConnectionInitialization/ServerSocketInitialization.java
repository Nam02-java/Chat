package MVC.Service.InterfaceService.ConnectionInitialization;

import MVC.Model.Data;

import java.io.IOException;
import java.net.ServerSocket;

public interface ServerSocketInitialization {

    /**
     * new update from interface (java 8)
     * defauld void method
     * https://www.oracle.com/webfolder/technetwork/tutorials/obe/java/JavaSE8DefaultMethods/JavaSE8DefaultMethods.html
     * @return
     * @throws IOException
     */
    default ServerSocket setUp() throws IOException {
        return new ServerSocket(Data.getPORT());
    }
}
