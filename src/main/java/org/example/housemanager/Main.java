package org.example.housemanager;

import org.example.housemanager.configuration.SessionFactoryUtility;
import org.example.housemanager.dto.*;
import org.example.housemanager.dao.*;
import org.example.housemanager.entity.Building;
import org.example.housemanager.entity.Company;
import org.example.housemanager.services.*;

public class Main {
    public static void main(String[] args) {
        SessionFactoryUtility.getSessionFactory().openSession();
        Building building = new Building("Beli Dunav", 3, 6, 120.0f, 15.0f);
        BuildingService.createBuilding(building);

        CreateApartmentDto createApartmentDto = new CreateApartmentDto(1, 1, 50.0f, 1);
        ApartmentService.saveApartment(createApartmentDto);
    }
}