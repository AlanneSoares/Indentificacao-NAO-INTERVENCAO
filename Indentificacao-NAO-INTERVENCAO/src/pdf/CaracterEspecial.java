package pdf;

import java.text.Normalizer;

public class CaracterEspecial {

    public static String deleteCaracter(String pdf){

        pdf = Normalizer.normalize(pdf, Normalizer.Form.NFD)
                .replaceAll("[^\\p{ASCII}]", "")
                .replaceAll("\\s+", " ")
                .replace(".", "")
                .replace(",", "")
                .replace(";", "")
                .replace("ª", "")
                .replace("/", "")
                .replace("|", "")
                .replace("(", "")
                .replace(")", "")
                .replace("-", " ")
                .replace(":", "")
                .replace("%", "")
                .replace("'", "")
                .trim()
                .toUpperCase();

        return pdf;
    }
}
