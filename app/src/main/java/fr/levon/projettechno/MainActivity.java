package fr.levon.projettechno;

import android.app.Activity;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;

public class MainActivity extends AppCompatActivity {

    final OkHttpClient client = new OkHttpClient();
    ListView listViewPrincipale;
    String[] itemName;
    Integer[] imgIds;
    private Button opener;
    private Coin[] coins;
    private CoinBase coinBase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.coinBase=new CoinBase(this, "Coins", 1);
        setContentView(R.layout.activity_main);
        Folio folio = new Folio();

        this.listViewPrincipale=(ListView) findViewById(R.id.list);
        //Button viewCoin = findViewById (R.id.buttonCoin);
        try {
            this.run();
            this.opener = (Button) this.findViewById(R.id.ButtonCoin);
            /*Log.e("osef",opener.toString());
            opener.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(listViewPrincipale.getContext(), BuySellActivity.class);
                    startActivity(intent);
                }
            });*/

        } catch (IOException e) {
            e.printStackTrace();
        }





    }


    protected void run() throws IOException {

        final Request request = new Request.Builder()
                .url("https://api.coinmarketcap.com/v1/ticker/")
                .build();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String jsonRecup= null;
                try (ResponseBody responseBody = response.body()) {
                    if (!response.isSuccessful())
                        throw new IOException("Unexpected code " + response);
                    jsonRecup = responseBody.string();
                }
                ObjectMapper mapper = new ObjectMapper();
                final Coin[] coins = mapper.readValue(jsonRecup, Coin[].class);

                itemName = new String[coins.length];
                imgIds=new Integer[coins.length];

                CoinBase coinBase = new CoinBase(getActivity(),"Coins",1);
                Log.i("Test",coinBase.toString());
                ((MainActivity)getActivity()).setCoins(coins); //et on set les coins ?
                for (int i=0;i<coins.length;i++){
                    coinBase.addData(coins[i]);
                    itemName[i]=coins[i].getSymbol();
                    imgIds[i]=(Integer) getResources().getIdentifier(itemName[i].toLowerCase(),"drawable",getPackageName());
                    /*((MainActivity)getActivity()).getCoinBase().addData(coins[i]);*/
                }

                runOnUiThread(new Runnable() {
                    public void run(){
                        MainAdapter adapter = new MainAdapter(coins,MainActivity.this, itemName, imgIds);
                        listViewPrincipale.setAdapter(adapter);
                        Toast.makeText(MainActivity.this, "Informations récupérées.",
                                Toast.LENGTH_SHORT).show();


                    }
                });




            }





        });

    }
    public Coin[] getCoins() {
        return coins;
    }

    public void setCoins(Coin[] coins) {
        this.coins = coins;
    }


    public Activity getActivity() {
        return this;
    }

    public CoinBase getCoinBase() {
        return coinBase;
    }

    public void setCoinBase(CoinBase coinBase) {
        this.coinBase = coinBase;
    }
}
