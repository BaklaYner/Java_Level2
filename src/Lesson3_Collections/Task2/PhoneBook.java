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

    public ArrayList<Long> getPhones(String secondName) {
        if (!phoneBook.containsKey(secondName)) {
            return null;
        }
        Set<Entry> entries = phoneBook.get(secondName);
        ArrayList<Long> phones = new ArrayList<>();
        for (Entry entry : entries) {
            phones.add(entry.getPhoneNumber());
        }
        return phones;
    }

}