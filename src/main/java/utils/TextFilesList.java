package utils;

import java.io.File;
import java.util.HashMap;

public class TextFilesList {

    public HashMap<String, String> getTextFilesList() {
        File directory = new File("lines/");
        File[] filesList = directory.listFiles();
        HashMap<String, String> filesMap = new HashMap<>();

        if (filesList != null) {
            for (File file : filesList) {
                filesMap.put(file.getName(), file.getPath());
            }
        }

        return filesMap;
    }
}
