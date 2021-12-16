package com.bridgelabz.service;

import com.bridgelabz.builder.PayrollBuilder;
import com.bridgelabz.dto.EmployeeResponseDto;
import com.bridgelabz.dto.PayrollDto;
import com.bridgelabz.exception.NoDataFoundException;
import com.bridgelabz.model.Employee;
import com.bridgelabz.repository.EmployeePayrollRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class PayrollServiceTests {

    Employee employee = new Employee();
    PayrollDto payrollDto = new PayrollDto();

    @Mock
    ModelMapper modelMapper;
    @InjectMocks
    private PayrollService payrollService;
    @Mock
    private EmployeePayrollRepository employeePayrollRepository;
    @Mock
    private PayrollBuilder payrollBuilder;


    @BeforeEach
    void setUp(){
        employee.setId(1);
        employee.setName("Ashwith");
        employee.setGender("Male");
        employee.setDepartment("Backend");

        payrollDto.setName("Ashwith");
        payrollDto.setGender("Male");
        payrollDto.setDepartment("Backend");
    }

    @Test
    void givenGetAllEmployeeMethodIsCalled_ShouldReturnListOfEmployeeResponseDto() {
        List<Employee> employeeList = new ArrayList<>();
        employeeList.add(employee);
        Employee employee2 = new Employee();
        employee2.setId(2);
        employee2.setName("Rohith");
        employee2.setGender("Male");
        employee2.setDepartment("Java");
        employeeList.add(employee2);

        List<EmployeeResponseDto> employeeResponseList = new ArrayList<>();
        EmployeeResponseDto employeeResponseDto = new EmployeeResponseDto();
        employeeResponseDto.setId(1);
        employeeResponseDto.setName("Ashwith");
        employeeResponseDto.setGender("Male");
        employeeResponseDto.setDepartment("Backend");
        employeeResponseList.add(employeeResponseDto);
        EmployeeResponseDto employeeResponseDto2 = new EmployeeResponseDto();
        employeeResponseDto2.setId(2);
        employeeResponseDto2.setName("Rohith");
        employeeResponseDto2.setGender("Male");
        employeeResponseDto2.setDepartment("Java");
        employeeResponseList.add(employeeResponseDto2);

        when(employeePayrollRepository.findAll()).thenReturn(employeeList);
        when(modelMapper.map(employeeList.get(0), EmployeeResponseDto.class)).thenReturn(employeeResponseDto);
        when(modelMapper.map(employeeList.get(1), EmployeeResponseDto.class)).thenReturn(employeeResponseDto2);
        List<EmployeeResponseDto> actualListOfEmployee = payrollService.getAllEmployees();
        assertEquals(2, actualListOfEmployee.size());
        assertEquals(employeeResponseList, actualListOfEmployee);
    }

    @Test
    void givenAddEmployeeMethodIsCalled_ShouldAddEmployeeAndGenerateSuccessMessage() {
        when(modelMapper.map(payrollDto, Employee.class)).thenReturn(employee);
        String actualStringMessage = payrollService.addEmployee(payrollDto);
        assertEquals("Employee Added Successfully", actualStringMessage);
        verify(employeePayrollRepository, times(1)).save(employee);
    }

    @Test
    void givenEditEmployeeMethodIsCalled_WhenIdIsNotPresent_ShouldThrowExceptionMessage() {
        int id = 1;
        when(employeePayrollRepository.findById(id)).thenReturn(Optional.empty());
        assertThrows(NoDataFoundException.class, () -> payrollService.editEmployee(id, payrollDto));
    }

    @Test
    void givenEditEmployeeMethodIsCalled_ShouldUpdateEmployeeDetailsAndReturnSuccessMessage() {
        ArgumentCaptor<Employee> employeeEntityArgumentCaptor = ArgumentCaptor.forClass(Employee.class);

        int id = 1;
        Employee employee2 = new Employee();

        when(employeePayrollRepository.findById(id)).thenReturn(Optional.of(employee2));
        employee2.setName(payrollDto.getName());
        employee2.setGender(payrollDto.getGender());
        employee2.setDepartment(payrollDto.getDepartment());
        when(payrollBuilder.buildEmployeeEntity(payrollDto, employee2)).thenReturn(employee2);
        String actualSuccessMessage = payrollService.editEmployee(id, payrollDto);
        verify(employeePayrollRepository, times(1)).save(employeeEntityArgumentCaptor.capture());
        assertEquals("Employee edited successfully", actualSuccessMessage);
        assertEquals(payrollDto.getName(), employeeEntityArgumentCaptor.getValue().getName());
        assertEquals(payrollDto.getGender(), employeeEntityArgumentCaptor.getValue().getGender());
        assertEquals(payrollDto.getDepartment(), employeeEntityArgumentCaptor.getValue().getDepartment());
    }

    @Test
    void givenDeleteEmployeeMethodIsCalled_IfIdNotFound_shouldThrowExceptionMessage() {
        int id = 1;
        when(employeePayrollRepository.findById(id)).thenReturn(Optional.empty());
        assertThrows(NoDataFoundException.class, () -> payrollService.deleteEmployee(id));
    }

    @Test
    void givenDeleteEmployeeMethodIsCalledWithAnId_ShouldDeleteTheDataOfThatId() {
        int id = 1;
        when(employeePayrollRepository.findById(id)).thenReturn(Optional.of(employee));
        String actualMessage = payrollService.deleteEmployee(id);
        assertEquals("Employee delete successful", actualMessage);
        verify(employeePayrollRepository, times(1)).delete(employee);
    }
}
