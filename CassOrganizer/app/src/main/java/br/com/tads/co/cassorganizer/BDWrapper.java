package br.com.tads.co.cassorganizer;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by julio on 11/13/15.
 */
public class BDWrapper extends SQLiteOpenHelper {

    public static final String TABLE = "Students";
    public static final String TABLE_ID = "_id";
    public static final String NOME = "_name";
    public static final String NOTA = "_nota";
    public static final String TURMA = "_turma";
    public static final String STATUS = "_status";

    public static final String DATABASE_NAME = "class";
    public static final int DATABASE_VERSION = 1;

    public static final  String DATABASE_CREATE = "create table " + TABLE
            + "(" + TABLE_ID + " integer primary key autoincrement, "
            + NOME + " text not null, "
            + NOTA + " decimal, "
            + TURMA + " text not null, "
            + STATUS + " text not null);";

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
        onCreate(db);

    }
}
