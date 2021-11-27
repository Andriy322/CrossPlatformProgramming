package com.company;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class SentenceManager {

    private List<String> sentenceList;
    private final NamesSearcher namesSearcher;

    public SentenceManager() {
        this.sentenceList = new ArrayList<>();
        this.namesSearcher = new NamesSearcher();
    }

    public void displayCollection() {
        for (var s : sentenceList) {
            System.out.println(s);
        }
    }

    public void replaceTripletsWithMostFrequentWord() {
        for (int i = 0; i < sentenceList.size(); ++i) {
            List<String> names = namesSearcher.findTripletsThatCanRepresentNames(sentenceList.get(i));
            String mostFrequentWord = findMostFrequentWord(sentenceList.get(i));

            for (var name : names)
                sentenceList.set(i, sentenceList.get(i).replace(name, "{" + mostFrequentWord +
                        "}"));
        }
    }

    public List<String> findTripletsThatCanRepresentNames() {
        List<String> names = new ArrayList<>();
        for (var sentence : sentenceList)
            names.addAll(namesSearcher.findTripletsThatCanRepresentNames(sentence));

        return names;
    }

    public void DisplayAdjacentSentences() {

        for (int i = 0; i < sentenceList.size(); ++i) {
            var names = namesSearcher.findTripletsThatCanRepresentNames(sentenceList.get(i));
            if (!names.isEmpty()) {
                for (var name : names) {

                    System.out.println("Adjacent sentences to one with the triplet: " + name);
                    System.out.println("");
                    if (i - 1 >= 0) System.out.println(sentenceList.get(i - 1));
                    if (i + 1 < sentenceList.size()) System.out.println(sentenceList.get(i + 1));
                    System.out.println("\n");
                }
            }
        }
    }

    private String findMostFrequentWord(String sentence) {

        List<String> words = WordsSplitter.splitSentenceIntoWords(sentence);
        Map<String, Integer> countWordMap = new LinkedHashMap<>();

        for (var word : words) {
            Integer count = countWordMap.get(word);
            countWordMap.put(word, (count == null) ? 1 : count + 1);
        }

        int maxWordCount = 0;
        String mostFrequentWord = "";
        for (var el : countWordMap.entrySet()) {
            if (el.getValue() > maxWordCount) {
                maxWordCount = el.getValue();
                mostFrequentWord = el.getKey();
            }
        }

        return mostFrequentWord;
    }

    public void setSentenceList(String text) {
        this.sentenceList = splitTextIntoSentences(text);
    }

    private List<String> splitTextIntoSentences(String text) {
        List<String> sentences = new ArrayList<>();
        text = text.trim();
        int lastIndex = 0;
        while (lastIndex <= text.length() - 1) {
            int firstIndex = lastIndex;
            lastIndex = findNextSentence(text, firstIndex);

            if (lastIndex <= text.length()) {
                String sentence = text.substring(firstIndex, lastIndex + 1);
                sentences.add(sentence);
            }

            while (lastIndex < text.length() && !Character.isLetter(text.charAt(lastIndex)))
                ++lastIndex;
        }

        return sentences;
    }

    private int findNextSentence(String initialData, int startIndex) {
        int nextSentenceIndex = -1;
        int i = startIndex;

        while (i < initialData.length() && nextSentenceIndex == -1) {
            char currCharacter = initialData.charAt(i);
//Check if the character is sentence end mark -> skip if there are a few end marks
            while (isCharacterSentenceEndMark(currCharacter) && i < initialData.length() - 1) {
                nextSentenceIndex = i;
                currCharacter = initialData.charAt(++i);
            }

            if (nextSentenceIndex == -1) {
                ++i;
                continue;
            }

//Find the first letter after end mark (all the sentences starts with letters
            while (!Character.isLetter(currCharacter) && i < initialData.length() - 1) {
                currCharacter = initialData.charAt(++i);
            }

//If the first letter is of the lower case -> it's not the sentence start
            if (!Character.isUpperCase(currCharacter)) {
                nextSentenceIndex = -1;
            }

            //Check for abbreviations
            if (Character.isUpperCase(currCharacter) &&
                    isCharacterSentenceEndMark(initialData.charAt(i - 1))) {
                nextSentenceIndex = -1;
            }
        }

        return i >= initialData.length() ? initialData.length() - 1 : nextSentenceIndex;
    }

    private boolean isCharacterSentenceEndMark(Character currCharacter) {
        final Character[] SENTENCE_DELIMITERS = {'!', '?', '.'};
        for (var delimiter : SENTENCE_DELIMITERS) {
            if (delimiter.equals(currCharacter))
                return true;
        }

        return false;
    }

    public void setSentenceList(List<String> sentenceList) {
        this.sentenceList = sentenceList;
    }

}





