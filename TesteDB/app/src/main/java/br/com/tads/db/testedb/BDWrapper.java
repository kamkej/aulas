package br.com.tads.db.testedb;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by julio on 11/5/15.
 */
public class BDWrapper extends SQLiteOpenHelper  {
    public static final String TABLE = "gas";
    public static final String TABLE_ID = "_id";
    public static final String POSTO_NOME = "nome";
    public static final String VALOR = "valor";

    public static final String DATABASE_NAME = "Gas.db";
    public static final int DATABASE_VERSION = 1;

    public static final String DATABASE_CREATE = "create table " + TABLE
            + "(" + TABLE_ID + " integer primary key autoincrement, "
            + POSTO_NOME + " text not null, "
            + VALOR + " real not null);";




    public BDWrapper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(DATABASE_CREATE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE);
    }
}
