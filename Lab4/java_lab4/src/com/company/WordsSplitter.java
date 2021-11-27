package com.company;

import java.util.ArrayList;
import java.util.List;

public class WordsSplitter {

    static private boolean isSymbolPartOfWord(Character symbol) {
        return Character.isLetter(symbol) || Character.isDigit(symbol) || symbol == '\'' || symbol
                == '-' || symbol == '.';
    }
    static public List<String> splitSentenceIntoWords(String sentence) { List<String> words = new ArrayList<>();
        int i = 0;

        while (i < sentence.length() - 1) {

            char currChar = sentence.charAt(i); String word = "";

            while(i < sentence.length() - 1 && isSymbolPartOfWord(currChar)) { word += currChar;
                currChar = sentence.charAt(++i);
            }

            while(i < sentence.length() - 1 && !isSymbolPartOfWord(currChar)) { currChar = sentence.charAt(++i);
            }

            words.add(word);
        }

        return words;
    }
}
