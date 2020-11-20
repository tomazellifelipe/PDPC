import java.net.Socket;
import java.util.concurrent.Semaphore;

/**
 * Coordenador
 */
public class Coordenador {

    private static int[][] parametros = new int[4][2];
    private static final int N_SERVIDORES = 4;
    private static final int[] ports = {4000, 4500, 5000, 5500};
    private static Matriz matrizA, matrizB, matrizC;
    private static int linhas = 400, colunas = 400;
    private static Socket[] sockets = new Socket[N_SERVIDORES];
    private static Comunicacao[] coms = new Comunicacao[N_SERVIDORES];

    public static void main(String[] args) {
        matrizA = new Matriz(linhas, colunas);
        matrizA.setMatrizFromCSV("./src/matrizA.csv");
        matrizB = new Matriz(linhas, colunas);
        matrizB.setMatrizFromCSV("./src/matrizB.csv");
        matrizC = new Matriz(linhas, colunas);
        Matriz[] matrizes = {matrizA, matrizB, matrizC};
        
        int passo = linhas / N_SERVIDORES;
        int inicio = 0;
        int fim = passo;
        for (int i = 0; i < N_SERVIDORES; i++) {
            parametros[i][0] = inicio;
            parametros[i][1] = fim;
            inicio = fim;
            fim = inicio + passo;    
        }

        Semaphore semaphore = new Semaphore(0);
        
        try {
            for (int i = 0; i < N_SERVIDORES; i++) {
                sockets[i] = new Socket("localhost", ports[i]);
                coms[i] = new Comunicacao(sockets[i], matrizes, parametros[i], semaphore);
                coms[i].start();
                
            }
            semaphore.acquire(N_SERVIDORES);
            CSVHandler.criarCSV("./src/matrizC.csv", matrizes[2].getMatriz());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    

}