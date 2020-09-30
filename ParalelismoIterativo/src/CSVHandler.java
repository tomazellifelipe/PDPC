import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class CSVHandler {

    public static void criarCSV(double[][] matriz) throws IOException {
        FileWriter csvWriter = new FileWriter("testFile.csv");
        for (double[] row : matriz) {
            String[] line = new String[row.length];
            for (int i = 0; i < row.length; i++) {
                line[i] = String.valueOf(row[i]);
            }
            csvWriter.append(String.join(",", line));
            csvWriter.append("\n");
        }
        
        csvWriter.flush();
        csvWriter.close();
        
    }

    public static double[][] lerCSV() throws IOException {
        BufferedReader csvReader = new BufferedReader(new FileReader("testFile.csv"));
        String[][] matrixData = new String[10][10];
        String row;
        int i = 0;
        while ((row = csvReader.readLine()) != null) {
            String[] data = row.split(",");
            matrixData[i] = data;
            i++;
        }
        csvReader.close();
        return parseToDouble(matrixData);
    }

    private static double[][] parseToDouble(String[][] data) {
        double[][] matriz = new double[data.length][data[0].length];
        for (int i = 0; i < data.length; i++) {
            for (int j = 0; j < data[i].length; j++) {
                matriz[i][j] = Double.parseDouble(data[i][j]);     
            }
        }
        return matriz;    
    }
    
}
