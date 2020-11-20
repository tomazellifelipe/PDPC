import java.io.IOException;
import java.net.Socket;

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
        matrizA.setMatrizFromCSV("MatrizA.csv");
        matrizB = new Matriz(linhas, colunas);
        matrizB.setMatrizFromCSV("MatrizB.csv");
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
        
        try {
            for (int i = 0; i < N_SERVIDORES; i++) {
                sockets[i] = new Socket("localhost", ports[i]);
                coms[i] = new Comunicacao(sockets[i], matrizes, parametros[i]);
                coms[i].start();
                
            }
            for (Comunicacao com : coms) {
                com.join();
            }
            // salvar matriz C em arquivo
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    

}