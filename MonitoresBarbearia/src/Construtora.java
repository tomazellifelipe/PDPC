public class Construtora {

    public static Pessoa criarBarbeiro(Barbearia barbearia) {
        return new Barbeiro(barbearia);
    }

    public static Pessoa criarCliente(Barbearia barbearia) {
        return new Cliente(barbearia);
    }

    public static Barbearia criarBarbearia() {
        return new Barbearia();
    }

}
