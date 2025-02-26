package by.com.lifetech.billingapi.utils;

import java.util.Arrays;

public class EnumUtils {
    public static <E extends Enum<E>> boolean containsEnumValue(Class<E> enumClass, String name) {
        return Arrays.stream(enumClass.getEnumConstants())
                .map(Enum::name)
                .anyMatch(value -> value.equals(name));
    }

    public static <E extends Enum<E>> E getEnumValueByName(Class<E> enumClass, String name) {
        try {
            return Enum.valueOf(enumClass, name);
        } catch (IllegalArgumentException e) {
            return null;
        }
    }
}
