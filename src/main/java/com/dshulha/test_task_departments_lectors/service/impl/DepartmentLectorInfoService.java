package com.dshulha.test_task_departments_lectors.service.impl;

import com.dshulha.test_task_departments_lectors.entities.Degree;
import com.dshulha.test_task_departments_lectors.entities.Department;
import com.dshulha.test_task_departments_lectors.entities.Lector;
import com.dshulha.test_task_departments_lectors.service.interf.DepartmentService;
import com.dshulha.test_task_departments_lectors.service.interf.LectorService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import static java.util.stream.Collectors.groupingBy;

@Slf4j
@Service
@AllArgsConstructor
public class DepartmentLectorInfoService {
    private final DepartmentService departmentService;
    private final LectorService lectorService;

    public void printInfoAboutHeadOfDepartment(String departmentName) {
        Department department = departmentService.getDepartment(departmentName);
        log.info("Head of {} department is {} {}", department.getName(), department.getHead().getFirstName(), department.getHead().getLastName());
    }

    @Transactional(readOnly = true)
    public void printStatisticOfDepartment(String departmentName) {
        Department department = departmentService.getDepartment(departmentName);
        Map<Degree, List<Lector>> mapDegreeToCollectors = department.getLectors().stream().collect(groupingBy(Lector::getDegree));
        mapDegreeToCollectors.forEach((key, value) -> log.info("{} : {}", key.getName(), value.size()));
    }

    public void printAvgSalaryOfDepartment(String departmentName) {
        BigDecimal avgSalary = departmentService.getAvgSalaryForDepartment(departmentName);
        log.info("The average salary of {} is {}", departmentName, avgSalary);
    }

    @Transactional(readOnly = true)
    public void printCountOfEmployeesInDepartment(String departmentName) {
        Department department = departmentService.getDepartment(departmentName);
        log.info("Count: {}", department.getLectors().size());
    }

    public void printGlobalSearch(String template) {
        List<Department> departments = departmentService.getDepartmentsWithNameLike(template);
        List<Lector> lectors = lectorService.getLectorsWhereFirstNameOrLastNameLike(template);
        if (lectors.isEmpty() && departments.isEmpty()) {
            log.error("There is no info about {}!", template);
            return;
        }
        if (!departments.isEmpty()) {
            log.info("Departments like template: {}", Arrays.toString(departments.toArray()));
        }
        if (!lectors.isEmpty()) {
            log.info("Lectors like template: {}", Arrays.toString(lectors.toArray()));
        }
    }
}
