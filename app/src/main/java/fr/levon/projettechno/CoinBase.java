package fr.levon.projettechno;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class CoinBase extends SQLiteOpenHelper{

    private static final String DATABASE_NAME = "Coin";
    private static final String NOM = "nom";
    private static final String SYMBOL = "Symbole";
    private static final String RANK = "Rang";
    private static final String PRICE_USD = "prixUSD";
    private static final String PRICE_BTC = "prixBTC";
    private static final String CHANGE1H = "Variation1H";
    private static final String CHANGE24H = "Variation24H";
    private static final String CHANGE7D = "Variation7J";

    public CoinBase(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, DATABASE_NAME, null, version);
    }
    public CoinBase(Context context, String name, int version) {
        super(context, DATABASE_NAME, null, version);
    }


    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String requete = "CREATE TABLE " + DATABASE_NAME + " ("
                + NOM + " TEXT PRIMARY KEY,"
                + SYMBOL + " TEXT, " + RANK + " INTEGER, "
                + PRICE_USD + " REAL, " + PRICE_BTC + " REAL,"
                + CHANGE1H + " REAL, " + CHANGE24H + " REAL, " + CHANGE7D +" REAL )";
        sqLiteDatabase.execSQL(requete);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    public void addData(Coin coin){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(NOM,coin.getName());
        values.put(SYMBOL,coin.getName());
        values.put(RANK,coin.getName());
        values.put(PRICE_USD,coin.getName());
        values.put(PRICE_BTC,coin.getName());
        values.put(CHANGE1H,coin.getName());
        values.put(CHANGE24H,coin.getName());
        values.put(CHANGE7D,coin.getName());


    }
}
