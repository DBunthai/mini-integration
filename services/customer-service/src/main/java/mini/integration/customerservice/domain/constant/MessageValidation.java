package mini.integration.customerservice.domain.constant;

public class MessageValidation {

    public static final String TEST = "";

    public static String MAX(String column, int length) {
        return String.format("%s is exceeded %d characters", column, length);
    }

    public static String REQUIRED(String column) {
        return String.format("%s is required", column);
    }
}
