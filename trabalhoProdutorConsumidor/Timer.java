import java.util.Random;

public class Timer {
    private static Random r = new Random();

    public static void timer(final char id_produto, final char id_fabrica) {
        switch (id_fabrica) {
            case 'A':
                switch (id_produto) {
                    case 'A':
                        dormir(600, 1000);
                    case 'B':
                        dormir(200, 400);
                    case 'C':
                        dormir(1000, 1200);
                    case 'D':
                        dormir(400, 600);
                    case 'E':
                        dormir(800, 1000);
                    case 'F':
                        dormir(1400, 1600);
                    case 'G':
                        dormir(400, 600);
                    case 'H':
                        dormir(800, 1000);

                }
            case 'B':
                switch (id_produto) {
                    case 'A':
                        dormir(400, 600);
                    case 'B':
                        dormir(800, 1000);
                    case 'C':
                        dormir(1200, 1400);
                    case 'D':
                        dormir(800, 1000);
                    case 'E':
                        dormir(200, 400);
                    case 'F':
                        dormir(1000, 1200);
                    case 'G':
                        dormir(1000, 1200);
                    case 'H':
                        dormir(600, 800);

                }
            case 'C':
                switch (id_produto) {
                    case 'A':
                        dormir(1000, 1200);
                    case 'B':
                        dormir(1200, 1400);
                    case 'C':
                        dormir(400, 600);
                    case 'D':
                        dormir(600, 800);
                    case 'E':
                        dormir(400, 600);
                    case 'F':
                        dormir(400, 600);
                    case 'G':
                        dormir(1000, 1200);
                    case 'H':
                        dormir(400, 600);

                }
            case 'D':
                switch (id_produto) {
                    case 'A':
                        dormir(800, 1000);
                    case 'B':
                        dormir(600, 800);
                    case 'C':
                        dormir(400, 600);
                    case 'D':
                        dormir(1000, 1200);
                    case 'E':
                        dormir(1200, 1400);
                    case 'F':
                        dormir(800, 1000);
                    case 'G':
                        dormir(600, 800);
                    case 'H':
                        dormir(1200, 1400);

                }

        }
    }

    public static void deliverytimer(char id_transp) {
        switch (id_transp) {
            case 'A':
                dormir(100, 200);
            case 'B':
                dormir(400, 600);

        }
    }

    static public void dormir(final int min, final int max) {
        try {
            final int tempo = min + r.nextInt(max - min + 1);
            Thread.sleep(tempo);
        } catch (final Exception e) {
            e.printStackTrace();
        }
    }
}