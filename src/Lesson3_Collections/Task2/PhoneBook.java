package Lesson3_Collections.Task2;

import java.util.*;

public class PhoneBook {
    private Map<String, Set<Entry>> phoneBook;

    public PhoneBook() {
        phoneBook = new HashMap<>();
    }

    public void add(Entry entry) {
        Set<Entry> set = phoneBook.get(entry.getSecondName());
        if (set == null) {
            set = new HashSet<>();
        }
        set.add(entry);
        phoneBook.put(entry.getSecondName(), set);
    }

    public void add(String secondName, long phoneNumber) {
        Entry entry = new Entry(secondName, phoneNumber);
        add(entry);
    }

    public Set<Entry> get(String secondName) {
        return phoneBook.get(secondName);
    }

}