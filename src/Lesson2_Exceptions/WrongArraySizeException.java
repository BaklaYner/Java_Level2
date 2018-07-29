package Lesson2_Exceptions;

public class WrongArraySizeException extends Exception {
    WrongArraySizeException(String message) {
        super(message);
    }
}