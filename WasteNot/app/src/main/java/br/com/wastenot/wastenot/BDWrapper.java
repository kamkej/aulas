
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
    public  List<Cards> getCard(String  name){
        List<Cards> cardsList = new ArrayList<Cards>();
        String selectQuery = "SELECT * FROM "+ TABLE +" WHERE name like '%"+ name+"%'";
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery,null);

        if(cursor.moveToFirst()){
            do{
                Cards card = new Cards();

                card.setId(cursor.getString(0));
                card.setLayout(cursor.getString(1));
                card.setName(cursor.getString(2));
                card.setNames(cursor.getString(3));
                card.setManaCost(cursor.getString(4));
                try {
                    card.setCmc(Float.parseFloat(String.valueOf(cursor.getString(5))));
                }catch(NumberFormatException e){
                    card.setCmc(5);
                }
                card.setColor(cursor.getString(6));
                card.setColorIdentity(cursor.getString(7));
                card.setType(cursor.getString(8));
                card.setSupertypes(cursor.getString(9));
                card.setTypes(cursor.getString(10));
                card.setSubtypes(cursor.getString(11));
                card.setRarity(cursor.getString(12));
                card.setText(cursor.getString(13));
                card.setFlavor(cursor.getString(14));
                card.setArtist(cursor.getString(15));
                card.setNumber(cursor.getString(16));
                card.setPower(cursor.getString(17));
                card.setToughness(cursor.getString(18));
                card.setLoyalty(cursor.getString(19));
                card.setMultiverseid(cursor.getString(20));
                card.setVariations(cursor.getString(21));
                card.setImageName(cursor.getString(22));
                card.setWatermark(cursor.getString(23));
                card.setBorder(cursor.getString(24));
                card.setTimeshifted(cursor.getString(25));
                card.setHand(cursor.getString(26));
                card.setLife(cursor.getString(27));
                card.setReserved(cursor.getString(28));
                card.setReleaseDate(cursor.getString(29));
                card.setStarter(cursor.getString(30));



                cardsList.add(card);

            }while (cursor.moveToNext());
        }
        return  cardsList;
    }
    public  List<Cards> getHaveCard(){
        List<Cards> cardsList = new ArrayList<Cards>();
        String selectQuery = "SELECT * FROM  cards WHERE  haveList = '1'";
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery,null);

        if(cursor.moveToFirst()){
            do{
                Cards card = new Cards();

                card.setId(cursor.getString(0));
                card.setLayout(cursor.getString(1));
                card.setName(cursor.getString(2));
                card.setNames(cursor.getString(3));
                card.setManaCost(cursor.getString(4));
                try {
                    card.setCmc(Float.parseFloat(String.valueOf(cursor.getString(5))));
                }catch(NumberFormatException e){
                    card.setCmc(5);
                }
                card.setColor(cursor.getString(6));
                card.setColorIdentity(cursor.getString(7));
                card.setType(cursor.getString(8));
                card.setSupertypes(cursor.getString(9));
                card.setTypes(cursor.getString(10));
                card.setSubtypes(cursor.getString(11));
                card.setRarity(cursor.getString(12));
                card.setText(cursor.getString(13));
                card.setFlavor(cursor.getString(14));
                card.setArtist(cursor.getString(15));
                card.setNumber(cursor.getString(16));
                card.setPower(cursor.getString(17));
                card.setToughness(cursor.getString(18));
                card.setLoyalty(cursor.getString(19));
                card.setMultiverseid(cursor.getString(20));
                card.setVariations(cursor.getString(21));
                card.setImageName(cursor.getString(22));
                card.setWatermark(cursor.getString(23));
                card.setBorder(cursor.getString(24));
                card.setTimeshifted(cursor.getString(25));
                card.setHand(cursor.getString(26));
                card.setLife(cursor.getString(27));
                card.setReserved(cursor.getString(28));
                card.setReleaseDate(cursor.getString(29));
                card.setStarter(cursor.getString(30));



                cardsList.add(card);

            }while (cursor.moveToNext());
        }
        return  cardsList;
    }
    public boolean updateCard(String id,String have, String  wish ){
        SQLiteDatabase db = this.getReadableDatabase();
        ContentValues values = new ContentValues();
        values.put("whishlist",wish);
        values.put("havelist",have);

        return  db.update(TABLE,values,"id = '"+id+"'",null)>0;
    }
    public List<Cards> getAllCard(){
        List<Cards> cardsList = new ArrayList<Cards>();
        String selectQuery = "SELECT * FROM "+ TABLE;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery,null);

        if(cursor.moveToFirst()){
            do{
                Cards card = new Cards();

                card.setId(cursor.getString(0));
                card.setLayout(cursor.getString(1));
                card.setName(cursor.getString(2));
                card.setNames(cursor.getString(3));
                card.setManaCost(cursor.getString(4));
                try {
                    card.setCmc(Float.parseFloat(String.valueOf(cursor.getString(5))));
                }catch(NumberFormatException e){
                    card.setCmc(5);
                }
//                    c.setColor(Integer.parseInt(js.get("color").toString()));
//                    c.setColorIdentity(Integer.parseInt(js.get("colorIdentity").toString()));
                card.setType(cursor.getString(8));
                card.setSupertypes(cursor.getString(9));
                card.setTypes(cursor.getString(10));
                card.setSubtypes(cursor.getString(11));
                card.setRarity(cursor.getString(12));
                card.setText(cursor.getString(13));
                card.setFlavor(cursor.getString(14));
                card.setArtist(cursor.getString(15));
                card.setNumber(cursor.getString(16));
                card.setPower(cursor.getString(17));
                card.setToughness(cursor.getString(18));
                card.setLoyalty(cursor.getString(19));
                card.setMultiverseid(cursor.getString(20));
                card.setVariations(cursor.getString(21));
                card.setImageName(cursor.getString(22));
                card.setWatermark(cursor.getString(23));
                card.setBorder(cursor.getString(24));
                card.setTimeshifted(cursor.getString(25));
                card.setHand(cursor.getString(26));
                card.setLife(cursor.getString(27));
                card.setReserved(cursor.getString(28));
                card.setReleaseDate(cursor.getString(29));
                card.setStarter(cursor.getString(30));



                cardsList.add(card);

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
        values.put(KEY_M_S_ID, cards.getMultiverseid());
        //values.put(KEY_CARD_ID,cards.getCard_id());

        db.insert(TABLE, null, values);
        db.close();


    }

}

