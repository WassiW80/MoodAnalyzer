package com.moodanalyzer;

import com.moodanalyzer.exception.MoodAnalysisException;

public class MoodAnalyzer {
    private String message;

    public MoodAnalyzer() {
    }

    public MoodAnalyzer(String message) {
        this.message = message;
    }

    public String analyseMood() throws MoodAnalysisException {
        try {
            if (message.isEmpty())
                throw new MoodAnalysisException("Entered field cannot be empty.", MoodAnalysisException.ExceptionType.EXCEPTION_EMPTY);
            if (message.contains("sad"))
                return "SAD";
            return "HAPPY";

        } catch (NullPointerException e) {
            throw new MoodAnalysisException("Please enter a proper mood.", MoodAnalysisException.ExceptionType.EXCEPTION_NULL);
        }
    }
}
