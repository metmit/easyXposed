package com.metmit.xp.utils;

import android.app.Activity;
import android.os.Bundle;

import java.lang.reflect.Method;
import java.util.Arrays;

import de.robv.android.xposed.XC_MethodHook;
import de.robv.android.xposed.XposedHelpers;

public class Sposed {

    public static Class<?> findClass(String className) {
        return XposedHelpers.findClass(className, Container.loadPackageParam.classLoader);
    }

    public static Object callMethod(Object obj, String methodName, Object... args) {
        return XposedHelpers.callMethod(obj, methodName, args);
    }

    public static Object callStaticMethod(String className, String methodName, Object... args) {
        Class<?> clazz = findClass(className);
        return callStaticMethod(clazz, methodName, args);
    }

    public static Object callStaticMethod(Class<?> clazz, String methodName, Object... args) {
        return XposedHelpers.callStaticMethod(clazz, methodName, args);
    }

    private void hookMethod(String className, String methodName, Object... parameterTypesAndCallback) {
        try {
            XposedHelpers.findAndHookMethod(className, Container.loadPackageParam.classLoader, methodName, parameterTypesAndCallback);
        } catch (Exception e) {
            Helper.log(e.toString());
        }
    }

    public static void printMethods(Class<?> clazz) {
        for (Method method : clazz.getDeclaredMethods()) {
            Helper.log(method.getName() + ':' + Arrays.toString(method.getParameterTypes()));
        }
    }

    public static void activity() {
        XposedHelpers.findAndHookMethod(Activity.class, "onCreate", Bundle.class, new XC_MethodHook() {
            @Override
            protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                Activity thisObject = (Activity) param.thisObject;
                Helper.log("当前 Activity : " + thisObject.getClass().getName());
                /*Field[] fields = thisObject.getClass().getDeclaredFields();
                for (Field field : fields) {
                    field.setAccessible(true);
                    try {
                        field.set(thisObject, field.getName());
                    } catch (Throwable e) {
                        e.printStackTrace();
                    }

                    Object o = field.get(thisObject);
                    Helper.log("\t\t\t" + field.getName() + " = " + o.getClass().getName());
                }*/
                super.afterHookedMethod(param);
            }
        });
    }

}
