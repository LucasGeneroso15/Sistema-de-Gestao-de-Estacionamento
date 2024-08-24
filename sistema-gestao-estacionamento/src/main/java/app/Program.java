package app;

import db.DBtest;
import entities.Pessoa;

public class Program {
    public static void main(String[] args) {

        DBtest.getConnection();

        Pessoa p = new Pessoa(null, "Pedro");
        //DBtest.insert(p);
        System.out.println("Pronto!");

        p = DBtest.findById(9);
        System.out.println(p);
    }
}
