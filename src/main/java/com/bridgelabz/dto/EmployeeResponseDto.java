package com.bridgelabz.dto;

import lombok.Data;

import javax.persistence.Column;
import java.time.LocalDateTime;
import java.util.Date;

@Data
public class EmployeeResponseDto {
    private int id;
    private String name;
    private String imagePath;
    private String gender;
    private String salary;
    private LocalDateTime startDate;
    private String department;
    private String notes;
}
