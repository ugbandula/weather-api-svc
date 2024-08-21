package com.weather.demo.api.exception;

import java.util.Date;

/**
 * Implements the exception response with customized information.
 *
 * @author Bandula Gamage
 */

public class ExceptionResponse {

    /**
     * Time stamp
     */
    private Date 	timestamp;
    /**
     * Brief exception message detailing what went wrong/failed.
     */
    private String 	message;

    /**
     * Sttus of the action.
     */
    private String 	status;

    /**
     * Detailed exception details. Usually this contains the failed URI information.
     */
    private String 	details;

    /**
     * Constructor
     * @param timestamp Timestamp value
     * @param message	Custom message
     * @param details	A detailed message
     */
    public ExceptionResponse(Date timestamp, String message, String details) {
        super();
        this.timestamp = timestamp;
        this.message = message;
        this.details = details;
    }

    /**
     * Constructor
     * @param timestamp Timestamp value
     * @param message	Custom message
     * @param details	A detailed message
     * @param status Exception status
     */
    public ExceptionResponse(Date timestamp, String message, String details, String status) {
        super();
        this.timestamp = timestamp;
        this.message = message;
        this.details = details;
        this.status = status;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public String getMessage() {
        return message;
    }

    public String getDetails() {
        return details;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
