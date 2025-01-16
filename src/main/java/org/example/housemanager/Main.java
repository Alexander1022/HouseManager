package org.example.housemanager;

import org.example.housemanager.configuration.SessionFactoryUtility;
import org.example.housemanager.dto.*;
import org.example.housemanager.dao.*;
import org.example.housemanager.entity.*;
import org.example.housemanager.services.*;

import java.math.BigDecimal;

public class Main {
    public static void main(String[] args) {
        SessionFactoryUtility.getSessionFactory().openSession();

//        CreateBuildingDto buildingDto = new CreateBuildingDto(
//                "Beli Dunav 681",
//                3,
//                5,
//                250.0f,
//                30.0f,
//                BigDecimal.valueOf(10)
//        );
//        BuildingService.saveBuilding(buildingDto);
//
//        CreateApartmentDto apartmentDto1 = new CreateApartmentDto(1, 2, 50.0f, 1);
//        CreateApartmentDto apartmentDto2 = new CreateApartmentDto(2, 2, 50.0f, 1);
//        CreateApartmentDto apartmentDto3 = new CreateApartmentDto(3, 3, 50.0f, 1);
//        CreateApartmentDto apartmentDto4 = new CreateApartmentDto(4, 3, 50.0f, 1);
//        CreateApartmentDto apartmentDto5 = new CreateApartmentDto(5, 3, 50.0f, 1);
//
//        ApartmentService.saveApartment(apartmentDto1);
//        ApartmentService.saveApartment(apartmentDto2);
//        ApartmentService.saveApartment(apartmentDto3);
//        ApartmentService.saveApartment(apartmentDto4);
//        ApartmentService.saveApartment(apartmentDto5);
//
//        CreateResidentDto residentDto1 = new CreateResidentDto("Alexander", 21, true);
//        CreateResidentDto residentDto2 = new CreateResidentDto("Peter", 40, true);
//        CreateResidentDto residentDto3 = new CreateResidentDto("Marie", 31, false);
//
//        ResidentService.saveResident(residentDto1);
//        ResidentService.saveResident(residentDto2);
//        ResidentService.saveResident(residentDto3);
//
//        Resident resident1 = ResidentService.getResidentById(1);
//        Resident resident2 = ResidentService.getResidentById(2);
//        Resident resident3 = ResidentService.getResidentById(3);
//
//        Apartment apartment1 = ApartmentService.getApartmentById(1);
//        Apartment apartment2 = ApartmentService.getApartmentById(2);
//
//        ResidentService.addResidentToApartment(resident1, apartment1);
//        ResidentService.addResidentToApartment(resident2, apartment1);
//        ApartmentService.assignOwnerToApartment(resident3, apartment1);
//
//        CreatePetDto petDto1 = new CreatePetDto("Zigy", false);
//        CreatePetDto petDto2 = new CreatePetDto("Nochi", false);
//        CreatePetDto petDto3 = new CreatePetDto("Marko", true);
//
//        PetService.savePet(petDto1);
//        PetService.savePet(petDto2);
//        PetService.savePet(petDto3);
//
//        Pet pet1 = PetService.getPetById(1);
//        Pet pet2 = PetService.getPetById(2);
//        Pet pet3 = PetService.getPetById(3);
//
//        PetService.addPetToApartment(pet1, apartment1);
//        PetService.addPetToApartment(pet2, apartment1);
//        PetService.addPetToApartment(pet3, apartment2);
//
//
//        CreateEmployeeDto employeeDto1 = new CreateEmployeeDto("Mister Clean");
//        CreateEmployeeDto employeeDto2 = new CreateEmployeeDto("Miss Vanya");
//        CreateEmployeeDto employeeDto3 = new CreateEmployeeDto("Gospodinov");
//
//        EmployeeService.saveEmployee(employeeDto1);
//        EmployeeService.saveEmployee(employeeDto2);
//        EmployeeService.saveEmployee(employeeDto3);
//
//        CreateCompanyDto companyDto1 = new CreateCompanyDto("Finance");
//        CreateCompanyDto companyDto2 = new CreateCompanyDto("Finance");
//
//        CompanyService.saveCompany(companyDto1);
//        CompanyService.saveCompany(companyDto2);

        Employee employee = EmployeeService.getEmployeeById(1);
//        Company company = CompanyService.getCompanyById(2);
//
//        CompanyService.hireEmployee(employee, company);

        Building building = BuildingService.getBuildingById(1);
        EmployeeService.serveBuilding(employee, building);
    }
}