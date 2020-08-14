import java.util.Random;
import java.util.concurrent.Semaphore;

public class Tarefa2 extends Thread {

    private int[] x;
    private Semaphore mutex;

    public Tarefa2(int[] x, Semaphore mutex) {
        this.x = x; this.mutex = mutex; }

    public void run() {
        try {
            Random r = new Random();
            Thread.sleep(r.nextInt(7000));

            //x = x * 2;
            //x[0] = x[0] * 2;
            mutex.acquire(); // esperar
                int temp = x[0];
                Thread.sleep(r.nextInt(7000));
                temp = temp * 2;
                x[0] = temp;
                System.out.println("Tarefa2: " + x[0]);
            mutex.release(); // sinalizar

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
