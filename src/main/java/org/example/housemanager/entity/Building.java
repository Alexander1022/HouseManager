package org.example.housemanager.entity;

import jakarta.persistence.*;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;

import java.math.BigDecimal;
import java.util.Set;

@Entity
public class Building extends BaseEntity {
    @Column(name = "address", nullable = false)
    private String address;
    @Column(name = "floors_count", nullable = false)
    private int floorsCount;
    @Column(name = "apartments_count", nullable = false)
    private int apartmentsCount;
    @Column(name = "built_area", nullable = false)
    private float builtArea;
    @Column(name = "common_area", nullable = false)
    private float commonArea;

    @PositiveOrZero
    @Column(name = "tax", nullable = false)
    private BigDecimal tax = BigDecimal.ZERO;

    @Valid
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "employee_id")
    private Employee employee;

    @OneToMany(mappedBy = "building", cascade = CascadeType.ALL)
    private Set<Apartment> apartments;

    public Building() {}

    public Building(String address, int floorsCount, int apartmentsCount, float builtArea, float commonArea) {
        this.address = address;
        this.floorsCount = floorsCount;
        this.apartmentsCount = apartmentsCount;
        this.builtArea = builtArea;
        this.commonArea = commonArea;
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

    public @PositiveOrZero BigDecimal getTax() {
        return tax;
    }

    public void setTax(@PositiveOrZero BigDecimal tax) {
        this.tax = tax;
    }

    public @Valid Employee getEmployee() {
        return employee;
    }

    public Set<Apartment> getApartments() {
        return apartments;
    }

    public void setEmployee(@Valid Employee employee) {
        this.employee = employee;
    }

    public void setApartments(Set<Apartment> apartments) {
        this.apartments = apartments;
    }

    @Override
    public String toString() {
        return "Building{" +
                "address='" + address + '\'' +
                ", floorsCount=" + floorsCount +
                ", apartmentsCount=" + apartmentsCount +
                ", builtArea=" + builtArea +
                ", commonArea=" + commonArea +
                '}';
    }
}
