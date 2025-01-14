package org.example.housemanager.dto;

import org.apache.logging.log4j.core.config.plugins.validation.constraints.NotBlank;

import java.math.BigDecimal;

public class CompanyDto {
    @NotBlank(message = "Company name cannot be blank!")
    private String name;

    @NotBlank(message = "Company income cannot be blank!")
    private BigDecimal income;

    public CompanyDto(String name) {
        this.name = name;
        this.income = BigDecimal.ZERO;
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
}
