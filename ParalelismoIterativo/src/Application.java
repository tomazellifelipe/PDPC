import java.io.IOException;

/**
 * Application
 */
public class Application {

    public static void main(String[] args) {

        final int totalProcessadores = Runtime.getRuntime().availableProcessors();

        final int[][] matrizSetup = { { 1000, 1000, 1000 }, { 2000, 2000, 2000 }, 
                                      { 1000, 2000, 1000 }, { 2000, 4000, 2000 } };

        try {
            configurarSetup(matrizSetup);

            for (int i = 0; i < matrizSetup.length; i++) {


                long tempoExec = Iniciador.executeSequencial(i, matrizSetup[i]);

                System.out.println("Tempo de execução sequencial: " + tempoExec);

                

            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public static void configurarSetup(int[][] setup) throws IOException {
        for (int i = 0; i < setup.length; i++) {
            CriadorMatriz matrizA = new CriadorMatriz(setup[i][0], setup[i][1]);
            matrizA.popular();
            CSVHandler.criarCSV("matrizA" + i + ".csv", matrizA.getMatriz());
            CriadorMatriz matrizB = new CriadorMatriz(setup[i][1], setup[i][2]);
            matrizB.popular();
            CSVHandler.criarCSV("matrizB" + i + ".csv", matrizB.getMatriz());
            
        }
        
    } 
}