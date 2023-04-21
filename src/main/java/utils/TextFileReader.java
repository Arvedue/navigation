package utils;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class TextFileReader implements Reader {

    @Override
    public List<String> read(String pathname) {
        List<String> data = new ArrayList<>();
        try {
            File myFile = new File(pathname);
            Scanner myReader = new Scanner(myFile);
            while (myReader.hasNextLine()) {
                data.add(myReader.nextLine());
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        return data;
    }

}
