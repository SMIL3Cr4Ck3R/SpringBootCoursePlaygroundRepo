package io.sc0.REST.API.Demo.service;


import io.sc0.REST.API.Demo.dao.IEmployeeRepo;
import io.sc0.REST.API.Demo.entity.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class EmployeeService implements IEmployeeService {

    private final IEmployeeRepo employeeRepo;

    @Autowired
    public EmployeeService(IEmployeeRepo employeeRepo) {
        this.employeeRepo = employeeRepo;

    }

    @Override
    public List<Employee> getEmployees() {
        return employeeRepo.getEmployees();
    }

    public Employee getEmployee(int employeeId){
        return employeeRepo.getEmployee(employeeId);
    }

    @Transactional
    @Override
    public Employee createEmployee(Employee employee) {
        return employeeRepo.createEmployee(employee);
    }

    @Transactional
    @Override
    public void deleteEmployee(int employeeId) {
        employeeRepo.deleteEmployee(employeeId);
    }

}
