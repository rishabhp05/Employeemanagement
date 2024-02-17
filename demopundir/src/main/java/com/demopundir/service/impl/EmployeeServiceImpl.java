package com.demopundir.service.impl;

import Mapper.EmployeeMapper;
import com.demopundir.dto.EmployeeDto;
import com.demopundir.entity.Employee;
import com.demopundir.exception.ResourceNotFoundException;
import com.demopundir.repository.EmployeeRepository;
import com.demopundir.service.EmployeeService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {
    private EmployeeRepository employeeRepository;

    @Override
    public EmployeeDto createEmployee(EmployeeDto employeeDto) {
       Employee employee = EmployeeMapper.mapToEmployee(employeeDto);
       Employee savedEmployee =  employeeRepository.save(employee);
        return EmployeeMapper.mapToEmployeeDto(savedEmployee);
    }

    @Override
    public EmployeeDto getEmployeeById(Long employeeId) {
     Employee employee =  employeeRepository.findById(employeeId).orElseThrow(()-> new ResourceNotFoundException("Employee id does not exist :" + employeeId));
        return EmployeeMapper.mapToEmployeeDto(employee);
    }

    @Override
    public List<EmployeeDto> getAllemployee() {
        List<Employee>  employees = employeeRepository.findAll();
        return employees.stream().map((employee)->EmployeeMapper.mapToEmployeeDto(employee)).collect(Collectors.toList());
    }

    @Override
    public EmployeeDto updateEmployee(Long employeeId, EmployeeDto updatedEmployee) {
        Employee employee =  employeeRepository.findById(employeeId).orElseThrow(()-> new ResourceNotFoundException("Employee id does not exist :" + employeeId));
        employee.setFirstName(updatedEmployee.getFirstName());
        employee.setLastName(updatedEmployee.getLastName());
        employee.setEmail(updatedEmployee.getEmail());
        Employee updatedEmployeeobj = employeeRepository.save(employee);

        return EmployeeMapper.mapToEmployeeDto(updatedEmployeeobj);
    }

    @Override
    public void deleteEmployee(Long employeeId) {
            Employee employee =  employeeRepository.findById(employeeId).orElseThrow(()-> new ResourceNotFoundException("Employee id does not exist :" + employeeId));
            employeeRepository.deleteById(employeeId);
    }


}
