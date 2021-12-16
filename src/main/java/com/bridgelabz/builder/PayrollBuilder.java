package com.bridgelabz.builder;

import com.bridgelabz.dto.PayrollDto;
import com.bridgelabz.model.Employee;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;
/********************************************************************************************************
 * Purpose: This class is for building the methods so that we can avoid DRY principal.
 *
 * @author Ashwith
 * @since 11/12/21
 *******************************************************************************************************/
@Component
public class PayrollBuilder {
    private final ModelMapper modelMapper = new ModelMapper();

    /**
     * This method is to map the data of PayrollDto class object to Employee class object.
     *
     * @param employeePayrollDto object of PayrollDto class which has the source data.
     * @param employee           object of Employee class to which the data is to be mapped.
     * @return Object of Employee class after the data is mapped into it.
     */
    public Employee buildEmployeeEntity(PayrollDto employeePayrollDto, Employee employee) {
        modelMapper.map(employeePayrollDto, employee);
        return employee;
    }
}
