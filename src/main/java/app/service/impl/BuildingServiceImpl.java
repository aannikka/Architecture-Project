package app.service.impl;

import app.entity.Building;
import app.exception.EntityNotFoundException;
import app.repository.BuildingRepository;
import app.service.BaseService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class BuildingServiceImpl implements BaseService<Building> {

    private final BuildingRepository buildingRepository;

    @Override
    public Building create(Building building) {
        return buildingRepository.save(building);
    }

    @Override
    public Building update(Long id, Building building) {
        Building existingBuilding = findById(id);
        existingBuilding.setLength(building.getLength());
        existingBuilding.setWidth(building.getWidth());
        existingBuilding.setFloors(building.getFloors());
        existingBuilding.setFloorHeight(building.getFloorHeight());
        existingBuilding.setWallThickness(building.getWallThickness());
        existingBuilding.setRoofType(building.getRoofType());
        existingBuilding.setFoundationType(building.getFoundationType());
        return buildingRepository.save(existingBuilding);
    }

    @Override
    public Building findById(Long id) {
        return buildingRepository.findById(id).orElseThrow(() -> new EntityNotFoundException(
                String.format("Building with id %d not found", id)));
    }

    @Override
    public List<Building> findAll() {
        return buildingRepository.findAll();
    }

    @Override
    public void deleteById(Long id) {
        findById(id);
        buildingRepository.deleteById(id);
    }
}
