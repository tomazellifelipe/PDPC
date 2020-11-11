package src;

import java.util.Random;

public class Produto {

    Random r = new Random();

    private String[] produtos = {"cama", "mesa", "cadeira", "armario",
                                 "celular", "computador", "monitor", "livro", 
                                 "video game", "carro"};
    private float[] precos = {58.00f, 124.89f, 36.50f, 18.99f, 249.90f,
                              23.60f, 299.99f, 47.50f, 12.85f, 105.49f};

    private String nome;
    private float preco;
    
    public Produto() {
        this.nome = nomeAleatorio();
        this.preco = precoAleatorio();
    }

    private String nomeAleatorio() {
        return produtos[r.nextInt(10)];        
    }

    private float precoAleatorio() {
        return precos[r.nextInt(10)];
    }

    public String getNome() {
        return nome;
    }

    public float getPreco() {
        return preco;
    }
    
}
