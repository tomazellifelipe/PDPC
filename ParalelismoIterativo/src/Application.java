/**
 * Application
 */
public class Application {

    public static void main(String[] args) {
        CriadorMatriz matriz = new CriadorMatriz(10, 10);
        matriz.popular();
        try {
            CSVHandler.criarCSV(matriz.getMatriz());
            double[][] matrizData = CSVHandler.lerCSV();
            System.out.println(matrizData);
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}