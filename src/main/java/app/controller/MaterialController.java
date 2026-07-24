package app.controller;

import app.dto.request.MaterialRequest;
import app.dto.response.MaterialResponse;
import app.service.MaterialService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/materials")
public class MaterialController {

    private final MaterialService materialService;

    @PostMapping
    public ResponseEntity<MaterialResponse> create(@Valid @RequestBody MaterialRequest request){
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(materialService.create(request));
    }

    @PutMapping("{/id}")
    public ResponseEntity<MaterialResponse> update(@PathVariable Long id, @Valid @RequestBody MaterialRequest request){
        return  ResponseEntity.ok(materialService.update(id,request));
    }

    @GetMapping("/{id}")
    public ResponseEntity<MaterialResponse> findById(@PathVariable Long id) {
        return ResponseEntity.ok(materialService.findById(id));
    }

    @GetMapping
    public ResponseEntity<List<MaterialResponse>> findAll(){
        return ResponseEntity.ok(materialService.findAll());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id){
        materialService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
