public class Calculo {

    private int inicio, fim;

    public Calculo(
        int inicio, 
        int fim ) {

        this.inicio = inicio;
        this.fim = fim;
        
    }

    public void multiplicarMatrizes(
        double[][] matrizA, 
        double[][] matrizB,
        double[][] matrizC) {
        for (int i = this.inicio; i < this.fim; i++) {
            for (int j = 0; j < matrizC[0].length; j++) {
                matrizC[i][j] = 0.0;
                for (int k = 0; k < matrizA[0].length; k++) {
                    matrizC[i][j] = matrizC[i][j] + matrizA[i][k] * matrizB[k][j];            
                }  
            }
        }
        
    }
}