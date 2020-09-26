public class Cliente extends Pessoa {

    private Barbearia barbearia;

    public Cliente() {
        this.barbearia = null;
    }

    @Override
    public void run() {
        try {
            while (true) {
                barbearia.corteCabelo();
                System.out.println("Cliente satisfeito");

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
