package org.example.housemanager.dto;

import org.apache.logging.log4j.core.config.plugins.validation.constraints.NotBlank;
import org.example.housemanager.entity.Building;

public class ApartmentDto {
    @NotBlank(message = "Number cannot be blank!")
    private int number;

    @NotBlank(message = "Floors count cannot be blank!")
    private int floor;

    @NotBlank(message = "Area cannot be blank!")
    private float area;

    public ApartmentDto(int number, int floor, float area, BuildingDto buildingDto) {
        this.number = number;
        this.floor = floor;
        this.area = area;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public int getFloor() {
        return floor;
    }

    public void setFloor(int floor) {
        this.floor = floor;
    }

    public float getArea() {
        return area;
    }

    public void setArea(float area) {
        this.area = area;
    }

    @Override
    public String toString() {
        return "ApartmentDto{" +
                "number=" + number +
                ", floor=" + floor +
                ", area=" + area +
                '}';
    }
}
