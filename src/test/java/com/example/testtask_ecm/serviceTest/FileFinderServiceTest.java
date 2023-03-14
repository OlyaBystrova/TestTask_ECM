package com.example.testtask_ecm.serviceTest;

import com.example.testtask_ecm.parcer.FileByFormatParser;
import com.example.testtask_ecm.service.FileFinderServiceImp;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.List;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;


@RunWith(MockitoJUnitRunner.class)
public class FileFinderServiceTest {
    @InjectMocks
    private FileFinderServiceImp pageCounterServiceImp;
    @Mock
    private FileByFormatParser fileByFormatParser;

    @BeforeEach
    void setUp() {
        fileByFormatParser = Mockito.mock(FileByFormatParser.class);
        pageCounterServiceImp = new FileFinderServiceImp(Arrays.asList(fileByFormatParser));
    }

    @Test
    void findFilesTest() throws IOException {
        Path tempDir = Files.createTempDirectory("test-dir");
        File testFile1 = new File(tempDir.toFile(), "test.pdf");
        File testFile2 = new File(tempDir.toFile(), "test.docx");
        assertTrue(testFile1.createNewFile());
        assertTrue(testFile2.createNewFile());

        List<File> filesList1 = pageCounterServiceImp.findFiles(tempDir.toFile(), null);
        assertEquals(2, filesList1.size());
        assertTrue(filesList1.contains(testFile1));
        assertTrue(filesList1.contains(testFile2));

        List<File> filesList2 = pageCounterServiceImp.findFiles(tempDir.toFile(), new String[]{"pdf"});
        assertEquals(1, filesList2.size());
        assertTrue(filesList2.contains(testFile1));

        Files.deleteIfExists(testFile1.toPath());
        Files.deleteIfExists(testFile2.toPath());
        Files.deleteIfExists(tempDir);
    }
}
