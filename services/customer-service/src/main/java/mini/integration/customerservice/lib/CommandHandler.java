package mini.integration.customerservice.lib;

public interface CommandHandler<T> {
    void handle(T command);
}
