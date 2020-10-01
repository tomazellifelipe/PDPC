import java.util.concurrent.Semaphore;

public class ThreadParalela extends Thread {

    private int inicio, fim;
    private Semaphore conclusao;

    public ThreadParalela(int inicio, int fim, Semaphore conclusao) {
        this.inicio = inicio;
        this.fim = fim;
        this.conclusao = conclusao;
    }

    public void run() {
        Calculo c = new Calculo(this.inicio, this.fim);
        //c.multiplicarMatrizes(matrizA, matrizB)
        conclusao.release();        

    }

    

    
}
