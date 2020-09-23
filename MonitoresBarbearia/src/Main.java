class Main {
    public static void main(String[] args) {

        Barbearia barbearia = Construtora.criarBarbearia();
        Pessoa cliente = Construtora.criarBarbeiro(barbearia);
        Pessoa barbeiro = Construtora.criarCliente(barbearia);

        cliente.start();
        barbeiro.start();

    }
}