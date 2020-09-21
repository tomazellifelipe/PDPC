import java.io.*;
import java.util.*;
import java.util.concurrent.Semaphore;

public class Trabalhadora extends Thread {

    private ArrayList<String> listaDeArquivos;
    private Semaphore mutex, barreiraEntrada, barreiraSaída, semCombinadora;

    public Trabalhadora(ArrayList<String> lista, Semaphore mutex, Semaphore barreiraEntrada, Semaphore barreiraSaida,
            Semaphore semCombinadora) {
        this.listaDeArquivos = lista;
        this.mutex = mutex;
        this.barreiraEntrada = barreiraEntrada;
        this.barreiraSaída = barreiraSaida;
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
                Main.contador++;
                if (Main.contador == Main.MAX_TRABALHADORAS) {
                    barreiraSaída.acquire(); // fecha
                    barreiraEntrada.release(); // abre
                }
                mutex.release();
                barreiraEntrada.acquire();
                listaDeArquivos.add(nome); // insere o nome do arquivo na lista de// arquivos
                semCombinadora.release(); // arquivo pronto
                barreiraEntrada.release();
                mutex.acquire();
                Main.contador--;
                if (Main.contador == 0) {
                    barreiraEntrada.acquire(); // fecha
                    semCombinadora.acquire(); // espera o arquivo da combinadora ficar pronto
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

    private String criarArquivo(ListaDeInteiros arquivo) throws IOException {
        String output = this.getName() + ".ser";
        ManipularArquivo.salvar(output, arquivo);
        return output;
    }
}
