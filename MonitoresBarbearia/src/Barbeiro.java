public class Barbeiro extends Pessoa {

    public Barbeiro(Barbearia barbearia) {
        this.barbearia = barbearia;

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

}
