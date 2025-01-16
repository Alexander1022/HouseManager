package org.example.housemanager.entity;

import jakarta.persistence.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Entity
public class Pet extends BaseEntity {
    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "uses_common_area", nullable = false)
    private boolean usesCommonArea = true;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "apartment_id")
    private Apartment apartment;

    public Pet() {}

    public Pet(String name, boolean usesCommonArea, Apartment apartment) {
        this.name = name;
        this.usesCommonArea = usesCommonArea;
        this.apartment = apartment;
    }

    public Pet(long id, String name, boolean usesCommonArea, Apartment apartment) {
        super(id);
        this.name = name;
        this.usesCommonArea = usesCommonArea;
        this.apartment = apartment;
    }

    public Pet(String name, boolean usesCommonArea) {
        this.name = name;
        this.usesCommonArea = usesCommonArea;
    }

    public Pet(long id, String name, boolean usesCommonArea) {
        super(id);
        this.name = name;
        this.usesCommonArea = usesCommonArea;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isUsesCommonArea() {
        return usesCommonArea;
    }

    public void setUsesCommonArea(boolean usesCommonArea) {
        this.usesCommonArea = usesCommonArea;
    }

    public Apartment getApartment() {
        return apartment;
    }

    public void setApartment(Apartment apartment) {
        this.apartment = apartment;
    }

    @Override
    public String toString() {
        return "Pet{" +
                "name='" + name + '\'' +
                ", usesCommonArea=" + usesCommonArea +
                '}';
    }
}
