package com.bridgelabz.service;

import com.bridgelabz.builder.PayrollBuilder;
import com.bridgelabz.dto.PayrollDto;
import com.bridgelabz.dto.EmployeeResponseDto;
import com.bridgelabz.exception.NoDataFoundException;
import com.bridgelabz.model.Employee;
import com.bridgelabz.repository.EmployeePayrollRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PayrollService {
    private static final String ATM_ADDED_SUCCESSFULLY = "Atm Added Successfully";
    private static final String ATM_EDITED_SUCCESSFULLY = "Atm edited successfully";
    private static final String ATM_DELETED_SUCCESSFULLY = "Atm delete successful";
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
    public List<EmployeeResponseDto> getAllAtm() {
        return employeePayrollRepository.findAll()
                .stream()
                .map(atmEntity -> modelMapper.map(atmEntity, EmployeeResponseDto.class))
                .collect(Collectors.toList());
    }

    /**
     * This method is to check if there is data in the database for a specific id.
     *
     * @param id to be checked if the data exists or not.
     * @return boolean true if data exists else false.
     */
    private Employee findAtmEntityById(int id) {
        return employeePayrollRepository.findById(id).orElseThrow(() -> new NoDataFoundException(INVALID_ID));
    }

    /**
     * This method is to add the data that is being passed into the database.
     *
     * @param employeePayrollDto the data that is to saved in the database.
     * @return string to say that if the data is saved successfully or not.
     */
    public String addEmployee(PayrollDto employeePayrollDto) {
        Employee employee = modelMapper.map(employeePayrollDto, Employee.class);
        employeePayrollRepository.save(employee);
        return ATM_ADDED_SUCCESSFULLY;
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
        Employee employee = findAtmEntityById(id);
        employee = payrollBuilder.buildAtmEntity(employeePayrollDto, employee);
        employeePayrollRepository.save(employee);
        return ATM_EDITED_SUCCESSFULLY;
    }

    /**
     * This method is to delete the data in the database based on id of that database.
     *
     * @param id of the data to be deleted.
     * @return string to say that if the data is deleted successfully or not.
     * @throws NoDataFoundException if there is a invalid id passed as argument.
     */
    public String deleteEmployee(int id) throws NoDataFoundException {
        Employee employee = findAtmEntityById(id);
        employeePayrollRepository.delete(employee);
        return ATM_DELETED_SUCCESSFULLY;
    }
}
