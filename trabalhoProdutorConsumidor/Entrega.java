import java.util.Random;
import java.util.concurrent.Semaphore;

public class Entrega extends Thread {

    private Produto[] entrega;
    private int[] entrega_idx = { 0, 0 };
    private Semaphore mtx_entrega, itens_entrega, espacos_entrega;

    public Entrega(Produto[] delivery_array, int[] index, Semaphore mutex, Semaphore itens, Semaphore espacos_entrega) {
        /**
         * @param delivery_array: delivery output array
         * @param mutex:          consumer mutex;
         * @param itens:          consumer semaphore for available item
         * @param espacos:        consumer semphore for available index in
         *                        delivery_array
         */
        this.entrega = delivery_array;
        this.entrega_idx = index;
        this.mtx_entrega = mutex;
        this.itens_entrega = itens;
        this.espacos_entrega = espacos_entrega;
    }

    public void run() {
        Random r = new Random();
        try {
            while (true) {
                itens_entrega.acquire();
                mtx_entrega.acquire();
                Produto k = entrega[entrega_idx[0]];
                entrega_idx[0] = (entrega_idx[0] + 1) % entrega.length;
                mtx_entrega.release();
                Thread.sleep(r.nextInt(2000));
                System.out.println("Finalizando entrega de: " + k.getid_produto() + k.getid_venda());
                espacos_entrega.release();

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}