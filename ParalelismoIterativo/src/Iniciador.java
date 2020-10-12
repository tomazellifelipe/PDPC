import java.io.IOException;
import java.util.concurrent.Semaphore;

public class Iniciador {

    public static long executeSequencial(
        double[][] matrizA,
        double[][] matrizB, 
        double[][] matrizC ) throws IOException {
        
        long tempoInicio = System.currentTimeMillis();
        // processamento
        Calculo c = new Calculo(0, matrizA.length);
        c.multiplicarMatrizes( matrizA, matrizB, matrizC );
    
        long tempoFinal = System.currentTimeMillis();

        return tempoFinal - tempoInicio;
            
    }

    public static long executeParalela(
        int numThreads, 
        double[][] matrizA,
        double[][] matrizB,
        double[][] matrizC ) throws InterruptedException, IOException {

        Semaphore conclusao = new Semaphore(0);

        ThreadParalela[] threadsParalelas = new ThreadParalela[numThreads];
        int passo = matrizA.length / numThreads; // numero de colunas;
        int inicio = 0;
        int fim = inicio + passo;

        long tempoInicio = System.currentTimeMillis();
        // processamento
        for (int i = 0; i < threadsParalelas.length; i++) {
            threadsParalelas[i] = new ThreadParalela( matrizA, matrizB, matrizC, 
                                                      inicio, fim, 
                                                      conclusao);
            inicio = inicio + passo;
            fim = inicio + passo;
        }
        
        for (int i = 0; i < threadsParalelas.length; i++) {
            threadsParalelas[i].start();
        }

        conclusao.acquire(numThreads);
        
        long tempoFinal = System.currentTimeMillis();

        return tempoFinal - tempoInicio;
    }
}
