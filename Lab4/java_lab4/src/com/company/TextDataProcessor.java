package com.company;

import java.util.List;

public class TextDataProcessor {
    private final FileReader fileReader;
    public TextDataProcessor() {
        fileReader = new FileReader();
    }

    public void run(String fileName) {
        SentenceManager sentenceManager = new SentenceManager();
        sentenceManager.setSentenceList(fileReader.readFile(fileName));
        List<String> names = sentenceManager.findTripletsThatCanRepresentNames();

        System.out.println("Triplets which can represent names");
        System.out.println("*************************************************************************");
        for(var name : names)
        {
            System.out.println(name);
        }

        System.out.println("\n");
        System.out.println("Adjacent sentences");
        System.out.println("*************************************************************************");
        sentenceManager.DisplayAdjacentSentences();
        System.out.println("Replace triplets with the most frequent word in the sentence");
        System.out.println("*************************************************************************");
        sentenceManager.replaceTripletsWithMostFrequentWord();
        sentenceManager.displayCollection();
    }
}

