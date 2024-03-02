package com.codegym.service;

import com.codegym.model.Glasses;

import java.util.List;

public interface IGlassesService {
    List<Glasses> findAll();

    void save(Glasses glasses);

    Glasses findById(int id);

    void remove(int id);
}