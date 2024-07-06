package com.masterspring.backenddayoff.repository;

import com.masterspring.backenddayoff.entity.Holiday;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;

@Repository
public interface HolidayRepositoy extends JpaRepository<Holiday, Long> {
    @Query("SELECT CASE WHEN COUNT(e) > 0 THEN true ELSE false END FROM Holiday e WHERE :targetDate BETWEEN e.startDate AND e.endDate")
    boolean existsByDateBetween(@Param("targetDate") LocalDate targetDate);
}
