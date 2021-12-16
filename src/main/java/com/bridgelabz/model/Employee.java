package com.bridgelabz.model;

import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;
/********************************************************************************************************
 * Purpose: This is the entity class where all the messages are stored in the database.
 *
 * @author Ashwith
 * @since 2/12/21
 *******************************************************************************************************/
@Entity
@Data
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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
