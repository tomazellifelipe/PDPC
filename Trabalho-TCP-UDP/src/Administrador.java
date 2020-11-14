package src;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class Administrador {

    private static Socket socket;
    private static boolean admin = true;

    public static void main(String[] args) {
        try {
            conexaoUTPComServidor("127.0.0.1", 5050);
            enviarPermissao();
            String logCompleto = receberMsgDoServidor();
            System.out.println(logCompleto);
            int timeout = receberTimeoutDoServidor();
            System.out.println("Timeout " + timeout);
            alterarTimeOut(5000);
            socket.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void conexaoUTPComServidor(String host, int port) throws IOException {
        socket = new Socket(host, port); 
    }

    private static void enviarPermissao() throws IOException {
        DataOutputStream saida = new DataOutputStream(socket.getOutputStream());
        saida.writeBoolean(admin);;
    }

    private static String receberMsgDoServidor() throws IOException {
        DataInputStream entrada = new DataInputStream(socket.getInputStream());
        return entrada.readUTF();
    }

    private static void alterarTimeOut(int milisegundos) throws IOException {
        DataOutputStream saida = new DataOutputStream(socket.getOutputStream());
        saida.writeInt(milisegundos);
    }

    private static int receberTimeoutDoServidor() throws IOException {
        DataInputStream entrada = new DataInputStream(socket.getInputStream());
        return entrada.readInt();
    }
    
}
