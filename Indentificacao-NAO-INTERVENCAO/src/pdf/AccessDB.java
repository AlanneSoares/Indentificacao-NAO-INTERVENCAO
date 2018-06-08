package pdf;

import org.apache.pdfbox.cos.*;
import org.apache.pdfbox.pdfparser.*;
import org.apache.pdfbox.pdmodel.*;
import org.apache.pdfbox.util.*;
import java.sql.*;
import java.util.*;


public class AccessDB {

    public static void obterTexto() throws ClassNotFoundException, SQLException {

        String queryIds;
        String queryPdf;
        String nome_arquivo;
        String driver;
        String url;
        String username;
        String password;


        // ACESSO AO BANCO
        queryIds = "SELECT MAND.MAAN_PCAO_DK AS nome_arquivo FROM TJRJ.TJRJ_MANIFESTACAO_ANDAMENTO mand INNER JOIN MCPR.MCPR_SUB_ANDAMENTO suba ON SUBA.STAO_PCAO_DK = MAND.MAAN_PCAO_DK AND SUBA.STAO_TPPR_DK = 6512 INNER JOIN MCPR.MCPR_OBJETOS_ANEXOS oba ON OBA.OBJX_PCAO_DK = MAND.MAAN_PCAO_DK WHERE 1=1 AND MAND.MAAN_DT_INCLUSAO BETWEEN to_date('01/05/2018','dd/mm/yyyy') AND  to_date('03/05/2018','dd/mm/yyyy') AND MAND.MAAN_PCAO_DK NOT IN (-1) ORDER BY 1";
        driver = "oracle.jdbc.driver.OracleDriver";
        url = "jdbc:oracle:thin:@(DESCRIPTION=(LOAD_BALANCE=on)(ADDRESS = (PROTOCOL=TCP)(HOST=exa-scan.pgj.rj.gov.br)(PORT=1521))(CONNECT_DATA = (SERVICE_NAME=CORR)))";
        username = "TJRJ_WEBSERVICE_CON";
        password = "TJRJ_WEBSERVICE_CON";

        Class.forName(driver);

        Connection connection = DriverManager.getConnection(url, username, password);

        PreparedStatement psExclui = connection.prepareStatement("delete from TJRJ.TJRJ_ARQUIVO_PALAVRA where nr_arquivo=?");

        PreparedStatement ps = connection.prepareStatement(queryIds);
        ResultSet rsIds = ps.executeQuery();

        String inserePalavra = "insert into TJRJ.TJRJ_ARQUIVO_PALAVRA (nr_arquivo, seq_palavra, de_palavra) values (?,?,?)";
        PreparedStatement psInsere = connection.prepareStatement(inserePalavra);

        while (rsIds.next()) {

            nome_arquivo = rsIds.getString("NOME_ARQUIVO");
            queryPdf = "select MAND.MAAN_PCAO_DK as nome_arquivo,OBA.OBJX_OBJETO_ANEXO as pdf, SUBA.STAO_TPPR_DK as tipo_andamento " +
                    "FROM TJRJ.TJRJ_MANIFESTACAO_ANDAMENTO mand INNER JOIN MCPR.MCPR_SUB_ANDAMENTO suba ON SUBA.STAO_PCAO_DK = MAND.MAAN_PCAO_DK AND SUBA.STAO_TPPR_DK = 6512 INNER JOIN MCPR.MCPR_OBJETOS_ANEXOS oba ON OBA.OBJX_PCAO_DK = MAND.MAAN_PCAO_DK " +
                    "WHERE 1=1 AND MAND.MAAN_DT_INCLUSAO BETWEEN to_date('01/05/2018','dd/mm/yyyy') AND  to_date('03/05/2018','dd/mm/yyyy') AND MAND.MAAN_PCAO_DK NOT IN (-1) " +
                    "and MAND.MAAN_PCAO_DK = '" + nome_arquivo + "'" +
                    "ORDER BY 1";

            ps = connection.prepareStatement(queryPdf);
            ResultSet rsPdf = ps.executeQuery();

            rsPdf.next();
            Blob pdf = rsPdf.getBlob("PDF");

            PDFParser parser;
            PDDocument pdDoc;
            COSDocument cosDoc;
            PDFTextStripper pdfStripper;
            String textoPdf = pdf.toString() + nome_arquivo + ".pdf";


            try {

                parser = new PDFParser(pdf.getBinaryStream());
                parser.parse();
                cosDoc = parser.getDocument();
                pdfStripper = new PDFTextStripper();
                pdDoc = new PDDocument(cosDoc);
                textoPdf = pdfStripper.getText(pdDoc);

                psExclui.setInt(1, new Integer(nome_arquivo).intValue());
                psExclui.execute();

                List<String> map = ChamaLista.removePalavrasInuteis(Arrays.asList(CaracterEspecial.deleteCaracter(textoPdf).split(" ")));

                for (int i = 1; i < map.size(); i++) {

                    String palavra = map.get(i);
                    System.out.println("Nome: " + nome_arquivo + " Sequência: " + i + " Palavra: " + palavra);

                    psInsere.setInt(1, new Integer(nome_arquivo).intValue());
                    psInsere.setInt(2, i + 1);
                    psInsere.setString(3, palavra);
                    psInsere.execute();

                }

                connection.commit();
                cosDoc.close();

                connection.commit();

            } catch (Exception e) {

                e.printStackTrace();
            }
        }
    }
}