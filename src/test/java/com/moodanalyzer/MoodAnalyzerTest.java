package com.moodanalyzer;

import com.moodanalyzer.exception.MoodAnalysisException;
import org.junit.Assert;

import org.junit.Test;


import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;


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
    public void givenMoodAnalyzer_WhenProper_ShouldReturnObject() {
        try {
            Constructor<?> constructor = Class.forName("com.moodanalyzer.MoodAnalyzer").getConstructor(String.class);
            try {
                Object myObject = constructor.newInstance("This is sad mood.");
                MoodAnalyzer moodAnalyzer = (MoodAnalyzer) myObject;
                String mood = moodAnalyzer.analyseMood();
                Assert.assertEquals("SAD", mood);
            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
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
}
