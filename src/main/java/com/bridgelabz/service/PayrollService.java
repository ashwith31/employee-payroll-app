package com.bridgelabz.service;

import com.bridgelabz.builder.PayrollBuilder;
import com.bridgelabz.dto.PayrollDto;
import com.bridgelabz.dto.EmployeeResponseDto;
import com.bridgelabz.exception.NoDataFoundException;
import com.bridgelabz.repository.EmployeePayrollRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PayrollService {
    private static final String EMPLOYEE_ADDED_SUCCESSFULLY = "Employee Added Successfully";
    private static final String EMPLOYEE_EDITED_SUCCESSFULLY = "Employee edited successfully";
    private static final String EMPLOYEE_DELETED_SUCCESSFULLY = "Employee delete successful";
    private static final String INVALID_ID = "Invalid id";

    @Autowired
    private EmployeePayrollRepository employeePayrollRepository;

    @Autowired
    private PayrollBuilder payrollBuilder;

    @Autowired
    private ModelMapper modelMapper;

    /**
     * This method is to get all the data that is present in the database.
     *
     * @return List of all the data in the database.
     */
    public List<EmployeeResponseDto> getAllEmployees() {
        return employeePayrollRepository.findAll()
                .stream()
                .map(employee -> modelMapper.map(employee, EmployeeResponseDto.class))
                .collect(Collectors.toList());
    }

    /**
     * This method is to check if there is data in the database for a specific id.
     *
     * @param id to be checked if the data exists or not.
     * @return boolean true if data exists else false.
     */
    private com.bridgelabz.model.Employee findEmployeeById(int id) {
        return employeePayrollRepository.findById(id).orElseThrow(() -> new NoDataFoundException(INVALID_ID));
    }

    /**
     * This method is to add the data that is being passed into the database.
     *
     * @param employeePayrollDto the data that is to saved in the database.
     * @return string to say that if the data is saved successfully or not.
     */
    public String addEmployee(PayrollDto employeePayrollDto) {
        com.bridgelabz.model.Employee employee = modelMapper.map(employeePayrollDto, com.bridgelabz.model.Employee.class);
        employeePayrollRepository.save(employee);
        return EMPLOYEE_ADDED_SUCCESSFULLY;
    }

    /**
     * This method is to update the data in the database based on id of that database.
     *
     * @param id of the data to be updated.
     * @param employeePayrollDto the data to be updated
     * @return string to say that if the data is updated successfully or not.
     * @throws NoDataFoundException if there is a invalid id passed as argument.
     */
    public String editEmployee(int id,  PayrollDto employeePayrollDto) throws NoDataFoundException{
        com.bridgelabz.model.Employee employee = findEmployeeById(id);
        employee = payrollBuilder.buildAtmEntity(employeePayrollDto, employee);
        employeePayrollRepository.save(employee);
        return EMPLOYEE_EDITED_SUCCESSFULLY;
    }

    /**
     * This method is to delete the data in the database based on id of that database.
     *
     * @param id of the data to be deleted.
     * @return string to say that if the data is deleted successfully or not.
     * @throws NoDataFoundException if there is a invalid id passed as argument.
     */
    public String deleteEmployee(int id) throws NoDataFoundException {
        com.bridgelabz.model.Employee employee = findEmployeeById(id);
        employeePayrollRepository.delete(employee);
        return EMPLOYEE_DELETED_SUCCESSFULLY;
    }
}
