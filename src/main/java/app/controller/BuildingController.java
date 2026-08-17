package app.controller;

import app.dto.request.BuildingRequest;
import app.dto.response.BuildingResponse;
import app.service.BuildingService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/buildings")
public class BuildingController {

    private final BuildingService buildingService;

    @PostMapping
    public ResponseEntity<BuildingResponse> create(@Valid @RequestBody BuildingRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(buildingService.create(request));
    }

    @PutMapping("/{id}")
    public ResponseEntity<BuildingResponse> update(@PathVariable Long id, @Valid @RequestBody BuildingRequest request) {
        return ResponseEntity.ok(buildingService.update(id, request));
    }

    @GetMapping("/{id}")
    public ResponseEntity<BuildingResponse> findById(@PathVariable Long id) {
        return ResponseEntity.ok(buildingService.findById(id));
    }

    @GetMapping("/project/{projectId}")
    public ResponseEntity<List<BuildingResponse>> findByProjectId(
            @PathVariable Long projectId
    ) {
        return ResponseEntity.ok(
                buildingService.findByProjectId(projectId)
        );
    }

    @GetMapping
    public ResponseEntity<List<BuildingResponse>> findAll() {
        return ResponseEntity.ok(buildingService.findAll());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id) {
        buildingService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
