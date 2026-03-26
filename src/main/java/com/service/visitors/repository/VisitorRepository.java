package com.service.visitors.repository;

import com.service.visitors.entity.Visitor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

public interface VisitorRepository extends JpaRepository<Visitor, Integer> {

    List<Visitor> findByRequestId(Integer requestId);

    List<Visitor> findByStatusIn(List<String> statuses);

    @Modifying
    @Transactional
    @Query("UPDATE Visitor v SET v.passportData = :passportData, v.entryTime = :entryTime, v.status = 'IN' WHERE v.id = :id")
    void markEntry(@Param("id") Integer id, @Param("passportData") String passportData, @Param("entryTime") LocalDateTime entryTime);

    @Modifying
    @Transactional
    @Query("UPDATE Visitor v SET v.exitTime = :exitTime, v.status = 'EXITED' WHERE v.id = :id")
    void markExit(@Param("id") Integer id, @Param("exitTime") LocalDateTime exitTime);

    List<Visitor> findByStatus(String status);
}