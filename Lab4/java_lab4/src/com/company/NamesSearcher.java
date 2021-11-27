package com.company;
import java.util.ArrayList;
import java.util.List;

public class NamesSearcher {

    public List<String> findTripletsThatCanRepresentNames(String sentence) {

        List<String> words = WordsSplitter.splitSentenceIntoWords(sentence);
        List<String> names = new ArrayList<>();
        final int WORDS_IN_TRIPLET = 3;
        int shift = 0;
        for(int i = 0; i + WORDS_IN_TRIPLET <= words.size(); i += shift) { boolean nameFound = true;
            String name = "";

            for(int j = i; j < i + WORDS_IN_TRIPLET; ++j) {

                nameFound = canWordRepresentName(words.get(j)); name += words.get(j) + " ";
                shift = j - i + 1; if(!nameFound) break;
            }

            if(nameFound)
                names.add(name.trim());
        }

        return names;
    }
    private boolean canWordRepresentName(String word) { for(int i=0; i<word.length(); ++i) {
        if(!Character.isLetter(word.charAt(i)) && word.charAt(i) != '\'' && word.charAt(i) != '-')
        return false;
    }

        return Character.isUpperCase(word.charAt(0));
    }
}

