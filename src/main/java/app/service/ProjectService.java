package app.service;

import app.dto.request.ProjectRequest;
import app.dto.response.ProjectResponse;

import app.entity.Project;
import app.entity.User;
import app.exception.EntityNotFoundException;
import app.mapper.ProjectMapper;
import app.repository.ProjectRepository;
import app.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class ProjectService {

    private final ProjectRepository projectRepository;
    private final ProjectMapper mapper;
    private final UserRepository userRepository;

    public ProjectResponse create(ProjectRequest request) {
        User user = getUser(request.userId());
        Project project = mapper.toEntity(request, user);
        Project saved = projectRepository.save(project);
        return mapper.toResponse(saved);
    }

    public ProjectResponse update(Long id, ProjectRequest request) {
        Project existingProject = getProject(id);
        User user = getUser(request.userId());
        existingProject.setName(request.name());
        existingProject.setDescription(request.description());
        existingProject.setAddress(request.address());
        existingProject.setUser(user);
        Project updated = projectRepository.save(existingProject);
        return mapper.toResponse(updated);
    }

    public ProjectResponse findById(Long id) {
      Project project = getProject(id);
      return mapper.toResponse(project);
    }

    public List<ProjectResponse> findAll() {
        return projectRepository.findAll()
                .stream()
                .map(mapper::toResponse)
                .toList();
    }

    public void deleteById(Long id) {
        projectRepository.delete(getProject(id));
    }

    private User getUser(Long id) {
        return userRepository.findById(id).orElseThrow(() -> new EntityNotFoundException(
                String.format("User with id %d not found", id)));
    }

    private Project getProject(Long id) {
        return projectRepository.findById(id).orElseThrow(() -> new EntityNotFoundException(
                String.format("Project with id %d not found", id)));
    }
}
