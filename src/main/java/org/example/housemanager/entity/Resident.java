package org.example.housemanager.entity;

import jakarta.persistence.*;
import jakarta.validation.Valid;

@Entity
public class Resident extends BaseEntity {
    @Column(name="name", nullable = false)
    private String name;

    @Column(name="age", nullable = false)
    private int age;

    @Column(name="uses_elevator", nullable = true)
    private boolean usesElevator = true; // Accept the fact that there are more adults than children

    @Valid
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "apartment_id")
    private Apartment apartment;

    public Resident() {}

    public Resident(String name, int age, boolean usesElevator) {
        this.name = name;
        this.age = age;
        this.usesElevator = usesElevator;
    }

    public Resident(long id, String name, int age, boolean usesElevator) {
        super(id);
        this.name = name;
        this.age = age;
        this.usesElevator = usesElevator;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public boolean isUsesElevator() {
        return usesElevator;
    }

    public void setUsesElevator(boolean usesElevator) {
        this.usesElevator = usesElevator;
    }

    public @Valid Apartment getApartment() {
        return apartment;
    }

    public void setApartment(@Valid Apartment apartment) {
        this.apartment = apartment;
    }
}
