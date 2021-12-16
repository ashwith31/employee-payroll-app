package com.bridgelabz.integration;

import com.bridgelabz.controller.PayrollController;
import com.bridgelabz.dto.EmployeeResponseDto;
import com.bridgelabz.dto.PayrollDto;
import com.bridgelabz.service.PayrollService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.result.ViewResultMatchers;

import java.util.ArrayList;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@ActiveProfiles("test")
@WebMvcTest(PayrollController.class)
public class PayrollControllerIT {

    ObjectMapper objectMapper = new ObjectMapper();

    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private PayrollService payrollService;

    @Test
    void getAllEmployeeTest() throws Exception {
        when(payrollService.getAllEmployees()).thenReturn(new ArrayList<>());
        mockMvc.perform(MockMvcRequestBuilders.get("/employee/getAllEmployees"))
                .andExpect(status().isOk());
    }

    @Test
    void addEmployeeTest() throws Exception {
        PayrollDto employeeDto = new PayrollDto();
        employeeDto.setName("Ashwith");
        employeeDto.setGender("Male");
        employeeDto.setSalary("50000");
        employeeDto.setDepartment("Backend");
        employeeDto.setNotes("Nothing");
        String jsonRequest = objectMapper.writeValueAsString(employeeDto);
        when(payrollService.addEmployee(any())).thenReturn("Employee Added Successfully");
        mockMvc.perform(MockMvcRequestBuilders.post("/employee/addEmployee")
                        .content(jsonRequest)
                        .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("text/plain;charset=UTF-8"))
                .andExpect(MockMvcResultMatchers.content().string("Employee Added Successfully"));

    }

    @Test
    void editEmployeeTest() throws Exception {
        int id = 1;
        PayrollDto employeeDto = new PayrollDto();
        employeeDto.setName("Ashwith");
        employeeDto.setGender("Male");
        employeeDto.setSalary("50000");
        employeeDto.setDepartment("Backend");
        employeeDto.setNotes("Nothing");

        String jsonRequest = objectMapper.writeValueAsString(employeeDto);
        when(payrollService.editEmployee(id, employeeDto)).thenReturn("Employee Edited Successfully");
        mockMvc.perform(MockMvcRequestBuilders.put("/employee/editEmployee?id=1")
                        .content(jsonRequest)
                        .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("text/plain;charset=UTF-8"))
                .andExpect(MockMvcResultMatchers.content().string("Employee Edited Successfully"));;
    }

    @Test
    void deleteEmployeeTest() throws Exception {
        int id = 1;
        when(payrollService.deleteEmployee(id)).thenReturn("Employee Deleted Successfully");
        mockMvc.perform(MockMvcRequestBuilders.delete("/employee/removeEmployee?id=1"))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("text/plain;charset=UTF-8"))
                .andExpect(MockMvcResultMatchers.content().string("Employee Deleted Successfully"));;
    }

}
