package src;

import java.io.*;
import java.util.*;
import java.util.concurrent.Semaphore;

public class Trabalhadora extends Thread {

    private int counter = 0;
    private ArrayList<String> Arquivos;
    private Semaphore mutex, 
                      barreiraEntrada, 
                      barreiraSaida, 
                      semCombinadora, 
                      mutexArquivos;

    public Trabalhadora(
        ArrayList<String> lista, 
        Semaphore mutex, 
        Semaphore barreiraEntrada,
        Semaphore barreiraSaida, 
        Semaphore semCombinadora, 
        Semaphore mutexArquivos) {

        this.Arquivos = lista;
        this.mutex = mutex;
        this.barreiraEntrada = barreiraEntrada;
        this.barreiraSaida = barreiraSaida;
        this.semCombinadora = semCombinadora;
        this.mutexArquivos = mutexArquivos;
    }

    public void run() {
        try {
            while (true) {
                ListaDeInteiros lista = new ListaDeInteiros();
                lista.popular();
                lista.ordenar();
                String nome = criarArquivo(lista.getList());
                System.out.println("Arquivo criado por: " + this.getName());
                // start mutex block
                mutex.acquire();
                    Main.contador++;
                    if (Main.contador == Main.MAX_TRABALHADORAS) {
                        barreiraSaida.acquire(); // fecha
                        barreiraEntrada.release(); // abre
                    }
                mutex.release();
                // end mutex block
                barreiraEntrada.acquire();
                barreiraEntrada.release();
                mutexArquivos.acquire();
                    Arquivos.add(nome);
                mutexArquivos.release();
                semCombinadora.release();
                // mutex
                // start mutex block
                mutex.acquire();
                    Main.contador--;
                    if (Main.contador == 0) {
                        barreiraEntrada.acquire(); // fecha
                        barreiraSaida.release(); // abre
                    }
                mutex.release();
                // end mutex block
                barreiraSaida.acquire();
                barreiraSaida.release();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private String criarArquivo(ArrayList<Integer> aIntegers) 
        throws IOException {

        String output = this.getName() + this.counter + ".txt";
        ManipularArquivo.escrever(aIntegers, output);
        this.counter++;
        return output;
    }
}
