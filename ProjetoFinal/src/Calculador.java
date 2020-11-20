import java.io.DataInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

/**
 * Calculador
 */
public class Calculador {

    private static ServerSocket serverSocket;
    private static Socket socket;

    public static void main(String[] args) {
        try {
            Scanner input = new Scanner(System.in);
            int port = input.nextInt();
            serverSocket = new ServerSocket(port);
            socket = serverSocket.accept();
            Matriz matrizA = receberMatriz();
            Matriz matrizB = receberMatriz();
            Matriz matrizC = receberMatriz();
            int inicio = receberParametro();
            int fim = receberParametro();

            Calculo c = new Calculo(inicio, fim);
            c.multiplicarMatrizes(matrizA.getMatriz(), 
                                  matrizB.getMatriz(), 
                                  matrizC.getMatriz());
            
            enviarMatriz(matrizC);
        } catch (Exception e) {
            e.printStackTrace();
        }
        
    }

    private static Matriz receberMatriz() throws IOException, ClassNotFoundException {
        ObjectInputStream receber = new ObjectInputStream(socket.getInputStream());
        return (Matriz) receber.readObject();
    }
    
    private static int receberParametro() throws IOException {
        DataInputStream receber = new DataInputStream(socket.getInputStream());
        return receber.readInt();
    }

    private static void enviarMatriz(Matriz matriz) throws IOException {
        ObjectOutputStream enviar = new ObjectOutputStream(socket.getOutputStream());
        enviar.writeObject(matriz);
    }
    
}