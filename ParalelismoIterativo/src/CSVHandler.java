import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class CSVHandler {

    public static void criarCSV( String fileName, double[][] matriz ) throws IOException {
        FileWriter csvWriter = new FileWriter(fileName); 
        for (double[] row : matriz) {
            String[] line = new String[row.length];
            for (int i = 0; i < line.length; i++) {
                line[i] = String.valueOf(row[i]);
            }

            csvWriter.append(String.join(",", line));
            csvWriter.append("\n");
        }
        
        csvWriter.flush();
        csvWriter.close();
        
    }

    public static double[][] lerCSV(String fileName, int rows, int columns) throws IOException {
        FileReader fr = new FileReader(fileName); 
        BufferedReader csvReader = new BufferedReader(fr); 
        String[][] matriz = new String[rows][columns]; 
        String row;
        int i = 0;
        while ((row = csvReader.readLine()) != null) {
            matriz[i] = row.split(",");
            i++;
        }

        csvReader.close();
        return parseToDouble(matriz);
    }

    private static double[][] parseToDouble( String[][] data ) {
        double[][] output = new double[data.length][data[0].length];
        for (int i = 0; i < data.length; i++) {
            for (int j = 0; j < data[i].length; j++) {
                output[i][j] = Double.parseDouble(data[i][j]);     
            }
            
        }

        return output;    
    }
    
}
