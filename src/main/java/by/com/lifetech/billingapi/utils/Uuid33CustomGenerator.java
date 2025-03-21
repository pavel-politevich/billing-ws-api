package by.com.lifetech.billingapi.utils;

import java.math.BigInteger;
import java.util.UUID;

public class Uuid33CustomGenerator {
    private static final String BASE_33_CHARS = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private static final int BASE = 33;

    public static String uuid33() {
        UUID uuid = UUID.randomUUID();

        BigInteger bigInt = BigInteger.valueOf(uuid.getMostSignificantBits())
                .shiftLeft(64)
                .or(BigInteger.valueOf(uuid.getLeastSignificantBits()));

        return encodeBase33(bigInt);
    }

    private static String encodeBase33(BigInteger value) {
        StringBuilder encoded = new StringBuilder();

        BigInteger base = BigInteger.valueOf(BASE);
        BigInteger current = value.abs();

        while (current.compareTo(BigInteger.ZERO) > 0) {
            int remainder = current.mod(base).intValue();
            encoded.append(BASE_33_CHARS.charAt(remainder));
            current = current.divide(base);
        }

        encoded.reverse();

        while (encoded.length() < 12) {
            encoded.insert(0, '0');
        }

        return encoded.length() > 12 ? encoded.substring(0, 12) : encoded.toString();
    }
}