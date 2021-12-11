package com.bridgelabz.dto;

import lombok.Data;

import javax.validation.constraints.*;

@Data
public class PayrollDto {

    @Pattern(regexp = "^[A-Z]{1}[a-z]{3,10}$", message = "Improper Name!!!")
    private String name;
    @Pattern(regexp = "^M(ale)?$|^F(emale)?$",message = "Improper Gender!!!")
    private String gender;
    @Pattern(regexp = "[0-9]{3,}$", message = "Improper Salary!!!")
    private String salary;
    @Pattern(regexp = "^[A-Z][a-z]{3,}$", message = "Improper department!!!")
    private String department;
    @Pattern(regexp = "^[A-Z,a-z]{3,}$", message = "Improper Notes!!!")
    private String notes;
}
