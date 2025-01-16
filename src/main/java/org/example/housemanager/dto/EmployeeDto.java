package org.example.housemanager.dto;

import org.apache.logging.log4j.core.config.plugins.validation.constraints.NotBlank;

import java.time.LocalDate;

public class EmployeeDto {
    @NotBlank(message = "Name cannot be blank!")
    private String name;

    public EmployeeDto(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "EmployeeDto{" +
                "name='" + name + '\'' +
                '}';
    }
}
