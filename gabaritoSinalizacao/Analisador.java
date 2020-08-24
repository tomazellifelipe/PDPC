import java.util.concurrent.Semaphore;

public class Analisador extends Thread {
    private char[] v2;
    private Semaphore filtro_escrita;
    private Semaphore analisador_leitura;

    private int contador;

    public Analisador(char[] v2,
                  Semaphore filtro_escrita,
                  Semaphore analisador_leitura) {
        this.v2 = v2;
        this.filtro_escrita = filtro_escrita;
        this.analisador_leitura = analisador_leitura;
        contador = 0;
    }

    public void run() {
        while (true) {
            try {
                analisador_leitura.acquire(); // pede acesso a v2 (aguarda o Filtro)
                    analise();
                filtro_escrita.release(); // libera v2 (sinaliza o Filtro)
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private void analise() {
        contador++;
        System.out.println("Analisando ..." + contador);
        Simulador.dormir(1000, 5000);

        int vogais = 0;
        for (int i = 0; i < v2.length; i++)
            switch (v2[i]) {
                case 'A': case 'E': case 'I': case 'O': case 'U': vogais++;
            }
        System.out.print( "Vogais em ");
        for (int i = 0; i < v2.length; i++) System.out.print(v2[i]);
        System.out.println( ": " + vogais);
    }
}
