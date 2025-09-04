import java.util.*;
import java.util.stream.Collectors;

public class WordFrequencyGame {
    public static final String ANY_SPACE_SEPARATOR = "\\s+";
    record WordCount(String word, int wordCount) {}

    public String getResult(String inputStr) {
        String[] inputStrSplit = inputStr.split(ANY_SPACE_SEPARATOR);
        if (inputStrSplit.length == 1) {
            return inputStr + " 1";
        } else {
            try {
                //split the input string with 1 to n pieces of spaces
                List<WordCount> wordFrequencies = groupSameWords(inputStrSplit).entrySet().stream()
                        .map(entry -> new WordCount(entry.getKey(), entry.getValue().size()))
                        .sorted((w1, w2) -> w2.wordCount - w1.wordCount).collect(Collectors.toList());

                return composeOutput(wordFrequencies);
            } catch (Exception e) {
                return "Calculate Error";
            }
        }
    }

    private String composeOutput(List<WordCount> wordFrequencies) {
        return wordFrequencies.stream()
                .map(w -> w.word + " " + w.wordCount)
                .reduce((s1, s2) -> s1 + "\n" + s2)
                .orElse("");
    }

    private Map<String, List<String>> groupSameWords(String[] words) {
        return Arrays.stream(words).collect(Collectors.groupingBy(word -> word, Collectors.toList()));
    }
}
