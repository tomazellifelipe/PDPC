import java.util.concurrent.Semaphore;

public class ThreadParalela extends Thread {

    private double[][] matrizA, matrizB, matrizC;
    private int inicio, fim;
    private Semaphore conclusao;

    public ThreadParalela(
        double[][] matrizA, 
        double[][] matrizB, 
        double[][] matrizC, 
        int inicio, 
        int fim, 
        Semaphore conclusao) {
        
        this.matrizA = matrizA;
        this.matrizB = matrizB;
        this.matrizC = matrizC;
        this.inicio = inicio;
        this.fim = fim;
        this.conclusao = conclusao;
    }

    public void run() {
        Calculo c = new Calculo(inicio, fim);
        c.multiplicarMatrizes(matrizA, matrizB, matrizC);
        conclusao.release();        

    }

    

    
}
