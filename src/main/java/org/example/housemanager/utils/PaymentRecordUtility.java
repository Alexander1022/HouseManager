package org.example.housemanager.utils;

import org.example.housemanager.configuration.SessionFactoryUtility;
import org.example.housemanager.entity.Apartment;
import org.example.housemanager.entity.Building;
import org.example.housemanager.entity.Company;
import org.example.housemanager.entity.Employee;
import org.hibernate.Session;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class PaymentRecordUtility {
    public static void writePaidTaxesToFile(String filePath, List<Company> companies) {
        if (companies == null || companies.isEmpty()) {
            throw new IllegalArgumentException("The company list cannot be null or empty.");
        }

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath, false))) {
            writer.write("Company, Employee, Building, Apartment, Amount, Payment Date");
            writer.newLine();

            try (Session session = SessionFactoryUtility.getSessionFactory().openSession()) {
                for (Company company : companies) {
                    List<Employee> employees = session.createQuery(
                                    "SELECT e FROM Employee e WHERE e.company = :company", Employee.class)
                            .setParameter("company", company)
                            .getResultList();

                    for (Employee employee : employees) {
                        List<Building> buildings = session.createQuery(
                                        "SELECT b FROM Building b WHERE b.employee = :employee", Building.class)
                                .setParameter("employee", employee)
                                .getResultList();

                        for (Building building : buildings) {
                            List<Apartment> apartments = session.createQuery(
                                            "SELECT a FROM Apartment a WHERE a.building = :building", Apartment.class)
                                    .setParameter("building", building)
                                    .getResultList();

                            for (Apartment apartment : apartments) {
                                if (apartment.getMonthlyTax() > 0 && apartment.isTaxPaid()) {
                                    String paymentDate = new SimpleDateFormat("yyyy-MM-dd").format(new Date());

                                    writer.write(company.getName() + ", "
                                            + employee.getName() + ", "
                                            + building.getAddress() + ", "
                                            + apartment.getNumber() + ", "
                                            + apartment.getMonthlyTax() + ", "
                                            + apartment.getPaidTaxDate() + ", "
                                            + paymentDate);
                                    writer.newLine();
                                }
                            }
                        }
                    }
                }
                System.out.println("Data has been written to " + filePath);

            } catch (Exception e) {
                e.printStackTrace();
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
