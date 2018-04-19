/*

    "A", "ANTE", "APOS", "ATE", "COM", "CONTRA",
                "DE", "DESDE", "EM", "ENTRE", "PARA", "PER",
                "PERANTE", "POR", "SEM", "SOB", "SOBRE", "TRAS",
                "AFORA", "COMO", "CONFORME", "CONSOANTE", "DURANTE",
                "EXCETO", "SALVO", "SEGUNDO", "VISTO", "O", "OS", "AO",
                "AOS", "AONDE", "ONDE", "AS", "DA", "NO", "NA", "NOS",
                "NAS", "NUMA", "PELO", "DESSA", "DESSAS", "DESSE",
                "DESSES", "NAQUELA", "NAQUELAS", "NAQUELE", "NAQUELES",
                "DE MODO QUE", "ABAIXO DE", "ACIMA DE", "ALÉM DE", "AO INVÉS",
                "ANTES DE", "AO LADO DE", "APESAR DE", "ATRÁS DE", "ATRÁS DE",
                "DE ACORDO COM", "DENTRO DE", "DEPOIS DE", "EMBAIXO DE", "EM FRENTE DE",
                "EM FRENTE A", "EM VEZ DE", "JUNTO DE", "PERTO DE", "POR ENTRE"

 */



package pdf;

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

    public static String obterTexto() {

        PDFParser parser = null;
        PDDocument pdDoc = null;
        COSDocument cosDoc = null;
        PDFTextStripper pdfStripper;

        String textoPdf;
        String fileName = "c:/users/alanne.soares/documents/teste-3.pdf";
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
        List<String> palavras = removePalavrasInuteis(Arrays.asList(conteudoSemEspacoMaiuscula.split(" ")));

        return palavras.toString().replaceAll("", "").toUpperCase();
    }

    private static List<String> removePalavrasInuteis(List<String> palavras) {
        List<String> palavraSemPreposicao = new ArrayList<>(palavras);
        for (String palavra : palavras) {
            for (String preposicao : Palavras.PALAVRASINUTEIS) {
                if (palavra.equals(preposicao)) {
                    palavraSemPreposicao.remove(preposicao);
                }
            }
        }
        return palavraSemPreposicao;
    }
    private static String removePalavrasInuteis(String texto) {
        String textoLimpo = null;
            for (String palavraInutil : Palavras.PALAVRASINUTEIS) {
                if (texto.contains(palavraInutil)) {
                    textoLimpo = texto.replaceAll(palavraInutil, "");
                }
            }
            
        return textoLimpo;
    }
}