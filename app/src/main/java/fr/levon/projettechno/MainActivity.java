package fr.levon.projettechno;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;

public class MainActivity extends AppCompatActivity {

    final OkHttpClient client = new OkHttpClient();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ListView listViewPrincipale = (ListView) findViewById(R.id.list);
        try {
            this.run();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }


    protected void run() throws IOException {

        Request request = new Request.Builder()
                .url("https://api.coinmarketcap.com/v1/ticker/url")
                .build();
        Response response = client.newCall(request).execute();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                try (ResponseBody responseBody = response.body()) {
                    if (!response.isSuccessful())
                        throw new IOException("Unexpected code " + response);
                    Log.i("reponse",responseBody.string());
                }
                String jsonRecpu=response.body().string();
                JSONObject json=null;
                try {
                    json= new JSONObject(jsonRecpu);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                JSONArray noms = null;
                JSONArray symboles = null;
                JSONArray rangs = null;
                JSONArray prixUSD=null;
                JSONArray prixBTC=null;
                JSONArray change1H=null;
                JSONArray change24H=null;
                JSONArray change7J=null;
                try {
                    noms = json.getJSONArray("name");
                    symboles = json.getJSONArray("symbol");
                    rangs = json.getJSONArray("rank");
                    prixUSD = json.getJSONArray("price_usd");
                    prixBTC = json.getJSONArray("price_btc");
                    change1H = json.getJSONArray("percent_change_1h");
                    change24H = json.getJSONArray("percent_change_24h");
                    change7J = json.getJSONArray("percent_change_7d");
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        });
    }
}
