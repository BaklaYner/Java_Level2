package Lesson3_Collections.Task1;

public class WordsCounterTestDrive {
    private static final String SOURCE_TEXT = "Ночь, улица, фонарь, аптека,\n" +
            "Бессмысленный и тусклый свет.\n" +
            "Живи еще хоть четверть века -\n" +
            "Все будет так. Исхода нет.\n" +
            "Умрешь - начнешь опять сначала\n" +
            "И повторится все, как встарь:\n" +
            "Ночь, ледяная рябь канала,\n" +
            "Аптека, улица, фонарь.";
    private static final String EXISTING_WORD = "все";
    private static final String MISSING_WORD = "яблоко";

    public static void main(String[] args) {
        System.out.println("Исходный текст:\n" + SOURCE_TEXT + "\n");

        WordsAnalyzer cnt = new WordsAnalyzer(SOURCE_TEXT);

        System.out.println("Набор уникальных слов:\n" + cnt.getUniqueWordsSet());
        System.out.println("Набор уникальных слов с их количеством в исходном тексте:\n" + cnt.getUniqueWordsCount());
        System.out.println("Количество повторений слова \"" + EXISTING_WORD + "\" в исходном тексте: " +
                cnt.getWordCount(EXISTING_WORD));
        System.out.println("Количество повторений слова \"" + MISSING_WORD + "\" в исходном тексте: " +
                cnt.getWordCount(MISSING_WORD));
        System.out.println("Первое из наиболее часто встречающихся слов в исходном тексте: " + cnt.getFirstMostCommonWord());
    }

}