import java.io.Serializable;

/**
 * Matriz
 */
public class Matriz implements Serializable {

    private double [] [] m;
    private int linhas;
    private int colunas;

    public Matriz(int linhas, int colunas) {
        this.linhas = linhas;
        this.colunas = colunas;
        this.m = new double[linhas][colunas]; 
    }

    public void setMatrizFromCSV(String fileName) {
        this.m = CSVHandler.lerCSV(fileName, this.linhas, this.colunas);
    }
}