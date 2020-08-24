import java.util.concurrent.Semaphore;

public class Filtro extends Thread {
    private char[] v1;
    private char[] v2;
    private Semaphore gerador_escrita;
    private Semaphore filtro_leitura;
    private Semaphore filtro_escrita;
    private Semaphore analisador_leitura;

    private int contador;

    public Filtro(char[] v1, char[] v2,
                  Semaphore gerador_escrita,
                  Semaphore filtro_leitura,
                  Semaphore filtro_escrita,
                  Semaphore analisador_leitura) {
        this.v1 = v1;
        this.v2 = v2;
        this.gerador_escrita = gerador_escrita;
        this.filtro_leitura = filtro_leitura;
        this.filtro_escrita = filtro_escrita;
        this.analisador_leitura = analisador_leitura;
        contador = 0;
    }

    public void run() {
        while (true) {
            try {
                filtro_leitura.acquire(); // pede acesso a v1 (aguarda o Gerador)
                filtro_escrita.acquire(); // pede acesso a v2 (aguarda o Analisador)
                    filtre();
                analisador_leitura.release(); // libera v2 (sinaliza o Analisador)
                gerador_escrita.release(); // libera v1 (sinaliza o Gerador)
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private void filtre() {
        contador++;
        System.out.println("Filtrando ..." + contador);
        Simulador.dormir(1000, 3000);

        for (int i = 0; i < v1.length; i++)
            v2[i] = Character.toUpperCase( v1[i] );
    }
}
