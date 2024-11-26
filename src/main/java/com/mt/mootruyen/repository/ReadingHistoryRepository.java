package com.mt.mootruyen.repository;

import com.mt.mootruyen.entity.ReadingHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReadingHistoryRepository extends JpaRepository<ReadingHistory, String> {
}
