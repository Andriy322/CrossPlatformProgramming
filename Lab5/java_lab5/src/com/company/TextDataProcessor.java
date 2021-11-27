package com.company;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TextDataProcessor {
    private String text;
    public TextDataProcessor(String text)
    {
        this.text = text;
    }

    public void displayUniqueWords(List<String> uniqueWords)
    {
        if(uniqueWords.isEmpty())
        {
            System.out.println("The unique word is not found in the first sentence");
        }
        else
        {
            for(var word : uniqueWords) System.out.println(word);
        }
    }

    public List<String> findUniqueWordsInFirstSentence()
    {
        List<String> result = new ArrayList<>();

        if(text.isEmpty()) {
            System.out.println("The text is empty"); return result;
        }

        String firstSentence = text.split("(?<=[a-z])\\.\\s+")[0]; text = text.replaceFirst(firstSentence, "");
        firstSentence = firstSentence.replaceAll("[!?,]", ""); String[] firstSentenceWords = firstSentence.split("\\s+");
        for(var word : firstSentenceWords)
        {
            Pattern uniqueWordPattern = Pattern.compile(word); Matcher matcher = uniqueWordPattern.matcher(text);

            if(!matcher.find()) result.add(word);
        }

        return result;
    }

}
