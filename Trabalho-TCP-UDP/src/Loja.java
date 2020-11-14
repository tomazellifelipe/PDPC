package src;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.MulticastSocket;

public class Loja extends Thread {

    static int qtdLojas = 0;
    private int id;
    private int porta, index;
    private Produto[] catalogo = new Produto[6];
    private MulticastSocket multiSocket;
    private DatagramSocket socketServidor;
    private InetAddress grupo, enderecoServidor;

    public Loja(int id) {
        popularCatalogo();
        this.id = id;
        qtdLojas++;
    }

    public void run() {
        try {
            conexaoUDPComGrupo("224.0.0.1", 3000);
            conexaoUDPComServidor("127.0.0.1", 4545);

            while (true) {
                String msgDoCliente = receberMsgDoGrupo();
                if(contemItem(msgDoCliente)) {
                    enviarMsgParaServidor(formatarMsgDeRetorno()); 
                } else {
                    enviarMsgParaServidor("Loja: " + id + "\n" + 
                                          "\tProduto em falta");
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void popularCatalogo() {
        for (int i = 0; i < catalogo.length; i++) {
            catalogo[i] = new Produto();
        }
    }

    private void conexaoUDPComGrupo(String host, int port) throws IOException {
        grupo = InetAddress.getByName(host);
        multiSocket = new MulticastSocket(port);
        multiSocket.joinGroup(grupo);
    }

    private String receberMsgDoGrupo() throws IOException {
        byte[] mensagemBytes = new byte[1000];
        DatagramPacket mensagemPacote = new DatagramPacket(mensagemBytes, mensagemBytes.length);
        multiSocket.receive(mensagemPacote);
        return new String(mensagemPacote.getData(), mensagemPacote.getOffset(), mensagemPacote.getLength());
    }

    private void conexaoUDPComServidor(String host, int port) throws IOException {
        enderecoServidor = InetAddress.getByName(host);
        socketServidor = new DatagramSocket();
        porta = port;
    }

    private void enviarMsgParaServidor(String msg) throws IOException {
        byte[] retornoBytes = msg.getBytes();
        DatagramPacket retornoPacote = new DatagramPacket(retornoBytes, retornoBytes.length, enderecoServidor, porta);
        socketServidor.send(retornoPacote);
    }

    private boolean contemItem(String busca) {
        for (int i = 0; i < catalogo.length; i++) {
            if(catalogo[i].getNome().equals(busca)) {
                index = i;
                return true;
            }            
        }
        return false;
    }

    private String formatarMsgDeRetorno() {
        String item = catalogo[index].getNome();
        float preco = catalogo[index].getPreco();
        return "Loja: " + id + "\n" +
               "\tProd: " + item + "\n" +
               "\tPreco: " + preco;
    }
}
