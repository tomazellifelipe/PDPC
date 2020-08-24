import java.util.concurrent.Semaphore;

public class Sinalizacao {
    public static void main(String args[]) {
        final int TAMANHO = 10;
        char[] v1 = new char[TAMANHO];
        char[] v2 = new char[TAMANHO];

        Semaphore gerador_escrita = new Semaphore(1);
        Semaphore filtro_leitura = new Semaphore(0);

        Semaphore filtro_escrita = new Semaphore(1);
        Semaphore analisador_leitura = new Semaphore(0);

        Gerador gerador = new Gerador(v1, gerador_escrita, filtro_leitura);
        Filtro filtro = new Filtro(v1, v2, gerador_escrita, filtro_leitura,
                filtro_escrita, analisador_leitura);
        Analisador analisador = new Analisador(v2, filtro_escrita, analisador_leitura);
        gerador.start();
        filtro.start();
        analisador.start();
    }
}
