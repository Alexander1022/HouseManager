package org.example.housemanager;

import org.example.housemanager.configuration.SessionFactoryUtility;
import org.example.housemanager.dto.*;
import org.example.housemanager.dao.*;
import org.example.housemanager.entity.*;
import org.example.housemanager.services.*;
import org.example.housemanager.utils.PaymentRecordUtility;

import java.math.BigDecimal;
import java.util.List;


public class Main {
    public static void main(String[] args) {
//        CreateCompanyDto company1 = new CreateCompanyDto("Residents are Evil");
//        CreateCompanyDto company2 = new CreateCompanyDto("Home Luxury");
//
//        CompanyService.saveCompany(company1);
//        CompanyService.saveCompany(company2);
//
//        CreateBuildingDto building1 = new CreateBuildingDto("Oak Street 123", 12, 48, 2400, 600, BigDecimal.valueOf(15.50));
//        CreateBuildingDto building2 = new CreateBuildingDto("Maple Avenue 45", 6, 24, 1800, 400, BigDecimal.valueOf(12.75));
//        CreateBuildingDto building3 = new CreateBuildingDto("Park View 78", 4, 8, 1200, 250, BigDecimal.valueOf(25.00));
//        CreateBuildingDto building4 = new CreateBuildingDto("River Road 256", 5, 20, 1500, 300, BigDecimal.valueOf(8.50));
//
//        BuildingService.saveBuilding(building1);
//        BuildingService.saveBuilding(building2);
//        BuildingService.saveBuilding(building3);
//        BuildingService.saveBuilding(building4);
//
//        CreateApartmentDto apt101 = new CreateApartmentDto(101, 1, 85, 1);
//        CreateApartmentDto apt102 = new CreateApartmentDto(102, 1, 65, 1);
//        CreateApartmentDto apt301 = new CreateApartmentDto(301, 3, 90, 1);
//        CreateApartmentDto apt402 = new CreateApartmentDto(402, 4, 75, 1);
//        CreateApartmentDto apt601 = new CreateApartmentDto(601, 6, 120, 1);
//
//        CreateApartmentDto apt201 = new CreateApartmentDto(201, 2, 75, 2);
//        CreateApartmentDto apt202 = new CreateApartmentDto(202, 2, 68, 2);
//        CreateApartmentDto apt203 = new CreateApartmentDto(203, 2, 85, 2);
//        CreateApartmentDto apt204 = new CreateApartmentDto(204, 2, 70, 2);
//        CreateApartmentDto apt302 = new CreateApartmentDto(302, 3, 68, 2);
//        CreateApartmentDto apt303 = new CreateApartmentDto(303, 3, 85, 2);
//        CreateApartmentDto apt304 = new CreateApartmentDto(304, 3, 70, 2);
//        CreateApartmentDto apt401 = new CreateApartmentDto(401, 4, 75, 2);
//        CreateApartmentDto apt403 = new CreateApartmentDto(403, 4, 85, 2);
//        CreateApartmentDto apt404 = new CreateApartmentDto(404, 4, 70, 2);
//
//        ApartmentService.saveApartment(apt101);
//        ApartmentService.saveApartment(apt102);
//        ApartmentService.saveApartment(apt301);
//        ApartmentService.saveApartment(apt402);
//        ApartmentService.saveApartment(apt601);
//        ApartmentService.saveApartment(apt201);
//        ApartmentService.saveApartment(apt202);
//        ApartmentService.saveApartment(apt203);
//        ApartmentService.saveApartment(apt204);
//        ApartmentService.saveApartment(apt302);
//        ApartmentService.saveApartment(apt303);
//        ApartmentService.saveApartment(apt304);
//        ApartmentService.saveApartment(apt401);
//        ApartmentService.saveApartment(apt403);
//        ApartmentService.saveApartment(apt404);
//
//        CreateResidentDto resident1 = new CreateResidentDto("Alexander Young", 21, true);
//        CreateResidentDto resident2 = new CreateResidentDto("Maria Garcia", 45, true);
//        CreateResidentDto resident3 = new CreateResidentDto("James Wilson", 68, true);
//        CreateResidentDto resident4 = new CreateResidentDto("Sarah Chen", 34, true);
//        CreateResidentDto resident5 = new CreateResidentDto("Robert Brown", 52, true);
//        CreateResidentDto resident6 = new CreateResidentDto("Emma Davis", 29, false);
//        CreateResidentDto resident7 = new CreateResidentDto("Thomas Anderson", 73, true);
//        CreateResidentDto resident8 = new CreateResidentDto("Sofia Patel", 31, false);
//        CreateResidentDto resident9 = new CreateResidentDto("Michael Scott", 42, true);
//        CreateResidentDto resident10 = new CreateResidentDto("Laura Martinez", 27, false);
//        CreateResidentDto resident11 = new CreateResidentDto("David Kim", 58, true);
//        CreateResidentDto resident12 = new CreateResidentDto("Anna Kowalski", 39, true);
//        CreateResidentDto resident13 = new CreateResidentDto("John Murphy", 65, true);
//        CreateResidentDto resident14 = new CreateResidentDto("Nina Rodriguez", 33, false);
//        CreateResidentDto resident15 = new CreateResidentDto("William Taylor", 48, true);
//
//        ResidentService.saveResident(resident1);
//        ResidentService.saveResident(resident2);
//        ResidentService.saveResident(resident3);
//        ResidentService.saveResident(resident4);
//        ResidentService.saveResident(resident5);
//        ResidentService.saveResident(resident6);
//        ResidentService.saveResident(resident7);
//        ResidentService.saveResident(resident8);
//        ResidentService.saveResident(resident9);
//        ResidentService.saveResident(resident10);
//        ResidentService.saveResident(resident11);
//        ResidentService.saveResident(resident12);
//        ResidentService.saveResident(resident13);
//        ResidentService.saveResident(resident14);
//        ResidentService.saveResident(resident15);
//
//        CreatePetDto pet1 = new CreatePetDto("Ziggy", false);
//        CreatePetDto pet2 = new CreatePetDto("Luna", true);
//        CreatePetDto pet3 = new CreatePetDto("Max", true);
//        CreatePetDto pet4 = new CreatePetDto("Bella", false);
//
//        PetService.savePet(pet1);
//        PetService.savePet(pet2);
//        PetService.savePet(pet3);
//        PetService.savePet(pet4);
//
//        CreateEmployeeDto employee1 = new CreateEmployeeDto("John Smith");
//        CreateEmployeeDto employee2 = new CreateEmployeeDto("Elena Rodriguez");
//        CreateEmployeeDto employee3 = new CreateEmployeeDto("Marcus Wong");
//        CreateEmployeeDto employee4 = new CreateEmployeeDto("Diana Clark");
//        CreateEmployeeDto employee5 = new CreateEmployeeDto("Pavel Kovac");
//
//        EmployeeService.saveEmployee(employee1);
//        EmployeeService.saveEmployee(employee2);
//        EmployeeService.saveEmployee(employee3);
//        EmployeeService.saveEmployee(employee4);
//        EmployeeService.saveEmployee(employee5);

//        Company company1 = CompanyService.getCompanyById(1);
//        Company company2 = CompanyService.getCompanyById(2);
//
//        Employee employee1 = EmployeeService.getEmployeeById(1);
//        Employee employee2 = EmployeeService.getEmployeeById(2);
//        Employee employee3 = EmployeeService.getEmployeeById(3);
//        Employee employee4 = EmployeeService.getEmployeeById(4);
//        Employee employee5 = EmployeeService.getEmployeeById(5);
//
//        CompanyService.hireEmployee(employee1, company1);
//        CompanyService.hireEmployee(employee2, company1);
//
//        CompanyService.hireEmployee(employee3, company2);
//        CompanyService.hireEmployee(employee4, company2);
//        CompanyService.hireEmployee(employee5, company2);


//        ResidentService.addResidentToApartment(ResidentService.getResidentById(1), ApartmentService.getApartmentById(1));
//        ResidentService.addResidentToApartment(ResidentService.getResidentById(2), ApartmentService.getApartmentById(1));
//        ResidentService.addResidentToApartment(ResidentService.getResidentById(3), ApartmentService.getApartmentById(1));
//        ResidentService.addResidentToApartment(ResidentService.getResidentById(4), ApartmentService.getApartmentById(1));
//        ResidentService.addResidentToApartment(ResidentService.getResidentById(5), ApartmentService.getApartmentById(1));
//        ApartmentService.assignOwnerToApartment(ResidentService.getResidentById(1), ApartmentService.getApartmentById(1));
//        ResidentService.addResidentToApartment(ResidentService.getResidentById(6), ApartmentService.getApartmentById(2));
//        ResidentService.addResidentToApartment(ResidentService.getResidentById(7), ApartmentService.getApartmentById(2));
//        ResidentService.addResidentToApartment(ResidentService.getResidentById(8), ApartmentService.getApartmentById(2));
//        ResidentService.addResidentToApartment(ResidentService.getResidentById(9), ApartmentService.getApartmentById(2));
//        ResidentService.addResidentToApartment(ResidentService.getResidentById(10), ApartmentService.getApartmentById(2));
//        ApartmentService.assignOwnerToApartment(ResidentService.getResidentById(6), ApartmentService.getApartmentById(2));
//        ResidentService.addResidentToApartment(ResidentService.getResidentById(11), ApartmentService.getApartmentById(3));
//        ResidentService.addResidentToApartment(ResidentService.getResidentById(12), ApartmentService.getApartmentById(3));
//        ResidentService.addResidentToApartment(ResidentService.getResidentById(13), ApartmentService.getApartmentById(3));
//        ResidentService.addResidentToApartment(ResidentService.getResidentById(14), ApartmentService.getApartmentById(3));
//        ResidentService.addResidentToApartment(ResidentService.getResidentById(15), ApartmentService.getApartmentById(3));
//        ApartmentService.assignOwnerToApartment(ResidentService.getResidentById(11), ApartmentService.getApartmentById(3));
//
//        PetService.addPetToApartment(PetService.getPetById(1), ApartmentService.getApartmentById(1));
//        PetService.addPetToApartment(PetService.getPetById(2), ApartmentService.getApartmentById(1));
//        PetService.addPetToApartment(PetService.getPetById(3), ApartmentService.getApartmentById(2));
//        PetService.addPetToApartment(PetService.getPetById(4), ApartmentService.getApartmentById(2));

//        CompanyService.serveBuilding(BuildingService.getBuildingById(1), CompanyService.getCompanyById(1));
//        CompanyService.serveBuilding(BuildingService.getBuildingById(2), CompanyService.getCompanyById(1));
//        CompanyService.serveBuilding(BuildingService.getBuildingById(3), CompanyService.getCompanyById(2));
//        CompanyService.serveBuilding(BuildingService.getBuildingById(4), CompanyService.getCompanyById(2));

//        ApartmentService.payTax(ApartmentService.getApartmentById(1));
//        ApartmentService.payTax(ApartmentService.getApartmentById(3));
//
//        CompanyService.calculateTaxPerEmployee(CompanyService.getCompanyById(1), EmployeeService.getEmployeeById(1));
//        CompanyService.calculateTaxPerEmployee(CompanyService.getCompanyById(1), EmployeeService.getEmployeeById(2));
//        CompanyService.calculateTaxPerEmployee(CompanyService.getCompanyById(2), EmployeeService.getEmployeeById(3));
//        CompanyService.calculateTaxPerEmployee(CompanyService.getCompanyById(2), EmployeeService.getEmployeeById(5));

        List<Company> companies = CompanyService.getAllCompanies();

        CompanyService.detailedPaidTaxesInformation(companies);
        PaymentRecordUtility.writePaidTaxesToFile("paid_taxes.txt", companies);
    }
}