package pdf;

import java.io.*;
import java.text.Normalizer;

import org.apache.pdfbox.cos.COSDocument;
import org.apache.pdfbox.pdfparser.PDFParser;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.util.PDFTextStripper;


public class TrataPDF {

    public static String obterTexto() {

        PDFParser parser;
        PDDocument pdDoc;
        COSDocument cosDoc;
        PDFTextStripper pdfStripper;

        String textoPdf = null;
        String fileName = "c:/users/alanne.soares/documents/teste.pdf";
        File file = new File(fileName);


        try {

            parser = new PDFParser(new FileInputStream(file));
            parser.parse();
            cosDoc = parser.getDocument();
            pdfStripper = new PDFTextStripper();
            pdDoc = new PDDocument(cosDoc);
            textoPdf = pdfStripper.getText(pdDoc);


        } catch (Exception e) {

            e.printStackTrace();

        }

        String conteudoPDFSemAcentuacao = Normalizer.normalize(textoPdf, Normalizer.Form.NFD)
                .replaceAll("\\p{InCombiningDiacriticalMarks}+", "")
                .toUpperCase().replaceAll("\\s[A-Z]{1,2}\\s", " ");

        return conteudoPDFSemAcentuacao;
    }
}