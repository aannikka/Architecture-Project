package app.mapper;

import app.dto.request.BuildingRequest;
import app.dto.response.BuildingResponse;
import app.entity.Building;
import app.entity.Project;

import org.springframework.stereotype.Component;

@Component
public class BuildingMapper {

    public Building toEntity(BuildingRequest request, Project project) {
        return Building.builder()
                .length(request.length())
                .width(request.width())
                .floors(request.floors())
                .floorHeight(request.floorHeight())
                .wallThickness(request.wallThickness())
                .roofType(request.roofType())
                .foundationType(request.foundationType())
                .project(project)
                .build();

    }

    public BuildingResponse toResponse(Building building){
        return new BuildingResponse(
                building.getId(),
                building.getLength(),
                building.getWidth(),
                building.getFloors(),
                building.getFloorHeight(),
                building.getWallThickness(),
                building.getRoofType().name(),
                building.getFoundationType().name()
        );
    }
}
