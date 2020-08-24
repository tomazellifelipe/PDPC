import java.util.concurrent.Semaphore;

public class Aplicacao {

    public static int compra_primeiro = 0;
    public static int compra_ultimo = 0;

    public static int transp_primeiro = 0;
    public static int transp_ultimo = 0;

    public static void main(String[] args) {
        Produto[] pedido_compra = new Produto[100];
        Semaphore mtx_compra = new Semaphore(1);
        Semaphore itens_compra = new Semaphore(0);
        Semaphore espacos_compra = new Semaphore(100);

        Produto[] pedido_transp = new Produto[100];
        Semaphore mtx_transp = new Semaphore(1);
        Semaphore itens_transp = new Semaphore(0);
        Semaphore espacos_transp = new Semaphore(100);

        Loja la = new Loja('A', pedido_compra, mtx_compra, itens_compra, espacos_compra);
        la.setName("LojaA");

        Fabricante fd = new Fabricante('D', 4, pedido_compra, mtx_compra, itens_compra, espacos_compra, pedido_transp,
                mtx_transp, itens_transp, espacos_transp);
        fd.setName("FabD");

        Transportadora ta = new Transportadora(pedido_transp, mtx_transp, itens_transp, espacos_transp, 10);
        ta.setName("TranspA");

        la.start();
        fd.start();
        ta.start();

    }
}