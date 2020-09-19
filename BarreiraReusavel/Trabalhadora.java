import java.util.ArrayList;
import java.io.*;
import java.util.*;
import java.util.Random;
import java.util.concurrent.Semaphore;

public class Trabalhadora extends Thread implements Serializable {

    private final int MAX_LIST_SIZE;
    private int fileNumber = 0;
    private int idTrabalhadora;
    private ArrayList<Integer> numIntList = new ArrayList<Integer>();
    private ArrayList<String> stringFileList;
    private Semaphore mutex, barreiraEntrada, barreiraSaída;
    private Random r = new Random();

    public Trabalhadora(int id, int listSize, ArrayList<String> filesList, Semaphore mutex, Semaphore barreiraEntrada,
            Semaphore barreiraSaida) {
        this.idTrabalhadora = id;
        this.MAX_LIST_SIZE = listSize;
        this.stringFileList = filesList;
        this.mutex = mutex;
        this.barreiraEntrada = barreiraEntrada;
        this.barreiraSaída = barreiraSaida;
    }

    private int gerarInt(int bound) {
        return r.nextInt(bound + 1);
    }

    private void addIntToList(int maxListSize) {
        for (int i = 0; i < maxListSize; i++) {
            this.numIntList.add(gerarInt((int) Math.pow(10, 7)));
        }
        return;
    }

    private void sortList(ArrayList<Integer> list) {
        Collections.sort(list);
        return;
    }

    private void salvar(String nomeDoArquivo) throws IOException {
        FileOutputStream arquivo = new FileOutputStream(nomeDoArquivo);
        ObjectOutputStream gravador = new ObjectOutputStream(arquivo);
        gravador.writeObject(this.numIntList);
        gravador.close();
        arquivo.close();
    }

    private String criarArquivo() throws IOException {
        String fileName = "ArrayListFile" + fileNumber + "_" + idTrabalhadora + ".ser";
        salvar(fileName);
        fileNumber++;
        return fileName;
    }

    public void run() {
        try {
            while (true) {
                addIntToList(MAX_LIST_SIZE);
                sortList(this.numIntList);
                String fileName = criarArquivo();
                mutex.acquire();
                Main.contador++;
                if (Main.contador == Main.MAX_TRABALHADORA) {
                    barreiraSaída.acquire();
                    barreiraEntrada.release();
                }
                mutex.release();
                barreiraEntrada.acquire();
                barreiraEntrada.release();
                mutex.acquire();
                stringFileList.add(fileName); // insere o nome do arquivo na lista de arquivos
                mutex.release();
                mutex.acquire();
                Main.contador--;
                if (Main.contador == 0) {
                    barreiraEntrada.acquire();
                    barreiraSaída.release();
                }
                mutex.release();
                barreiraSaída.acquire();
                barreiraSaída.release();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
