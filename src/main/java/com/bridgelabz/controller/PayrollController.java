package com.bridgelabz.controller;

import com.bridgelabz.dto.PayrollDto;
import com.bridgelabz.dto.EmployeeResponseDto;
import com.bridgelabz.service.PayrollService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api")
public class PayrollController {
    @Autowired
    private PayrollService employeePayrollService;

    /**
     * This method is to get all the data that is present in the database.
     *
     * @return List of all the data in the database.
     */
    @GetMapping(value = "/get-all-atm")
    public ResponseEntity<List<EmployeeResponseDto>> getAllAtm() {
        return new ResponseEntity<>(employeePayrollService.getAllAtm(), HttpStatus.OK);
    }

    /**
     * This method is to add the data that is being passed into the database.
     *
     * @param employeePayrollDto the data that is to saved in the database.
     * @return string to say that if the data is saved successfully or not.
     */
    @PostMapping(value = "/add-atm")
    public ResponseEntity<String> addEmployee(@Valid @RequestBody PayrollDto employeePayrollDto) {
        return new ResponseEntity<String>(employeePayrollService.addEmployee(employeePayrollDto), HttpStatus.OK);
    }

    /**
     * This method is to update the data in the database based on id of that database.
     *
     * @param id     of the data to be updated.
     * @param employeePayrollDto the data to be updated
     * @return string to say that if the data is updated successfully or not.
     */
    @PutMapping("/edit-atm")
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
    @DeleteMapping("/delete")
    public ResponseEntity<String> deleteAtm(@Valid @RequestParam int id) {
        return new ResponseEntity<>(employeePayrollService.deleteEmployee(id), HttpStatus.OK);
    }
}
