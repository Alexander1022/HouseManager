package org.example.housemanager.entity;

import jakarta.persistence.*;
import jakarta.validation.Valid;
import jakarta.validation.constraints.PastOrPresent;
import org.apache.logging.log4j.core.config.plugins.validation.constraints.NotBlank;

import java.time.LocalDate;
import java.util.Set;

@Entity
public class Employee extends BaseEntity {
    @NotBlank(message = "Employee's name cannot be blank!")
    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "hire_date", nullable = true)
    private LocalDate hireDate;

    @Valid
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "company_id")
    private Company company;

    @OneToMany(mappedBy = "employee", fetch = FetchType.EAGER)
    private Set<Building> buildings;

    public Employee() {}

    public Employee(String name, LocalDate hireDate) {
        this.name = name;
        this.hireDate = hireDate;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getHireDate() {
        return hireDate;
    }

    public void setHireDate(LocalDate hireDate) {
        this.hireDate = hireDate;
    }

    public @Valid Company getCompany() {
        return company;
    }

    public void setCompany(@Valid Company company) {
        this.company = company;
    }

    public Set<Building> getBuildings() {
        return buildings;
    }

    public void setBuildings(Set<Building> buildings) {
        this.buildings = buildings;
    }
}
