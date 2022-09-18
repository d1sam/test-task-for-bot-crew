package com.dshulha.test_task_departments_lectors.repository;

import com.dshulha.test_task_departments_lectors.entities.Lector;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LectorJpaRepository extends JpaRepository<Lector, Long> {
    @Query("SELECT l FROM Lector l where lower(l.firstName) like %:template% or lower(l.lastName) like %:template%")
    List<Lector> findLectorsWhereFirstNameOrLastNameLike(@Param("template") String template);
}
