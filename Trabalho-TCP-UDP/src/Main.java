package src;


public class Main {

    public static void main(String[] args) {
        Servidor servidor = new Servidor();
        servidor.start();
        for (int i = 0; i < 3; i++) {
            new Loja(i).start();
            
        }
    }
    
}
