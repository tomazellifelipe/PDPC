import java.io.*;
import java.util.*;
import java.util.concurrent.Semaphore;

public class Combinadora extends Thread {

    private int counter = 0;
    private int arquivosRecebidos = 0;
    private Semaphore mutex, barreiraEntrada, barreiraSaida, semCombinadora;
    private ArrayList<String> listaDeArquivos;

    public Combinadora(ArrayList<String> listaDeArquivos, Semaphore mutex,
            Semaphore barreiraEntrada, Semaphore barreiraSaida, Semaphore semCombinadora) {
        this.listaDeArquivos = listaDeArquivos;
        this.mutex = mutex;
        this.barreiraEntrada = barreiraEntrada;
        this.barreiraSaida = barreiraSaida;
        this.semCombinadora = semCombinadora;
    }

    public void run() {
        try {
            while (true) {
                // simulate fix (just training issues)
                // still don't know how to do it
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public ListaDeInteiros carregarArquivos(ListaDeInteiros list)
            throws IOException, ClassNotFoundException {
        for (String nome : listaDeArquivos) {
            System.out.println(this.getName() + " carregando arquivos...");
            ListaDeInteiros arquivo = ManipularArquivo.abrir(nome);
            list.getList().removeAll(arquivo.getList());
            list.getList().addAll(arquivo.getList());
        }
        list.ordenar();
        return list;
    }
}
