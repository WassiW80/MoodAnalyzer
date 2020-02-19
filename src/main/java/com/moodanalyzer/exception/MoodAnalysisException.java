package com.moodanalyzer.exception;


public class MoodAnalysisException extends RuntimeException {
    public enum ExceptionType{EXCEPTION_NULL,EXCEPTION_EMPTY}
    public ExceptionType type;

    public MoodAnalysisException(String message, ExceptionType type) {
        super(message);
        this.type=type;
    }
}
