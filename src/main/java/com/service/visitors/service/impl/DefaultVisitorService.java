package com.service.visitors.service.impl;

import com.service.visitors.entity.Visitor;
import com.service.visitors.repository.TempVisitorRepository;
import com.service.visitors.service.VisitorService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DefaultVisitorService implements VisitorService {

    private final TempVisitorRepository tempVisitorRepository;  // изменён тип

    @Override
    public List<Visitor> findAllVisitors() {
        return this.tempVisitorRepository.findAll();
    }

    @Override
    public Visitor addVisitor(String fullName, String organization, String department, String date) {
        return this.tempVisitorRepository.save(new Visitor(null, fullName, organization, department,
                date, null, null, null, null, null));
    }

    @Override
    public void deleteAllVisitors() {
        tempVisitorRepository.deleteAll();
    }
}