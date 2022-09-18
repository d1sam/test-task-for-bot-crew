package com.dshulha.test_task_departments_lectors.service.interf;

import com.dshulha.test_task_departments_lectors.entities.Lector;

import java.util.List;

public interface LectorService {
    List<Lector> getLectorsWhereFirstNameOrLastNameLike(String template);
}
