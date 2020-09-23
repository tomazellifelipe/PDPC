package src;

import java.util.ArrayList;
import java.util.concurrent.Semaphore;

public class Main {

        public static final int MAX_TRABALHADORAS = 4;
        public static int contador = 0;

        public static void main(String[] args) {

                Semaphore mutex = new Semaphore(1);
                Semaphore barreiraEntrada = new Semaphore(0);
                Semaphore barreiraSaida = new Semaphore(1);
                Semaphore semCombinadora = new Semaphore(0);

                ArrayList<String> listaDeArquivos = new ArrayList<>();

                Trabalhadora trabalhadora0 = new Trabalhadora(listaDeArquivos, mutex,
                                barreiraEntrada, barreiraSaida, semCombinadora);

                Trabalhadora trabalhadora1 = new Trabalhadora(listaDeArquivos, mutex,
                                barreiraEntrada, barreiraSaida, semCombinadora);
                Trabalhadora trabalhadora2 = new Trabalhadora(listaDeArquivos, mutex,
                                barreiraEntrada, barreiraSaida, semCombinadora);
                Trabalhadora trabalhadora3 = new Trabalhadora(listaDeArquivos, mutex,
                                barreiraEntrada, barreiraSaida, semCombinadora);

                Combinadora combinadora = new Combinadora(listaDeArquivos, mutex, semCombinadora);

                trabalhadora0.start();

                trabalhadora1.start();
                trabalhadora2.start();
                trabalhadora3.start();
                combinadora.start();
        }
}
