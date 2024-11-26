package com.mt.moontruyen.repository;

import com.mt.moontruyen.entity.ReadingHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReadingHistoryRepository extends JpaRepository<ReadingHistory, String> {
}
