package pdf;

public class Application {
    public static void main(String args[]) {
        System.out.println(TrataPDF.obterTexto());

        Trilha cidadeAtoBtoC = new Trilha();
        System.out.println("De A para A = "+ cidadeAtoBtoC.addPonto(0,50,100));
        System.out.println("De A para B = " + cidadeAtoBtoC.addPonto(50,0,30));
        System.out.println("De B para C = " + cidadeAtoBtoC.addPonto(100,30,0));
        System.out.println("Distancia total = " + cidadeAtoBtoC.distancia());

    }
}