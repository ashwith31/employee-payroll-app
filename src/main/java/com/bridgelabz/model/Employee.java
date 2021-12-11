package com.bridgelabz.model;

import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import javax.persistence.*;
import javax.validation.constraints.*;

@Entity
@Data
public class Employee {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "NAME", length = 35)
    private String name;
    @Column(name = "IMAGE_PATH")
    private String imagePath;
    @Column(name = "GENDER", length = 10)
    private String gender;
    @Column(name = "SALARY")
    private String salary;
    @Column(name = "START_DATE")
    @CreationTimestamp
    private LocalDateTime startDate;
    @Column(name = "DEPARTMENT")
    private String department;
    @Column(name = "NOTES")
    private String notes;
}
