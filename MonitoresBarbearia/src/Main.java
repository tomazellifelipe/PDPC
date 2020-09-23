class Main {
    public static void main(String[] args) {

        Barbearia barbearia = Construtora.criarBarbearia();

        Pessoa cliente = Construtora.criarBarbeiro();
        cliente.setBarbearia(barbearia);

        Pessoa barbeiro = Construtora.criarCliente();
        barbeiro.setBarbearia(barbearia);

        cliente.start();
        barbeiro.start();

    }
}