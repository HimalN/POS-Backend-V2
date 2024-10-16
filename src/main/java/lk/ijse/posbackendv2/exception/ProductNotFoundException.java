package lk.ijse.posbackendv2.exception;

public class ProductNotFoundException extends RuntimeException{
    public ProductNotFoundException() {

    }

    public ProductNotFoundException(String message) {
        super(message);
    }

    public ProductNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}