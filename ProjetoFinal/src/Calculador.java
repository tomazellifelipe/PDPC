import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Calculador
 */
public class Calculador {

    public static void main(String[] args) {
        try {
            ServerSocket serverSocket = new ServerSocket(4000);
            Socket socket = serverSocket.accept();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        
    }
    
}