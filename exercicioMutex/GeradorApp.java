import java.util.concurrent.Semaphore;

public class GeradorApp {

    public static void main(String[] args) {

        int[] chave = new int[100];
        int[] pares = { 0 };
        int[] impares = { 0 };
        Semaphore mutex = new Semaphore(1);

        GeradorPares gPares1 = new GeradorPares(chave, pares, impares, mutex);
        GeradorPares gPares2 = new GeradorPares(chave, pares, impares, mutex);
        GeradorImpares gImpares1 = new GeradorImpares(chave, pares, impares, mutex);
        GeradorImpares gImpares2 = new GeradorImpares(chave, pares, impares, mutex);

        gPares1.start();
        gPares2.start();
        gImpares1.start();
        gImpares2.start();

        try {
            gPares1.join();
            gPares2.join();
            gImpares1.join();
            gImpares2.join();
            for (int i : chave) {
                System.out.println(i);
            }
            System.out.println("Pares: " + pares[0]);
            System.out.println("Impares: " + impares[0]);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}