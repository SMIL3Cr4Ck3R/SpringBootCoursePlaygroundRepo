package io.sc0.REST.API.Demo.dao;


import io.sc0.REST.API.Demo.Exception.NotFoundException;
import io.sc0.REST.API.Demo.entity.Employee;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import jakarta.persistence.NoResultException;
import java.util.List;

@Repository
public class EmployeeRepo implements IEmployeeRepo {


    private final EntityManager entityManager;

    @Autowired
    public EmployeeRepo(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public List<Employee> getEmployees() {

        TypedQuery<Employee> query = entityManager.createQuery("FROM Employee",Employee.class);
        return query.getResultList();

    }

    @Override
    public Employee getEmployee(int employeeId) {

        try {

        TypedQuery<Employee> query = entityManager.createQuery("FROM Employee WHERE id=:id",Employee.class);
        query.setParameter("id",employeeId);

        return query.getSingleResult();

        } catch (NoResultException e) {
            throw new NotFoundException("Employee with ID " + employeeId + " not found.");
        }

    }

    @Override
    public Employee createEmployee(Employee employee) {
        //this saves or update the user based on existing id
        return entityManager.merge(employee);
    }

    @Override
    public void deleteEmployee(int employeeId) {

        Employee existingEmployee =  getEmployee(employeeId);

        if(existingEmployee == null)
            throw new NotFoundException("Employee Not Found Please Try Employee with other Id");

        Query q =  entityManager.createQuery("DELETE FROM Employee WHERE id=:id");
        q.setParameter("id",employeeId);
        q.executeUpdate();

    }

}
