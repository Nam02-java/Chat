package MVC.Service.ServiceImplenments.String;

import MVC.Service.InterfaceService.String.ParseString;

import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class ParseStringImplementation implements ParseString {


    @Override
    public int getSize(String fullText, String parseText) {
        int sizeHistoryData = 0;
        String[] parts = fullText.split(parseText);
        if (parts.length == 2) {
            sizeHistoryData = Integer.parseInt(parts[1].trim());
        }
        return sizeHistoryData;
    }

    @Override
    public String removeText(String fullText, String discardText) {
        int index = fullText.indexOf(discardText);
        if (index != -1) {
            fullText = fullText.substring(0, index);
        }
        return fullText;
    }

    @Override
    public int getID(String fullText) {

        int id = 0;

        Pattern pattern = Pattern.compile("\\(\\s*(\\d+)\\.");
        Matcher matcher = pattern.matcher(fullText);

        if (matcher.find()) {
            String idStr = matcher.group(1);
            id = Integer.parseInt(idStr);
        }

        return id;
    }
}
