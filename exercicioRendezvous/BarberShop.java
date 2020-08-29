import java.util.concurrent.Semaphore;

public class BarberShop {

    public static int[] line_order = { 0, 0 };
    public static final int MAX_CLIENTS = 4;

    public static void main(String[] args) {

        Semaphore[] clients_line = new Semaphore[MAX_CLIENTS];
        Semaphore client = new Semaphore(0);
        Semaphore clienteSatisfeito = new Semaphore(0);
        Semaphore corteConcluido = new Semaphore(0);
        Semaphore mutex = new Semaphore(1);
        int[] clients_counter = { 0 };

        Barber barber = new Barber(clients_line, mutex, client, clienteSatisfeito, corteConcluido);
        Client client1 = new Client(clients_line, mutex, client, clienteSatisfeito, corteConcluido, clients_counter);

        barber.start();
        client1.start();

    }

}