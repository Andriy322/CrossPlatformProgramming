package com.company;

public class Main {
    public static void main(String[] args) {
        TextDataProcessor textDataProcessor = new TextDataProcessor();
        textDataProcessor.run("data.txt");
        //System.getProperty("user.dir")+"\\data.txt"
        System.out.println("\n\n");
        //System.getProperty("user.dir")+"\\dataUk.txt"
        textDataProcessor.run("dataUk.txt");
    }
}

