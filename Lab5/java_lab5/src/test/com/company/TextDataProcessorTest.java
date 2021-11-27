package test.com.company;
import com.company.TextDataProcessor;
import com.company.TextInitializer;
import org.junit.*;

import java.util.List;

public class TextDataProcessorTest {

    @Test
    public void findUniqueWordsInFirstSentenceTestDefault()
    {
        TextDataProcessor textDataProcessor = new TextDataProcessor(TextInitializer.getDefaultText());
        List<String> uniqueWords = textDataProcessor.findUniqueWordsInFirstSentence();
        Assert.assertEquals("[ipsum, sit, amet, consectetuer, adipiscing, elit]",
            uniqueWords.toString());
    }

    @Test
    public void findUniqueWordsInFirstSentenceTestEmpty()
    {
        TextDataProcessor textDataProcessor = new TextDataProcessor("");
        List<String> uniqueWords = textDataProcessor.findUniqueWordsInFirstSentence(); Assert.assertTrue(uniqueWords.isEmpty());
    }

    @Test
    public void findUniqueWordsInFirstSentenceTestWithoutUniqueWords()
    {
        TextDataProcessor textDataProcessor = new TextDataProcessor("Lorem ipsum sit.Amet ipsum elit.Lorem ipsum sit.");
                List<String> uniqueWords = textDataProcessor.findUniqueWordsInFirstSentence();
                Assert.assertTrue(uniqueWords.isEmpty());
    }

}
