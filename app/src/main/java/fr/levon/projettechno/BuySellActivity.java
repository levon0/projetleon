package fr.levon.projettechno;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class BuySellActivity extends AppCompatActivity {

    View view;
    Button backButton;
    Button buyButton;
    Coin coin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buy_sell);
        backButton = this.findViewById(R.id.backAchat);
        view = this.findViewById(R.id.amountCrypto);
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
        /*public void onCheckboxClicked(View view) {

        }*/
    }
}
