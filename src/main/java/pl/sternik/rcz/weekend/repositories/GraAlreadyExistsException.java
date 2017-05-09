package pl.sternik.rcz.weekend.repositories;

public class GraAlreadyExistsException extends Exception {
    private static final long serialVersionUID = -4576295597218170159L;

    public GraAlreadyExistsException() {     
    }

    public GraAlreadyExistsException(String message, Throwable cause, boolean enableSuppression,
            boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

    public GraAlreadyExistsException(String message, Throwable cause) {
        super(message, cause);
    }

    public GraAlreadyExistsException(String message) {
        super(message);
    }

    public GraAlreadyExistsException(Throwable cause) {
        super(cause);
    }
    
}
