package v2;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;

public class Teste {

    public static void parsePdf(String pdf, String txt) throws IOException {

        PdfReader reader = new PdfReader(pdf);
        PdfReaderContentParser parser = new PdfReaderContentParser(reader);
        PrintWriter out = new PrintWriter(new FileOutputStream(txt));
        TextExtractionStrategy strategy;


        for (int i = 1; i <= reader.getNumberOfPages(); i++) {
            strategy = parser.processContent(i, new SimpleTextExtractionStrategy());
            out.println(strategy.getResultantText());
        }

        out.flush();
        out.close();
        out.close();

    }
}


        class Application {
            public static void main(String[] args) throws IOException {
                Teste.parsePdf("boleto-bradesco.pdf", "boleto-bradesco.txt");
            }
        }

