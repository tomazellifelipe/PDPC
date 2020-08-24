import java.util.concurrent.Semaphore;

public class Producao extends Thread {

    private char id_fabricante;

    private Produto[] linha_prod;
    private int[] producao_idx;
    private Semaphore mtx_prod, itens_prod, espacos_prod;
    private Produto[] pedido_transp;
    private Semaphore mtx_transp, itens_transp, espacos_transp;

    public Producao(Produto[] linha_prod, Semaphore mutex_1, Semaphore itens_1, Semaphore espacos_1, char id,
            int[] index, Produto[] consumer_array, Semaphore mutex_2, Semaphore itens_2, Semaphore espacos_2) {
        /**
         * @param linha_prod:     production line array
         * @param mutex_1:        producer mutex;
         * @param itens_1:        producer semaphore for available item
         * @param espacos_1:      producer semphore for available index in
         *                        products_array
         * @param id:             identification char
         * @param index:          production line index
         * @param consumer_array: consumer output array
         * @param mutex_2:        consumer mutex;
         * @param itens_2:        consumer semaphore for available item
         * @param espacos_2:      consumer semphore for available index in
         *                        products_array
         */
        this.linha_prod = linha_prod;
        this.mtx_prod = mutex_1;
        this.itens_prod = itens_1;
        this.espacos_prod = espacos_1;
        this.producao_idx = index;

        this.id_fabricante = id;

        this.pedido_transp = consumer_array;
        this.mtx_transp = mutex_2;
        this.itens_transp = itens_2;
        this.espacos_transp = espacos_2;
    }

    public void run() {
        try {
            while (true) {
                itens_prod.acquire();
                mtx_prod.acquire();
                Produto k = linha_prod[producao_idx[0]];
                producao_idx[0] = (producao_idx[0] + 1) % linha_prod.length;
                mtx_prod.release();
                System.out.println(id_fabricante + " Produzindo: " + k.getid_produto() + k.getid_venda());
                Timer.timer(k.getid_produto(), id_fabricante);
                System.out.println(id_fabricante + " Finalizou: " + k.getid_produto() + k.getid_venda());
                espacos_prod.release();
                espacos_transp.acquire(); // verifica se ha espaco para transporte pedido_transp[100]
                mtx_transp.acquire();
                pedido_transp[Aplicacao.transp_ultimo] = k;
                Aplicacao.transp_ultimo = (Aplicacao.transp_ultimo + 1) % pedido_transp.length;
                mtx_transp.release();
                itens_transp.release();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}