package org.example.housemanager.dto;

import java.math.BigDecimal;

public class CreateCompanyDto {
    private String name;
    private BigDecimal income;


    public CreateCompanyDto(String name) {
        this.name = name;
        this.income = BigDecimal.valueOf(0.0);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getIncome() {
        return income;
    }

    public void setIncome(BigDecimal income) {
        this.income = income;
    }

    @Override
    public String toString() {
        return "CreateCompanyDto{" +
                "name='" + name + '\'' +
                ", income=" + income +
                '}';
    }
}
