package com.moodanalyzer.exception;


public class MoodAnalysisException extends RuntimeException {
    public enum ExceptionType {EXCEPTION_NULL, EXCEPTION_EMPTY, EXCEPTION_CLASS_NOT_FOUND, EXCEPTION_NO_SUCH_METHOD, EXCEPTION_INVOCATION_ISSUE, EXCEPTION_NO_SUCH_FIELD}

    public ExceptionType type;

    public MoodAnalysisException(String message, ExceptionType type) {
        super(message);
        this.type = type;
    }
}
