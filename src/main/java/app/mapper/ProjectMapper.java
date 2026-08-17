package app.mapper;

import app.dto.request.ProjectRequest;
import app.dto.response.ProjectResponse;
import app.dto.shorts.UserShortResponse;

import app.entity.Project;
import app.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class ProjectMapper {

    private final BuildingMapper buildingMapper;

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
                project.getBuildings() == null
                        ? List.of()
                        : project.getBuildings().stream()
                        .map(buildingMapper::toShortResponse)
                        .toList()
        );
    }
}
