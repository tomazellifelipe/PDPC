import java.io.*;
import java.util.*;
import java.util.concurrent.Semaphore;

public class Combinadora extends Thread {

    private Semaphore mutex, semCombinadora;
    private ArrayList<String> listaDeArquivos;

    public Combinadora(ArrayList<String> listaDeArquivos, Semaphore mutex, Semaphore semCombinadora) {
        this.listaDeArquivos = listaDeArquivos;
        this.mutex = mutex;
        this.semCombinadora = semCombinadora;
    }

    public void run() {
        try {
            while (true) {
                ListaDeInteiros lista = new ListaDeInteiros();
                semCombinadora.acquire();
                mutex.acquire();
                carregarArquivos(lista); // carrega, faz merge e ordena
                mutex.release();
                String nome = this.getName() + ".ser";
                ManipularArquivo.salvar(nome, lista);
                System.out.println("Arquivo gerado por: " + this.getName());
                semCombinadora.release();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void carregarArquivos(ListaDeInteiros list) throws IOException, ClassNotFoundException {
        for (String nome : listaDeArquivos) {
            ListaDeInteiros arquivo = ManipularArquivo.abrir(nome);
            list.getList().removeAll(arquivo.getList());
            list.getList().addAll(arquivo.getList());
        }
        list.ordenar();
    }
}
