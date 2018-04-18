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

    public static String pdf() {

        PDFParser parser = null;
        PDDocument pdDoc = null;
        COSDocument cosDoc = null;
        PDFTextStripper pdfStripper;

        String interiorPdf;
        String fileName = "c:/users/alanne.soares/documents/teste-1.pdf";
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

            List<String> palavraSemPreposicao = removePreposicao(Arrays.asList(conteudoSemEspacoMaiuscula.split(" ")));


            return conteudoPDFSemAcentuacao.toUpperCase();

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }



    public static List<String> removePreposicao(List<String> palavras) {

        String[] EXECAOPALAVRA = {
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

        };

        List<String> palavraSemPreposicao = new ArrayList<>(palavras);

        for (String palavra : palavras) {

            for (String preposicao : EXECAOPALAVRA) {

                if (palavra.equals(preposicao)) {
                    palavraSemPreposicao.remove(preposicao);
                }
            }
        }

        return palavraSemPreposicao;

    }
}
