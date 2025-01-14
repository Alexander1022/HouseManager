package org.example.housemanager.dao;

import jakarta.validation.Valid;
import org.example.housemanager.configuration.SessionFactoryUtility;
import org.example.housemanager.dto.BuildingDto;
import org.example.housemanager.dto.CreateApartmentDto;
import org.example.housemanager.dto.CreateBuildingDto;
import org.example.housemanager.entity.Building;
import org.example.housemanager.entity.Company;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class BuildingDao {
    public static void createBuilding(@Valid Building building) {
        try(Session session = SessionFactoryUtility.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            session.save(building);
            transaction.commit();
        }
    }

    public static void saveBuildingDto(CreateBuildingDto createBuildingDto) {
        try(Session session = SessionFactoryUtility.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            Building building = new Building();
            building.setAddress(createBuildingDto.getAddress());
            building.setFloorsCount(createBuildingDto.getFloorsCount());
            building.setApartmentsCount(createBuildingDto.getApartmentsCount());
            building.setBuiltArea(createBuildingDto.getBuiltArea());
            building.setCommonArea(createBuildingDto.getCommonArea());
            building.setTax(createBuildingDto.getTax());
            session.save(building);
            transaction.commit();
        }
    }

    public static void updateBuilding(Building building) {
        try (Session session = SessionFactoryUtility.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            session.saveOrUpdate(building);
            transaction.commit();
        }
    }

    public static void deleteBuilding(Building building) {
        try (Session session = SessionFactoryUtility.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            session.delete(building);
            transaction.commit();
        }
    }

    public static void deleteBuildingById(long id) {
        try(Session session = SessionFactoryUtility.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            Building building = session.get(Building.class, id);

            if(building == null) {
                System.out.println("Building with ID " + id + " does not exist.");
            } else {
                session.delete(building);
                transaction.commit();
                System.out.println("Building with ID " + id + " was successfully deleted.");
            }
        }
    }

    public static Building getBuildingById(long id) {
        Building building;
        try(Session session = SessionFactoryUtility.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            building = session.get(Building.class, id);
            transaction.commit();
        }
        return building;
    }

    public static List<Building> getBuildings() {
        List<Building> buildings;
        try(Session session = SessionFactoryUtility.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            buildings = session
                    .createQuery("Select b from Building b", Building.class)
                    .getResultList();
            transaction.commit();
        }

        return buildings;
    }
}
