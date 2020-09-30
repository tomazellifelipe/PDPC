package src;

import java.util.ArrayList;
import java.util.Collections;

public class ListaDeInteiros {

    private final int MAX_LENGTH = 1000000;
    private ArrayList<Integer> list;

    public ListaDeInteiros() {
        
        this.list = new ArrayList<>();

    }

    public void popular() {

        for (int i = 0; i < MAX_LENGTH; i++) {
            list.add(GerarInteiro.gerar(10000000));
        }        
    }

    public void ordenar() { Collections.sort(list); }

    public ArrayList<Integer> getList() { return list; }

    public void setList(ArrayList<Integer> list) { this.list = list; }

}
