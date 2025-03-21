package by.com.lifetech.billingapi.utils;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

public class ObjectToMapConverter {
    private static final String ANNOTATION_FAMILY_NAME = "CustomName";

    public static Map<String, Object> objectToMap(Object object, boolean isSetNullValues
            , Class<? extends Annotation> annotationType) throws IllegalAccessException {
        Map<String, Object> fieldMap = new HashMap<>();
        Class<?> clazz = object.getClass();
        Field[] fields = clazz.getDeclaredFields();
        for (Field field : fields) {
            field.setAccessible(true);
            Object value = field.get(object);
            if (value != null || isSetNullValues) {
                fieldMap.put(getKey(field, annotationType), value);
            }
        }
        return fieldMap;
    }

    private static String getKey(Field field, Class<? extends Annotation> annotationType) {
        String key = field.getName();

        if (annotationType == null) {
            return key;
        }

        Annotation customNameAnnotation = field.getAnnotation(annotationType);
        if (customNameAnnotation == null) {
            return key;
        }

        if (!annotationType.getSimpleName().startsWith(ANNOTATION_FAMILY_NAME)) {
            return key;
        }

        Method nameMethod;
        try {
            nameMethod = annotationType.getMethod("name");
        } catch (NoSuchMethodException e) {
            return key;
        }

        try {
            return (String) nameMethod.invoke(customNameAnnotation);
        } catch (IllegalAccessException | InvocationTargetException e) {
            return key;
        }
    }
}
