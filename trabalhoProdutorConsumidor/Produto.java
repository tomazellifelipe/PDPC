public class Produto {
    private char id_produto;
    private int id_venda;

    public Produto(char id_produto, int id_venda) {
        this.id_produto = id_produto;
        this.id_venda = id_venda;

    }

    public char getid_produto() {
        return id_produto;

    }

    public int getid_venda() {
        return id_venda;
    }

}