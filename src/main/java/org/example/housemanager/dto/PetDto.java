package org.example.housemanager.dto;

import org.apache.logging.log4j.core.config.plugins.validation.constraints.NotBlank;

public class PetDto {
    @NotBlank(message = "Name cannot be blank!")
    private String name;

    @NotBlank(message = "Pet can or can't use common area!")
    private boolean usesCommonArea;

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

    @Override
    public String toString() {
        return "PetDto{" +
                "name='" + name + '\'' +
                ", usesCommonArea=" + usesCommonArea +
                '}';
    }
}
