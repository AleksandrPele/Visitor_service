package com.service.visitors.repository;

import com.service.visitors.entity.Visitor;

import java.util.List;

public interface TempVisitorRepository {
    List<Visitor> findAll();
    Visitor save(Visitor visitor);
    void deleteAll();
}
