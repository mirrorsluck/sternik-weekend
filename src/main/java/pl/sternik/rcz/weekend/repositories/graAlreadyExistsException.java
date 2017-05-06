package pl.sternik.rcz.weekend.repositories;

public class graAlreadyExistsException extends Exception {
    private static final long serialVersionUID = -4576295597218170159L;

    public graAlreadyExistsException() {     
    }

    public graAlreadyExistsException(String message, Throwable cause, boolean enableSuppression,
            boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

    public graAlreadyExistsException(String message, Throwable cause) {
        super(message, cause);
    }

    public graAlreadyExistsException(String message) {
        super(message);
    }

    public graAlreadyExistsException(Throwable cause) {
        super(cause);
    }
    
}
