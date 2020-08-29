import java.util.concurrent.Semaphore;

public class Client extends Thread {

    private Semaphore[] clients_line;
    private Semaphore client, client_pass, clienteSatisfeito, corteConcluido;
    private Semaphore mutex;

    private int[] clients_counter;

    public Client(Semaphore[] clients_line, Semaphore mutex, Semaphore client, Semaphore clienteSatisfeito,
            Semaphore corteConcluido, int[] clients_counter) {
        this.client_pass = new Semaphore(0);
        this.clients_line = clients_line;
        this.mutex = mutex;
        this.client = client;
        this.clienteSatisfeito = clienteSatisfeito;
        this.corteConcluido = corteConcluido;
        this.clients_counter = clients_counter;

    }

    public void run() {
        try {
            mutex.acquire();
            if (clients_counter[0] == BarberShop.MAX_CLIENTS) {
                mutex.release();
                desistir();
            }
            clients_counter[0]++;
            join_line();
            mutex.release();

            client.release();
            client_pass.acquire();

            terCabeloCortado();

            clienteSatisfeito.release();
            corteConcluido.acquire();

            mutex.acquire();
            clients_counter[0]--;
            System.out.println("Concluido");
            mutex.release();

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private void join_line() {
        clients_line[BarberShop.line_order[1]] = client_pass;
        BarberShop.line_order[1] = (BarberShop.line_order[1] + 1) % BarberShop.MAX_CLIENTS;
    }

    private void terCabeloCortado() {
        System.out.println("Cortando o cabelo");
        return;
    }

    private void desistir() {
        System.out.println("Voltar pra casa");
        return;
    }

}