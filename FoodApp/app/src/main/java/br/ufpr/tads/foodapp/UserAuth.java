package br.ufpr.tads.foodapp;

import android.app.Application;

/**
 * Created by julio on 11/25/15.
 */
public class UserAuth extends Application {
    private int id;
    private String nome;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
