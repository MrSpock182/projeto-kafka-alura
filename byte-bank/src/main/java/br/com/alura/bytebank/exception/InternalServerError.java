package br.com.alura.bytebank.exception;

public class InternalServerError extends RuntimeException {
    public InternalServerError() {
        super();
    }

    public InternalServerError(final String message) {
        super(message);
    }

    public InternalServerError(final String message, Throwable cause) {
        super(message, cause);
    }

    public InternalServerError(final Throwable cause) {
        super(cause);
    }
}
