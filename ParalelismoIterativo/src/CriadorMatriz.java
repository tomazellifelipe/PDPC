import java.util.Random;

public class CriadorMatriz {
    
    private Random r = new Random();
    private double[][] matriz;
    
    public CriadorMatriz(int rows, int colums) {
        this.matriz = new double[rows][colums];     
    }

    public double gerarDouble(double max) {
        return max * r.nextDouble();
    }

    public void popular() {
        for (int i = 0; i < matriz.length; i++) {
            for (int j = 0; j < matriz.length; j++) {
                matriz[i][j] = gerarDouble(1000);    
            }
        }
    }

    public double[][] getMatriz() {
        return this.matriz;
    }


}
