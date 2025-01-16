package org.example.housemanager.services;

import jakarta.validation.Valid;
import org.example.housemanager.dao.CompanyDao;
import org.example.housemanager.dto.CreateCompanyDto;
import org.example.housemanager.entity.Company;
import org.example.housemanager.entity.Employee;

import java.util.List;

public class CompanyService {
    public static void createCompany(Company company) {
        CompanyDao.createCompany(company);
    }

    public static void saveCompany(CreateCompanyDto createCompanyDto) {
        CompanyDao.saveCompanyDto(createCompanyDto);
    }

    public static void updateCompany(Company company) {
        CompanyDao.updateCompany(company);
    }

    public static void deleteCompany(Company company) {
        CompanyDao.deleteCompany(company);
    }

    public static void deleteCompanyById(long id) {
        CompanyDao.deleteCompanyById(id);
    }

    public static Company getCompanyById(long id) {
        return CompanyDao.getCompanyById(id);
    }

    public static List<Company> getAllCompanies() {
        return CompanyDao.getCompanies();
    }

    public static void hireEmployee(Employee employee, Company company) {
        CompanyDao.hireEmployee(employee, company);
    }

    public static void fireEmployee(Employee employee, Company company) {
        CompanyDao.fireEmployee(employee, company);
    }
}