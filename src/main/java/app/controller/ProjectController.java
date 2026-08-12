package app.controller;

import app.dto.request.ProjectRequest;
import app.dto.response.ProjectResponse;
import app.service.ProjectService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/projects")
public class ProjectController {

    private final ProjectService projectService;

    @PostMapping
    public ResponseEntity<ProjectResponse> create(@Valid @RequestBody ProjectRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(projectService.create(request));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProjectResponse> update(@PathVariable Long id, @Valid @RequestBody ProjectRequest request){
        return ResponseEntity.ok(projectService.update(id,request));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProjectResponse> findById(@PathVariable Long id){
        return ResponseEntity.ok(projectService.findById(id));
    }

    @GetMapping
    public ResponseEntity<List<ProjectResponse>> findAll(){
        return ResponseEntity.ok(projectService.findAll());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id){
        projectService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
