package org.example.housemanager.services;

import jakarta.validation.Valid;
import org.example.housemanager.dao.BuildingDao;
import org.example.housemanager.dto.CreateBuildingDto;
import org.example.housemanager.entity.Apartment;
import org.example.housemanager.entity.Building;
import org.example.housemanager.entity.Pet;
import org.example.housemanager.entity.Resident;

import java.util.List;

public class BuildingService {
    public static void createBuilding(Building building) {
        BuildingDao.createBuilding(building);
    }

    public static void saveBuilding(CreateBuildingDto createBuildingDto) {
        BuildingDao.saveBuildingDto(createBuildingDto);
    }

    public static void updateBuilding(Building building) {
        BuildingDao.updateBuilding(building);
    }

    public static void deleteBuilding(Building building) {
        BuildingDao.deleteBuilding(building);
    }

    public static void deleteBuildingById(long id) {
        BuildingDao.deleteBuildingById(id);
    }

    public static Building getBuildingById(long id) {
        return BuildingDao.getBuildingById(id);
    }

    public static List<Building> getAllBuildings() {
        return BuildingDao.getBuildings();
    }

    public static List<Resident> getResidentsByBuildingSortedByName(Building building, boolean order) {
        return BuildingDao.getResidentsByBuildingSortedByName(building, order);
    }

    public static List<Resident> getResidentsByBuildingSortedByAge(Building building, boolean order) {
        return BuildingDao.getResidentsByBuildingSortedByAge(building, order);
    }

    public static void printApartmentsInBuilding(Building building) {
        List<Apartment> apartments = BuildingDao.getApartmentsInBuilding(building);

        System.out.println("Apartments in building: " + building.getAddress());
        for (Apartment apartment : apartments) {
            System.out.println("------------");
            System.out.println("Apartment ID: " + apartment.getNumber());
            if(!apartment.getResidents().isEmpty()) {
                System.out.println("Residents : ");
                for(Resident resident : apartment.getResidents()) {
                    System.out.println(" (👱🏻‍♂️) " + resident.getName() + " " + resident.getAge());
                }
            }

            if(!apartment.getPets().isEmpty()) {
                System.out.println("Pets : ");
                for(Pet pet : apartment.getPets()) {
                    System.out.println(" (🐶)" + pet.getName());
                }
            }
            System.out.println("(💸) Paid tax: " + apartment.isTaxPaid());
        }
        System.out.println("------------");
    }

    public static void printAllResidentsInBuilding(Building building) {
        List<Resident> residents = BuildingDao.getResidentsInBuilding(building);

        System.out.println("Residents in building: " + building.getAddress());

        for (Resident resident : residents) {
            System.out.println("------------");
            System.out.println("Name: " + resident.getName());
            System.out.println("Age: " + resident.getAge());
            System.out.println("Uses elevator: " + resident.isUsesElevator());
        }
        System.out.println("------------");
    }
}