package com.bridgelabz.exception;

import com.bridgelabz.dto.ResponseDto;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.validation.Valid;
import java.time.LocalDate;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/********************************************************************************************************
 * Purpose: This is a Global Exception class.It handles the exceptions globally.
 *
 * @author Ashwith
 * @since 11/12/21
 *******************************************************************************************************/
@ControllerAdvice
public class EmployeePayrollGlobalException extends ResponseEntityExceptionHandler {
    /**
     * This method is to handle the NoDataFound Exception globally.
     *
     * @param noDataFoundException object of NoDataFoundException
     * @return ResponseEntity of type object
     */
    @ExceptionHandler(NoDataFoundException.class)
    public ResponseEntity<Object> handleNoDataFoundException(NoDataFoundException noDataFoundException) {

        ResponseDto responseDto = new ResponseDto
                (HttpStatus.NOT_FOUND, noDataFoundException.getLocalizedMessage());
        return new ResponseEntity<>(responseDto, responseDto.getHttpStatus());
    }

    /**
     * This method is to handle the MethodArgumentNotValid exception globally.
     *
     * @param ex      object of MethodArgumentNotValidException
     * @param headers object of HttpHeaders
     * @param status  object of HttpStatus
     * @param request object of WebRequest
     * @return ResponseEntity of type object
     */
    @Valid
    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(
            MethodArgumentNotValidException ex, HttpHeaders headers,
            HttpStatus status, WebRequest request) {

        Map<String, Object> body = new LinkedHashMap<>();
        body.put("timestamp", LocalDate.now());
        body.put("status", status.value());

        List<String> errors = ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(x -> x.getDefaultMessage())
                .collect(Collectors.toList());

        body.put("errors", errors);

        return new ResponseEntity<Object>(body, headers, status);
    }
}
