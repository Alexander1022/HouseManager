package org.example.housemanager.dao;

import jakarta.validation.Valid;
import org.example.housemanager.configuration.SessionFactoryUtility;
import org.example.housemanager.dto.CreateCompanyDto;
import org.example.housemanager.entity.Company;
import org.example.housemanager.entity.Employee;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.io.ObjectStreamException;
import java.time.LocalDate;
import java.util.List;

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

    public static void fireEmployee(Employee employee, Company company) {
        if (employee == null) {
            throw new IllegalArgumentException("Employee does not exist.");
        }

        if (company == null) {
            throw new IllegalArgumentException("Company does not exist.");
        }

        try (Session session = SessionFactoryUtility.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();

            employee = session.merge(employee);
            company = session.merge(company);

            if (!company.getEmployees().contains(employee)) {
                throw new IllegalStateException("Employee is not part of the specified company.");
            }

            employee.setCompany(null);
            employee.setHireDate(null);
            company.getEmployees().remove(employee);

            session.saveOrUpdate(employee);
            session.saveOrUpdate(company);

            transaction.commit();
        }
    }
}
