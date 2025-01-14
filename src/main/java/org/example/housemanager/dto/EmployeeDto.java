package org.example.housemanager.dto;

import org.apache.logging.log4j.core.config.plugins.validation.constraints.NotBlank;

import java.time.LocalDate;

public class EmployeeDto {
    @NotBlank(message = "Name cannot be blank!")
    private String name;

    @NotBlank(message = "Hire date cannot be blank!")
    private LocalDate hireDate = LocalDate.now();

    public EmployeeDto(String name, LocalDate hireDate) {
        this.name = name;
        this.hireDate = hireDate;
    }

    @Override
    public String toString() {
        return "EmployeeDto{" +
                "name='" + name + '\'' +
                ", hireDate=" + hireDate +
                '}';
    }
}
