package src;

import java.util.ArrayList;
import java.util.concurrent.Semaphore;

public class Main {

        public static final int MAX_TRABALHADORAS = 4;
        public static int contador = 0;

        public static void main(String[] args) {

                Semaphore mutex = new Semaphore(1);
                Semaphore mutexArquivos = new Semaphore(1);
                Semaphore barreiraEntrada = new Semaphore(0);
                Semaphore barreiraSaida = new Semaphore(1);
                Semaphore semCombinadora = new Semaphore(0);

                ArrayList<String> Arquivos = new ArrayList<>();

                for (int i = 0; i < MAX_TRABALHADORAS; i++) {
                        new Trabalhadora(
                        Arquivos, 
                        mutex,
                        barreiraEntrada, 
                        barreiraSaida, 
                        semCombinadora, 
                        mutexArquivos).start();
                }
                new Combinadora(Arquivos, mutex, semCombinadora).start();
                
        }
}
