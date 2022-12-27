package br.com.alura.bytebank.exception;

public class NotFound extends RuntimeException {
    public NotFound() {
        super();
    }

    public NotFound(final String message) {
        super(message);
    }

    public NotFound(final String message, Throwable cause) {
        super(message, cause);
    }

    public NotFound(final Throwable cause) {
        super(cause);
    }
}