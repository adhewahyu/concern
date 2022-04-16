package com.data.civil.repository;

import com.data.civil.model.entity.CommLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CommLogRepository extends JpaRepository<CommLog, Long> {



}
