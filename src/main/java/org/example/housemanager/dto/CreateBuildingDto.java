package org.example.housemanager.dto;

import java.math.BigDecimal;

public class CreateBuildingDto {
    private String address;
    private int floorsCount;
    private int apartmentsCount;
    private float builtArea;
    private float commonArea;
    private BigDecimal tax;

    public CreateBuildingDto(String address, int floorsCount, int apartmentsCount, float builtArea, float commonArea, BigDecimal tax) {
        this.address = address;
        this.floorsCount = floorsCount;
        this.apartmentsCount = apartmentsCount;
        this.builtArea = builtArea;
        this.commonArea = commonArea;
        this.tax = tax;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getFloorsCount() {
        return floorsCount;
    }

    public void setFloorsCount(int floorsCount) {
        this.floorsCount = floorsCount;
    }

    public int getApartmentsCount() {
        return apartmentsCount;
    }

    public void setApartmentsCount(int apartmentsCount) {
        this.apartmentsCount = apartmentsCount;
    }

    public float getBuiltArea() {
        return builtArea;
    }

    public void setBuiltArea(float builtArea) {
        this.builtArea = builtArea;
    }

    public float getCommonArea() {
        return commonArea;
    }

    public void setCommonArea(float commonArea) {
        this.commonArea = commonArea;
    }

    public BigDecimal getTax() {
        return tax;
    }

    public void setTax(BigDecimal tax) {
        this.tax = tax;
    }

    @Override
    public String toString() {
        return "CreateBuildingDto{" +
                "address='" + address + '\'' +
                ", floorsCount=" + floorsCount +
                ", apartmentsCount=" + apartmentsCount +
                ", builtArea=" + builtArea +
                ", commonArea=" + commonArea +
                ", tax=" + tax +
                '}';
    }
}
