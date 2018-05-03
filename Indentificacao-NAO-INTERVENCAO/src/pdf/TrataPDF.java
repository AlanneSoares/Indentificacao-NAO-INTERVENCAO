package pdf;

import java.io.*;
import java.text.Normalizer;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

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


        String conteudoPDFSemAcentuacao = Normalizer.normalize(textoPdf, Normalizer.Form.NFD).replaceAll("\\p{InCombiningDiacriticalMarks}+", "").toUpperCase();
        String conteudoSemEspacoMaiuscula = conteudoPDFSemAcentuacao.replaceAll("\\s+", " ").toUpperCase();

        List<String> palavras = ChamaLista.removePalavrasInuteis(Collections.singletonList(Arrays.asList(conteudoSemEspacoMaiuscula.split(" ,")).toString().replaceAll("", "").toUpperCase()));


        return palavras.toString();

    }
}



    /*private static String removePalavrasInuteis(String texto) {
        String textoLimpo = null;
            for (String palavraInutil : Palavras.PALAVRASINUTEIS) {
                if (texto.contains(palavraInutil)) {
                    textoLimpo = texto.replaceAll(palavraInutil, "");
                }
            }

        return textoLimpo;

    }*/