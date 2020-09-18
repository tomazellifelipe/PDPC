import java.util.ArrayList;
import java.util.*;
import java.util.Random;

public class Trabalhadora extends Thread {

    private ArrayList<Integer> numIntList = new ArrayList<Integer>();
    private Random r = new Random();

    public Trabalhadora() {

    }

    public int gerarInt(int bound) {
        return r.nextInt(bound + 1);
    }

    public void addIntToList(int maxListSize) {
        for (int i = 0; i < maxListSize; i++) {
            numIntList.add(gerarInt((int) Math.pow(10, 7)));
        }
        return;
    }

    public void sortList(ArrayList<Integer> list) {
        Collections.sort(list);
        return;
    }

}
