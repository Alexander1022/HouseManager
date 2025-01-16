package org.example.housemanager.dto;

public class CreatePetDto {
    private String name;
    private boolean usesCommonArea;

    public CreatePetDto(String name, boolean usesCommonArea) {
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

    @Override
    public String toString() {
        return "CreatePetDto{" +
                "name='" + name + '\'' +
                ", usesCommonArea=" + usesCommonArea +
                '}';
    }
}
