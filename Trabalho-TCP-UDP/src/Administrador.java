package src;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

public class Administrador {

    private static Socket socket;
    private static boolean admin = true;

    public static void main(String[] args) {
        try {
            Scanner input = new Scanner(System.in);
            while (true) {
                menu();
                System.out.println("Escolhar uma opção:");
                int key = input.nextInt();
                conexaoUTPComServidor("127.0.0.1", 5050);
                enviarPermissao();
                switch (key) {
                    case 1:
                        funcaoDesejada(key);
                        String logCompleto = receberMsgDoServidor();
                        System.out.println(logCompleto);
                        break;
                    case 2:
                        funcaoDesejada(key);
                        int timeout = receberTimeoutDoServidor();
                        System.out.println("Timeout do servidor: " + timeout);
                        break;
                    case 3:
                        funcaoDesejada(key);
                        System.out.println("Alterar o timeout do servidor (ms):");
                        alterarTimeOut(input.nextInt());
                        break;
                    default:
                        break;
                }
            }
            
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void conexaoUTPComServidor(String host, int port) throws IOException {
        socket = new Socket(host, port); 
    }

    private static void enviarPermissao() throws IOException {
        DataOutputStream saida = new DataOutputStream(socket.getOutputStream());
        saida.writeBoolean(admin);
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

    private static void funcaoDesejada(int key) throws IOException {
        DataOutputStream saida = new DataOutputStream(socket.getOutputStream());
        saida.writeInt(key); 
    }
    
    private static void menu() {
        System.out.println("Logado como Admin");
        System.out.println("\tReceber relatório de buscas: 1");
        System.out.println("\tReceber timeout do servidor: 2");
        System.out.println("\tAlterar timeout do servidor: 3");
    }
}
