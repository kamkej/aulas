package br.com.tads.db.testedb;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.sql.SQLException;

/**
 * Created by julio on 11/5/15.
 */
public class GasOperations {

    private  BDWrapper dbHelper;
    private String[] GAS_COLUMNS= {BDWrapper.TABLE_ID,BDWrapper.POSTO_NOME,BDWrapper.VALOR};
    private SQLiteDatabase database;

    public GasOperations(Context context) {
        dbHelper = new BDWrapper(context);
    }

    public void open() throws SQLException{
        database = dbHelper.getWritableDatabase();
    }
    public void close(){
        dbHelper.close();
    }
    public Gas addGas(String nome,float value){
        ContentValues contentValues = new ContentValues();
        contentValues.put(BDWrapper.POSTO_NOME,nome);
        contentValues.put(BDWrapper.VALOR, value);
    long id  = database.insert(BDWrapper.TABLE,null,contentValues);
        Cursor cursor = database.query(BDWrapper.TABLE, GAS_COLUMNS, BDWrapper.TABLE_ID + " = " + id, null, null, null, null);
        cursor.moveToFirst();

        Gas gas = parseStudent(cursor);
        cursor.close();
        return  gas;
    }


    private Gas parseStudent(Cursor cursor) {
        Gas gas = new Gas();
        gas.setId(cursor.getInt(0));
        gas.setNome(cursor.getString(1));
        gas.setValor(cursor.getFloat(2));
        return gas;
    }
}
