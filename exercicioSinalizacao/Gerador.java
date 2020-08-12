import java.util.Random;
import java.util.concurrent.Semaphore;

public class Gerador extends Thread {
    private String[] texto;
    private Semaphore gerador;
    private Semaphore filtro;

    public String generatedString(int n) {
        String alphaString = "ABCDEFGHIJKLMNOPQRSTUVWXYZ" + "abcdefghijklmnopqrstuvxyz";
        StringBuilder sb = new StringBuilder(n);
        Random r = new Random();
        for (int i = 0; i < n; i++) {
            sb.append(alphaString.charAt(r.nextInt(alphaString.length())));

        }
        return sb.toString();

    }

    public Gerador(String[] texto, Semaphore a, Semaphore ba) {
        this.gerador = a;
        this.filtro = ba;
        this.texto = texto;
    }

    public void run() {
        try {
            while (true) {
                gerador.acquire();
                // TO DO enviar texto
                texto[0] = generatedString(10);
                System.out.println("Random String: " + texto[0]);
                filtro.release();

            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}