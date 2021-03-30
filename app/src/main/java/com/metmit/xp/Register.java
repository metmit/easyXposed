package com.metmit.xp;

import com.metmit.xp.constraint.PackageHandler;
import com.metmit.xp.register.Aweme;
import com.metmit.xp.register.Kwai;
import com.metmit.xp.utils.Helper;

import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class Register {

    protected static volatile Register instance;

    protected static ArrayList<Class<?>> classArrayList = new ArrayList<>();

    protected final HashMap<String, Object> clazzHashMap = new HashMap<>();

    protected final HashMap<String, Class<?>> resolveHashMap = new HashMap<>();

    /*
      自定义待解析注册的类
     */
    static {
        classArrayList = new ArrayList<Class<?>>(Arrays.asList(
                Aweme.class,
                Kwai.class
        ));
    }

    public static Register getInstance() {
        if (instance == null) {
            synchronized (Register.class) {
                if (instance == null) {
                    instance = new Register();
                }
            }
        }
        return instance;
    }

    protected Register() {
        for (Class<?> clazz : classArrayList) {
            if (clazzHashMap.containsKey(clazz.toString())) continue;
            clazzHashMap.put(clazz.toString(), clazz);
            Annotation[] annotations = clazz.getAnnotations();
            for (Annotation annotation : annotations) {
                if (annotation.annotationType() == PackageHandler.class) {
                    String name = ((PackageHandler) annotation).name();
                    if (!name.isEmpty()) {
                        resolveHashMap.put(name, clazz);
                    }
                    break;
                }
            }
        }
    }

    protected void register(String packageName) {
        try {
            Class<?> clazz = resolveHashMap.get(packageName);
            if (clazz == null) throw new Exception("Can not register " + packageName);

            Object object = clazz.getDeclaredConstructor().newInstance();
            Method method = clazz.getMethod("register");
            method.invoke(object);
        } catch (IllegalAccessException | InstantiationException | NoSuchMethodException e) {
            Helper.log("Exception: \n\n"
                    + Arrays.toString(e.getStackTrace()));
        }  catch (InvocationTargetException e) {
            Helper.log("InvocationTargetException: \n\n"
                    + Arrays.toString(e.getTargetException().getStackTrace()));
        } catch (Throwable e) {
            Helper.log("Throwable: \n\n"
                    + Arrays.toString(e.getStackTrace()));
        }
    }

    public boolean hasResolved(String packageName) {
        return resolveHashMap.get(packageName) != null;
    }
}
