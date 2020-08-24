import java.util.Random;
import java.util.concurrent.Semaphore;

public class Gerador extends Thread {
    private char[] v1;
    private Semaphore gerador_escrita;
    private Semaphore filtro_leitura;

    private int contador;

    private char[] alpha = new char[52];
    Random r = new Random();

    public Gerador(char[] v1,
                  Semaphore gerador_escrita,
                  Semaphore filtro_leitura) {
        this.v1 = v1;
        this.gerador_escrita = gerador_escrita;
        this.filtro_leitura = filtro_leitura;

        contador = 0;

        for(int i = 0; i < 26; i++){
            alpha[i] = (char)(97 + i);
            alpha[i+26] = Character.toUpperCase(alpha[i]);
        }
        for(int i = 0; i < 52; i++)
            System.out.print(alpha[i]);
        System.out.println();
    }

    public void run() {
        while (true) {
            try {
                gerador_escrita.acquire(); // pede acesso a v1 (aguarda sinalização do Filtro)
                    gere();
                filtro_leitura.release(); // libera v1 (sinaliza para o Filtro)
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private void gere() {
        contador++;
        System.out.println("Gerando ..." + contador);
        Simulador.dormir(1000, 3000);

        for (int i = 0; i < v1.length; i++)
            v1[i] = letra_aleatoria();

        System.out.print( "Gerado: ");
        for (int i = 0; i < v1.length; i++) System.out.print(v1[i]);
        System.out.println();
    }

    private char letra_aleatoria() {
        return alpha[r.nextInt(52)];
    }
}
