package app.service.impl;

import app.entity.Project;
import app.exception.EntityNotFoundException;
import app.repository.ProjectRepository;
import app.service.BaseService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class ProjectServiceImpl implements BaseService<Project> {

    private final ProjectRepository projectRepository;

    @Override
    public Project create(Project project) {
        return projectRepository.save(project);
    }

    @Override
    public Project update(Long id, Project project) {
        Project existingProject = findById(id);
        existingProject.setName(project.getName());
        existingProject.setDescription(project.getDescription());
        existingProject.setAddress(project.getAddress());
        return projectRepository.save(existingProject);
    }

    @Override
    public Project findById(Long id) {
        return projectRepository.findById(id).orElseThrow(() -> new EntityNotFoundException(
                String.format("Project with id %d not found", id)));
    }

    @Override
    public List<Project> findAll() {
        return projectRepository.findAll();
    }

    @Override
    public void deleteById(Long id) {
        findById(id);
        projectRepository.deleteById(id);
    }
}
