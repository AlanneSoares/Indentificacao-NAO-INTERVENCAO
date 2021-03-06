package br.com.javac.pdf;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import org.apache.pdfbox.pdfparser.PDFParser;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.util.PDFTextStripper;

public class ConverterPdf2Txt {

    public static void main(String[] args) {
        try {
            String pdfFile = "arquivo.pdf";

            File filePDF = new File(pdfFile);
            FileInputStream fileInputStream = new FileInputStream(filePDF);

            PDDocument pdfDocument = null;
            try {
                PDFParser parser = new PDFParser(fileInputStream);
                parser.parse();
                pdfDocument = parser.getPDDocument();
                PDFTextStripper stripper = new PDFTextStripper();

                info("Arquivo PDF: ");
                info("");
                info(stripper.getText(pdfDocument));
            } finally {
                if (pdfDocument != null) {
                    try {
                        fileInputStream.close();
                    } catch (IOException e) {}
                }
            }
        } catch (Exception e) {
            error(e.toString());
        }
    }

    private static void info(String log) {
        System.out.println("INFO: " + log);
    }

    private static void error(String log) {
        System.out.println("ERROR: " + log);
    }
}