package com.bridgelabz.dto;

import lombok.Data;

import java.time.LocalDateTime;

/********************************************************************************************************
 * Purpose: This is a pojo class which is used to for generating objects.
 *
 * @author Ashwith
 * @since 11/12/21
 *******************************************************************************************************/
@Data
public class EmployeeResponseDto {
    private int id;
    private String name;
    private String gender;
    private String salary;
    private LocalDateTime startDate;
    private String department;
    private String notes;
}
