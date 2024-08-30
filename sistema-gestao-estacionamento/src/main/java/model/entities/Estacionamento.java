package model.entities;


import java.util.ArrayList;
import java.util.List;

public class Estacionamento {

    private static List<Vaga> vagas = new ArrayList<>(500);

    public Estacionamento(){
    }

    public static List<Vaga> getVagas() {
        return vagas;
    }

}
