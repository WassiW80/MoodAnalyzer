package com.moodanalyzer;

import com.moodanalyzer.exception.MoodAnalysisException;
import org.junit.Assert;

import org.junit.Test;


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

    @Test
    public void givenMooAnalyzerClass_WhenProper_ShouldReturnObject() {
        MoodAnalyzer moodAnalyzer = MoodAnalyzerFactory.createMoodAnalyzer("I am in happy mood.", "com.moodanalyzer.MoodAnalyzer", String.class);
        Assert.assertEquals(new MoodAnalyzer("I am in happy mood."), moodAnalyzer);
    }

    @Test
    public void givenMoodAnalyzerDefaultConstructor_WhenProper_ShouldReturnObject() {
        MoodAnalyzer moodAnalyzer = MoodAnalyzerFactory.createMoodAnalyzer();
        Assert.assertEquals(new MoodAnalyzer(), moodAnalyzer);
    }

    @Test
    public void givenWrongClass_WhenImproper_ShouldReturnNoSuchClassError() {
        try {
            MoodAnalyzerFactory.createMoodAnalyzer("Class not found error", "com.moodanalyzer.MoodAnyzer", String.class);
        } catch (MoodAnalysisException e) {
            Assert.assertEquals(MoodAnalysisException.ExceptionType.EXCEPTION_CLASS_NOT_FOUND, e.type);
        }
    }

    @Test
    public void givenWrongMethod_WhenImproper_ShouldReturnNoSuchMethodError() {
        try {
            MoodAnalyzerFactory.createMoodAnalyzer("Method not found error", "com.moodanalyzer.MoodAnalyzer", Integer.class);
        } catch (MoodAnalysisException e) {
            Assert.assertEquals(MoodAnalysisException.ExceptionType.EXCEPTION_NO_SUCH_METHOD, e.type);
        }
    }

    @Test
    public void givenHappyMessageUsingReflector_WhenProper_ShouldReturnHappy() {
        String mood = MoodAnalyzerFactory.invokeMethod("com.moodanalyzer.MoodAnalyzer", "analyseMood", String.class, "happy");
        Assert.assertEquals("HAPPY", mood);
    }

    @Test
    public void givenHappyMessageUsingReflector_WhenImproper_ShouldThrowException() {
        try {
            MoodAnalyzerFactory.invokeMethod("com.moodanalyzer.MoodAnalyzer", "analyse", String.class, "happy");
        } catch (MoodAnalysisException e) {
            Assert.assertEquals(MoodAnalysisException.ExceptionType.EXCEPTION_NO_SUCH_METHOD, e.type);
        }
    }

    @Test
    public void givenFieldNameUsingReflector_WhenProper_ShouldSetMoodDynamically() {
        String mood = MoodAnalyzerFactory.settingFieldValue("com.moodanalyzer.MoodAnalyzer", "message", "I am in happy mood");
        Assert.assertEquals("HAPPY", mood);
    }

    @Test
    public void givenFieldNameUsingReflector_WhenImproper_ShouldThrowException() {
        try {
            MoodAnalyzerFactory.settingFieldValue("com.moodanalyzer.MoodAnalyzer", "mess", "I am in happy mood");
        } catch (MoodAnalysisException e) {
            Assert.assertEquals(MoodAnalysisException.ExceptionType.EXCEPTION_NO_SUCH_FIELD, e.type);
        }
    }

    @Test
    public void givenFieldNameUsingReflector_WhenNull_ShouldThrowException() {
        try {
            MoodAnalyzerFactory.settingFieldValue("com.moodanalyzer.MoodAnalyzer", "message", null);
        } catch (MoodAnalysisException e) {
            Assert.assertEquals(MoodAnalysisException.ExceptionType.EXCEPTION_INVOCATION_ISSUE, e.type);
        }
    }
}
