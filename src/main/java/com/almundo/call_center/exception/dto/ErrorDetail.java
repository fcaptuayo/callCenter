package com.almundo.call_center.exception.dto;

import org.springframework.http.HttpStatus;

/**
 * Representation of the Manager.
 * Created by Fredy Gonzalo Captuayo Novoa on 19/03/18.
 */
public class ErrorDetail {

    private int status;
    private long timeStamp;
    private String reasonPhrase;
    private HttpStatus httpStatus;

    /**
     * Constructor generic the ErrorDetail .
     */
    public ErrorDetail() {
    }

    /**
     * Constructor the ErrorDetail whit timeStamp and HttpStatus .
     *
     * @param timeStamp
     * @param httpStatus
     */
    public ErrorDetail(long timeStamp, HttpStatus httpStatus) {
        super();
        this.timeStamp = timeStamp;
        this.status = httpStatus.value();
        this.reasonPhrase = httpStatus.getReasonPhrase();
    }

    // Generic getter.

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public long getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(long timeStamp) {
        this.timeStamp = timeStamp;
    }

    public String getReasonPhrase() {
        return reasonPhrase;
    }

    public void setReasonPhrase(String reasonPhrase) {
        this.reasonPhrase = reasonPhrase;
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }

    public void setHttpStatus(HttpStatus httpStatus) {
        this.httpStatus = httpStatus;
    }
}
