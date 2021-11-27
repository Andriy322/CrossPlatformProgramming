package com.company;
import java.util.Scanner;


public class TextInitializer {

    private final static Scanner scanner = new Scanner(System.in);
    public static String initText()
    {
        System.out.println("Do you want enter text or use default one?[pick 0/1]");
        int choice;

        while(true)
        {
            choice = scanner.nextInt();

            switch(choice)
            {
                case 0:
                    return getTextFromConsole();
                    case 1:
                    return getDefaultText();
                default:
                System.out.println("Wrong choice");
            }
        }
    }

    public static String getDefaultText()
    {
        return "I wrote some text "
                + System.lineSeparator() + "for this task. I believe this is "
                + "a good text. " + System.lineSeparator()
                + "Find and display in the console such a word in the first sentence, "
                + System.lineSeparator() + "which is not in any of the other sentences. "
                + "The text should be entered from the console.";
    }

    public static String getTextFromConsole()
    {
        System.out.println("Enter \"End\" when you want to finish writing."); String text = "", currLine = "";
        Scanner scanner = new Scanner(System.in);

        do {
            text += currLine;
            currLine = scanner.nextLine();
        }while(!currLine.contains("End"));

        return text;
    }
}

