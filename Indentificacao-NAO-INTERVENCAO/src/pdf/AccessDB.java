package pdf;

import org.apache.pdfbox.cos.COSDocument;
import org.apache.pdfbox.pdfparser.PDFParser;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.util.PDFTextStripper;

import java.io.*;
import java.sql.*;
import java.text.Normalizer;
import java.util.*;


public class AccessDB {

    public static void obterTexto() throws ClassNotFoundException, SQLException, IOException {

        String queryIds;
        String queryPdf;
        String nome_arquivo;
        String driver;
        String url;
        String username;
        String password;
        List<Map<String, List<String>>> mapPalavras = new ArrayList<>();


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

                String semAcentuacao = Normalizer.normalize(textoPdf, Normalizer.Form.NFD).replaceAll("[^\\p{ASCII}]", "");
                String semEspacoDuplo = semAcentuacao.replaceAll("\\s+", " ");
                String semPonto = semEspacoDuplo.replace(".", "");
                String semVirgula = semPonto.replace(",", "");
                String semDoisPonto = semVirgula.replace(":", "");
                String semPontoVirgula = semDoisPonto.replace(";", "");
                String semSimbolOrdinalª = semPontoVirgula.replace("ª", "");
                String semSimbolOrdinalº = semSimbolOrdinalª.replace("º", "");
                String semBarra = semSimbolOrdinalº.replace("/", "");
                String semSimboloComercial = semBarra.replace("|", "");
                String semParentes1 = semSimboloComercial.replace("(", "");
                String semParentes2 = semParentes1.replace(")", "");
                String semHifen = semParentes2.replace("-", " ");
                String conteudo = semHifen.toUpperCase();

                psExclui.setInt(1, new Integer(nome_arquivo).intValue());
                psExclui.execute();

                List<String> map = ChamaLista.removePalavrasInuteis(Arrays.asList(conteudo.split(" ")));

                for (int i = 0; i < map.size(); i++) {

                    String palavra = map.get(i);
                    System.out.println("nome_arquivo=" + nome_arquivo + " i=" + i + " palavra=" + palavra);

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