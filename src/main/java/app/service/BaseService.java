package app.service;

import app.entity.Building;

import java.util.List;

public interface BaseService<T> {
    T findById(Long id);
    T create(T entity);
    T update(Long id, T entity);
    List<T> findAll();
    void deleteById(Long id);
}
