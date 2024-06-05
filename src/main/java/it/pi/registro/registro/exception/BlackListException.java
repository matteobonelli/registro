package it.pi.registro.registro.exception;

public class BlackListException extends RuntimeException {
    public BlackListException(String error,String message) {
        super(message);
    }
}
