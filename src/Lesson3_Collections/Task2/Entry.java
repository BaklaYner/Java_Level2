package Lesson3_Collections.Task2;

import java.util.Objects;

public class Entry {
    private String secondName;
    private long phoneNumber;

    public Entry(String secondName, long phoneNumber) {
        this.secondName = secondName;
        this.phoneNumber = phoneNumber;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Entry entry = (Entry) o;
        return phoneNumber == entry.phoneNumber &&
                Objects.equals(secondName, entry.secondName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(secondName, phoneNumber);
    }

    @Override
    public String toString() {
        return "\"" + secondName + ": " + phoneNumber +
                "\"";
    }

    public String getSecondName() {
        return secondName;
    }

    public long getPhoneNumber() {
        return phoneNumber;
    }

}