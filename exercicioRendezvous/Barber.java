import java.util.concurrent.Semaphore;

public class Barber extends Thread {

    private Semaphore[] clients_line;
    private Semaphore client, clienteSatisfeito, corteConcluido;
    private Semaphore mutex;

    public Barber(Semaphore[] clients_line, Semaphore mutex, Semaphore client, Semaphore clienteSatisfeito,
            Semaphore corteConcluido) {

        this.clients_line = clients_line;
        this.mutex = mutex;
        this.client = client;
        this.clienteSatisfeito = clienteSatisfeito;
        this.corteConcluido = corteConcluido;
    }

    public void run() {
        try {
            while (true) {
                client.acquire();

                mutex.acquire();
                Semaphore sem = exit_line();
                mutex.release();

                sem.release();

                cortarCabelo();

                clienteSatisfeito.acquire();
                corteConcluido.release();

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private Semaphore exit_line() {
        Semaphore temp = clients_line[BarberShop.line_order[0]];
        BarberShop.line_order[0] = (BarberShop.line_order[0] + 1) % BarberShop.MAX_CLIENTS;
        return temp;
    }

    private void cortarCabelo() {
        System.out.println("Trabalhando");
        return;
    }

}