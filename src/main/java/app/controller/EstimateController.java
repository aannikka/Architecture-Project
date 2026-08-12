package app.controller;

import app.dto.request.EstimateRequest;
import app.dto.response.EstimateResponse;
import app.service.EstimateService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/estimates")
public class EstimateController {

    private final EstimateService estimateService;

    @PostMapping
    public ResponseEntity<EstimateResponse> create(@Valid @RequestBody EstimateRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(estimateService.create(request));
    }

    @PutMapping("/{id}")
    public ResponseEntity<EstimateResponse>  update(@PathVariable Long id, @Valid @RequestBody EstimateRequest request) {
        return ResponseEntity.ok(estimateService.update(id, request));
    }

    @GetMapping("/{id}")
    public ResponseEntity<EstimateResponse> findById(@PathVariable Long id) {
        return ResponseEntity.ok(estimateService.findById(id));
    }

    @GetMapping
    public ResponseEntity<List<EstimateResponse>> findAll() {
        return ResponseEntity.ok(estimateService.findAll());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id) {
        estimateService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}

