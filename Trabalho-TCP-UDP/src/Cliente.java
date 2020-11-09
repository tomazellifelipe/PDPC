package src;

import java.io.DataInput;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

public class Cliente {
    public static void main(String[] args) {
        try {
            Socket socket = new Socket("127.0.0.1", 5050);

            DataOutputStream saida = new DataOutputStream(socket.getOutputStream());
            saida.writeUTF("mesa");
            System.out.println("Mensagem enviada cliente-servidor");

            DataInputStream entrada = new DataInputStream(socket.getInputStream());
            String pesquisa = entrada.readUTF();

            socket.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
