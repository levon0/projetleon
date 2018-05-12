package fr.levon.projettechno;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

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
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS Coin");
        String requete = "CREATE TABLE " + DATABASE_NAME + "    (" +
                "ID integer PRIMARY KEY AUTOINCREMENT, "
                + NOM + " TEXT,"
                + SYMBOL + " TEXT, " + RANK + " INTEGER, "
                + PRICE_USD + " REAL, " + PRICE_BTC + " REAL,"
                + CHANGE1H + " REAL, " + CHANGE24H + " REAL, " + CHANGE7D +" REAL )";
        sqLiteDatabase.execSQL(requete);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS Coin");
    }

    public void addData(Coin coin){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(NOM,coin.getName());
        values.put(SYMBOL,coin.getSymbol());
        values.put(RANK,coin.getRank());
        values.put(PRICE_USD,coin.getPriceUSD());
        values.put(PRICE_BTC,coin.getPriceBTC());
        values.put(CHANGE1H,coin.getChange1H());
        values.put(CHANGE24H,coin.getChange24H());
        values.put(CHANGE7D,coin.getChange7D());
        db.insert(DATABASE_NAME,null,values);

    }

    public Coin getCoinBySymbol(String symbol){
        SQLiteDatabase db = this.getReadableDatabase();
        String requete = "SELECT * FROM " + DATABASE_NAME + " WHERE " + SYMBOL + "= ?";

        String[] args={symbol};
        Cursor cursor = db.rawQuery(requete, args);
        /*String[] whereArgs = new String[] {symbol};
        Cursor cursor = db.query(DATABASE_NAME,null,SYMBOL+ "=?",whereArgs,null,null,null);*/
        cursor.moveToFirst();
        Log.e("SYMBOLEDEFDP ",cursor.getString(1));
        Coin coin = new Coin();
        coin.setName(cursor.getString(0));
        coin.setSymbol(symbol);
        coin.setRank((cursor.getInt(2)));
        coin.setPriceBTC(cursor.getFloat(3));
        coin.setPriceUSD(cursor.getFloat(4));
        coin.setChange1H(cursor.getFloat(5));
        coin.setChange24H(cursor.getFloat(6));
        coin.setChange7D(cursor.getFloat(7));
        cursor.close();
        return coin;
    }

}
