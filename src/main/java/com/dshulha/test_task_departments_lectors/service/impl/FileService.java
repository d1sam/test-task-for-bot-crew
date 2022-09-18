package com.dshulha.test_task_departments_lectors.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

@Service
@Slf4j
public class FileService {
    public StringBuilder readFileByName(String fileName) throws FileNotFoundException {
        StringBuilder resultStringBuilder = new StringBuilder();
        File myObj = new File(fileName);
        Scanner myReader = new Scanner(myObj);
        while (myReader.hasNextLine()) {
            resultStringBuilder.append(myReader.nextLine()).append("\n");
        }
        myReader.close();
        return resultStringBuilder;
    }
}
