package com.moodanalyzer;


import com.moodanalyzer.exception.MoodAnalysisException;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

public class MoodAnalyzerFactory {

    public static MoodAnalyzer createMoodAnalyzer(String message, String className, Class aClass) {
        try {
            Constructor<?> constructor = Class.forName(className).getConstructor(aClass);
            Object myObject = constructor.newInstance(message);
            return (MoodAnalyzer) myObject;
        } catch (NoSuchMethodException e) {
            throw new MoodAnalysisException("Method not found", MoodAnalysisException.ExceptionType.EXCEPTION_NO_SUCH_METHOD);
        } catch (ClassNotFoundException e) {
            throw new MoodAnalysisException("Class not found Error", MoodAnalysisException.ExceptionType.EXCEPTION_CLASS_NOT_FOUND);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static MoodAnalyzer createMoodAnalyzer() {
        try {
            Constructor<?> constructor = Class.forName("com.moodanalyzer.MoodAnalyzer").getConstructor();
            Object myObject = constructor.newInstance();
            return (MoodAnalyzer) myObject;
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        return null;
    }
}
