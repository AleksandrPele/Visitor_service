package com.service.visitors.service;

import com.service.visitors.entity.Visitor;

import java.util.List;

public interface VisitorService {
    List<Visitor> findAllVisitors();
    Visitor addVisitor(String fullName, String organization,
                       String department, String date);
    void deleteAllVisitors();
}
