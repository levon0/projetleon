package fr.levon.projettechno;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteCursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


public class BuySellActivity extends AppCompatActivity {

    View view;
    Button backButton;
    Button buyButton;
    TextView coinName;
    Coin coin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        CoinBase coinBase = new CoinBase(this, "Coins", null, 1);
        String coinSymbol = getIntent().getStringExtra("achatOuVente");
        this.coin = coinBase.getCoinBySymbol(coinSymbol);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buy_sell);
        backButton = this.findViewById(R.id.backAchat);
        view = this.findViewById(R.id.amountCrypto);
        coinName = this.findViewById(R.id.coinNameBuyMenu);
        coinName.setText(coin.getSymbol());
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(view.getContext(), MainActivity.class);
                startActivity(intent);
            }
        });
        buyButton = (Button) this.findViewById(R.id.buttonEnvoyer);
        buyButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intent = new Intent(view.getContext(), MainActivity.class);

            }
        });

    }
}
