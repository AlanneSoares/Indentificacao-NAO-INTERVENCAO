package lerpdf;

import java.io.*;
import java.text.Normalizer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.pdfbox.cos.COSDocument;
import org.apache.pdfbox.pdfparser.PDFParser;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.util.PDFTextStripper;

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

            String conteudoSemEspacoMaiuscula = conteudoPDFSemAcentuacao.replaceAll("\\s+", " ").toUpperCase();

            List<String> palavrasSemPreposicao = removePreposicao(Arrays.asList(conteudoSemEspacoMaiuscula.split(" ")));

            return conteudoPDFSemAcentuacao;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
            }
        }

    public static List<String> removePreposicao(List<String> palavras) {
        String[] PREPOSICOES = {"", "AGRAVO", "A", "ANTE", "APOS", "ATE", "COM", "CONTRA", "DE", "DESDE", "EM", "ENTRE", "PARA", "PER", "PERANTE", "POR", "SEM", "SOB", "SOBRE", "TRAS"};

        List<String> palavrasSemPreposicoes = new ArrayList<>();
        for (String palavra : palavras) {
            for (String preposicao : PREPOSICOES) {
                if (palavra.equals(preposicao)){
                    palavrasSemPreposicoes.add(palavra);
                    break;
                }
            }
        }
        return palavrasSemPreposicoes;
    }
}

    //String[] strings = (String[]) lista.toArray (new String[lista.size()]);
