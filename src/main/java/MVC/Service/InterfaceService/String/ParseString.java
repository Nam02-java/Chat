package MVC.Service.InterfaceService.String;


public interface ParseString {

    int getSize(String fullText, String parseText);

    String removeText(String fullText, String discardText);

    int getID(String fullText);

}
