package org.example.housemanager.services;

import jakarta.validation.Valid;
import org.example.housemanager.dao.CompanyDao;
import org.example.housemanager.dto.CreateCompanyDto;
import org.example.housemanager.entity.Company;

import java.util.List;

public class CompanyService {
    public static void createCompany(@Valid Company company) {
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

    public static void hireEmployee(long companyId, long employeeId) {
        CompanyDao.hireEmployee(companyId, employeeId);
    }
}
