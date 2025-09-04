import java.util.*;

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

                List<WordCount> wordFrequencies = countWordFrequency(inputStrSplit);

                wordFrequencies.sort((w1, w2) -> w2.wordCount - w1.wordCount);

                StringJoiner joiner = new StringJoiner("\n");
                for (WordCount w : wordFrequencies) {
                    String s = w.word + " " + w.wordCount;
                    joiner.add(s);
                }

                return joiner.toString();

            } catch (Exception e) {
                return "Calculate Error";
            }
        }
    }

    private List<WordCount> countWordFrequency(String[] words) {
        Map<String, List<String>> groups = groupSameWords(words);

        List<WordCount> frequencies = new ArrayList<>();

        for (Map.Entry<String, List<String>> entry : groups.entrySet()) {
            WordCount wordCount = new WordCount(entry.getKey(), entry.getValue().size());
            frequencies.add(wordCount);
        }
        return frequencies;
    }

    private Map<String, List<String>> groupSameWords(String[] words) {
        Map<String, List<String>> wordGroups = new HashMap<>();
        for (String word : words) {
//       map.computeIfAbsent(input.getValue(), k -> new ArrayList<>()).add(input);
            if (!wordGroups.containsKey(word)) {
                ArrayList<String> newWordList = new ArrayList<>();
                newWordList.add(word);
                wordGroups.put(word, newWordList);
            } else {
                wordGroups.get(word).add(word);
            }
        }
        return wordGroups;
    }
}
