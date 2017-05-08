package model.dao.exception;

import org.apache.log4j.Logger;

/**
 * Created by Dyvak on 26.01.2017.
 */
public class DAOException extends RuntimeException {

    private static final Logger logger=Logger.getLogger(DAOException.class);

    public DAOException(String message) {
        super(message);
        logger.info(message);
    }

    public DAOException(Throwable cause) {
        super(cause);
        logger.info(cause);
    }

    public DAOException(String message, Throwable cause) {
        super(message, cause);
        logger.info(message, cause);
    }
}
