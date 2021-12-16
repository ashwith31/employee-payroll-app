package com.bridgelabz.repository;

import com.bridgelabz.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
/********************************************************************************************************
 * Purpose: This is a repository interface which is used to perform different operations in the database.
 *
 * @author Ashwith
 * @since 11/12/21
 *******************************************************************************************************/
@Repository
public interface EmployeePayrollRepository extends JpaRepository<Employee, Integer> {
}
