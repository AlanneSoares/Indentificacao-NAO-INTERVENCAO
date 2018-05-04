/*



Boa noite,
eu estava procurando como ler algo do pdf (comecei agora a usar o iText para ler pdf's), seu exemplo foi de grande ajuda.
para pegar linha por linha utilize o método split, que lhe retorna uma array de String's de acordo com o texto que você passar como delimitador.
Como ficou o código:

String texto = PdfTextExtractor.getTextFromPage(reader, 1);
 String[] linhas = texto.split("\n");
Agora você tem uma String para cada linha.
Entendeu ?



 */

package pdf;

import java.io.*;
import java.text.Normalizer;

import org.apache.pdfbox.cos.COSDocument;
import org.apache.pdfbox.pdfparser.PDFParser;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.util.PDFTextStripper;


public class TrataPDF {

    public static String obterTexto() {

        // CHAMA DOCUMENTO
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


        String conteudoPDFModificado = Normalizer.normalize(textoPdf, Normalizer.Form.NFD).replaceAll("[^\\p{ASCII}]", "").toUpperCase()  //remove acentuação e transforma em maiúsculas

                .replaceAll("[.,;?:/|()]", "") // remove pontuação
                .replaceAll("[-]", " ") //remove traços
                .replaceAll("\\s[A-Z]{1,2}\\s", " ");


        return conteudoPDFModificado;

    }
}