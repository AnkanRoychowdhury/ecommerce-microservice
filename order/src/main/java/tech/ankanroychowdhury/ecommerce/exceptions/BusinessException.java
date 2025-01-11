package tech.ankanroychowdhury.ecommerce.exceptions;


public class BusinessException extends RuntimeException {
    public BusinessException(String message) {
        super(message);
    }
}
