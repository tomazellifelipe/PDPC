package src;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Random;
import java.util.Scanner;

public class Cliente {

    private static Socket socket;
    private int id;
    private static boolean admin = false;

    public Cliente() {
        this.id = gerarIdAleatorio();
    }

    public static void main(String[] args) {
        Cliente cli = new Cliente();
        Scanner input = new Scanner(System.in);

        try {
            conexaoUTPComServidor("127.0.0.1", 5050);
            System.out.println("Procurar por: ");
            enviarPermissao();
            enviarMsgParaServidor(input.nextLine());
            enviarMsgParaServidor(Integer.toString(cli.id));
            System.out.println("Num do protocolo: " + cli.id);
            input.close();
            String resposta = receberMsgDoServidor();
            System.out.println("Resultado da busca:\n" + resposta);
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
    
    private static void enviarPermissao() throws IOException {
        DataOutputStream saida = new DataOutputStream(socket.getOutputStream());
        saida.writeBoolean(admin);;
    }

    private static String receberMsgDoServidor() throws IOException {
        DataInputStream entrada = new DataInputStream(socket.getInputStream());
        return entrada.readUTF();
    }

    private int gerarIdAleatorio() {
        Random r = new Random();
        char[] rchars = {'a', 'w', 'e', 'r', 't', 'y', 'u', 'i', 'o', 'p'};
        String nomeRandom = "";
        for (int i = 0; i < 6; i++) {
            nomeRandom += rchars[r.nextInt(10)];
        }
        return nomeRandom.hashCode();
    }

}
