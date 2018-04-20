package pdf;

import java.io.*;

import org.apache.pdfbox.cos.COSDocument;
import org.apache.pdfbox.pdfparser.PDFParser;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.util.PDFTextStripper;

public class TrataPDF {

    public static void obterTexto() {

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

            // String conteudoPDFSemAcentuacao = Normalizer.normalize(textoPdf, Normalizer.Form.NFD).replaceAll("\\p{InCombiningDiacriticalMarks}+", "");
            //String conteudoSemEspacoMaiuscula = conteudoPDFSemAcentuacao.replaceAll("\\s+", " ").toUpperCase();


            //int cnj = rs.getString("cnj");
            int dois = 2;

            // para palavras que contém 2 caracteres remova e também remova o espaço

            while (textoPdf.contains() == 2){

                // com traços e pontos
                //cnj = rs.getString("cnj"); //.replaceAll("[^0-9]", "");  //sem traços e pontos

                int array[] = new int[1];

                for (int i = 0; i <= array.length; i++) {

                    if (cnj.length() == cnj22) { // 22 dígitos acrescenta 3 dígitos e imprime
                    }
                    int words = 2;

                    for (int i = 0; i <= textoPdf.length(); i++) {
                        System.out.println(textoPdf.toUpperCase().replace("", ""));

                    }
                }
            }
        }
    }
}



    //String conteudoPDFSemAcentuacao = Normalizer.normalize(textoPdf, Normalizer.Form.NFD).replaceAll("\\p{InCombiningDiacriticalMarks}+", "");

       // String conteudoSemEspacoMaiuscula = conteudoPDFSemAcentuacao.replaceAll("\\s+", " ").toUpperCase();

        //List<String> palavras = ChamaLista.removePalavrasInuteis(Arrays.asList(conteudoSemEspacoMaiuscula.split(" ,")));

        //return palavras.toString().replaceAll("", "").toUpperCase();
   // }

    /*private static String removePalavrasInuteis(String texto) {
        String textoLimpo = null;
            for (String palavraInutil : Palavras.PALAVRASINUTEIS) {
                if (texto.contains(palavraInutil)) {
                    textoLimpo = texto.replaceAll(palavraInutil, "");
                }
            }

        return textoLimpo;
    }*/