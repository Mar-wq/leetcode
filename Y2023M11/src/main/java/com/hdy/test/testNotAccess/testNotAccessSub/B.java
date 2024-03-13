package com.hdy.test.testNotAccess.testNotAccessSub;


import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class B {
    public static void main(String[] args) throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        Class<?> aClass = Class.forName("com.hdy.test.testNotAccess.A");
        Constructor<?> constructor = aClass.getDeclaredConstructor();
        constructor.setAccessible(true); // 覆盖访问控制
        Object a = constructor.newInstance();
        Method printt = aClass.getDeclaredMethod("printt");
        printt.setAccessible(true); // 覆盖方法的访问控制
        printt.invoke(a);
    }
}
