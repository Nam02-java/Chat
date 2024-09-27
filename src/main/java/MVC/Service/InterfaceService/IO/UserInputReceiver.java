package MVC.Service.InterfaceService.IO;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public interface UserInputReceiver {

    /**
     * new update from interface (java 8)
     * defauld void method
     * https://www.oracle.com/webfolder/technetwork/tutorials/obe/java/JavaSE8DefaultMethods/JavaSE8DefaultMethods.html
     * @return
     */
    default BufferedReader getData() {
        return new BufferedReader(new InputStreamReader(System.in));
    }
}
