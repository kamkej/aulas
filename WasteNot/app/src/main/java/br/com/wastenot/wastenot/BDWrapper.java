package br.com.wastenot.wastenot;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.SQLDataException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by ssl on 3/21/16.
 */
public class BDWrapper extends SQLiteOpenHelper {
    //Database
    public static final String DB_PATH = "/data/data/br.com.wastenot.wastenot/databases/";
    public static final String DATABASE_NAME= "wastenot.db";
    public static final int DATABASE_VERSION=  1;
    public static final String TABLE = "Cards";
    private SQLiteDatabase myDatabase;
    private final Context myContext;


// Table columns names
    public static final String KEY_ID =         "id";
    public static final String KEY_LAYOUT =     "layout";
    public static final String KEY_NAME =       "name";
    public static final String KEY_M_C =        "manaCost";
    public static final String KEY_CMC =        "cmc";
    public static final String KEY_COLOR =      "colors";
    public static final String KEY_TYPE =       "type";
    public static final String KEY_SUPER_TYPE = "supertype";
    public static final String KEY_TYPES =      "types";
    public static final String KEY_SUB_TYPE =   "subtypes";
    public static final String KEY_RARITY =     "rarity";
    public static final String KEY_TEXT =       "text";
    public static final String KEY_FLAVOR =     "flavor";
    public static final String KEY_ARTIST =     "artist";
    public static final String KEY_NUNBER =     "number";
    public static final String KEY_POWER =      "power";
    public static final String KEY_TOUGHNESS =  "toughness";
    public static final String KEY_IMG_NAME =   "imageName";
    public static final String KEY_M_S_ID =     "multiverseid";
    public static final String KEY_CARD_ID =    "card_id";


    public BDWrapper(Context context) {

        super(context, DATABASE_NAME,null,DATABASE_VERSION);
        this.myContext = context;
        this.createDatabase();
    }

    public void createDatabase(){
        try {
            boolean dbExit= checkDatabase();
            if(dbExit){
                Log.d("msg", "exists");
            }else {
                this.getReadableDatabase();
                copyDatabase();
                Log.d("msg", "copy");
            }
        }catch (Exception e){

        }
    }
    private boolean checkDatabase(){
        SQLiteDatabase checkDB = null;
        try {
            String myPath  = DB_PATH + DATABASE_NAME;
            checkDB = SQLiteDatabase.openDatabase(myPath,null,SQLiteDatabase.OPEN_READONLY);
        }catch (SQLiteException e){

        }
        if(checkDB != null){
            checkDB.close();
        }
        return checkDB != null ? true:false;
    }
    private void copyDatabase(){
       // Log.d("dbi", ');

        try{
            InputStream myInput = myContext.getAssets().open(DATABASE_NAME);
            String outFileName = DB_PATH + DATABASE_NAME;
            OutputStream myOutput = new FileOutputStream(outFileName);
            byte[] buffer = new byte[1024];
            int length;
            while ((length = myInput.read(buffer))>0){
                myOutput.write(buffer, 0, length);
            }

            //Close the streams
            myOutput.flush();
            myOutput.close();
            myInput.close();
        }
        catch (Exception e) {
            //catch exception
        }
    }
    public SQLiteDatabase openDatabase() throws  SQLDataException{
        String myPath = DB_PATH + DATABASE_NAME;
        myDatabase = SQLiteDatabase.openDatabase(myPath,null,SQLiteDatabase.OPEN_READONLY);
        return myDatabase;
    }
    public synchronized  void close(){
        if(myDatabase != null){
            myDatabase.close();
            super.close();
        }
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    //    db.execSQL("DROP TABLE IF EXISTS "+TABLE);
      //  onCreate(db);
    }

    public Cards getCard(int id){
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TABLE,new String[]{KEY_ID,KEY_NAME},KEY_ID + "=?", new String[] {String.valueOf(id)},null,null,null,null );
        if(cursor != null)
            cursor.moveToFirst();
        Cards cards = new Cards();
        return cards;
    }
    public List<Cards> getAllCard(){
        List<Cards> cardsList = new ArrayList<Cards>();
        String selectQuery = "SELECT * FROM "+ TABLE;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery,null);

        if(cursor.moveToFirst()){
            do{
                Cards cards = new Cards();
                cards.setId(cursor.getString(0));
                cards.setName(cursor.getString(1));
                cards.setManaCost(cursor.getString(2));
                cards.setCmc(Float.parseFloat(cursor.getString(3)));
                cards.setColor(Integer.parseInt(cursor.getString(4)));
                cards.setType(cursor.getString(5));
                cards.setSupertypes(cursor.getString(6));
                cards.setTypes(cursor.getString(7));
                cards.setSubtypes(cursor.getString(8));
                cards.setRarity(cursor.getString(9));
                cards.setText(cursor.getString(10));
                cards.setFlavor(cursor.getString(11));
                cards.setArtist(cursor.getString(12));
                cards.setNumber(cursor.getString(13));
                cards.setPower(cursor.getString(14));
                cards.setToughness(cursor.getString(15));
                cards.setLayout(cursor.getString(16));
                cards.setMultiverseid(cursor.getString(17));
                cards.setImageName(cursor.getString(18));
                cardsList.add(cards);

            }while (cursor.moveToNext());
        }
        return  cardsList;
    }
    public void addCards(Cards cards){
        SQLiteDatabase db = this.getReadableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_LAYOUT,cards.getLayout());
        values.put(KEY_NAME,cards.getName());
        values.put(KEY_M_C,cards.getManaCost());
        values.put(KEY_CMC,cards.getCmc());
     //   values.put(KEY_COLOR,cards.getColors());
        values.put(KEY_TYPE,cards.getType());
       // values.put(KEY_SUPER_TYPE,cards.getSupertype());
        values.put(KEY_TYPES,cards.getTypes());
        values.put(KEY_SUB_TYPE,cards.getSubtypes());
        values.put(KEY_RARITY,cards.getRarity());
        values.put(KEY_TEXT,cards.getText());
        values.put(KEY_FLAVOR,cards.getFlavor());
        values.put(KEY_ARTIST,cards.getArtist());
        values.put(KEY_NUNBER,cards.getNumber());
        values.put(KEY_POWER,cards.getPower());
        values.put(KEY_TOUGHNESS,cards.getToughness());
        values.put(KEY_IMG_NAME,cards.getImageName());
        values.put(KEY_M_S_ID,cards.getMultiverseid());
        //values.put(KEY_CARD_ID,cards.getCard_id());

        db.insert(TABLE, null, values);
        db.close();

    }

}
