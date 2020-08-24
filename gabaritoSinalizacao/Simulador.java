import java.util.Random;

public class Simulador {
    static private Random r = new Random();
    static public void dormir(int min, int max) {
        try {
            int tempo = min + r.nextInt(max-min+1);
            Thread.sleep(tempo);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
