package org.example.housemanager.dao;

import jakarta.validation.Valid;
import org.example.housemanager.configuration.SessionFactoryUtility;
import org.example.housemanager.dto.CreateApartmentDto;
import org.example.housemanager.dto.CreateResidentDto;
import org.example.housemanager.entity.Apartment;
import org.example.housemanager.entity.Resident;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class ResidentDao {
    public static void createResident(@Valid Resident resident) {
        try(Session session = SessionFactoryUtility.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            session.save(resident);
            transaction.commit();
        }
    }

    public static void saveResidentDto(CreateResidentDto createResidentDto) {
        try(Session session = SessionFactoryUtility.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            Resident resident = new Resident();
            resident.setName(createResidentDto.getName());
            resident.setAge(createResidentDto.getAge());
            resident.setUsesElevator(createResidentDto.isUseElevator());
            session.save(resident);
            transaction.commit();
        }
    }

    public static void updateResident(Resident resident) {
        try(Session session = SessionFactoryUtility.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            session.saveOrUpdate(resident);
            transaction.commit();
        }
    }

    public static void deleteResident(Resident resident) {
        try(Session session = SessionFactoryUtility.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            session.delete(resident);
            transaction.commit();
        }
    }

    public static void deleteResidentById(long id) {
        try(Session session = SessionFactoryUtility.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            Resident resident = session.get(Resident.class, id);

            if(resident == null) {
                System.out.println("Resident with ID " + id + " does not exist.");
            } else {
                session.delete(resident);
                transaction.commit();
                System.out.println("Resident with ID " + id + " was successfully deleted.");
            }
        }
    }

    public static Resident getResidentById(long id) {
        Resident resident;
        try(Session session = SessionFactoryUtility.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            resident = session.get(Resident.class, id);
            transaction.commit();
        }
        return resident;
    }

    public static void addResidentToApartment(Resident resident, Apartment apartment) {
        if (apartment == null) {
            throw new IllegalArgumentException("Apartment does not exist.");
        }
        if (resident == null) {
            throw new IllegalArgumentException("Resident does not exist.");
        }

        try(Session session = SessionFactoryUtility.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();

            resident = session.merge(resident);
            apartment = session.merge(apartment);

            resident.setApartment(apartment);
            apartment.getResidents().add(resident);

            session.saveOrUpdate(resident);
            session.saveOrUpdate(apartment);
            transaction.commit();
            System.out.println("Resident " + resident.getName() + " has been added to apartment #" + apartment.getNumber());
        }
    }
}
