package Lesson3_Collections.Task1;

import java.util.Map;
import java.util.TreeMap;

public class UniqueWordCounter {

    public Map<String, Integer> countWords(String[] words) {
        TreeMap<String, Integer> map = new TreeMap<>();
        for (int i = 0; i < words.length; i++) {
            Integer val = map.get(words[i]);
            if (val == null) {
                val = 1;
            } else val++;
            map.put(words[i], val);
        }
        return map;
    }

    public Map<String, Integer> countWords(String source) {
        String[] words = toWordsArray(source);
        return countWords(words);
    }

    private String[] toWordsArray(String source) {
        String temp = source.replaceAll("(?U)\\p{Punct}", " ")
                .replaceAll("\\s+", " ")
                .toLowerCase();
        return temp.split(" ");
    }

}