import java.util.concurrent.Semaphore;

public class Sinalapp {
    public static void main(String[] args) {
        // TO DO vetor com 10 char
        String[] texto = { "", "" };
        //
        Semaphore mutexGerador = new Semaphore(1);
        Semaphore mutexGerFiltro = new Semaphore(0);
        Semaphore mutexFilAnalis = new Semaphore(1);
        Semaphore mutexAnalisador = new Semaphore(0);

        Gerador gerador = new Gerador(texto, mutexGerador, mutexGerFiltro);
        Filtro filtro = new Filtro(texto, mutexGerador, mutexGerFiltro, mutexFilAnalis, mutexAnalisador);
        Analisador analisador = new Analisador(texto, mutexAnalisador, mutexFilAnalis);

        gerador.start();
        filtro.start();
        analisador.start();
    }
}