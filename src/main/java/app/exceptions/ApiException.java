package app.exceptions;

import app.config.ApplicationConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ApiException extends RuntimeException {

    private final int statusCode;
    private static final Logger logger = LoggerFactory.getLogger(ApiException.class);

    public ApiException(int statusCode, String message) {
        super(message);
        this.statusCode = statusCode;
        logger.error("StatusCode: {}, Message: {}", statusCode, message);
    }

    public int getStatusCode() {
        return statusCode;
    }
}
