public class Factory {

    public static Pessoa criarBarbeiro() {
        return new Barbeiro();
    }

    public static Pessoa criarCliente() {
        return new Cliente();
    }

    public static Barbearia criarBarbearia() {
        return new Barbearia();
    }

}
