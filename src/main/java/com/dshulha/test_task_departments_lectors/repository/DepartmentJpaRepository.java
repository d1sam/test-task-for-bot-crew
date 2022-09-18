package com.dshulha.test_task_departments_lectors.repository;

import com.dshulha.test_task_departments_lectors.entities.Department;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Repository
public interface DepartmentJpaRepository extends JpaRepository<Department, Long> {
    Optional<Department> findByNameIgnoreCase(String name);

    @Query(value = "SELECT avg(salary) from departments d join departments_lectors dl on d.id = dl.department_id " +
            "join lectors l on l.id = dl.lector_id where lower(d.name) = :departmentName", nativeQuery = true)
    BigDecimal findAvgSalaryForDepartment(@Param("departmentName") String departmentName);

    List<Department> findByNameContainingIgnoreCase(String template);
}
