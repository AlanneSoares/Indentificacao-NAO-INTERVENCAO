package writePDF;

import lerpdf.COSDocument;
import java.io.RandomAccessFile;

public class PDFParser {

    COSDocument document;


    public PDFParser(RandomAccessFile r) { }

    public void parse() { }

    public COSDocument getDocument() {
        return document;
    }
}
