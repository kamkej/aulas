package br.com.wastenot.wastenot;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ssl on 3/21/16.
 */
public class BDWrapper extends SQLiteOpenHelper {
    //Database
    public static final String DATABASE_NAME= "wastenote";
    public static final int DATABASE_VERSION=  1;
    public static final String TABLE = "Cards";
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
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_CARDS_TABLE= "CREATE TABLE "+ TABLE+"("
                + KEY_ID +" INTEGER PRIMARY KEY AUTOINCREMENT,"
<<<<<<< HEAD
                + KEY_NAME+" VARCHAR(50),"
                + KEY_M_C+" VARCHAR(50) ,"
                + KEY_CMC+" VARCHAR(50) ,"
                + KEY_COLOR+" VARCHAR(50),"
                + KEY_TYPE+" VARCHAR(50) ,"
                + KEY_SUPER_TYPE+" VARCHAR(50) ,"
                + KEY_TYPES+" VARCHAR(50) ,"
                + KEY_SUB_TYPE+" VARCHAR(50),"
                + KEY_RARITY+" VARCHAR(50),"
                + KEY_TEXT+" VARCHAR(300),"
                + KEY_FLAVOR+" VARCHAR(50),"
                + KEY_ARTIST+" VARCHAR(50),"
                + KEY_NUNBER+" VARCHAR(50),"
                + KEY_POWER+" VARCHAR(50),"
                + KEY_TOUGHNESS+" VARCHAR(50),"
                + KEY_LAYOUT+" VARCHAR(50),"
                + KEY_M_S_ID+" VARCHAR(50),"
                + KEY_IMG_NAME+" VARCHAR(50),"
                + KEY_CARD_ID+" VARCHAR(50),)";
=======
                + KEY_NAME+" VARCHAR(50) NOT NULL,"
                + KEY_M_C+" VARCHAR(50) NOT NULL,"
                + KEY_CMC+" VARCHAR(50) NOT NULL,"
                + KEY_COLOR+" VARCHAR(50)NOT NULL,"
                + KEY_TYPE+" VARCHAR(50) NOT NULL,"
                + KEY_SUPER_TYPE+" VARCHAR(50) NOT NULL,"
                + KEY_TYPES+" VARCHAR(50) NOT NULL,"
                + KEY_SUB_TYPE+" VARCHAR(50) NOT NULL,"
                + KEY_RARITY+" VARCHAR(50) NOT NULL,"
                + KEY_TEXT+" VARCHAR(300) NOT NULL,"
                + KEY_FLAVOR+" VARCHAR(50) NOT NULL,"
                + KEY_ARTIST+" VARCHAR(50) NOT NULL,"
                + KEY_NUNBER+" VARCHAR(50) NOT NULL,"
                + KEY_POWER+" VARCHAR(50) NOT NULL,"
                + KEY_TOUGHNESS+" VARCHAR(50) NOT NULL,"
                + KEY_LAYOUT+" VARCHAR(50) NOT NULL,"
                + KEY_M_S_ID+" VARCHAR(50) NOT NULL,"
                + KEY_IMG_NAME+" VARCHAR(50) NOT NULL,"
                + KEY_CARD_ID+" VARCHAR(50) NOT NULL)";
>>>>>>> 9cc8e11f8b7fbaef1400c53f27961eefffab2ea8
        db.execSQL(CREATE_CARDS_TABLE);




    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+TABLE);
        onCreate(db);
    }

    public Cards getCard(int id){
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TABLE,new String[]{KEY_ID,KEY_NAME},KEY_ID + "=?", new String[] {String.valueOf(id)},null,null,null,null );
        if(cursor != null)
            cursor.moveToFirst();
        Cards cards = new Cards(Integer.parseInt(cursor.getString(0)),cursor.getString(1));
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
                cards.setId(Integer.parseInt(cursor.getString(0)));
                cards.setName(cursor.getString(1));
                cards.setText(cursor.getString(10));
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
        values.put(KEY_COLOR,cards.getColors());
        values.put(KEY_TYPE,cards.getType());
        values.put(KEY_SUPER_TYPE,cards.getSupertype());
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
        values.put(KEY_CARD_ID,cards.getCard_id());

        db.insert(TABLE, null, values);
        db.close();

    }

}
