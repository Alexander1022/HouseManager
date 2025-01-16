package org.example.housemanager.dto;

public class CreateEmployeeDto {
    private String name;

    public CreateEmployeeDto(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "CreateEmployeeDto{" +
                "name='" + name + '\'' +
                '}';
    }
}
