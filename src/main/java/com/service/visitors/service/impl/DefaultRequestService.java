package com.service.visitors.service.impl;

import com.service.visitors.entity.Request;
import com.service.visitors.entity.Visitor;
import com.service.visitors.repository.RequestRepository;
import com.service.visitors.repository.VisitorRepository;
import com.service.visitors.service.RequestService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DefaultRequestService implements RequestService {

    private final RequestRepository requestRepository;
    private final VisitorRepository visitorRepository;

    @Override
    public List<Request> findAllRequests() {
        return requestRepository.findAll();
    }

    @Override
    @Transactional
    public Request create(List<Visitor> visitors, String status) {
        Request request = new Request();
        request.setStatus(status);
        Request savedRequest = requestRepository.save(request);

        for (Visitor visitor : visitors) {
            Visitor newVisitor = new Visitor();
            newVisitor.setFullName(visitor.getFullName());
            newVisitor.setOrganization(visitor.getOrganization());
            newVisitor.setDepartment(visitor.getDepartment());
            newVisitor.setDate(visitor.getDate());
            newVisitor.setStatus("WAITING".equals(status) ? "WAITING" : "PENDING");
            newVisitor.setRequest(savedRequest);

            visitorRepository.save(newVisitor);
        }
        return savedRequest;
    }

    @Override
    public List<Request> findByStatus(String status) {
        return requestRepository.findByStatus(status);
    }

    @Override
    @Transactional
    public void updateRequestStatus(Integer id, String status) {
        requestRepository.updateStatus(id, status);
        if ("APPROVE".equals(status)) {
            Request request = requestRepository.findById(id).orElse(null);
            if (request != null) {
                for (Visitor visitor : request.getVisitors()) {
                    visitor.setStatus("WAITING");
                    visitorRepository.save(visitor);
                }
            }
        } else if ("REJECT".equals(status)) {
            Request request = requestRepository.findById(id).orElse(null);
            if (request != null) {
                for (Visitor visitor : request.getVisitors()) {
                    visitor.setStatus("REJECTED");
                    visitorRepository.save(visitor);
                }
            }
        }
    }
}