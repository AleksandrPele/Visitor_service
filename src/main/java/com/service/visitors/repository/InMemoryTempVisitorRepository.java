package com.service.visitors.repository;

import com.service.visitors.entity.Visitor;
import org.springframework.stereotype.Repository;

import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;

@Repository
public class InMemoryTempVisitorRepository implements TempVisitorRepository {

    private final List<Visitor> visitors = new LinkedList<>();

    @Override
    public List<Visitor> findAll() {
        return this.visitors;
    }

    @Override
    public Visitor save(Visitor visitor) {
        visitor.setId(this.visitors.stream()
                .max(Comparator.comparingInt(Visitor::getId))
                .map(Visitor::getId)
                .orElse(0) + 1);
        this.visitors.add(visitor);
        return visitor;
    }

    @Override
    public void deleteAll() {
        visitors.clear();
    }
}
