package org.example.housemanager.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.PositiveOrZero;
import org.apache.logging.log4j.core.config.plugins.validation.constraints.NotBlank;

import java.math.BigDecimal;
import java.util.Set;

@Entity
public class Company extends BaseEntity {
    @NotBlank(message = "Company name cannot be blank!")
    @Column(name = "name", nullable = false)
    private String name;

    @PositiveOrZero
    @Column(name = "income", nullable = false)
    private BigDecimal income = BigDecimal.valueOf(0.0);

    @OneToMany(mappedBy = "company", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Set<Employee> employees;

    public Company() {}

    public Company(long id, String name) {
        super(id);
        this.name = name;
    }

    public Company(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public Set<Employee> getEmployees() {
        return employees;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setEmployees(Set<Employee> employees) {
        this.employees = employees;
    }

    public @PositiveOrZero BigDecimal getIncome() {
        return income;
    }

    public void setIncome(@PositiveOrZero BigDecimal income) {
        this.income = income;
    }

    @Override
    public String toString() {
        return "Company{" +
                "name='" + name + '\'' +
                ", income=" + income +
                '}';
    }
}
