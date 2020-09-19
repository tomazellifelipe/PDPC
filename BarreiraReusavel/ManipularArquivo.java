import java.io.*;

public class ManipularArquivo implements Serializable {

    public static void salvar(String nomeDoArquivo, Object dados) throws IOException {
        FileOutputStream arquivo = new FileOutputStream(nomeDoArquivo);
        ObjectOutputStream gravador = new ObjectOutputStream(arquivo);
        gravador.writeObject(dados);
        gravador.close();
        arquivo.close();
    }

}
