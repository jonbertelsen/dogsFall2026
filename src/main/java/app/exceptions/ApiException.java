package app.exceptions;

import app.Main;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ApiException extends RuntimeException {
    private int statusCode;
    private static final Logger logger = LoggerFactory.getLogger(ApiException.class);

    public ApiException(String message, int statusCode) {
        super(message);
        this.statusCode = statusCode;
        logger.error("StatusCode: {}, Message: {}", statusCode, message);
    }

    public int getStatusCode() {
        return statusCode;
    }
}
