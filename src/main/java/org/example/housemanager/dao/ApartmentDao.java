package org.example.housemanager.dao;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;
import org.example.housemanager.configuration.SessionFactoryUtility;
import org.example.housemanager.dto.CreateApartmentDto;
import org.example.housemanager.dto.CreateResidentDto;
import org.example.housemanager.entity.Apartment;
import org.example.housemanager.entity.Building;
import org.example.housemanager.entity.Resident;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class ApartmentDao {
    public static void createApartment(@Valid Apartment apartment) {
        try (Session session = SessionFactoryUtility.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            session.save(apartment);
            transaction.commit();
        }
    }

    public static void saveApartmentDto(CreateApartmentDto createApartmentDto) {
        try (Session session = SessionFactoryUtility.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            Building building = BuildingDao.getBuildingById(createApartmentDto.getBuildingId());

            if (building == null) {
                throw new IllegalArgumentException("No building with this ID found.");
            }

            Apartment apartment = new Apartment();
            apartment.setNumber(createApartmentDto.getNumber());
            apartment.setFloor(createApartmentDto.getFloor());
            apartment.setArea(createApartmentDto.getArea());
            apartment.setBuilding(building);

            session.save(apartment);
            transaction.commit();
        }
    }

    public static void updateApartment(Apartment apartment) {
        try (Session session = SessionFactoryUtility.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            session.saveOrUpdate(apartment);
            transaction.commit();
        }
    }

    public static Apartment getApartmentById(long id) {
        Apartment apartment;
        try (Session session = SessionFactoryUtility.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            apartment = session.get(Apartment.class, id);
            transaction.commit();
        }

        return apartment;
    }

    public static void deleteApartment(Apartment apartment) {
        try (Session session = SessionFactoryUtility.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            session.delete(apartment);
            transaction.commit();
        }
    }

    public static void deleteApartmentById(long id) {
        try (Session session = SessionFactoryUtility.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            Apartment apartment = session.get(Apartment.class, id);

            if (apartment == null) {
                System.out.println("Apartment with ID " + id + " does not exist");
            } else {
                session.delete(apartment);
                transaction.commit();
                System.out.println("Apartment with ID " + id + "was successfully deleted.");
            }
        }
    }

    public static List<Apartment> getAllApartmentsInBuilding(long buildingId) {
        List<Apartment> apartments;
        try (Session session = SessionFactoryUtility.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            apartments = session
                    .createQuery("Select a from Apartment where a.building.id = :buildingId", Apartment.class)
                    .setParameter("buildingId", buildingId)
                    .getResultList();
            transaction.commit();
        }

        return apartments;
    }

    public static List<Apartment> getAllApartmentsInBuildingOnFloor(long buildingId, @Positive int floorNumber) {
        List<Apartment> apartments;

        try (Session session = SessionFactoryUtility.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            apartments = session
                    .createQuery("Select a from Apartment where a.building.id = :buildingId AND a.floor = :floorNumber", Apartment.class)
                    .setParameter("buildingId", buildingId)
                    .setParameter("floorNumber", floorNumber)
                    .getResultList();
            transaction.commit();
        }
        return apartments;
    }

    public static void assignOwnerToApartment(Resident resident, Apartment apartment) {
        if (apartment == null) {
            throw new IllegalArgumentException("Apartment does not exist.");
        }
        if (resident == null) {
            throw new IllegalArgumentException("Resident does not exist.");
        }

        try (Session session = SessionFactoryUtility.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            apartment.setOwner(resident);

            // Може да апартаметът да е под наем и реално да не живее собственикът в него
            // resident.setApartment(apartment);
            // session.saveOrUpdate(resident);

            session.saveOrUpdate(apartment);
            transaction.commit();

            System.out.println(resident.getName() + " is now the owner of apartment #" + apartment.getNumber());
        }
    }

    public static Resident getApartmentOwner(long id) {
        try (Session session = SessionFactoryUtility.getSessionFactory().openSession()) {
            Apartment apartment = session.get(Apartment.class, id);
            return apartment != null ? apartment.getOwner() : null;
        }
    }
}