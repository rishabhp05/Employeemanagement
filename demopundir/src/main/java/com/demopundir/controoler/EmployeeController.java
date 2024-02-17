package com.demopundir.controoler;

import com.demopundir.dto.EmployeeDto;
import com.demopundir.service.EmployeeService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/api/employees")
public class EmployeeController {
    private EmployeeService employeeService;
    //build add employee rest api
    @PostMapping
    public ResponseEntity<EmployeeDto>createEmployee(@RequestBody EmployeeDto employeeDto){
        EmployeeDto savedEmployee = employeeService.createEmployee(employeeDto);
        return new ResponseEntity<>(savedEmployee, HttpStatus.CREATED);
    }
    //build add employee rest api
    @GetMapping("{id}")
    public ResponseEntity<EmployeeDto>getEmployeeId(@PathVariable("id") Long employeeId ){
        EmployeeDto employeeDto =  employeeService.getEmployeeById(employeeId);
        return ResponseEntity.ok(employeeDto);


    }
    //build api to get all employee
    @GetMapping
    public ResponseEntity<List<EmployeeDto>>getAllemployee(){
       List<EmployeeDto> employees  =  employeeService.getAllemployee();
       return ResponseEntity.ok(employees);
    }

    @PutMapping("{id}")
    public  ResponseEntity<EmployeeDto>updateEmployee(@PathVariable ("id") Long employeeId,@RequestBody EmployeeDto updatedEmployee){
        EmployeeDto employeeDto = employeeService.updateEmployee(employeeId,updatedEmployee);
      return  ResponseEntity.ok(employeeDto);
    }
    @DeleteMapping("{id}")
    public ResponseEntity<String>deleteEmployee(@PathVariable("id") Long employeeId){
        employeeService.deleteEmployee(employeeId);
        return  ResponseEntity.ok("employee deleted sucessfully");
    }

}
