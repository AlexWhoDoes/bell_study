package org.example.utils.exeptionhandler;

import org.example.utils.customexception.NoCitizenshipException;
import org.example.utils.customexception.NoDocTypeException;
import org.example.utils.customexception.NoListException;
import org.example.utils.customexception.NoOfficeException;
import org.example.utils.customexception.NoObjectException;
import org.example.utils.customexception.NullException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import java.util.UUID;

@RestControllerAdvice
public class ExceptionHandlerController {
    private static int number = 0;
    private final Logger log = LoggerFactory.getLogger(getClass());

    @ExceptionHandler(NoCitizenshipException.class)
    public ErrorObject ExceptionProcessor(NoCitizenshipException e) {
        String exceptionNumber = exceptionNumberGenerator();
        log.error("NoCitizenshipException has been caught " + exceptionNumber);
        ErrorObject errorObject = new ErrorObject();
        errorObject.setError("There is no citizenship with code "
                + e.getCitizenshipCode()
                + " Exception number is "
                + exceptionNumber);
        return errorObject;
    }

    @ExceptionHandler(NoOfficeException.class)
    public ErrorObject ExceptionProcessor(NoOfficeException e) {
        String exceptionNumber = exceptionNumberGenerator();
        log.error("NoOfficeException has been caught " + exceptionNumber);
        ErrorObject errorObject = new ErrorObject();
        errorObject.setError("There is no office with id "
                + e.getOfficeId()
                + " Exception number is "
                + exceptionNumber);
        return errorObject;
    }

    @ExceptionHandler(NoDocTypeException.class)
    public ErrorObject ExceptionProcessor(NoDocTypeException e) {
        String exceptionNumber = exceptionNumberGenerator();
        log.error("NoDocTypeException has been caught " + exceptionNumber);
        ErrorObject errorObject = new ErrorObject();
        errorObject.setError("There is no document type with code "
                + e.getDocCode()
                + " and/or name "
                + e.getDocName()
                + " Exception number is "
                + exceptionNumber);
        return errorObject;
    }

    @ExceptionHandler(NoObjectException.class)
    public ErrorObject ExceptionProcessor(NoObjectException e) {
        String exceptionNumber = exceptionNumberGenerator();
        log.error("NoObjectException has been caught " + exceptionNumber);
        ErrorObject errorObject = new ErrorObject();
        errorObject.setError("There is no " + e.getObjectName() + " with id "
                + e.getId()
                + " Exception number is "
                + exceptionNumber);
        return errorObject;
    }

    @ExceptionHandler(NoListException.class)
    public ErrorObject ExceptionProcessor(NoListException e) {
        String exceptionNumber = exceptionNumberGenerator();
        log.error("NoListException has been caught " + exceptionNumber);
        ErrorObject errorObject = new ErrorObject();
        errorObject.setError("There are no "
                + e.getObject()
                + " with such parameters: "
                + e.getParam()
                + " Exception number is "
                + exceptionNumber);
        return errorObject;
    }

    @ExceptionHandler(NullException.class)
    public ErrorObject ExceptionProcessor(NullException e) {
        String exceptionNumber = exceptionNumberGenerator();
        log.error("NullException has been caught " + exceptionNumber);
        ErrorObject errorObject = new ErrorObject();
        errorObject.setError("Failed to process a request due to incorrect input data. Number of an exception is "
                + exceptionNumber);
        return errorObject;
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ErrorObject ExceptionProcessor(MethodArgumentNotValidException e) {
        String exceptionNumber = exceptionNumberGenerator();
        log.error("MethodArgumentNotValidException has been caught " + exceptionNumber);
        log.error(e.getMessage());
        ErrorObject errorObject = new ErrorObject();
        errorObject.setError("Failed to process a request due to requirements for a request is not met. Check out needed data for the request. Number of an exception is "
                + exceptionNumber);
        return errorObject;
    }

    @ExceptionHandler(Exception.class)
    public ErrorObject ExceptionProcessor(Exception e) {
        String exceptionNumber = exceptionNumberGenerator();
        log.error("Exception has been caught " + exceptionNumber);
        log.error(e.getMessage());
        ErrorObject errorObject = new ErrorObject();
        errorObject.setError("An unexpected exception has occurred. Number of an exception is "
                + exceptionNumber
                + ". Please contact a support time to get more information");
        return errorObject;
    }

    private String exceptionNumberGenerator() {
        return "ex_#" + number++ + "_" + UUID.randomUUID().toString();
    }
}