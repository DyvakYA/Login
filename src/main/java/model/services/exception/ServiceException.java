package model.services.exception;

import org.apache.log4j.Logger;

/**
 * Created by Dyvak on 26.01.2017.
 */
public class ServiceException extends RuntimeException {

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
