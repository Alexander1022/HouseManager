package org.example.housemanager.dto;

public class CreateApartmentDto {
    private int number;
    private int floor;
    private float area;
    private long buildingId;

    public CreateApartmentDto(int number, int floor, float area, long buildingId) {
        this.number = number;
        this.floor = floor;
        this.area = area;
        this.buildingId = buildingId;
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

    public long getBuildingId() {
        return buildingId;
    }

    public void setBuildingId(long buildingId) {
        this.buildingId = buildingId;
    }

    @Override
    public String toString() {
        return "CreateApartmentDto{" +
                "number=" + number +
                ", floor=" + floor +
                ", area=" + area +
                ", buildingId=" + buildingId +
                '}';
    }
}
