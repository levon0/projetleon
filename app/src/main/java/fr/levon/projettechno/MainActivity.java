package fr.levon.projettechno;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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
    String[] itemname;
    int[] imgIds;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.listViewPrincipale=(ListView) findViewById(R.id.list);
        try {
            this.run();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }


    protected void run() throws IOException {

        Request request = new Request.Builder()
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
                itemname= new String[coins.length];
                imgIds=new int[coins.length];
                for (int i=0;i<coins.length;i++){
                    itemname[i]=coins[i].getSymbol();
                    imgIds[i]=i;
                }
                runOnUiThread(new Runnable() {
                    public void run(){
                        Toast.makeText(MainActivity.this, "Informations récupérées.",
                                Toast.LENGTH_SHORT).show();
                        MainAdapter adapter = new MainAdapter(coins,MainActivity.this, itemname, imgIds);
                        listViewPrincipale.setAdapter(adapter);

                    }
                });



            }




        });

    }

}
