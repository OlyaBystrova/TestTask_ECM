package com.example.testtask_ecm.service;

import com.example.testtask_ecm.parcer.FileByFormatParser;
import com.example.testtask_ecm.util.exception.WrongPathException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

@Service
public class FileFinderServiceImp implements FileFinderService {

    private final List<FileByFormatParser> parsers;

    @Autowired
    public FileFinderServiceImp(List<FileByFormatParser> parsers) {
        this.parsers = parsers;
    }


    @Override
    public List<File> findFiles(File directory, String[] fileFormats) {
        List<File> filesList = new ArrayList<>();
        if (directory.isDirectory()) {
            File[] files = directory.listFiles();
            if (files != null) {
                for (File file : files) {
                    if (file.isDirectory()) {
                        filesList.addAll(findFiles(file, fileFormats));
                    } else {
                        if (fileFormats == null) {
                            filesList.add(file);
                        } else {
                            for (String format : fileFormats) {
                                if (file.getName().toLowerCase().endsWith(format)) {
                                    filesList.add(file);
                                    break;
                                }
                            }
                        }
                    }
                }
            }
        } else {
            throw new WrongPathException("The specified file path is incorrect");
        }
        return filesList;
    }
}