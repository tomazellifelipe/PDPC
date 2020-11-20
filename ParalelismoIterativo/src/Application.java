import java.io.IOException;

/**
 * Application
 */
public class Application {

    public static void main(String[] args) {

        final int totalProcessadores = Runtime.getRuntime().availableProcessors();

        final int[][] matrizSetup = { { 400, 400, 400 }, { 2000, 2000, 2000 }, 
                                      { 1000, 2000, 1000 }, { 2000, 4000, 2000 } };

        try {
            configurarSetup(matrizSetup);
            
            for (int i = 0; i < 1; i++) {

                System.out.println("Setup: " + i);
                long tempoInputInicial = System.currentTimeMillis();
                double[][] matrizA = CSVHandler.lerCSV( 
                    "matrizA" + i + ".csv", 
                    matrizSetup[i][0], 
                    matrizSetup[i][1] );
                double[][] matrizB = CSVHandler.lerCSV( 
                    "matrizB" + i + ".csv", 
                    matrizSetup[i][1], 
                    matrizSetup[i][2] );
                double[][] matrizC = new double[matrizSetup[i][0]][matrizSetup[i][2]];      
                long tempoInputFinal = System.currentTimeMillis();
                System.out.println("Tempo de input: " + (tempoInputFinal - tempoInputInicial));

                long tempoExecS = Iniciador.executeSequencial(matrizA, matrizB, matrizC);
                System.out.println("\tTempo de execução sequencial: " + tempoExecS);
                
                long tempoExecP = Iniciador.executeParalela(totalProcessadores, matrizA, matrizB, matrizC );
                System.out.println("\tTempo de execução paralela: " + tempoExecP);
                

                long tempoOutputInicial = System.currentTimeMillis();
                CSVHandler.criarCSV("matrizC.csv", matrizC);
                long tempoOutputFinal = System.currentTimeMillis();
                System.out.println("Tempo de output: " + (tempoOutputFinal- tempoOutputInicial));
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