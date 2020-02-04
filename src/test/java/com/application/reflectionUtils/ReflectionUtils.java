package com.application.reflectionUtils;

import com.application.dto.GroupUserDto;
import com.application.model.Payments;
import com.application.model.TransactionHistory;

import java.lang.reflect.Method;
import java.sql.Timestamp;
import java.time.Instant;
import java.util.Arrays;

public class ReflectionUtils {

    public static void invoke(Object obj) {

        String name = obj.getClass().getName();
        try {
            Class cls = Class.forName(name);
            Method[] methods = cls.getDeclaredMethods();
            Arrays.asList(methods).stream().forEach(method -> {
                try {
                    if (method.getName().startsWith("get")) {
                        method.invoke(obj);
                    } else if (method.getName().startsWith("set")) {
                        if (method.getParameters()[0].getType().isAssignableFrom(Integer.class) || method.getParameters()[0].getType().isAssignableFrom(int.class)) {
                            method.invoke(obj, 1);
                        } else if (method.getParameters()[0].getType().isAssignableFrom(Boolean.class) || method.getParameters()[0].getType().isAssignableFrom(boolean.class)) {
                            method.invoke(obj, false);
                        }else if (method.getParameters()[0].getType().isAssignableFrom(String.class)) {
                            method.invoke(obj, "test");
                        }else if (method.getParameters()[0].getType().isAssignableFrom(Long.class) || method.getParameters()[0].getType().isAssignableFrom(long.class)) {
                            method.invoke(obj, 14835684375L);
                        }else if (method.getParameters()[0].getType().isAssignableFrom(Double.class) || method.getParameters()[0].getType().isAssignableFrom(double.class)) {
                            method.invoke(obj, 22.0d);
                        }else if (method.getParameters()[0].getType().isAssignableFrom(Timestamp.class) ) {
                            method.invoke(obj, new Timestamp(Instant.now().getEpochSecond()));
                        }else if (method.getParameters()[0].getType().isAssignableFrom(GroupUserDto.class) ) {
                            method.invoke(obj, new GroupUserDto());
                        }else if(method.getParameters()[0].getType().isAssignableFrom(Payments.class)){
                            method.invoke(obj,new Payments());
                        }else if(method.getParameters()[0].getType().isAssignableFrom(TransactionHistory.class)){
                            method.invoke(obj,new TransactionHistory());
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
