package pdf;

import java.io.*;
import java.text.Normalizer;
import java.util.Arrays;
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

        String textoPdf;
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

            return null;
        }

        String conteudoPDFSemAcentuacao = Normalizer.normalize(textoPdf, Normalizer.Form.NFD).replaceAll("\\p{InCombiningDiacriticalMarks}+", "");
        String conteudoSemEspacoMaiuscula = conteudoPDFSemAcentuacao.replaceAll("\\s+", " ").toUpperCase();

        List<String> palavras = ChamaLista.removePalavrasInuteis(Arrays.asList(conteudoSemEspacoMaiuscula.split(" ,")));

        return palavras.toString().replaceAll("", "").toUpperCase();

        //conteudoSemEspacoMaiuscula =
        //if (conteudoPDFSemAcentuacao <= 1) {

        /* int letra = 2;
        Integer.parseInt(String.valueOf(letra));
        int letras = letra + letra;
        System.out.println(letras); */

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