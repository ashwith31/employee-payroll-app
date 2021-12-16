package com.bridgelabz.controller;

import com.bridgelabz.dto.EmployeeResponseDto;
import com.bridgelabz.dto.PayrollDto;
import com.bridgelabz.exception.NoDataFoundException;
import com.bridgelabz.service.PayrollService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class PayrollControllerTests {

    PayrollDto payrollDto = new PayrollDto();

    @InjectMocks
    private PayrollController payrollController;

    @Mock
    private PayrollService payrollService;

    @BeforeEach
    void setUp(){
        payrollDto.setName("Ashwith");
        payrollDto.setGender("Male");
        payrollDto.setDepartment("Backend");
    }

    @Test
    void givenGetAllEmployeeMethodIsCalled_ShouldReturnTheListOfAllEmployeeResponseDto() {
        List<EmployeeResponseDto> payrollResponseDtoList = new ArrayList<>();
        EmployeeResponseDto employeeResponseDto = new EmployeeResponseDto();
        employeeResponseDto.setName("Ashwith");
        employeeResponseDto.setGender("Male");
        employeeResponseDto.setDepartment("Backend");
        payrollResponseDtoList.add(employeeResponseDto);
        EmployeeResponseDto employeeResponseDto2 = new EmployeeResponseDto();
        employeeResponseDto2.setName("Rohith");
        employeeResponseDto2.setGender("Male");
        employeeResponseDto2.setDepartment("Java");
        payrollResponseDtoList.add(employeeResponseDto2);
        when(payrollService.getAllEmployees()).thenReturn(payrollResponseDtoList);
        ResponseEntity<List<EmployeeResponseDto>> actualResponse = payrollController.getAllEmployee();
        for (int i = 0; i < actualResponse.getBody().size(); i++) {
            assertEquals(payrollResponseDtoList.get(i).getName(), actualResponse.getBody().get(i).getName());
            assertEquals(payrollResponseDtoList.get(i).getDepartment(), actualResponse.getBody().get(i).getDepartment());
            assertEquals(payrollResponseDtoList.get(i).getGender(), actualResponse.getBody().get(i).getGender());
        }
    }

    @Test
    void givenAddEmployeeMethodIsCalled_ShouldAddEmployeeAndGenerateSuccessMessage() {
        String successString = "Employee Added Successfully";
        ResponseEntity<String> expectedResponseEntity = new ResponseEntity<>(successString, HttpStatus.OK);
        when(payrollService.addEmployee(payrollDto)).thenReturn(successString);
        ResponseEntity<String> actualResponseString = payrollController.addEmployee(payrollDto);
        assertEquals(expectedResponseEntity, actualResponseString);
    }

    @Test
    void givenEditEmployeeMethodIsCalled_ShouldUpdateEmployeeAndGenerateSuccessMessage() {
        String successString = "Employee edited successfully";
        ResponseEntity<String> expectedResponseEntity = new ResponseEntity<>(successString, HttpStatus.OK);
        int id = 1;
        when(payrollService.editEmployee(id, payrollDto)).thenReturn(successString);
        ResponseEntity<String> actualResponseString = payrollController.editEmployee(id, payrollDto);
        assertEquals(expectedResponseEntity, actualResponseString);
    }

    @Test
    void givenEditEmployeeMethodIsCalled_WhenIdNotFound_ShouldThrowException() {
        int id = 1;
        when(payrollService.editEmployee(id, payrollDto)).thenThrow(NoDataFoundException.class);
        Assertions.assertThrows(NoDataFoundException.class, () -> payrollController.editEmployee(id, payrollDto));
    }

    @Test
    void givenDeleteEmployeeMethodIsCalled_ShouldDeleteEmployeeAndGenerateSuccessMessage() {
        String successString = "Employee delete successful";
        ResponseEntity<String> expectedResponseEntity = new ResponseEntity<>(successString, HttpStatus.OK);
        int id = 1;
        when(payrollService.deleteEmployee(id)).thenReturn(successString);
        ResponseEntity<String> actualResponseString = payrollController.deleteEmployee(id);
        assertEquals(expectedResponseEntity, actualResponseString);
    }

    @Test
    void givenDeleteEmployeeMethodIsCalled_WhenIdNotFound_ShouldThrowException() {
        int id = 1;
        when(payrollService.deleteEmployee(id)).thenThrow(NoDataFoundException.class);
        Assertions.assertThrows(NoDataFoundException.class, () -> payrollController.deleteEmployee(id));
    }
}
