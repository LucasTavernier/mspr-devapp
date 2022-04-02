package main.java;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class Execute {

    public static void main(String[] args) throws IOException {
        FileFactory.generateIndex();
        File file = new File("./gosecuri/fichesAgentsVues/");
        if(!file.exists()){
            file.mkdir();
        }
        FileFactory.generateAllAgentHtmlFile();
        FileFactory.generateHtFiles();
    }
}
