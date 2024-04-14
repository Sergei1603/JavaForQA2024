package ru.shop;

public class BadOrderCountException extends Exception{
    public BadOrderCountException() {
    }

    public BadOrderCountException(String message) {
        super(message);
    }
}
