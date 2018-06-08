package pdf;

import java.util.ArrayList;
import java.util.List;

public class ChamaLista {

    static List<String> removePalavrasInuteis(List<String> palavras) {

        List<String> palavraSemPreposicao = new ArrayList<>(palavras);

        for (String palavra : palavras) {

            if (palavra.length() <= 2) {

                palavraSemPreposicao.remove(palavra);

            }

            else {

                for (String preposicao : Palavras.PALAVRASINUTEIS) {

                    if (palavra.equals(preposicao)) {

                        palavraSemPreposicao.remove(preposicao);

                        }
                    }
                }
            }

        return palavraSemPreposicao;

    }
}