package com.bridgelabz.builder;

import com.bridgelabz.dto.PayrollDto;
import com.bridgelabz.model.Employee;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class PayrollBuilder {
    private ModelMapper modelMapper = new ModelMapper();

    public Employee buildAtmEntity(PayrollDto employeePayrollDto, Employee employee) {
        modelMapper.map(employeePayrollDto, employee);
        return employee;
    }
}
