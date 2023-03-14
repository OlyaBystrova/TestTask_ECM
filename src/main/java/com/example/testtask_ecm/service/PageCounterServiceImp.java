package com.example.testtask_ecm.service;

import com.example.testtask_ecm.models.request.RequestDoc;
import com.example.testtask_ecm.models.response.ResponseDoc;
import com.example.testtask_ecm.parcer.FileByFormatParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.List;

@Service
public class PageCounterServiceImp implements PageCounterService {
    private final List<FileByFormatParser> parsers;
    private final FileFinderServiceImp pageCounterServiceImp;

    @Autowired
    public PageCounterServiceImp(List<FileByFormatParser> parsers, FileFinderServiceImp pageCounterServiceImp) {
        this.parsers = parsers;
        this.pageCounterServiceImp = pageCounterServiceImp;
    }

    @Override
    public ResponseDoc countPages(RequestDoc doc) {
        String url = doc.getUrl();
        List<File> files = pageCounterServiceImp.findFiles(new File(doc.getUrl()), doc.getDocFormats());
        long count = 0;
        for (File file : files) {
            for (FileByFormatParser parser : parsers) {
                count += parser.getPages(file);
            }
        }
        return new ResponseDoc(url, (long) files.size(), count);
    }
}
