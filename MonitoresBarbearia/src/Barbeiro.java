public class Barbeiro extends Pessoa {

    private Barbearia barbearia;

    public Barbeiro() {
        this.barbearia = null;

    }

    @Override
    public void run() {
        try {
            while (true) {
                barbearia.pegueProximoCliente();
                barbearia.termineCorte();
                System.out.println("Corte Finalizado");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    public void setBarbearia(Barbearia barbearia) {
        this.barbearia = barbearia;
    }

}
