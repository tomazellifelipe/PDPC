import java.util.Random;
import java.util.concurrent.Semaphore;

public class Loja extends Thread {

    private char id_loja;
    private int total_vendas = 0;
    private int vendas = 0;
    private char[] catalogo = { 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H' };

    private Produto[] pedido_compra;

    private Semaphore mtx_compra, itens_compra, espacos_compra;

    public Loja(char id, Produto[] products_array, Semaphore mutex, Semaphore itens, Semaphore espacos) {
        /**
         * @param id:             store identification char
         * @param products_array: producer input array
         * @param mutex:          mutex;
         * @param itens:          producer semaphore for available item
         * @param espacos:        producer semphore for available index in
         *                        products_array
         */

        this.id_loja = id;
        this.pedido_compra = products_array;
        this.mtx_compra = mutex;
        this.itens_compra = itens;
        this.espacos_compra = espacos;

    }

    public void run() {
        Random r = new Random();
        try {
            while (true) {
                do {
                    espacos_compra.acquire();
                    mtx_compra.acquire();
                    compra(r);
                    mtx_compra.release();
                    itens_compra.release();
                    vendas++;

                } while (vendas < 2);
                Timer.dormir(10, 150);
                vendas = 0;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private void compra(Random r) throws Exception {
        Produto item = new Produto(catalogo[r.nextInt(8)], ++total_vendas);
        pedido_compra[Aplicacao.compra_ultimo] = item;
        Aplicacao.compra_ultimo = (Aplicacao.compra_ultimo + 1) % pedido_compra.length;
        System.out.println("Loja: " + id_loja + " Pedido: " + item.getid_produto() + item.getid_venda());

    }

}