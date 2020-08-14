import java.util.concurrent.Semaphore;

public class TesteMutex {

    public static void main(String[] args) {

        int[] x = {10};
        Semaphore mutex = new Semaphore(1);

        Tarefa1 t1 = new Tarefa1( x, mutex );
        Tarefa2 t2 = new Tarefa2( x, mutex );

        t1.start();
        t2.start();

        try {
            t1.join(); // espera a thread t1 terminar
            t2.join(); // espera a thread t2 terminar
            System.out.println("Main: " + x[0]);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
