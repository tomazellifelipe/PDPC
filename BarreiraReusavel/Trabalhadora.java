import java.util.ArrayList;
import java.io.*;
import java.util.*;
import java.util.Random;
import java.util.concurrent.Semaphore;

public class Trabalhadora extends Thread {

    private final int MAX_TAMANHO_LISTA;
    private int numeroDoArquivo = 0;
    private int idDaTrabalhadora;
    private ArrayList<Integer> listaDeInts = new ArrayList<Integer>();
    private ArrayList<String> listaDeArquivos;
    private Semaphore mutex, barreiraEntrada, barreiraSaída;
    private Random r = new Random();

    public Trabalhadora(int id, int listSize, ArrayList<String> filesList, Semaphore mutex, Semaphore barreiraEntrada,
            Semaphore barreiraSaida) {
        this.idDaTrabalhadora = id;
        this.MAX_TAMANHO_LISTA = listSize;
        this.listaDeArquivos = filesList;
        this.mutex = mutex;
        this.barreiraEntrada = barreiraEntrada;
        this.barreiraSaída = barreiraSaida;
    }

    private int gerarInt(int bound) {
        return r.nextInt(bound + 1);
    }

    private void addIntNaLista(int maxListSize) {
        for (int i = 0; i < maxListSize; i++) {
            this.listaDeInts.add(gerarInt((int) Math.pow(10, 7)));
        }
        return;
    }

    private void sortLista(ArrayList<Integer> list) {
        Collections.sort(list);
        return;
    }

    private String criarArquivo() throws IOException {
        String nomeDoArquivo = "ArrayListFile" + numeroDoArquivo + "_" + idDaTrabalhadora + ".ser";
        ManipularArquivo.salvar(nomeDoArquivo, listaDeInts);
        numeroDoArquivo++;
        return nomeDoArquivo;
    }

    public void run() {
        try {
            while (true) {
                addIntNaLista(MAX_TAMANHO_LISTA);
                sortLista(this.listaDeInts);
                String nomeDoArquivo = criarArquivo();
                mutex.acquire();
                Main.contador++;
                if (Main.contador == Main.MAX_TRABALHADORAS) {
                    barreiraSaída.acquire(); // fecha
                    barreiraEntrada.release(); // abre
                }
                mutex.release();
                barreiraEntrada.acquire();
                listaDeArquivos.add(nomeDoArquivo); // insere o nome do arquivo na lista de arquivos
                barreiraEntrada.release();
                mutex.acquire();
                Main.contador--;
                if (Main.contador == 0) {
                    barreiraEntrada.acquire(); // fecha
                    barreiraSaída.release(); // abre
                }
                mutex.release();
                barreiraSaída.acquire();

                barreiraSaída.release();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
