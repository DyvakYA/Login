package model.services.exception;

import controller.exception.ApplicationException;
import org.apache.log4j.Logger;

/**
 * Created by Dyvak on 26.01.2017.
 */
public class ServiceException extends ApplicationException {

    public static final String USER_ALREADY_EXISTS="User already exist";

    private static final Logger logger=Logger.getLogger(ServiceException.class);

    public ServiceException(String message) {
        super(message);
        logger.info(message);
    }

    public ServiceException(Throwable cause) {
        super(cause);
        logger.info(cause);
    }

    public ServiceException(String message, Throwable cause) {
        super(message, cause);
        logger.info(message, cause);
    }
}
