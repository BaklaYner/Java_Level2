package Lesson3_Collections.Task2;

import java.util.Set;

public class PhoneBookTestDrive {
    private static PhoneBook phoneBook = new PhoneBook();

    public static void main(String[] args) {
        Entry bogdanov1 = new Entry("Богданов", 79211111111L);
        Entry bogdanov2 = new Entry("Богданов", 79212222222L);
        Entry bogdanova = new Entry("Богданова", 79213333333L);
        Entry ivanov = new Entry("Иванов", 79114444444L);
        Entry petrov = new Entry("Петров", 79525555555L);
        Entry sidorov = new Entry("Сидоров", 79116666666L);
        Entry bogdanov3 = new Entry("Богданов", 79317777777L);
        Entry bogdanov11 = new Entry("Богданов", 79211111111L);

        phoneBook.add(bogdanov1);
        phoneBook.add(bogdanov2);
        phoneBook.add(bogdanova);
        phoneBook.add(ivanov);
        phoneBook.add(petrov);
        printPhoneNumbers("Богданов");
        printPhoneNumbers("Иванов");
        printPhoneNumbers("Петров");
        printPhoneNumbers("Богданова");
        printPhoneNumbers("Сидоров");

        phoneBook.add(sidorov);
        phoneBook.add(bogdanov3);
        phoneBook.add(bogdanov11);
        printPhoneNumbers("Богданов");
        printPhoneNumbers("Сидоров");
    }

    static void printPhoneNumbers(String secondName) {
        Set<Entry> set = phoneBook.get(secondName);
        System.out.println("Запрос номеров по фамилии " + secondName + ":");
        if (set != null) {
            for (Entry entry : set) {
                System.out.println(entry.getPhoneNumber());
            }
        } else {
            System.out.println("Номеров по фамилии " + secondName + " не найдено.");
        }
        System.out.println();
    }

}