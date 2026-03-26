package com.service.visitors.service.impl;

import com.service.visitors.entity.Visitor;
import com.service.visitors.repository.VisitorRepository;
import com.service.visitors.service.GuardService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class DefaultGuardService implements GuardService {

    private final VisitorRepository visitorRepository;

    @Override
    public List<Visitor> findActiveVisitors() {
        return visitorRepository.findByStatusIn(List.of("WAITING", "IN"));
    }

    @Override
    @Transactional
    public void markEntry(Integer id, String passportData) {
        visitorRepository.markEntry(id, passportData, LocalDateTime.now());
    }

    @Override
    @Transactional
    public void markExit(Integer id) {
        visitorRepository.markExit(id, LocalDateTime.now());
    }

    @Override
    public List<Visitor> findAllArchive() {
        return visitorRepository.findByStatus("EXITED");
    }
}