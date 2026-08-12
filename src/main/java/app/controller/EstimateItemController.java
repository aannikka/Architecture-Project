package app.controller;

import app.dto.request.EstimateItemRequest;
import app.dto.response.EstimateItemResponse;
import app.service.EstimateItemService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/estimates/items")
public class EstimateItemController {

    private final EstimateItemService estimateItemService;

    @PostMapping
    public ResponseEntity<EstimateItemResponse> create(@Valid @RequestBody EstimateItemRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(estimateItemService.create(request));
    }

    @PutMapping("/{id}")
    public ResponseEntity<EstimateItemResponse> update(@PathVariable Long id, @Valid @RequestBody EstimateItemRequest request) {
        return ResponseEntity.ok(estimateItemService.update(id, request));
    }

    @GetMapping("/{id}")
    public ResponseEntity<EstimateItemResponse> findById(@PathVariable Long id) {
        return ResponseEntity.ok(estimateItemService.findById(id));
    }

    @GetMapping
    public ResponseEntity<List<EstimateItemResponse>> findAll() {
        return ResponseEntity.ok(estimateItemService.findAll());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id) {
        estimateItemService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
