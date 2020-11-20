import java.io.BufferedReader;
import java.io.FileReader;

/**
 * CSVHandler
 */
public class CSVHandler {

    private static BufferedReader csvReader;

    public static double[][] lerCSV(String fileName, int linhas, int colunas) {
        String[][] matriz = new String[linhas][colunas];
		try {
            csvReader = new BufferedReader(new FileReader(fileName));
            String row;
            int i = 0;
            while ((row = csvReader.readLine()) != null) {
                matriz[i] = row.split(",");
                i++;
            }
            csvReader.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        return parseToDouble(matriz);
    }

    private static double[][] parseToDouble(String[][] matriz) {
        double[][] output = new double[matriz.length][matriz[0].length];
        for (int i = 0; i < matriz.length; i++) {
            for (int j = 0; j < matriz.length; j++) {
                output[i][j] = Double.parseDouble(matriz[i][j]);
            }            
        }
        return output;
    }
}