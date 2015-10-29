package br.com.tads.artman;

import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by julio on 10/29/15.
 */
public class MeuOpenHelper extends SQLiteOpenHelper {
    public MeuOpenHelper(Context context){
        super(context,BancoDeDados.NOME_BANCO,null,BancoDeDados.VERSAO_BANCO);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        try {
            db.execSQL(BancoDeDados.SQL_CREATE_TABLE);
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+ BancoDeDados.NOME_TABELA);
        onCreate(db);

    }
}
