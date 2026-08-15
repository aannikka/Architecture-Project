package app.mapper;

import app.dto.request.ProjectRequest;
import app.dto.response.ProjectResponse;
import app.dto.shorts.BuildingShortResponse;
import app.dto.shorts.UserShortResponse;

import app.entity.Project;
import app.entity.User;
import org.springframework.stereotype.Component;

@Component
public class ProjectMapper {

    public Project toEntity(ProjectRequest request, User user) {
       return Project.builder()
               .name(request.name())
               .description(request.description())
               .address(request.address())
               .user(user)
               .build();
    }

    public ProjectResponse toResponse(Project project) {
        return new ProjectResponse(
                project.getId(),
                project.getName(),
                project.getDescription(),
                project.getAddress(),
                new UserShortResponse(
                        project.getUser().getId(),
                        project.getUser().getUsername()
                ),
                project.getBuilding() == null
                        ? null
                        : new BuildingShortResponse(
                                project.getBuilding().getId(),
                                project.getBuilding().getLength(),
                                project.getBuilding().getWidth(),
                                project.getBuilding().getFloors(),
                                project.getBuilding().getRoofType().name(),
                                project.getBuilding().getFoundationType().name()
                        )
        );
    }
}
