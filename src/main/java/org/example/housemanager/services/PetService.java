package org.example.housemanager.services;

import jakarta.validation.Valid;
import org.example.housemanager.dao.EmployeeDao;
import org.example.housemanager.dao.PetDao;
import org.example.housemanager.dto.CreateApartmentDto;
import org.example.housemanager.dto.CreatePetDto;
import org.example.housemanager.entity.Apartment;
import org.example.housemanager.entity.Pet;

import java.util.List;

public class PetService {
    public static void createPet(Pet pet) {
        PetDao.createPet(pet);
    }

    public static void savePet(CreatePetDto createPetDto) {
        PetDao.savePetDto(createPetDto);
    }

    public static void updatePet(Pet pet) {
        PetDao.updatePet(pet);
    }

    public static Pet getPetById(long id) {
        return PetDao.getPetById(id);
    }

    public static void deletePet(Pet pet) {
        PetDao.deletePet(pet);
    }

    public static void deletePetById(long id) {
        PetDao.deletePetById(id);
    }

    public static List<Pet> getAllPetsInApartment(long apartmentId) {
        return PetDao.getAllPetsInApartment(apartmentId);
    }

    public static void addPetToApartment(Pet pet, Apartment apartment) {
        PetDao.addPetToApartment(pet, apartment);
    }
}
