import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.concurrent.Semaphore;

public class Comunicacao extends Thread {
    private Socket socket;
    private Matriz[] matrizes;
    private int[] parametros;
    private Semaphore semaphore;

    public Comunicacao(Socket socket, Matriz[] matrizes, int[] parametros, Semaphore semaphore) {
        this.socket = socket;
        this.matrizes = matrizes;
        this.parametros = parametros;
        this.semaphore = semaphore;
    }

    public void run() {
        try {
            for (Matriz matriz : matrizes) {
                enviarMatriz(matriz);
            }
            for (int param: parametros) {
                enviarParametro(param);
            }

            Matriz matrizTemp = receberMatriz();
            for (int i = parametros[0]; i < matrizes[2].getMatriz().length; i++) {
                for (int j = parametros[0]; j < matrizes[2].getMatriz()[0].length; j++) {
                    matrizes[2].getMatriz()[i][j] = matrizTemp.getMatriz()[i][j];
                }                
            }
            System.out.println("debug");
        } catch (Exception e) {
            e.printStackTrace();
        }
        semaphore.release();

    }

    public void enviarMatriz(Matriz matriz) throws IOException {
        ObjectOutputStream enviar = new ObjectOutputStream(socket.getOutputStream());
        enviar.writeObject(matriz);
    }

    public Matriz receberMatriz() throws IOException, ClassNotFoundException {
        ObjectInputStream receber = new ObjectInputStream(socket.getInputStream());
        return (Matriz) receber.readObject();
    }

    public void enviarParametro(int param) throws IOException {
        DataOutputStream enviar = new DataOutputStream(socket.getOutputStream());
        enviar.writeInt(param);
    }
}
