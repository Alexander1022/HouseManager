package org.example.housemanager.dto;

import org.apache.logging.log4j.core.config.plugins.validation.constraints.NotBlank;

public class ResidentDto {
    @NotBlank(message = "Name cannot be blank!")
    private String name;

    @NotBlank(message = "Age cannot be blank!")
    private int age;

    @NotBlank(message = "Using elevator boolean cannot be blank!")
    private boolean useElevator;

    public ResidentDto(String name, int age, boolean useElevator) {
        this.name = name;
        this.age = age;
        this.useElevator = useElevator;
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

    public boolean isUseElevator() {
        return useElevator;
    }

    public void setUseElevator(boolean useElevator) {
        this.useElevator = useElevator;
    }

    @Override
    public String toString() {
        return "ResidentDto{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", useElevator=" + useElevator +
                '}';
    }
}
