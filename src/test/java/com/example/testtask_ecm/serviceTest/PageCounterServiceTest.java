package com.example.testtask_ecm.serviceTest;

import com.example.testtask_ecm.models.request.RequestDoc;
import com.example.testtask_ecm.models.response.ResponseDoc;
import com.example.testtask_ecm.parcer.FileByFormatParser;
import com.example.testtask_ecm.service.PageCounterServiceImp;
import com.example.testtask_ecm.service.FileFinderServiceImp;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class PageCounterServiceTest {
    private final String url = "test.pdf";
    @Mock
    private FileFinderServiceImp fileFinderServiceImp;
    @InjectMocks
    private PageCounterServiceImp pageCounterServiceImp;
    @Mock
    private List<FileByFormatParser> parserServices;
    @Mock
    private RequestDoc requestDoc;
    @Mock
    private File file;

    @Test
    void countPagesTest() {
        when(requestDoc.getUrl()).thenReturn(url);
        when(requestDoc.getDocFormats()).thenReturn(new String[]{"pdf"});

        Iterator<FileByFormatParser> iterator = mock(Iterator.class);
        when(parserServices.iterator()).thenReturn(iterator);
        when(iterator.hasNext()).thenReturn(true, false);
        FileByFormatParser fileParser = Mockito.mock(FileByFormatParser.class);
        when(iterator.next()).thenReturn(fileParser);
        when(fileParser.getPages(file)).thenReturn(10L);
        List<File> files = new ArrayList<>();
        files.add(file);

        when(fileFinderServiceImp.findFiles(new File(requestDoc.getUrl()), requestDoc.getDocFormats())).thenReturn(files);

        ResponseDoc responseDoc = pageCounterServiceImp.countPages(requestDoc);

        assertNotNull(responseDoc);
        assertEquals(10L, responseDoc.getPagesNumber());
        assertEquals(1, responseDoc.getDocNumber());
        assertEquals(url, responseDoc.getUrl());
    }
}

