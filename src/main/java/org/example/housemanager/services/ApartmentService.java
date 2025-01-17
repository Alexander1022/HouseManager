package org.example.housemanager.services;

import jakarta.validation.Valid;
import org.example.housemanager.dao.ApartmentDao;
import org.example.housemanager.dto.CreateApartmentDto;
import org.example.housemanager.entity.Apartment;
import org.example.housemanager.entity.Building;
import org.example.housemanager.entity.Resident;

import java.util.List;

public class ApartmentService {
    public static void createApartment(Apartment apartment) {
        ApartmentDao.createApartment(apartment);
    }

    public static void saveApartment(CreateApartmentDto createApartmentDto) {
        ApartmentDao.saveApartmentDto(createApartmentDto);
    }

    public static void updateApartment(Apartment apartment) {
        ApartmentDao.updateApartment(apartment);
    }

    public static void deleteApartment(Apartment apartment) {
        ApartmentDao.deleteApartment(apartment);
    }

    public static void deleteApartmentById(long id) {
        ApartmentDao.deleteApartmentById(id);
    }

    public static Apartment getApartmentById(long id) {
        return ApartmentDao.getApartmentById(id);
    }

    public static List<Apartment> getAllApartmentsInBuilding(long buildingId) {
        return ApartmentDao.getAllApartmentsInBuilding(buildingId);
    }

    public static List<Apartment> getAllApartmentsInBuildingOnFloor(long buildingId, int floorNumber) {
        return ApartmentDao.getAllApartmentsInBuildingOnFloor(buildingId, floorNumber);
    }

    public static void assignOwnerToApartment(Resident resident, Apartment apartment) {
        ApartmentDao.assignOwnerToApartment(resident, apartment);
    }

    public static Resident getApartmentOwner(long id) {
        return ApartmentDao.getApartmentOwner(id);
    }

    public static void payTax(Apartment apartment) {
        ApartmentDao.payTax(apartment);
    }
}
