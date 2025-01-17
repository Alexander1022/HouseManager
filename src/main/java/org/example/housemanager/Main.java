package org.example.housemanager;

import org.example.housemanager.configuration.SessionFactoryUtility;
import org.example.housemanager.dto.*;
import org.example.housemanager.dao.*;
import org.example.housemanager.entity.*;
import org.example.housemanager.services.*;

import java.math.BigDecimal;


public class Main {
    public static void main(String[] args) {
        Apartment apartment = ApartmentService.getApartmentById(1);
        Resident resident = ResidentService.getResidentById(1);

        ResidentService.addResidentToApartment(resident, apartment);

        ApartmentService.payTax(apartment);
    }
}