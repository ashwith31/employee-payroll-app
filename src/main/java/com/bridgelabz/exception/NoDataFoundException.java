package com.bridgelabz.exception;

/********************************************************************************************************
 * Purpose: This class is to define a custom exception if there is no data found in the database.
 *
 * @author Ashwith
 * @since 11/12/21
 *******************************************************************************************************/
public class NoDataFoundException extends RuntimeException {
    public NoDataFoundException(String message) {
        super(message);
    }
}
