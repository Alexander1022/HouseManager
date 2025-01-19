package org.example.housemanager.services;

import jakarta.validation.Valid;
import org.example.housemanager.dao.CompanyDao;
import org.example.housemanager.dto.CreateCompanyDto;
import org.example.housemanager.entity.Building;
import org.example.housemanager.entity.Company;
import org.example.housemanager.entity.Employee;

import java.util.List;
import java.util.Map;

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

    public static void serveBuilding(Building building, Company company) {
        CompanyDao.serveBuilding(building, company);
    }

    public static void fireEmployee(Employee employee, Company company) {
        CompanyDao.fireEmployee(employee, company);
    }

    public static double calculateTaxPerEmployee(Company company, Employee employee) {
        return CompanyDao.calculateTaxPerEmployee(company, employee);
    }

    public static List<Company> getCompaniesSortedByIncome(boolean order) {
        return CompanyDao.getCompaniesSortedByIncome(order);
    }

    public static List<Employee> getEmployeesByCompanySortedByName(Company company, boolean order) {
        return CompanyDao.getEmployeesByCompanySortedByName(company, order);
    }

    public static List<Employee> getEmployeesByCompanySortedByBuildingsCount(Company company, boolean order) {
        return CompanyDao.getEmployeesByCompanySortedByBuildingsCount(company, order);
    }

    public static void printBuildingsByEmployeeInCompany(Company company) {
        Map<Employee, List<Building>> employeeBuildingMap = CompanyDao.getBuildingsByEmployeesInCompany(company);

        System.out.println("Buildings served by employees in company: " + company.getName());

        for (Map.Entry<Employee, List<Building>> entry : employeeBuildingMap.entrySet()) {
            Employee employee = entry.getKey();
            List<Building> buildings = entry.getValue();

            System.out.println("------------");

            System.out.println("Employee: " + employee.getName());
            System.out.println("Total buildings: " + buildings.size());

            for (Building building : buildings) {
                System.out.println("  - " + building.getAddress());
            }
        }
        System.out.println("------------");
    }

    public static void detailedTaxesInformation(List<Company> companies) {
        CompanyDao.detailedTaxesInformation(companies);
    }

    public static void detailedPaidTaxesInformation(List<Company> companies) {
        CompanyDao.detailedPaidTaxesInformation(companies);
    }
}