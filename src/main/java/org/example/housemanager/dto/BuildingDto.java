package org.example.housemanager.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;
import org.apache.logging.log4j.core.config.plugins.validation.constraints.NotBlank;
import org.example.housemanager.entity.Employee;

import java.math.BigDecimal;

public class BuildingDto {
    @NotBlank(message = "Address cannot be blank!")
    private String address;

    @Positive
    @NotBlank(message = "Floors count cannot be blank!")
    private float floorsCount;

    @Positive
    @NotBlank(message = "Apartments count cannot be blank!")
    private int apartmentsCount;

    @Positive
    @NotBlank(message = "Built area cannot be blank!")
    private float builtArea;

    @Positive
    @NotBlank(message = "Common area cannot be blank!")
    private float commonArea;

    @Valid
    @NotBlank(message = "Tax cannot be blank!")
    private BigDecimal tax;

    public BuildingDto(String address, float floorsCount, int apartmentsCount, float builtArea, float commonArea) {
        this.address = address;
        this.floorsCount = floorsCount;
        this.apartmentsCount = apartmentsCount;
        this.builtArea = builtArea;
        this.commonArea = commonArea;
        this.tax = BigDecimal.ZERO;
    }

    public BuildingDto(String address, float floorsCount, int apartmentsCount, float builtArea, float commonArea, BigDecimal tax) {
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

    @Positive
    public float getFloorsCount() {
        return floorsCount;
    }

    public void setFloorsCount(@Positive float floorsCount) {
        this.floorsCount = floorsCount;
    }

    @Positive
    public int getApartmentsCount() {
        return apartmentsCount;
    }

    public void setApartmentsCount(@Positive int apartmentsCount) {
        this.apartmentsCount = apartmentsCount;
    }

    @Positive
    public float getBuiltArea() {
        return builtArea;
    }

    public void setBuiltArea(@Positive float builtArea) {
        this.builtArea = builtArea;
    }

    @Positive
    public float getCommonArea() {
        return commonArea;
    }

    public void setCommonArea(@Positive float commonArea) {
        this.commonArea = commonArea;
    }

    public @Valid BigDecimal getTax() {
        return tax;
    }

    public void setTax(@Valid BigDecimal tax) {
        this.tax = tax;
    }

    @Override
    public String toString() {
        return "BuildingDto{" +
                "address='" + address + '\'' +
                ", floorsCount=" + floorsCount +
                ", apartmentsCount=" + apartmentsCount +
                ", builtArea=" + builtArea +
                ", commonArea=" + commonArea +
                ", tax=" + tax +
                '}';
    }
}