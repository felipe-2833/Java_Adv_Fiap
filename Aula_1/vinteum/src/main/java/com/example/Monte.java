package com.example;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Monte {

    private List<Carta> cartas = new ArrayList<>();

    public Monte() {
        // for (int n = 1; n <= 13; n++) {
        //     cartas.add(new Carta(n, Naipe.Clubs));
        //     cartas.add(new Carta(n, Naipe.Hearts));
        //     cartas.add(new Carta(n, Naipe.Diamonds));
        //     cartas.add(new Carta(n, Naipe.Spades));
        // }

        for (int n = 1; n <= 13; n++) {
            for(Naipe naipe : Naipe.values()){
                cartas.add(new Carta(n, naipe));
            }
        }
    }

    public void embaralhar(){
        Collections.shuffle(cartas);
    }

    public Carta virar(){
        return cartas.remove(0);
    }

}
