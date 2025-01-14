package org.example.housemanager.services;

import jakarta.validation.Valid;
import org.example.housemanager.dao.BuildingDao;
import org.example.housemanager.dto.CreateBuildingDto;
import org.example.housemanager.entity.Building;

import java.util.List;

public class BuildingService {
    public static void createBuilding(@Valid Building building) {
        BuildingDao.createBuilding(building);
    }

    public static void saveBuilding(CreateBuildingDto createBuildingDto) {
        BuildingDao.saveBuildingDto(createBuildingDto);
    }

    public static void updateBuilding(Building building) {
        BuildingDao.updateBuilding(building);
    }

    public static void deleteBuilding(Building building) {
        BuildingDao.deleteBuilding(building);
    }

    public static void deleteBuildingById(long id) {
        BuildingDao.deleteBuildingById(id);
    }

    public static Building getBuildingById(long id) {
        return BuildingDao.getBuildingById(id);
    }

    public static List<Building> getAllBuildings() {
        return BuildingDao.getBuildings();
    }
}
