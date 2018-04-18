package lerpdf;

import java.io.*;
import java.text.Normalizer;
import java.util.ArrayList;
import java.util.List;

import org.apache.pdfbox.cos.COSDocument;
import org.apache.pdfbox.pdfparser.PDFParser;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.util.PDFTextStripper;
import org.w3c.dom.Document;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

public class TrataPDF {
    public static void main(String args[]) {

        System.out.println(pdf());
    }

    public static String pdf(){

        PDFParser parser = null;
        PDDocument pdDoc = null;
        COSDocument cosDoc = null;
        PDFTextStripper pdfStripper;

        String interiorPdf;
        String fileName = "C:\\Users\\alanne.soares\\Documents\\teste.pdf";
        File file = new File(fileName);
        try {
            parser = new PDFParser(new FileInputStream(file));
            parser.parse();
            cosDoc = parser.getDocument();
            pdfStripper = new PDFTextStripper();
            pdDoc = new PDDocument(cosDoc);
            interiorPdf = pdfStripper.getText(pdDoc);

            String conteudoPDFSemAcentuacao = Normalizer.normalize(interiorPdf, Normalizer.Form.NFD).replaceAll("\\p{InCombiningDiacriticalMarks}+", "");

            String conteudoSemEspaco = conteudoPDFSemAcentuacao.replaceAll("\\s+", " ");

            String[] palavras = conteudoSemEspaco.split(" ");

            return conteudoPDFSemAcentuacao.toUpperCase();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
            }
        }


        public String[] removePreposicao() {
            List lista = new ArrayList();
            String[] preposicao;

            String[] preposicao = new String () ["A", "ANTE", "APOS", "ATE", "COM", "CONTRA", "DE", "DESDE", "EM", "ENTRE", "PARA", "PER", "PERANTE", "POR", "SEM", "SOB", "SOBRE", "TRAS"))];

        }
    }

    //String[] strings = (String[]) lista.toArray (new String[lista.size()]);
