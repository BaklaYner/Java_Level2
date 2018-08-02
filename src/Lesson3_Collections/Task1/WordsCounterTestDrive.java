package Lesson3_Collections.Task1;

import java.util.Map;

public class WordsCounterTestDrive {
    private static final String SOURCE_TEXT = "Ночь, улица, фонарь, аптека,\n" +
            "Бессмысленный и тусклый свет.\n" +
            "Живи еще хоть четверть века -\n" +
            "Все будет так. Исхода нет.\n" +
            "Умрешь - начнешь опять сначала\n" +
            "И повторится все, как встарь:\n" +
            "Ночь, ледяная рябь канала,\n" +
            "Аптека, улица, фонарь.";

    public static void main(String[] args) {
        System.out.println("Исходный текст:\n" + SOURCE_TEXT + "\n");

        UniqueWordCounter cnt = new UniqueWordCounter();
        Map<String, Integer> map = cnt.countWords(SOURCE_TEXT);

        System.out.println("Набор уникальных слов:\n" + map.keySet());
        System.out.println("Набор уникальных слов с их количеством в исходном тексте:\n" + map);
    }

}