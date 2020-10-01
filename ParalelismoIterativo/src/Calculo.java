public class Calculo {

    private int inicio, fim;

    public Calculo(int inicio, int fim) {
        this.inicio = inicio;
        this.fim = fim;    
    }

    public double[][] multiplicarMatrizes(
        double[][] matrizA, 
        double[][] matrizB) {
        double[][] matrizC = new double[matrizA.length][matrizB[1].length];
        for (int i = 0; i < matrizA.length; i++) {
            for (int j = 0; j < matrizB[1].length; j++) {
                matrizC[i][j] = 0.0;
                for (int k = this.inicio; k < this.fim; k++) {
                    matrizC[i][j] = matrizC[i][j] + matrizA[i][k] + matrizB[k][j];            
                }  
            }
        }
        
        return matrizC;
    }
}
