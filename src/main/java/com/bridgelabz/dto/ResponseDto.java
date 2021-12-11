package com.bridgelabz.dto;

import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
public class ResponseDto {

    HttpStatus httpStatus;
    String message;

    public ResponseDto(HttpStatus httpStatus, String message) {
        this.httpStatus = httpStatus;
        this.message = message;
    }
}
