package pdf;

import java.io.IOException;
import java.sql.SQLException;

public class Application {

    public static void main(String args[]) throws SQLException, ClassNotFoundException, IOException {

        AccessDB.obterTexto();
        
    }
}