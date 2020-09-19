import java.io.*;
import java.util.*;
import java.util.concurrent.Semaphore;

public class Combinadora extends Thread {

    private int numeroDoArquivo = 0;
    private Semaphore mutex, semCombinadora;
    private ArrayList<String> listaDeArquivos;
    private ArrayList<Integer> listaCompleta = new ArrayList<Integer>();

    public Combinadora(ArrayList<String> listaDeArquivos, Semaphore mutex, Semaphore semCombinadora) {
        this.listaDeArquivos = listaDeArquivos;
        this.mutex = mutex;
        this.semCombinadora = semCombinadora;

    }

    public void carregarArquivos() throws IOException, ClassNotFoundException {
        for (String nomeDoArquivo : listaDeArquivos) {
            ArrayList<Integer> arquivo = ManipularArquivo.abrir(nomeDoArquivo);
            listaCompleta.removeAll(arquivo);
            listaCompleta.addAll(arquivo);
        }
        Collections.sort(listaCompleta);

    }

    public void run() {
        try {
            while (true) {
                semCombinadora.acquire();
                mutex.acquire();
                carregarArquivos(); // carrega, faz merge e ordena
                String nomeDoArquivo = "ArquivoCombDeInts" + numeroDoArquivo;
                ManipularArquivo.salvar(nomeDoArquivo, listaCompleta);
                numeroDoArquivo++;
                mutex.release();
                semCombinadora.release();

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
