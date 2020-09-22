import java.io.*;
import java.util.*;
import java.util.concurrent.Semaphore;

public class Trabalhadora extends Thread {

    private ArrayList<String> listaDeArquivos;
    private Semaphore mutex, barreiraEntrada, barreiraSaida, semCombinadora;

    public Trabalhadora(ArrayList<String> lista, Semaphore mutex, Semaphore barreiraEntrada, Semaphore barreiraSaida,
            Semaphore semCombinadora) {
        this.listaDeArquivos = lista;
        this.mutex = mutex;
        this.barreiraEntrada = barreiraEntrada;
        this.barreiraSaida = barreiraSaida;
        this.semCombinadora = semCombinadora;
    }

    public void run() {
        try {
            while (true) {
                ListaDeInteiros lista = new ListaDeInteiros();
                lista.popular();
                lista.ordenar();
                String nome = criarArquivo(lista);
                System.out.println("Arquivo criado por: " + this.getName());
                mutex.acquire();
                // start mutex block
                Main.contador++;
                if (Main.contador == Main.MAX_TRABALHADORAS) {
                    barreiraSaida.acquire(); // fecha
                    barreiraEntrada.release(); // abre
                }
                mutex.release();
                // end mutex block
                barreiraEntrada.acquire(); 
                listaDeArquivos.add(nome);
                // still don't know how to fix it (testing issues)
                // send signal to Combinadora
                barreiraEntrada.release();
                mutex.acquire();
                // start mutex block
                Main.contador--;
                if (Main.contador == 0) {
                    barreiraEntrada.acquire(); // fecha
                    barreiraSaida.release(); // abre
                }
                mutex.release();
                // end mutex block
                barreiraSaida.acquire();
                semCombinadora.acquire();
                barreiraSaida.release();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private String criarArquivo(ListaDeInteiros arquivo) throws IOException {
        String output = this.getName() + ".ser";
        ManipularArquivo.salvar(output, arquivo);
        return output;
    }
}
