package com.example.testtask_ecm.service;

import java.io.File;
import java.util.List;

public interface FileFinderService {
    List<File> findFiles(File directory, String[] fileFormats);
}
