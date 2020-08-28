import java.util.concurrent.Semaphore;

public class Fabricante extends Thread {

    private char id_fabricante;
    private int capacidade;

    private Produto[] pedido_compra;
    private Semaphore mtx_compra, itens_compra, espacos_compra;

    private Produto[] pedido_transp;
    private Semaphore mtx_transp, itens_transp, espacos_transp;

    private Produto[] linha_producao;
    private int[] producao_idx = { 0, 0 };
    private Semaphore mtx_prod, itens_prod, espacos_prod;

    public Fabricante(char id, int capacity, Produto[] products_array, Semaphore mutex_1, Semaphore itens_1,
            Semaphore espacos_1, Produto[] consumer_array, Semaphore mutex_2, Semaphore itens_2, Semaphore espacos_2) {
        /**
         * @param id:             identification char
         * @param capacity:       producer parallel capacity
         * @param products_array: producer input array
         * @param mutex_1:        producer mutex;
         * @param itens_1:        producer semaphore for available item
         * @param espacos_1:      producer semphore for available index in
         *                        products_array
         * @param consumer_array: consumer output array
         * @param mutex_2:        consumer mutex;
         * @param itens_2:        consumer semaphore for available item
         * @param espacos_2:      consumer semphore for available index in
         *                        products_array
         */

        this.id_fabricante = id;
        this.capacidade = capacity;

        this.linha_producao = new Produto[capacity];

        this.pedido_compra = products_array;
        this.mtx_compra = mutex_1;
        this.itens_compra = itens_1;
        this.espacos_compra = espacos_1;

        this.pedido_transp = consumer_array;
        this.mtx_transp = mutex_2;
        this.itens_transp = itens_2;
        this.espacos_transp = espacos_2;

    }

    public void run() {
        iniciar();

        try {
            while (true) {
                espacos_prod.acquire(); // verifica se ha espaco na linha de producao
                itens_compra.acquire(); // verifica se houve pedido da loja
                mtx_compra.acquire();
                linha_producao[producao_idx[1]] = producao();
                producao_idx[1] = (producao_idx[1] + 1) % linha_producao.length;
                mtx_compra.release();
                itens_prod.release(); // libera o pedido pra producao
                espacos_compra.release(); // libera um espaco para pedidos

            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void iniciar() {
        mtx_prod = new Semaphore(1);
        itens_prod = new Semaphore(0);
        espacos_prod = new Semaphore(capacidade);

        for (int i = 0; i < linha_producao.length; i++) {
            new Producao(linha_producao, mtx_prod, itens_prod, espacos_prod, id_fabricante, producao_idx, pedido_transp,
                    mtx_transp, itens_transp, espacos_transp).start();

        }
    }

    private Produto producao() throws Exception {
        Produto item = pedido_compra[Aplicacao.compra_primeiro];
        Aplicacao.compra_primeiro = (Aplicacao.compra_primeiro + 1) % pedido_compra.length;
        return item;
    }

}