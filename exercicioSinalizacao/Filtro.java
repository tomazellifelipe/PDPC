import java.util.concurrent.Semaphore;

public class Filtro extends Thread {
    private String[] texto;
    private Semaphore gerador;
    private Semaphore geradorFiltro;
    private Semaphore analisadorFiltro;
    private Semaphore analisador;

    public Filtro(String[] texto, Semaphore a, Semaphore ba, Semaphore bc, Semaphore c) {
        this.texto = texto;
        this.gerador = a;
        this.geradorFiltro = ba;
        this.analisadorFiltro = bc;
        this.analisador = c;
    }

    public void run() {
        try {
            while (true) {
                geradorFiltro.acquire();
                analisadorFiltro.acquire();
                // TO DO ler texto e salvar uppercase
                texto[1] = texto[0].toUpperCase();
                System.out.println("Uppercase: " + texto[1]);
                gerador.release();
                analisador.release();

            }

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}