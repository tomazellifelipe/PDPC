public class Cliente extends Pessoa {

    public Cliente(Barbearia barbearia) {
        this.barbearia = barbearia;
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

}
