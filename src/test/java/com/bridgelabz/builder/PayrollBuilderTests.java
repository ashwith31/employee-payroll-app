package com.bridgelabz.builder;

import com.bridgelabz.dto.PayrollDto;
import com.bridgelabz.model.Employee;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class PayrollBuilderTests {

    @InjectMocks
    private PayrollBuilder payrollBuilder;

    @Test
    public void givenBuildEmployeeEntityMethodIsCalled_ShouldReturnEmployee() {
        PayrollDto payrollDto = new PayrollDto();
        payrollDto.setName("Ashwith");
        payrollDto.setGender("Male");
        payrollDto.setDepartment("Backend");

        Employee employee = new Employee();

        Employee actualEmployee = payrollBuilder.buildEmployeeEntity(payrollDto, employee);

        Employee expectedEmployee = new Employee();
        expectedEmployee.setName("Ashwith");
        expectedEmployee.setGender("Male");
        expectedEmployee.setDepartment("Backend");
        Assertions.assertEquals(expectedEmployee, actualEmployee);
    }
}
