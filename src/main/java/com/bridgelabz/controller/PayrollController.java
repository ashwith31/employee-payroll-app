package com.bridgelabz.controller;

import com.bridgelabz.dto.EmployeeResponseDto;
import com.bridgelabz.dto.PayrollDto;
import com.bridgelabz.service.PayrollService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**********************************************************************************************************************
 * Purpose: This class is to make different HTTP request method calls using Rest controller.
 *
 * @author Ashwith
 * @since 11/12/21
 *********************************************************************************************************************/
@RestController
@RequestMapping("/employee")
public class PayrollController {
    @Autowired
    private PayrollService employeePayrollService;

    /**
     * This method is to get all the data that is present in the database.
     *
     * @return List of all the data in the database.
     */
    @GetMapping(value = "/getAllEmployees")
    public ResponseEntity<List<EmployeeResponseDto>> getAllEmployee() {
        return new ResponseEntity<>(employeePayrollService.getAllEmployees(), HttpStatus.OK);
    }

    /**
     * This method is to add the data that is being passed into the database.
     *
     * @param employeePayrollDto the data that is to saved in the database.
     * @return string to say that if the data is saved successfully or not.
     */
    @PostMapping(value = "/addEmployee")
    public ResponseEntity<String> addEmployee(@Valid @RequestBody PayrollDto employeePayrollDto) {
        return new ResponseEntity<String>(employeePayrollService.addEmployee(employeePayrollDto), HttpStatus.OK);
    }

    /**
     * This method is to update the data in the database based on id of that database.
     *
     * @param id                 of the data to be updated.
     * @param employeePayrollDto the data to be updated
     * @return string to say that if the data is updated successfully or not.
     */
    @PutMapping("/editEmployee")
    public ResponseEntity<String> editEmployee(@Valid @RequestParam int id,
                                               @RequestBody PayrollDto employeePayrollDto) {
        return new ResponseEntity<>(employeePayrollService.editEmployee(id, employeePayrollDto), HttpStatus.OK);
    }

    /**
     * This method is to delete the data in the database based on id of that database.
     *
     * @param id of the data to be deleted.
     * @return string to say that if the data is deleted successfully or not.
     */
    @DeleteMapping("/removeEmployee")
    public ResponseEntity<String> deleteEmployee(@Valid @RequestParam int id) {
        return new ResponseEntity<>(employeePayrollService.deleteEmployee(id), HttpStatus.OK);
    }
}
