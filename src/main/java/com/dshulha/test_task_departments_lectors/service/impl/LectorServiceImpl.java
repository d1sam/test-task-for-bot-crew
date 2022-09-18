package com.dshulha.test_task_departments_lectors.service.impl;

import com.dshulha.test_task_departments_lectors.entities.Lector;
import com.dshulha.test_task_departments_lectors.repository.LectorJpaRepository;
import com.dshulha.test_task_departments_lectors.service.interf.LectorService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class LectorServiceImpl implements LectorService {
    private LectorJpaRepository lectorJpaRepository;

    @Override
    public List<Lector> getLectorsWhereFirstNameOrLastNameLike(String template) {
        return lectorJpaRepository.findLectorsWhereFirstNameOrLastNameLike(template);
    }
}
