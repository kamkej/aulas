package br.com.tads.artman;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.sql.SQLException;

/**
 * Created by julio on 10/29/15.
 */
public class BancoDeDados {
    static String KEY_ID = "_id";
    static String KEY_NOME = "nome";
    static String KEY_REVISTA= "revista";
    static String KEY_EDICAO = "edicao";
    static String KEY_STATUS = "status";
    static String KEY_PAGO = "status";

    static String NOME_BANCO = "db_Revista";
    static String NOME_TABELA = "artigo";
    static int VERSAO_BANCO = 1;

    static String SQL_CREATE_TABLE = "create table " + NOME_TABELA +
            "("+KEY_ID+" integer primary key autoincrement, "
            + KEY_NOME + "text not null, "
            + KEY_REVISTA + " text, "
            + KEY_EDICAO + " text, "
            + KEY_STATUS + " integer, "
            + KEY_STATUS + " integer,"
            + KEY_PAGO + " integer);";
    final Context context;
    MeuOpenHelper openHelper;
    SQLiteDatabase db;

    public BancoDeDados(Context context) {
        this.context = context;
        openHelper = new MeuOpenHelper(context);
    }

    public BancoDeDados abrir()throws SQLException{
        db = openHelper.getWritableDatabase();
        return  this;
    }
    public  void fechar(){
        openHelper.close();
    }
    public long insereArtigo(String nome, String revista,String edicao,
                             int status,int pago ){
        ContentValues campos  = new ContentValues();
        campos.put(KEY_NOME,nome);
        campos.put(KEY_REVISTA,revista);
        campos.put(KEY_EDICAO,edicao);
        campos.put(KEY_STATUS,status);
        campos.put(KEY_PAGO, pago);
        return db.insert(NOME_TABELA,null,campos);

    }
    public boolean apagaArtigo(long id){
        return db.delete(NOME_TABELA,KEY_ID + "=" + id,null)>0;
    }
    public Cursor retornaTodosArtigos(){
        return  db.query(NOME_TABELA,new String[]{
            KEY_ID,KEY_NOME,KEY_REVISTA,KEY_EDICAO,KEY_PAGO},null,null,null,null,null);
    }
    public boolean atualizarArtigo(long id, String nome , String revista,
                                   String edicao, int status, int pago) {
        ContentValues args = new ContentValues();
        args.put(KEY_NOME, nome);
        args.put(KEY_REVISTA, revista);
        args.put(KEY_EDICAO, edicao);
        args.put(KEY_STATUS, status);
        args.put(KEY_PAGO, pago);
        return db.update(NOME_TABELA, args, KEY_ID + "=" + id, null) > 0;
    }
}


