import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class Comunicacao extends Thread {
    private Socket socket;
    private Matriz[] matrizes;
    private int[] parametros;

    public Comunicacao(Socket socket, Matriz[] matrizes, int[] parametros) {
        this.socket = socket;
        this.matrizes = matrizes;
        this.parametros = parametros;
    }

    public void run() {
        try {
            for (Matriz matriz : matrizes) {
                enviarMatriz(matriz);
            }

            matrizes[2] = receberMatriz();
        } catch (Exception e) {
            //TODO: handle exception
        }

    }

    public void enviarMatriz(Matriz matriz) throws IOException {
        ObjectOutputStream enviar = new ObjectOutputStream(socket.getOutputStream());
        enviar.writeObject(matriz);
    }

    public Matriz receberMatriz() throws IOException, ClassNotFoundException {
        ObjectInputStream receber = new ObjectInputStream(socket.getInputStream());
        return (Matriz) receber.readObject();
    }
}
