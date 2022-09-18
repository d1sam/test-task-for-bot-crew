package com.dshulha.test_task_departments_lectors;

import com.dshulha.test_task_departments_lectors.service.impl.DepartmentLectorInfoService;
import com.dshulha.test_task_departments_lectors.service.impl.FileService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.FileNotFoundException;
import java.util.Scanner;

@SpringBootApplication
@Slf4j
@RequiredArgsConstructor
public class TestTaskDepartmentsLectorsApplication implements CommandLineRunner {
    private final DepartmentLectorInfoService departmentLectorInfoService;
    private final FileService fileService;
    private static final String DEPARTMENT_HEAD = "who is head of department";
    private static final String AVG_SALARY = "show the average salary for the department";
    private static final String EMPLOYEE_COUNT = "show count of employee for";
    private static final String GLOBAL_SEARCH = "global search by";
    private static final String UNKNOWN_OPERATION = "UNKNOWN OPERATION";
    private static final String SHOW = "show";
    private static final String STATISTICS = "statistics";
    private static final String STOP = "stop";

    @Value("${rules.path}")
    private String rulesPath;

    public static void main(String[] args) {
        SpringApplication.run(TestTaskDepartmentsLectorsApplication.class, args);
    }

    @Override
    public void run(String... args) {
        log.info("This program for lectors and departments :)");

        try {
            StringBuilder rules = fileService.readFileByName(rulesPath);
            System.out.println(rules.toString());
        } catch (FileNotFoundException e) {
            log.error("File with rules not found!");
        }

        while (true) {
            Scanner scanner = new Scanner(System.in);
            log.info("Input operation: ");
            String currentOperation = scanner.nextLine().toLowerCase();
            String[] currentOperationArr = currentOperation.split(" ");
            try {
                if (currentOperation.startsWith(DEPARTMENT_HEAD) && currentOperationArr.length > 5) {
                    String departmentName = getDepartmentNameFromTheEndOfString(currentOperation, DEPARTMENT_HEAD);
                    departmentLectorInfoService.printInfoAboutHeadOfDepartment(departmentName);
                } else if (currentOperationArr.length > 2 && currentOperationArr[0].equals(SHOW)
                        && currentOperationArr[currentOperationArr.length - 1].equals(STATISTICS)) {
                    departmentLectorInfoService.printStatisticOfDepartment(
                            currentOperation.substring(SHOW.length(), currentOperation.length() - STATISTICS.length()).trim());
                } else if (currentOperation.startsWith(AVG_SALARY)) {
                    String departmentName = getDepartmentNameFromTheEndOfString(currentOperation, AVG_SALARY);
                    departmentLectorInfoService.printAvgSalaryOfDepartment(departmentName);
                } else if (currentOperation.startsWith(EMPLOYEE_COUNT)) {
                    String departmentName = getDepartmentNameFromTheEndOfString(currentOperation, EMPLOYEE_COUNT);
                    departmentLectorInfoService.printCountOfEmployeesInDepartment(departmentName);
                } else if (currentOperation.startsWith(GLOBAL_SEARCH)) {
                    String template = getDepartmentNameFromTheEndOfString(currentOperation, GLOBAL_SEARCH);
                    departmentLectorInfoService.printGlobalSearch(template);
                } else if (currentOperation.equalsIgnoreCase(STOP)) {
                    log.info("Thanks for using :)");
                    break;
                } else {
                    log.info(UNKNOWN_OPERATION);
                }
            } catch (RuntimeException runtimeException) {
                log.error(runtimeException.getMessage());
            }
        }
    }

    private String getDepartmentNameFromTheEndOfString(String inputString, String stringCut) {
        return inputString.substring(stringCut.length()).trim();
    }
}
