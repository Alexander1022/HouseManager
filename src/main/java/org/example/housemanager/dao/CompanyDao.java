package org.example.housemanager.dao;

import jakarta.validation.Valid;
import org.example.housemanager.configuration.SessionFactoryUtility;
import org.example.housemanager.dto.CreateCompanyDto;
import org.example.housemanager.entity.Apartment;
import org.example.housemanager.entity.Building;
import org.example.housemanager.entity.Company;
import org.example.housemanager.entity.Employee;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.io.ObjectStreamException;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class CompanyDao {
    public static void createCompany(@Valid Company company) {
        try(Session session = SessionFactoryUtility.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            session.save(company);
            transaction.commit();
        }
    }

    public static void saveCompanyDto(CreateCompanyDto createCompanyDto) {
        try(Session session = SessionFactoryUtility.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            Company company = new Company();

            company.setName(createCompanyDto.getName());
            company.setIncome(createCompanyDto.getIncome());

            session.save(company);
            transaction.commit();
        }
    }

    public static void updateCompany(Company company) {
        try(Session session = SessionFactoryUtility.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            session.saveOrUpdate(company);
            transaction.commit();
        }
    }

    public static void deleteCompany(Company company) {
        try(Session session = SessionFactoryUtility.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            session.delete(company);
            transaction.commit();
        }
    }

    public static void deleteCompanyById(long id) {
        try (Session session = SessionFactoryUtility.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            Company company = session.get(Company.class, id);

            if (company == null) {
                System.out.println("Company with ID " + id + " does not exist.");
            } else {
                session.delete(company);
                transaction.commit();
                System.out.println("Company with ID " + id + " was successfully deleted.");
            }
        }
    }

    public static Company getCompanyById(long id) {
        Company company;
        try(Session session = SessionFactoryUtility.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            company = session.get(Company.class, id);
            transaction.commit();
        }
        return company;
    }

    public static List<Company> getCompanies() {
        List<Company> companies;
        try(Session session = SessionFactoryUtility.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            companies = session
                    .createQuery("Select c From Company c", Company.class)
                    .getResultList();
            transaction.commit();
        }
        return companies;
    }

    public static void hireEmployee(Employee employee, Company company) {
        if(employee == null) {
            throw new IllegalArgumentException("Employee does not exist.");
        }

        if(company == null) {
            throw new IllegalArgumentException("Company does not exist.");
        }

        try (Session session = SessionFactoryUtility.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();

            employee = session.merge(employee);
            company = session.merge(company);

            if (company.getEmployees().contains(employee)) {
                throw new IllegalStateException("Employee already works for this company.");
            }

            if(employee.getCompany() != null && employee.getCompany() != company) {
                throw new IllegalStateException("Employee works for another company.");
            }

            employee.setCompany(company);
            employee.setHireDate(LocalDate.now());

            company.getEmployees().add(employee);

            session.saveOrUpdate(employee);
            session.saveOrUpdate(company);

            transaction.commit();
        }
    }

    public static void serveBuilding(Building building, Company company) {
        if(building == null) {
            throw new IllegalArgumentException("Building does not exist.");
        }

        if(company == null) {
            throw new IllegalArgumentException("Company does not exist.");
        }

        try(Session session = SessionFactoryUtility.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();

            building = session.merge(building);
            company = session.merge(company);

            Employee employee = session.createQuery(
                    "SELECT e FROM Employee e LEFT JOIN e.buildings b " +
                            "WHERE e.company = :company " +
                            "GROUP BY e " +
                            "ORDER BY COUNT(b) ASC"
                    , Employee.class)
                    .setParameter("company", company)
                    .setMaxResults(1)
                    .getSingleResult();

            employee.getBuildings().add(building);
            building.setEmployee(employee);

            session.saveOrUpdate(employee);
            session.saveOrUpdate(building);

            transaction.commit();

            System.out.println("The building with ID " + building.getId() + " was served by employee with ID " + employee.getId() + " from " + company.getId());
        }
    }

    public static void fireEmployee(Employee employee, Company company) {
        if(employee == null) {
            throw new IllegalArgumentException("Employee does not exist.");
        }

        if(company == null) {
            throw new IllegalArgumentException("Company does not exist");
        }

        try(Session session = SessionFactoryUtility.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();

            employee = session.merge(employee);
            company = session.merge(company);

            if(!company.getEmployees().contains(employee)) {
                throw new IllegalStateException("Employee is not part of the specified company.");
            }

            Set<Building> buildings = new HashSet<>(employee.getBuildings());

            List<Employee> employees = session.createQuery(
                    "FROM Employee e WHERE e.company = :company AND e != :employee",
                    Employee.class)
                    .setParameter("company", company)
                    .setParameter("employee", employee)
                    .getResultList();

            if(employees.isEmpty()) {
                throw new IllegalStateException("No employees are available for redistribution");
            }

            int currentEmployeeIndex = 0;

            for(Building building : buildings) {
                Employee newEmployee = employees.get(currentEmployeeIndex);
                newEmployee = session.merge(newEmployee);
                building = session.merge(building);

                employee.getBuildings().remove(building);
                newEmployee.getBuildings().add(building);
                building.setEmployee(newEmployee);

                session.saveOrUpdate(newEmployee);
                session.saveOrUpdate(building);

                currentEmployeeIndex = (currentEmployeeIndex + 1) % employees.size();
            }

            employee.setCompany(null);
            employee.setHireDate(null);

            session.saveOrUpdate(employee);
            session.saveOrUpdate(company);

            transaction.commit();
        }
    }

    public static double calculateTaxPerEmployee(Company company, Employee employee) {
        if(company == null) {
            throw  new IllegalArgumentException("Company does not exist");
        }

        if(employee == null) {
            throw new IllegalArgumentException("Employee");
        }

        double tax = 0.0f;

        try (Session session = SessionFactoryUtility.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();

            employee = session.merge(employee);
            company = session.merge(company);

            System.out.println(employee);
            System.out.println(company);

            if(employee.getCompany() != company) {
                throw new IllegalStateException("The employee is not from that company.");
            }

            tax = EmployeeDao.calculateTax(employee);
        }

        return tax;
    }
}
