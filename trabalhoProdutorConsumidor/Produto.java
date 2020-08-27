public class Produto {
    private char id_produto;
    private int id_venda;
    private long startTime;

    public Produto(char id_produto, int id_venda, long miliseconds) {
        this.id_produto = id_produto;
        this.id_venda = id_venda;
        this.startTime = miliseconds;

    }

    public char getid_produto() {
        return id_produto;

    }

    public int getid_venda() {
        return id_venda;
    }

    public long getstartTime() {
        return startTime;
    }

    public void setstartTime(long miliseconds) {
        this.startTime = miliseconds;
        return;
    }

}