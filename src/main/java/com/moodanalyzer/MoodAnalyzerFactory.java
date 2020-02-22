package com.moodanalyzer;


import com.moodanalyzer.exception.MoodAnalysisException;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

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
        } catch (IllegalAccessException | InstantiationException | InvocationTargetException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static MoodAnalyzer createMoodAnalyzer() {
        try {
            Constructor<?> constructor = Class.forName("com.moodanalyzer.MoodAnalyzer").getConstructor();
            Object myObject = constructor.newInstance();
            return (MoodAnalyzer) myObject;
        } catch (NoSuchMethodException | InstantiationException | InvocationTargetException | ClassNotFoundException | IllegalAccessException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static String invokeMethod(String classname, String methodName, Class type, String mood) {
        try {
            Class<?> aClass1 = Class.forName(classname);
            Constructor<?> constructor = aClass1.getConstructor();
            Object object = constructor.newInstance();
            Method method1 = aClass1.getDeclaredMethod(methodName, type);
            method1.setAccessible(true);
            return (String) method1.invoke(object, mood);
        } catch (ClassNotFoundException | IllegalAccessException | InstantiationException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            throw new MoodAnalysisException("Method not found", MoodAnalysisException.ExceptionType.EXCEPTION_NO_SUCH_METHOD);
        } catch (InvocationTargetException e) {
            throw new MoodAnalysisException("Invocation issue", MoodAnalysisException.ExceptionType.EXCEPTION_INVOCATION_ISSUE);

        }
        return null;
    }

    public static String settingFieldValue(String className, String fieldName, String mood) {
        try {
            Class<?> aClass = Class.forName(className);
            Constructor<?> constructor = aClass.getConstructor();
            Object object = constructor.newInstance();
            Field field = aClass.getDeclaredField(fieldName);
            field.setAccessible(true);
            field.set(object, mood);
            return MoodAnalyzerFactory.invokeMethod(className, "analyseMood", String.class, mood);
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | NoSuchMethodException | InvocationTargetException e) {
            e.printStackTrace();
        } catch (NoSuchFieldException e) {
            throw new MoodAnalysisException("No such field", MoodAnalysisException.ExceptionType.EXCEPTION_NO_SUCH_FIELD);
        }
        return null;
    }
}
