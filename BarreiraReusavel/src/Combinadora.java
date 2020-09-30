package src;

import java.util.ArrayList;
import java.util.concurrent.Semaphore;

public class Combinadora extends Thread {

    private int counter = 0;
    private int arquivosRecebidos = 0;
    private Semaphore mutexArquivos, semCombinadora;
    private ArrayList<String> Arquivos;

    public Combinadora(
        ArrayList<String> listaDeArquivos, 
        Semaphore mutex,
        Semaphore semCombinadora) {

        this.Arquivos = listaDeArquivos;
        this.mutexArquivos = mutex;
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
                ListaDeInteiros inteiros = new ListaDeInteiros();
                int auxInicio = arquivosRecebidos;
                int auxFinal = arquivosRecebidos + 4;
                for (int i = auxInicio; i < auxFinal; i++) {
                    mutexArquivos.acquire();
                        String fileName = Arquivos.get(i);
                    mutexArquivos.release();
                    ArrayList<Integer> aux = ManipularArquivo.ler(fileName);
                    // System.out.println("Iniciando merge de arquivos");

                    for (Integer num : aux) {
                        if (!inteiros.getList().contains(num)) {
                             inteiros.getList().add(num);
                        }
                    }
                    inteiros.ordenar();
                    arquivosRecebidos++;
                }
                // end mutex block1
                String nome = this.getName() + counter + ".txt";
                counter++;
                ManipularArquivo.escrever(inteiros.getList(), nome);
                System.out.println("SALVOU");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
