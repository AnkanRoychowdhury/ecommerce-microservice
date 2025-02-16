package tech.ankanroychowdhury.cart.exceptions;

public class InvalidCartOperationException extends RuntimeException {

    public InvalidCartOperationException(String message) {
        super(message);
    }

    public InvalidCartOperationException(String message, Throwable cause) {
        super(message, cause);
    }
}

