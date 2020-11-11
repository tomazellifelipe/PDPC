package src;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class Cliente {

    private static Socket socket;

    public static void main(String[] args) {
        try {
            conexaoUTPComServidor("127.0.0.1", 5050);
            enviarMsgParaServidor("mesa");
            System.out.println("Mensagem enviada cliente-servidor");
            String resposta = receberMsgDoServidor();
            System.out.println("Recebido info: " + resposta);
            socket.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void conexaoUTPComServidor(String host, int port) throws IOException {
        socket = new Socket(host, port); 
    }

    private static void enviarMsgParaServidor(String msg) throws IOException {
        DataOutputStream saida = new DataOutputStream(socket.getOutputStream());
        saida.writeUTF(msg);
    }

    private static String receberMsgDoServidor() throws IOException {
        DataInputStream entrada = new DataInputStream(socket.getInputStream());
        return entrada.readUTF();
    }
}
