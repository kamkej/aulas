package br.com.tads.artman;

import java.io.Serializable;

/**
 * Created by julio on 10/29/15.
 */
public class Artigo implements Serializable {
    private static final long serialVersionUID = 1633833011084400384L;
    int id;
    String revista;
    String nome;
    String edicao;
    int status;
    int pago;
}
