package com.company;

import java.io.File;
import java.util.Scanner;

public class FileReader {

    public String readFile(String fileName) {

        try (Scanner scanner = new Scanner(new File(getFilePath(fileName))))
        {
            String result = "";
            while (scanner.hasNextLine()) { result += scanner.nextLine(); result += "\n";
            }
            return result;

        } catch (Exception ex) { System.out.println(ex.getMessage());
        }

        return null;
    }

    private String getFilePath(String fileName) {
        return System.getProperty("user.dir") + File.separator + fileName;
    }
}
