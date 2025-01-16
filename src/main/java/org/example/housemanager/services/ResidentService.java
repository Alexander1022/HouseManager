package org.example.housemanager.services;

import jakarta.validation.Valid;
import org.example.housemanager.dao.ResidentDao;
import org.example.housemanager.dto.CreateApartmentDto;
import org.example.housemanager.dto.CreateResidentDto;
import org.example.housemanager.entity.Apartment;
import org.example.housemanager.entity.Resident;

public class ResidentService {
    public static void createResident(Resident resident) {
        ResidentDao.createResident(resident);
    }

    public static void saveResident(CreateResidentDto createResidentDto) {
        ResidentDao.saveResidentDto(createResidentDto);
    }

    public static void updateResident(Resident resident) {
        ResidentDao.updateResident(resident);
    }

    public static void deleteResident(Resident resident) {
        ResidentDao.deleteResident(resident);
    }

    public static void deleteResidentById(long id) {
        ResidentDao.deleteResidentById(id);
    }

    public static Resident getResidentById(long id) {
        return ResidentDao.getResidentById(id);
    }

    public static void addResidentToApartment(Resident resident, Apartment apartment) {
        ResidentDao.addResidentToApartment(resident, apartment);
    }
}
