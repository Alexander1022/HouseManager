package org.example.housemanager.dao;

import jakarta.validation.Valid;
import org.example.housemanager.configuration.SessionFactoryUtility;
import org.example.housemanager.dto.CreateCompanyDto;
import org.example.housemanager.entity.Company;
import org.example.housemanager.entity.Employee;
import org.hibernate.Session;
import org.hibernate.Transaction;

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

    public static void hireEmployee(long companyId, long employeeId) {
        try (Session session = SessionFactoryUtility.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();

            Company company = session.get(Company.class, companyId);
            if (company == null) {
                throw new IllegalArgumentException("Company not found");
            }

            Employee employee = session.get(Employee.class, employeeId);
            if (employee == null) {
                throw new IllegalArgumentException("Employee not found");
            }

            company.getEmployees().add(employee);
            employee.setCompany(company);

            session.saveOrUpdate(company);
            transaction.commit();
        }
    }
}
