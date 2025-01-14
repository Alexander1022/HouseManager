package org.example.housemanager.services;

import jakarta.validation.Valid;
import org.example.housemanager.dao.EmployeeDao;
import org.example.housemanager.dto.CreateEmployeeDto;
import org.example.housemanager.entity.Employee;

public class EmployeeService {
    public static void createEmployee(@Valid Employee employee) {
        EmployeeDao.createEmployee(employee);
    }

    public static void saveEmployee(CreateEmployeeDto createEmployeeDto) {
        EmployeeDao.saveEmployeeDto(createEmployeeDto);
    }

    public static void updateEmployee(Employee employee) {
        EmployeeDao.updateEmployee(employee);
    }

    public static void deleteEmployee(Employee employee) {
        EmployeeDao.deleteEmployee(employee);
    }

    public static void deleteEmployeeById(long id) {
        EmployeeDao.deleteEmployeeById(id);
    }

    public static Employee getEmployeeById(long id) {
        return EmployeeDao.getEmployeeById(id);
    }
}
