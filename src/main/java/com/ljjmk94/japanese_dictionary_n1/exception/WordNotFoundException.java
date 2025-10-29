package com.ljjmk94.japanese_dictionary_n1.exception;

public class WordNotFoundException extends RuntimeException {
    public WordNotFoundException(Integer wordId) {
        super("Word Not Found with ID: " + wordId);
    }
}
