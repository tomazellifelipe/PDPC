import java.util.concurrent.Semaphore;

public class Analisador extends Thread {
    private String[] texto;
    private Semaphore analisador;
    private Semaphore analisadorFiltro;

    public Analisador(String[] texto, Semaphore c, Semaphore bc) {
        this.texto = texto;
        this.analisador = c;
        this.analisadorFiltro = bc;
    }

    public void run() {
        try {
            while (true) {
                analisador.acquire();
                // TO DO conta vogais
                int temp = 0;
                for (char c : texto[1].toCharArray()) {
                    if (c == 'A' || c == 'E' || c == 'I' || c == 'O' || c == 'U') {
                        temp++;
                    }
                }
                System.out.println("Vowals count:" + temp);
                analisadorFiltro.release();

            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}