package org.example.housemanager.dao;

import jakarta.validation.Valid;
import org.example.housemanager.configuration.SessionFactoryUtility;
import org.example.housemanager.dto.CreateEmployeeDto;
import org.example.housemanager.entity.Building;
import org.example.housemanager.entity.Company;
import org.example.housemanager.entity.Employee;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class EmployeeDao {
    public static void createEmployee(@Valid Employee employee) {
        try (Session session = SessionFactoryUtility.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            session.save(employee);
            transaction.commit();
        }
    }

    public static void saveEmployeeDto(CreateEmployeeDto createEmployeeDto) {
        try (Session session = SessionFactoryUtility.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            Employee employee = new Employee();
            employee.setName(createEmployeeDto.getName());

            session.save(employee);
            transaction.commit();
        }
    }

    public static void updateEmployee(Employee employee) {
        try (Session session = SessionFactoryUtility.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            session.saveOrUpdate(employee);
            transaction.commit();
        }
    }

    public static void deleteEmployee(Employee employee) {
        try (Session session = SessionFactoryUtility.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            session.delete(employee);
            transaction.commit();
        }
    }

    public static void deleteEmployeeById(long id) {
        try (Session session = SessionFactoryUtility.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            Employee employee = session.get(Employee.class, id);

            if (employee == null) {
                System.out.println("CEmployee with ID " + id + "does not exist.");
            } else {
                session.delete(employee);
                transaction.commit();
                System.out.println("Compnay with ID " + id + " was successfully deleted.");
            }
        }
    }

    public static Employee getEmployeeById(long id) {
        Employee employee;

        try (Session session = SessionFactoryUtility.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            employee = session.get(Employee.class, id);
            transaction.commit();
        }

        return employee;
    }

    public static void serveBuilding(Employee employee, Building building) {
        if (employee == null) {
            throw new IllegalArgumentException("Employee does not exist.");
        }

        if (building == null) {
            throw new IllegalArgumentException("Building does not exist.");
        }

        try (Session session = SessionFactoryUtility.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();

            employee = session.merge(employee);
            building = session.merge(building);

            if(building.getEmployee() != employee) {
                throw new IllegalStateException("Someone else is working on that");
            }

            if(employee.getBuildings().contains(building)) {
                throw new IllegalStateException("This employee is already working on it.");
            }

            employee.getBuildings().add(building);
            building.setEmployee(employee);

            session.saveOrUpdate(employee);
            session.saveOrUpdate(building);

            transaction.commit();
        }
    }
}