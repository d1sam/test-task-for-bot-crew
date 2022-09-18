package com.dshulha.test_task_departments_lectors.service.impl;

import com.dshulha.test_task_departments_lectors.entities.Department;
import com.dshulha.test_task_departments_lectors.repository.DepartmentJpaRepository;
import com.dshulha.test_task_departments_lectors.service.interf.DepartmentService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

@Service
@AllArgsConstructor
public class DepartmentServiceImpl implements DepartmentService {
    private DepartmentJpaRepository departmentJpaRepository;

    @Override
    public BigDecimal getAvgSalaryForDepartment(String departmentName) {
        return departmentJpaRepository.findAvgSalaryForDepartment(departmentName).setScale(2, RoundingMode.HALF_UP);
    }

    @Override
    public List<Department> getDepartmentsWithNameLike(String template) {
        return departmentJpaRepository.findByNameContainingIgnoreCase(template);
    }

    @Override
    public Department getDepartment(String departmentName) {
        return departmentJpaRepository.findByNameIgnoreCase(departmentName).orElseThrow(() ->
                new RuntimeException("There is no department with name " + departmentName));
    }
}
