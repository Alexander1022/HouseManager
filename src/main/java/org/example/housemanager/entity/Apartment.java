package org.example.housemanager.entity;

import jakarta.persistence.*;
import jakarta.validation.Valid;

import java.time.LocalDate;
import java.util.Set;

@Entity
public class Apartment extends BaseEntity {
    @Column(name = "number", nullable = false)
    private int number;
    @Column(name = "floor", nullable = false)
    private int floor;
    @Column(name = "area", nullable = false)
    private float area;

    @Column(name = "monthly_tax", nullable = false)
    private double monthlyTax = 0.0;

    @Column(name = "is_tax_paid", nullable = false)
    private boolean isTaxPaid = false;

    @Column(name = "paid_tax_date", nullable = false)
    private LocalDate paidTaxDate = LocalDate.of(1980, 1, 1);

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "owner_id", referencedColumnName = "id", unique = true)
    private Resident owner;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "building_id", nullable = false)
    private Building building;

    @OneToMany(mappedBy = "apartment", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Set<Pet> pets;

    @OneToMany(mappedBy = "apartment", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Set<Resident> residents;

    public Apartment() {}

    public Apartment(int number, int floor, float area, Resident owner, Building building) {
        this.number = number;
        this.floor = floor;
        this.area = area;
        this.owner = owner;
        this.building = building;
    }

    public Apartment(long id, int number, int floor, float area, Resident owner, Building building) {
        super(id);
        this.number = number;
        this.floor = floor;
        this.area = area;
        this.owner = owner;
        this.building = building;
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

    public Resident getOwner() {
        return owner;
    }

    public void setOwner(Resident owner) {
        this.owner = owner;
    }

    public @Valid Building getBuilding() {
        return building;
    }

    public void setBuilding(@Valid Building building) {
        this.building = building;
    }

    public Set<Pet> getPets() {
        return pets;
    }

    public void setPets(Set<Pet> pets) {
        this.pets = pets;
    }

    public Set<Resident> getResidents() {
        return residents;
    }

    public void setResidents(Set<Resident> residents) {
        this.residents = residents;
    }

    public double getMonthlyTax() {
        return monthlyTax;
    }

    public void setMonthlyTax(double monthlyTax) {
        this.monthlyTax = monthlyTax;
    }

    public boolean isTaxPaid() {
        return isTaxPaid;
    }

    public void setTaxPaid(boolean taxPaid) {
        isTaxPaid = taxPaid;
    }

    public LocalDate getPaidTaxDate() {
        return paidTaxDate;
    }

    public void setPaidTaxDate(LocalDate paidTaxDate) {
        this.paidTaxDate = paidTaxDate;
    }

    @Override
    public String toString() {
        return "Apartment{" +
                "number=" + number +
                ", floor=" + floor +
                ", area=" + area +
                ", monthlyTax=" + monthlyTax +
                ", isTaxPaid=" + isTaxPaid +
                ", paidTaxDate=" + paidTaxDate +
                '}';
    }
}
