package com.dshulha.test_task_departments_lectors.service.interf;

import com.dshulha.test_task_departments_lectors.entities.Department;

import java.math.BigDecimal;
import java.util.List;

public interface DepartmentService {

    BigDecimal getAvgSalaryForDepartment(String departmentName);

    List<Department> getDepartmentsWithNameLike(String template);

    Department getDepartment(String departmentName);
}
