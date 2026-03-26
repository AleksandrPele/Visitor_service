package com.service.visitors.service;

import com.service.visitors.entity.Visitor;
import java.util.List;

public interface GuardService {
    List<Visitor> findActiveVisitors();
    void markEntry(Integer id, String passportData);
    void markExit(Integer id);
    List<Visitor> findAllArchive();
}