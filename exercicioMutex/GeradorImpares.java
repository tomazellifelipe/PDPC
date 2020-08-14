import java.util.Random;
import java.util.concurrent.Semaphore;

public class GeradorImpares extends Thread {
    private int[] chave;
    private int[] pares;
    private int[] impares;
    private Semaphore mutex;

    public GeradorImpares(int[] chave, int[] pares, int[] impares, Semaphore mutex) {
        this.chave = chave;
        this.pares = pares;
        this.impares = impares;
        this.mutex = mutex;

    }

    public void run() {
        Random r = new Random();
        try {
            while (pares[0] + impares[0] < 100) {
                Thread.sleep(r.nextInt(2000) + 1000);
                mutex.acquire();
                int n = r.nextInt(10);
                do {
                    n = r.nextInt(10);
                } while (n % 2 == 0);
                if (pares[0] + impares[0] < 100) {
                    chave[pares[0] + impares[0]] = n;
                    impares[0]++;
                }
                mutex.release();

            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}