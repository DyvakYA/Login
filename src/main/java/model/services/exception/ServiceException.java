package model.services.exception;

import controller.exception.ApplicationException;

/**
 * Created by Dyvak on 26.01.2017.
 */
public class ServiceException extends ApplicationException {

    public static final String USER_ALREADY_EXISTS="User already exist";

    public ServiceException(String message) {
        super(message);
    }

    public ServiceException(Throwable cause) {
        super(cause);
    }

    public ServiceException(String message, Throwable cause) {
        super(message, cause);
    }
}
