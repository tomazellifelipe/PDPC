class Main {
    public static void main(String[] args) {

        Barbearia barbearia = Factory.criarBarbearia();

        Pessoa cliente = Factory.criarBarbeiro();
        cliente.setBarbearia(barbearia);

        Pessoa barbeiro = Factory.criarCliente();
        barbeiro.setBarbearia(barbearia);

        cliente.start();
        barbeiro.start();

    }
}