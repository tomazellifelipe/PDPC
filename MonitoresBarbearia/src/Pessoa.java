abstract class Pessoa extends Thread {

    Barbearia barbearia;

    public abstract void run();

    public abstract void setBarbearia(Barbearia barbearia);

}