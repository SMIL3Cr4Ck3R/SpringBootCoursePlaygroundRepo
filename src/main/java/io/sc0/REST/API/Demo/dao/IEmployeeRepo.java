package io.sc0.REST.API.Demo.dao;


import io.sc0.REST.API.Demo.entity.Employee;

import java.util.List;

public interface IEmployeeRepo {

    List<Employee> getEmployees ();

    Employee getEmployee(int employeeId);

    Employee createEmployee(Employee employee);

    void deleteEmployee(int employeeId);


}
