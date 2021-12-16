package com.bridgelabz.dto;

import lombok.Data;

import javax.validation.constraints.Pattern;

/********************************************************************************************************
 * Purpose: This is a pojo class which is used to for generating objects.
 *
 * @author Ashwith
 * @since 11/12/21
 *******************************************************************************************************/
@Data
public class PayrollDto {

    @Pattern(regexp = "^[A-Z]{1}[a-z]{3,10}$", message = "Improper Name!!!")
    private String name;
    @Pattern(regexp = "^M(ale)?$|^F(emale)?$", message = "Improper Gender!!!")
    private String gender;
    @Pattern(regexp = "[0-9]{3,}$", message = "Improper Salary!!!")
    private String salary;
    @Pattern(regexp = "^[A-Z][a-z]{3,}$", message = "Improper department!!!")
    private String department;
    @Pattern(regexp = "^[A-Z,a-z]{3,}$", message = "Improper Notes!!!")
    private String notes;
}
