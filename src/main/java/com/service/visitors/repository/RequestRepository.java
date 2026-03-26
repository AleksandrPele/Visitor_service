package com.service.visitors.repository;

import com.service.visitors.entity.Request;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface RequestRepository extends JpaRepository<Request, Integer> {

    List<Request> findByStatus(String status);

    @Modifying
    @Transactional
    @Query("UPDATE Request r SET r.status = :status WHERE r.id = :id")
    void updateStatus(@Param("id") Integer id, @Param("status") String status);
}