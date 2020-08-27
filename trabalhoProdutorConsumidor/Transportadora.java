import java.util.concurrent.Semaphore;

public class Transportadora extends Thread {
    private int capacidade;
    char id_transp;

    private Produto[] pedido_transp;
    private Semaphore mtx_transp, itens_transp, espacos_transp;

    private Produto[] entrega;
    private int[] entrega_idx = { 0, 0 };
    private Semaphore mtx_entrega, itens_entrega, espacos_entrega;

    public Transportadora(char id_transp, Produto[] consumer_array, Semaphore mutex, Semaphore itens, Semaphore espacos,
            int capacity) {
        /**
         * @param consumer_array: consumer output array
         * @param mutex:          consumer mutex;
         * @param itens:          consumer semaphore for available item
         * @param espacos:        consumer semphore for available index in
         *                        products_array
         * @param capacity:       consumer parallel capacity
         */
        this.id_transp = id_transp;
        this.pedido_transp = consumer_array;
        this.mtx_transp = mutex;
        this.itens_transp = itens;
        this.espacos_transp = espacos;

        this.capacidade = capacity;

        this.entrega = new Produto[capacity];

    }

    public void run() {
        iniciar();
        try {
            while (true) {
                espacos_entrega.acquire();
                itens_transp.acquire();
                mtx_transp.acquire();
                entrega[entrega_idx[1]] = entrega();
                entrega_idx[1] = (entrega_idx[1] + 1) % entrega.length;
                mtx_transp.release();
                itens_entrega.release();
                espacos_transp.release();

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void iniciar() {
        mtx_entrega = new Semaphore(1);
        itens_entrega = new Semaphore(0);
        espacos_entrega = new Semaphore(capacidade);

        for (int i = 0; i < entrega.length; i++) {
            new Entrega(id_transp, entrega, entrega_idx, mtx_entrega, itens_entrega, espacos_entrega).start();
        }
    }

    private Produto entrega() {
        Produto k = pedido_transp[Aplicacao.transp_primeiro];
        Aplicacao.transp_primeiro = (Aplicacao.transp_primeiro + 1) % pedido_transp.length;
        // System.out.println("Transportando: " + k.getid_produto() + k.getid_venda());
        return k;
    }

}