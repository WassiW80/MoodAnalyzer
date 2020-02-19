package com.moodanalyzer;

import com.moodanalyzer.exception.MoodAnalysisException;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.util.concurrent.ExecutionException;

public class MoodAnalyzerTest {


    @Test
    public void givenMood_WhenSad_ShouldReturnSad() {
        String mood = null;
        try {
            MoodAnalyzer moodAnalyzer = new MoodAnalyzer("This is a sad mood.");
            mood = moodAnalyzer.analyseMood();
            Assert.assertEquals("SAD", mood);
        } catch (MoodAnalysisException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void givenMood_WhenHappy_ShouldReturnHappy() {
        String mood = null;
        try {
            MoodAnalyzer moodAnalyzer = new MoodAnalyzer("This is a happy mood.");
            mood = moodAnalyzer.analyseMood();
            Assert.assertEquals("HAPPY", mood);
        } catch (MoodAnalysisException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void givenMood_WhenNull_shouldReturnHappy() {
        try {
            MoodAnalyzer moodAnalyzer = new MoodAnalyzer();
            moodAnalyzer.analyseMood();
        } catch (MoodAnalysisException e) {
            Assert.assertEquals(MoodAnalysisException.ExceptionType.EXCEPTION_NULL, e.type);
        }
    }

    @Test
    public void givenMood_WhenEmptyInput_ShouldReturnMessage() {
        try {
            MoodAnalyzer moodAnalyzer = new MoodAnalyzer("");
            moodAnalyzer.analyseMood();
        } catch (MoodAnalysisException e) {
            Assert.assertEquals(MoodAnalysisException.ExceptionType.EXCEPTION_EMPTY, e.type);
        }
    }
}
