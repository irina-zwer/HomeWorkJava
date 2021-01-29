package Java3Homework7;

import interfaces.AfterSuite;
import interfaces.BeforeSuite;
import interfaces.Test;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Comparator;

public class MyClass {

    public static void start(Class clazz) throws InvocationTargetException, IllegalAccessException {
        Method beforeMethod = null;
        Method afterMethod = null;
        ArrayList<Method> testMethods = new ArrayList<>();

        Object obj = null;
        try {
            obj = clazz.newInstance();
        } catch (InstantiationException e) {
            e.printStackTrace();
        }

        for (Method m : clazz.getDeclaredMethods()) {
            if (m.isAnnotationPresent(Test.class)) {
                testMethods.add(m);
            } else if (m.isAnnotationPresent(BeforeSuite.class)) {
                if (beforeMethod == null) {
                    beforeMethod = m;
                } else {
                    throw new RuntimeException("Не более одного метода с аннотацией @BeforeSuite");
                }
            } if (m.isAnnotationPresent(AfterSuite.class)) {
                if (afterMethod == null) {
                    afterMethod = m;
                } else {
                    throw new RuntimeException("Не более одного метода с аннотацией @AfterSuite");
                }
            }
        }

        if (beforeMethod != null) {
            beforeMethod.invoke(obj);
        }

        testMethods.sort(Comparator.comparingInt(o -> o.getAnnotation(Test.class).priority()));
        for (Method m : testMethods) {
            m.invoke(obj);
        }

        if (afterMethod != null) {
            afterMethod.invoke(obj);
        }
    }
}
