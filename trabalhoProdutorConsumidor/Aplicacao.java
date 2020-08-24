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

                Loja lA = new Loja('A', pedido_compra, mtx_compra, itens_compra, espacos_compra);
                Loja lB = new Loja('B', pedido_compra, mtx_compra, itens_compra, espacos_compra);
                Loja lC = new Loja('C', pedido_compra, mtx_compra, itens_compra, espacos_compra);
                Loja lD = new Loja('D', pedido_compra, mtx_compra, itens_compra, espacos_compra);
                Loja lE = new Loja('E', pedido_compra, mtx_compra, itens_compra, espacos_compra);
                Loja lF = new Loja('F', pedido_compra, mtx_compra, itens_compra, espacos_compra);
                Loja lG = new Loja('G', pedido_compra, mtx_compra, itens_compra, espacos_compra);
                Loja lH = new Loja('H', pedido_compra, mtx_compra, itens_compra, espacos_compra);

                Fabricante fA = new Fabricante('A', 4, pedido_compra, mtx_compra, itens_compra, espacos_compra,
                                pedido_transp, mtx_transp, itens_transp, espacos_transp);
                Fabricante fB = new Fabricante('B', 1, pedido_compra, mtx_compra, itens_compra, espacos_compra,
                                pedido_transp, mtx_transp, itens_transp, espacos_transp);
                Fabricante fC = new Fabricante('C', 4, pedido_compra, mtx_compra, itens_compra, espacos_compra,
                                pedido_transp, mtx_transp, itens_transp, espacos_transp);
                Fabricante fD = new Fabricante('D', 4, pedido_compra, mtx_compra, itens_compra, espacos_compra,
                                pedido_transp, mtx_transp, itens_transp, espacos_transp);

                Transportadora tA = new Transportadora('A', pedido_transp, mtx_transp, itens_transp, espacos_transp,
                                10);
                Transportadora tB = new Transportadora('B', pedido_transp, mtx_transp, itens_transp, espacos_transp,
                                20);

                lA.start();
                lB.start();
                lC.start();
                lD.start();
                lE.start();
                lF.start();
                lG.start();
                lH.start();

                fA.start();
                fB.start();
                fC.start();
                fD.start();

                tA.start();
                tB.start();

        }
}