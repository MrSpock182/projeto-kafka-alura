package br.com.alura.bytebank.exception;

public class BadRequest extends RuntimeException {
    public BadRequest() {
        super();
    }

    public BadRequest(final String message) {
        super(message);
    }

    public BadRequest(final String message, Throwable cause) {
        super(message, cause);
    }

    public BadRequest(final Throwable cause) {
        super(cause);
    }
}
