package org.example.housemanager.dao;

import jakarta.validation.Valid;
import org.example.housemanager.configuration.SessionFactoryUtility;
import org.example.housemanager.dto.CreateApartmentDto;
import org.example.housemanager.dto.CreatePetDto;
import org.example.housemanager.entity.Apartment;
import org.example.housemanager.entity.Pet;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class PetDao {
    public static void createPet(@Valid Pet pet) {
        try(Session session = SessionFactoryUtility.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            session.save(pet);
            transaction.commit();
        }
    }

    public static void savePetDto(CreatePetDto createPetDto) {
        try(Session session = SessionFactoryUtility.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();

            Pet pet = new Pet();
            pet.setName(createPetDto.getName());
            pet.setUsesCommonArea(createPetDto.isUsesCommonArea());

            session.save(pet);
            transaction.commit();
        }
    }

    public static void updatePet(Pet pet) {
        try(Session session = SessionFactoryUtility.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            session.saveOrUpdate(pet);
            transaction.commit();
        }
    }

    public static Pet getPetById(long id) {
        Pet pet;
        try(Session session = SessionFactoryUtility.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            pet = session.get(Pet.class, id);
            transaction.commit();
        }

        return pet;
    }

    public static void deletePet(Pet pet) {
        try(Session session = SessionFactoryUtility.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            session.delete(pet);
            transaction.commit();
        }
    }

    public static void deletePetById(long id) {
        try(Session session = SessionFactoryUtility.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            Pet pet = session.get(Pet.class, id);

            if(pet == null) {
                System.out.println("Pet with ID " + id + " does not exist");
            } else {
                session.delete(pet);
                transaction.commit();
                System.out.println("Pet with ID " + id + " was successfully deleted.");
            }
        }
    }

    public static List<Pet> getAllPetsInApartment(long apartmentId) {
        List<Pet> pets;
        try(Session session = SessionFactoryUtility.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            pets = session
                    .createQuery("Select p from Pets where p.apartment.id = :apartmentId", Pet.class)
                    .setParameter("apartmentId", apartmentId)
                    .getResultList();
            transaction.commit();
        }
        return pets;
    }

    public static void addPetToApartment(Pet pet, Apartment apartment) {
        if(pet == null) {
            throw new IllegalArgumentException("Pet does not exist.");
        }

        if (apartment == null) {
            throw new IllegalArgumentException("Apartment does not exist.");
        }

        try(Session session = SessionFactoryUtility.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();

            pet.setApartment(apartment);
            apartment.getPets().add(pet);

            session.saveOrUpdate(pet);
            session.saveOrUpdate(apartment);

            transaction.commit();

            System.out.println(pet.getName() + " was added to apartment #" + apartment.getNumber());
        }
    }
}
