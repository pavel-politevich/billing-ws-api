package by.com.lifetech.billingapi.configurations.properties.inputchecking;

public class InputParametersCheckingConfig {
    public static final int MSISDN_MIN_LENGTH = 12;
    public static final int MSISDN_MAX_LENGTH = 13;

    private InputParametersCheckingConfig() {
        throw new IllegalStateException("Utility class");
    }
}
