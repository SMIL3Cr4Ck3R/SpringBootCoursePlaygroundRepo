package io.sc0.REST.API.Demo.service;

import io.sc0.REST.API.Demo.entity.Employee;

import java.util.List;

public interface IEmployeeService {


    List<Employee> getEmployees ();

    Employee getEmployee(int employeeId);

    Employee createEmployee(Employee employee);

    void deleteEmployee(int employeeId);



}
