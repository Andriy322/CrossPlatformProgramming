package com.company;
import java.util.List;

public class Main {
    public static void main(String[] args) {

        String gettext = TextInitializer.initText();
        TextDataProcessor textDataProcessor = new TextDataProcessor(gettext);
        //System.out.println(gettext);
        List<String> uniqueWords = textDataProcessor.findUniqueWordsInFirstSentence();
        textDataProcessor.displayUniqueWords(uniqueWords);
    }
}

