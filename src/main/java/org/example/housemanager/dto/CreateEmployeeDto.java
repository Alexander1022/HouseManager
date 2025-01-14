package org.example.housemanager.dto;

import java.time.LocalDate;

public class CreateEmployeeDto {
    private String name;
    private LocalDate hireDate;

    public CreateEmployeeDto(String name, LocalDate hireDate) {
        this.name = name;
        this.hireDate = hireDate;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getHireDate() {
        return hireDate;
    }

    public void setHireDate(LocalDate hireDate) {
        this.hireDate = hireDate;
    }

    @Override
    public String toString() {
        return "CreateEmployeeDto{" +
                "name='" + name + '\'' +
                ", hireDate=" + hireDate +
                '}';
    }
}
