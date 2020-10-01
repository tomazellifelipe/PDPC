import java.io.IOException;
import java.util.concurrent.Semaphore;

public class Iniciador {

    public static long executeSequencial(
        int id, 
        int[] setup ) throws IOException {
        
        long tempoInicio = System.currentTimeMillis();
        double[][] matrizA = CSVHandler.lerCSV( "matrizA" + id + ".csv", setup[0], setup[1] );
        double[][] matrizB = CSVHandler.lerCSV( "matrizB" + id + ".csv", setup[1], setup[2] );
        Calculo c = new Calculo(0, setup[1]);
        double[][] matrizResultante = c.multiplicarMatrizes( matrizA, matrizB );
        CSVHandler.criarCSV( "matrizC" + id + ".csv", matrizResultante );
        long tempoFinal = System.currentTimeMillis();
        return tempoFinal - tempoInicio;
            
    }

    public static long executeParalela(int numThreads, int dimensao) throws InterruptedException {

        Semaphore conclusao = new Semaphore(0);

        ThreadParalela[] tParalelas = new ThreadParalela[numThreads];
        int p = dimensao / numThreads; // numero de colunas;
        int inicio = 0;
        int fim = inicio + p;
        for (int i = 0; i < tParalelas.length; i++) {
            tParalelas[i] = new ThreadParalela(inicio, fim, conclusao);
            inicio = inicio + p;
            fim = inicio + p;
        }

        long tempoInicio = System.currentTimeMillis();
        for (int i = 0; i < tParalelas.length; i++) {
            tParalelas[i].start();
        }

        conclusao.acquire(4);
        long tempoFinal = System.currentTimeMillis();

        return tempoFinal - tempoInicio;
    }
}
