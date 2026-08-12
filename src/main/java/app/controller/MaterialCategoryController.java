package app.controller;

import app.dto.request.MaterialCategoryRequest;
import app.dto.response.MaterialCategoryResponse;
import app.service.MaterialCategoryService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/materials/categories")
public class MaterialCategoryController {

    private final MaterialCategoryService materialCategoryService;

    @PostMapping
    public ResponseEntity<MaterialCategoryResponse> create(@Valid @RequestBody MaterialCategoryRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(materialCategoryService.create(request));
    }

    @PutMapping("/{id}")
    public ResponseEntity<MaterialCategoryResponse> update(@PathVariable Long id, @Valid @RequestBody MaterialCategoryRequest request) {
        return ResponseEntity.ok(materialCategoryService.update(id,request));
    }

    @GetMapping("/{id}")
    public ResponseEntity<MaterialCategoryResponse> findById(@PathVariable Long id) {
        return ResponseEntity.ok(materialCategoryService.findById(id));
    }

    @GetMapping
    public ResponseEntity<List<MaterialCategoryResponse>> findAll() {
        return ResponseEntity.ok(materialCategoryService.findAll());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id) {
        materialCategoryService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
