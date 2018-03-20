package com.almundo.call_center.exception.handler;

import com.almundo.call_center.exception.InternalServiceException;
import com.almundo.call_center.exception.dto.ErrorDetail;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

public class RestExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(InterruptedException.class)
    public ResponseEntity<?> handlerInterruptedException(InterruptedException exception, HttpServletRequest request) {
        ErrorDetail errorDetail = new ErrorDetail(new Date().getTime(), HttpStatus.INTERNAL_SERVER_ERROR);
        return new ResponseEntity<>(errorDetail, null, errorDetail.getHttpStatus());
    }

    @ExceptionHandler(InterruptedException.class)
    public ResponseEntity<?> handlerInternalServiceException(InternalServiceException exception, HttpServletRequest request) {
        ErrorDetail errorDetail = new ErrorDetail(new Date().getTime(), HttpStatus.INTERNAL_SERVER_ERROR);
        return new ResponseEntity<>(errorDetail, null, errorDetail.getHttpStatus());
    }

}
