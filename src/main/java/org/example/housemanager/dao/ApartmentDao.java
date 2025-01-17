package org.example.housemanager.dao;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;
import org.example.housemanager.configuration.SessionFactoryUtility;
import org.example.housemanager.dto.CreateApartmentDto;
import org.example.housemanager.dto.CreateResidentDto;
import org.example.housemanager.entity.*;
import org.example.housemanager.services.ApartmentService;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.math.BigDecimal;
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
            resident = session.merge(resident);
            apartment = session.merge(apartment);

            apartment.setOwner(resident);
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

    public static double calculateTax(Apartment apartment, Building building) {
        if(apartment == null) {
            throw new IllegalArgumentException("Apartment does not exist.");
        }

        if(building == null) {
            throw new IllegalArgumentException("Building does not exist.");
        }

        double tax = 0.0f;
        double BASE_TAX_PER_SQUARE_METER = 2.0;
        double PET_TAX = 10.0;

        try (Session session = SessionFactoryUtility.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();

            apartment = session.merge(apartment);
            building = session.merge(building);

            if(apartment.getResidents().isEmpty()) {
                return tax;
            }

            double ADDITIONAL_TAX_PER_RESIDENT = building.getTax().floatValue();

            tax += apartment.getArea() * BASE_TAX_PER_SQUARE_METER;

            for (Resident resident : apartment.getResidents()) {
                if (resident.getAge() > 7 && resident.isUsesElevator()) {
                    tax += ADDITIONAL_TAX_PER_RESIDENT;
                }
            }

            for(Pet pet : apartment.getPets()) {
                if(pet.isUsesCommonArea()) {
                    tax += PET_TAX;
                }
            }
        }
        return tax;
    }

    public static Company findManagingCompany(Apartment apartment) {
        if (apartment == null) {
            throw new IllegalArgumentException("Apartment does not exist.");
        }

        Company managingCompany;

        try (Session session = SessionFactoryUtility.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            apartment = session.merge(apartment);

            Building building = apartment.getBuilding();
            if (building == null) {
                throw new IllegalStateException("No building found for the given apartment.");
            }

            Employee manager = building.getEmployee();
            if (manager == null) {
                throw new IllegalStateException("No manager found for the building.");
            }

            managingCompany = manager.getCompany();
            if (managingCompany == null) {
                throw new IllegalStateException("No company managing the building.");
            }
        }

        return managingCompany;
    }


    public static void payTax(Apartment apartment) {
        if (apartment == null) {
            throw new IllegalArgumentException("Apartment does not exist.");
        }

        if(apartment.isTaxPaid()) {
            throw new IllegalStateException("The tax is already paid.");
        }

        try (Session session = SessionFactoryUtility.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();

            apartment = session.merge(apartment);
            Building building = apartment.getBuilding();

            if (building == null) {
                throw new IllegalStateException("Building for the apartment does not exist.");
            }

            Company managingCompany = findManagingCompany(apartment);

            if (managingCompany == null) {
                throw new IllegalStateException("Managing company for the building does not exist.");
            }

            if (apartment.isTaxPaid()) {
                throw new IllegalStateException("Tax for this apartment has already been paid.");
            }

            apartment.setTaxPaid(true);

            double tax = apartment.getMonthlyTax();
            BigDecimal newIncome = managingCompany.getIncome().add(BigDecimal.valueOf(tax));
            managingCompany.setIncome(newIncome);

            session.saveOrUpdate(apartment);
            session.saveOrUpdate(managingCompany);

            transaction.commit();

            System.out.println("Tax of " + tax + " has been paid for Apartment " + apartment.getNumber() + ".");
            System.out.println("Company " + managingCompany.getName() + " now has a total income of " + newIncome + ".");
        }
    }
}