package src;

import java.io.*;
import java.util.*;
import java.util.concurrent.Semaphore;

public class Combinadora extends Thread {

    private int counter = 0;
    private int arquivosRecebidos = 0;
    private Semaphore mutex, semCombinadora;
    private ArrayList<String> listaDeArquivos;

    public Combinadora(ArrayList<String> listaDeArquivos, Semaphore mutex,
            Semaphore semCombinadora) {
        this.listaDeArquivos = listaDeArquivos;
        this.mutex = mutex;
        this.semCombinadora = semCombinadora;
    }

    public void run() {
        try {
            while (true) {
                semCombinadora.acquire();
                semCombinadora.acquire();
                semCombinadora.acquire();
                semCombinadora.acquire();
                // start mutex block
                mutex.acquire();
                ListaDeInteiros inteiros = new ListaDeInteiros();
                for (int i = arquivosRecebidos; i < listaDeArquivos.size(); i++) {
                    ListaDeInteiros aux = carregarArquivos(listaDeArquivos.get(i));
                    inteiros.getList().removeAll(aux.getList());
                    inteiros.getList().addAll(aux.getList());
                    arquivosRecebidos++;
                }
                mutex.release();
                // end mutex block1
                String nome = this.getName() + counter + ".ser";
                ManipularArquivo.salvar(nome, inteiros);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public ListaDeInteiros carregarArquivos(String nome)
            throws IOException, ClassNotFoundException {
        System.out.println(this.getName() + " carregando arquivos...");
        ListaDeInteiros output = ManipularArquivo.abrir(nome);
        return output;
    }
}
