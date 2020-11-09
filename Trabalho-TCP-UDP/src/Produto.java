package src;

import java.util.Random;

public class Produto {

    Random r = new Random();

    private String[] produtos = {"cama", "mesa", "cadeira", "armario",
                                 "celular", "computador", "monitor", "livro", 
                                 "video game", "carro"};
    private double[] precos = {58.00, 124.89, 36.50, 18.99, 249.90,
                               23.60, 299.99, 47.50, 12.85, 105.49};

    private String nome;
    private double preco;
    
    public Produto() {
        this.nome = nomeAleatorio();
        this.preco = precoAleatorio();
    }

    private String nomeAleatorio() {
        return produtos[r.nextInt(10)];        
    }

    private double precoAleatorio() {
        return precos[r.nextInt(10)];
    }

    public String getNome() {
        return nome;
    }

    public Double getPreco() {
        return preco;
    }
    
}
