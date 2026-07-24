package app.service;

import app.dto.request.MaterialRequest;
import app.dto.response.MaterialResponse;

import java.util.List;

public interface MaterialService {
    MaterialResponse create(MaterialRequest request);
    MaterialResponse findById(Long id);
    List<MaterialResponse> findAll();
    MaterialResponse update(Long id, MaterialRequest request);
    void deleteById(Long id);
}