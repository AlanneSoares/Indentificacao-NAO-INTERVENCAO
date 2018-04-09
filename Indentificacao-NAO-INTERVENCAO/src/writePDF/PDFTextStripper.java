package writePDF;

import lerpdf.PDDocument;

public class PDFTextStripper {

    private int endPage;
    private int startPage;

    public void setStartPage(int startPage) {
        this.startPage = startPage;
    }

    public void setEndPage(int endPage) {
        this.endPage = endPage;
    }

    public void getText(PDDocument pdDoc) { }
}
