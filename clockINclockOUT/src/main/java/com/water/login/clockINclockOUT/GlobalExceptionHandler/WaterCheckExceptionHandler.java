package com.water.login.clockINclockOUT.GlobalExceptionHandler;

import com.water.login.clockINclockOUT.Exception.WaterCheckINException;
import jakarta.transaction.TransactionalException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.OptimisticLockingFailureException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.CannotCreateTransactionException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class WaterCheckExceptionHandler {

    @ExceptionHandler(WaterCheckINException.class)
    public ResponseEntity<String> handleWaterExceptionINException(WaterCheckINException waterCheckINException){
        return new ResponseEntity<>(waterCheckINException.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<String> handleDataIntegrityViolationException(DataIntegrityViolationException ex) {
        // Example: Duplicate key or other integrity constraint violations
        return new ResponseEntity<>("Data integrity violation: " + ex.getMessage(), HttpStatus.CONFLICT);
    }

    @ExceptionHandler(OptimisticLockingFailureException.class)
    public ResponseEntity<String> handleOptimisticLockingFailureException(OptimisticLockingFailureException ex) {
        return new ResponseEntity<>("Optimistic Locking failure: " + ex.getMessage(), HttpStatus.CONFLICT);
    }

    @ExceptionHandler(CannotCreateTransactionException.class)
    public ResponseEntity<String> handleCannotCreateTransactionException(CannotCreateTransactionException ex) {
        return new ResponseEntity<>("Database connection issue: " + ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleGeneralException(Exception ex) {
        return new ResponseEntity<>("An unexpected error occurred: " + ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
