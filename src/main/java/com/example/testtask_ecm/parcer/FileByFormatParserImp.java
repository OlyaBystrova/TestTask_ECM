package com.example.testtask_ecm.parcer;

import lombok.extern.slf4j.Slf4j;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.poi.ooxml.POIXMLDocument;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.springframework.stereotype.Component;
import java.io.File;
import java.io.IOException;


@Component
@Slf4j
public class FileByFormatParserImp implements FileByFormatParser {
    @Override
    public long getPages(File file) {
        if (!file.isFile()){
            throw new RuntimeException("No files found by the path");
        }

        String PDF = "pdf";
        String DOCX = "docx";

        int pages = 0;
        String fileName = file.getName();
        String fileExtension = fileName.substring(fileName.lastIndexOf(".") + 1);


        if (DOCX.equalsIgnoreCase(fileExtension)) {
            try (XWPFDocument document = new XWPFDocument(POIXMLDocument.openPackage(file.getPath()))) {
                pages = document.getProperties().getExtendedProperties().getPages();
            } catch (IOException e) {
                throw new RuntimeException("Error loading DOCX document: " + e.getMessage());
            } catch (Exception e) {
                log.error(e.getMessage());
            }
        }

        if (PDF.equalsIgnoreCase(fileExtension)) {
            try (PDDocument pdDocument = PDDocument.load(file)) {
                pages = pdDocument.getNumberOfPages();
            } catch (IOException e) {
                throw new RuntimeException("Error loading PDF document: " + e.getMessage());
            } catch (Exception e) {
                log.error(e.getMessage());
            }
        }
        return pages;
    }
}


