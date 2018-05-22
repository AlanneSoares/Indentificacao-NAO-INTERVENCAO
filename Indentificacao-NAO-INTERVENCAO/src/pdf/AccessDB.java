package pdf;

import java.io.File;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AccessDB {

    public static List obterTexto() throws ClassNotFoundException, SQLException, IOException {

        String query;
        String driver;
        String url;
        String username;
        String password;


        // ACESSO AO BANCO
        query = "SELECT MAND.MAAN_PCAO_DK AS nome_arquivo,OBA.OBJX_OBJETO_ANEXO AS pdf, SUBA.STAO_TPPR_DK AS tipo_andamento FROM TJRJ.TJRJ_MANIFESTACAO_ANDAMENTO mand INNER JOIN MCPR.MCPR_SUB_ANDAMENTO suba ON SUBA.STAO_PCAO_DK = MAND.MAAN_PCAO_DK AND SUBA.STAO_TPPR_DK = 6512 INNER JOIN MCPR.MCPR_OBJETOS_ANEXOS oba ON OBA.OBJX_PCAO_DK = MAND.MAAN_PCAO_DK WHERE 1=1 AND MAND.MAAN_DT_INCLUSAO BETWEEN to_date('01/05/2018','dd/mm/yyyy') AND  to_date('03/05/2018','dd/mm/yyyy') AND MAND.MAAN_PCAO_DK NOT IN (-1) ORDER BY 1";
        driver = "oracle.jdbc.driver.OracleDriver";
        url = "jdbc:oracle:thin:@(DESCRIPTION=(LOAD_BALANCE=on)(ADDRESS = (PROTOCOL=TCP)(HOST=exa-scan.pgj.rj.gov.br)(PORT=1521))(CONNECT_DATA = (SERVICE_NAME=CORR)))";
        username = "TJRJ_WEBSERVICE_CON";
        password = "TJRJ_WEBSERVICE_CON";

        Class.forName(driver);

        Connection connection = DriverManager.getConnection(url, username, password);
        PreparedStatement ps = connection.prepareStatement(query);
        ResultSet rs = ps.executeQuery();

        List lista = new ArrayList();

        while (rs.next()) {
            Blob pdf = rs.getBlob("PDF");

                lista.add(pdf);
            }



            //File file = new File(rs);
            //int count = file.listFiles().lenght;

            //arquivo = rs.getString("nome_arquivo");
            //arquivo;
            //int array[] = new int[0];
            //for (int i = 0; i <= array.length; obterTexto()) {
            //if (arquivo.length() == 10)
            //System.out.println(arquivo);

            //File arquivo = new File(String.valueOf(rs));

            // Carrega todos os arquivos em um vetor
            //File[] arquivos = arquivo.listFiles();
            //int aux = 0;

            // Se há arquivos, entra nesta rotina
            //if(arquivos != null){
            // int length = arquivos.length;
            //for (int i = 0; i < length; ++i) {
            // File f = arquivos[i];
            //if (f.isFile()) {
            // aux++;



        return lista;

    }
}



            //File file = new File(arquivo.toString());
            //StringBuffer br = new StringBuffer((CharSequence) new FileReader(file));

            //List<String> txt = new ArrayList<>();


            /*for (int i = 1; i <= 10; i++) {
                System.out.println(arquivo);
            }

            //System.out.println(br);

        }
        return obterTexto();
    }


    //public int contarArquivos (File path, String extensao) {

       //

    //}
}

           /* PDFParser parser;
            PDDocument pdDoc = null;
            COSDocument cosDoc;
            PDFTextStripper pdfStripper = null;

            String textoPdf = null;*/
            //String fileName = "c:/users/alanne.soares/documents/teste.pdf";
            //File file = new File(fileName);

            //try {

                /*parser = new PDFParser(new FileInputStream(file));
                parser.parse();
                cosDoc = parser.getDocument();
                pdfStripper = new PDFTextStripper();
                pdDoc = new PDDocument(cosDoc);
                textoPdf = pdfStripper.getText(pdDoc);*/

               // textoPdf = pdfStripper.getText(pdDoc);
           /* } catch (Exception e) {
                e.printStackTrace();
            }

            String conteudoPDFSemAcentuacao = Normalizer.normalize(textoPdf, Normalizer.Form.NFD).replaceAll("[^\\p{ASCII}]", "");
            String conteudoSemEspacoMaiuscula = conteudoPDFSemAcentuacao.replaceAll("\\s+", " ").toUpperCase();
            String conteudoSemPontuacao = conteudoSemEspacoMaiuscula.replaceAll("\\p{Punct}", "");

            List<String> palavras = ChamaLista.removePalavrasInuteis(Arrays.asList(conteudoSemPontuacao.split(" ")));

            return palavras.toString().replace("[", "").replace("]", "").replaceAll(",", "");
        }

        return null;*/


  /*  private static String removePalavrasInuteis(String texto) {
        String textoLimpo = null;
            for (String palavraInutil : Palavras.PALAVRASINUTEIS) {
                if (texto.contains(palavraInutil)) {
                    textoLimpo = texto.replaceAll(palavraInutil, "");
                }
            }

        return textoLimpo;
    //}*/










