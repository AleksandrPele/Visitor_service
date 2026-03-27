package com.service.visitors.service;

import com.service.visitors.entity.Request;
import com.service.visitors.entity.Visitor;
import java.util.List;

public interface RequestService {
    void create(List<Visitor> visitors, String status);
    List<Request> findByStatus(String status);
    void updateRequestStatus(Integer id, String status);
}