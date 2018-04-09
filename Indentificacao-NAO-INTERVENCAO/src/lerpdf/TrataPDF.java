/*
package lerpdf;

//import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
//import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;
//import java.text.Normalizer;
//import java.util.regex.Matcher;
//import java.util.regex.Pattern;

//import org.apache.pdfbox.cos.COSDocument;
//import org.apache.pdfbox.pdfparser.PDFParser;
//import org.apache.pdfbox.pdmodel.PDDocument;
//import org.apache.pdfbox.util.PDFTextStripper;

public class TrataPDF {

    String[] linhasPDF;
    int ultimaLinhaPDF = 0;
    int qtde_erros = 0;

    PDFTextStripper pdfStripper = null;
    PDDocument pdDoc = null;
    COSDocument cosDoc = null;
    String parsedText= "";


    public void linhasPDF ( String nomePDF, String nomePDFsemDiretorio) throws SQLException, FileNotFoundException {

        InputStream bin = new FileInputStream(new File(nomePDF));
        try {
            PDFParser parser = new PDFParser(bin);
            parser.parse();
            cosDoc = parser.getDocument();
            pdfStripper = new PDFTextStripper();
            pdDoc = new PDDocument(cosDoc);
            pdfStripper.setEndPage(1);
            parsedText = pdfStripper.getText(pdDoc);
            //System.out.println(parsedText);
            bin.close();
            bin.close();
        } catch (IOException e) {
            qtde_erros=1;
            try {
                if (cosDoc != null) bin.close();
                if (pdDoc  != null) bin.close();
            } catch (Exception e1) {
                e.printStackTrace();
            }
        }
        //System.out.println(parsedText);
        linhasPDF = parsedText.split("\\r?\\n");
        ultimaLinhaPDF = linhasPDF.length;
        // System.out.println(" numero de linhas do PDF "+ ultimaLinhaPDF);
    }
}
*/
