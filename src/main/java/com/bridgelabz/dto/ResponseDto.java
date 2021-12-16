package com.bridgelabz.dto;

import lombok.Data;
import org.springframework.http.HttpStatus;

/********************************************************************************************************
 * Purpose: This class is for generating objects for the response using some data regarding the call.
 *
 * @author Ashwith
 * @since 11/12/21
 *******************************************************************************************************/
@Data
public class ResponseDto {

    HttpStatus httpStatus;
    String message;

    public ResponseDto(HttpStatus httpStatus, String message) {
        this.httpStatus = httpStatus;
        this.message = message;
    }
}
