package Lesson3_Collections.Task1;

import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

public class WordsAnalyzer {
    private final Map<String, Integer> words;

    public WordsAnalyzer(String[] words) {
        this.words = toMap(words);
    }

    public WordsAnalyzer(String text) {
        String[] words = toWordsArray(text);
        this.words = toMap(words);
    }

    public Set<String> getUniqueWordsSet() {
        return words.keySet();
    }

    public Map<String, Integer> getUniqueWordsCount() {
        return words;
    }

    public int getWordCount(String word) {
        if (words.containsKey(word)) {
            return words.get(word);
        }
        return 0;
    }

    public String getFirstMostCommonWord() {
        String word = null;
        int count = 0;
        for (Map.Entry<String, Integer> entry : words.entrySet()) {
            if (entry.getValue() > count) {
                word = entry.getKey();
                count = entry.getValue();
            }
        }
        return word;
    }

    private String[] toWordsArray(String source) {
        if (source != null) {
            String temp = source.replaceAll("(?U)\\p{Punct}", " ")
                    .replaceAll("\\s+", " ")
                    .toLowerCase();
            return temp.split(" ");
        }
        return null;
    }

    private TreeMap<String, Integer> toMap(String[] words) {
        TreeMap<String, Integer> map = new TreeMap<>();
        if (words != null) {
            for (int i = 0; i < words.length; i++) {
                Integer val = map.get(words[i]);
                if (val == null) {
                    val = 1;
                } else val++;
                map.put(words[i], val);
            }
        }
        return map;
    }

}