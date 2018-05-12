package fr.levon.projettechno;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class MainAdapter extends BaseAdapter {

    Coin[] coins;
    Context context;
    LayoutInflater layoutInflater;
    private final String[] itemname;
    private final Integer[] imgIds;
    Coin coinChoisi;

    public MainAdapter(Coin[] coins, Context context, String[] itemname, Integer[] imgIds) {
        super();
        this.coins = coins;
        this.context = context;
        layoutInflater = LayoutInflater.from(context);
        this.itemname = itemname;
        this.imgIds = imgIds;
        coinChoisi=null;
    }

    @Override
    public int getCount() {
        return coins.length;
    }

    @Override
    public Object getItem(int i) {
        return coins[i];
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(final int i, View view, ViewGroup parent) {
        View v;
        if(view==null) {
             v = layoutInflater.inflate(R.layout.item_layout, null);
        }
        else{
            v=view;
        }

        ImageView img = v.findViewById(R.id.img);
        Button button=v.findViewById(R.id.ButtonCoin);
        button.setText(coins[i].getName());
        img.setImageResource(imgIds[i]);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), BuySellActivity.class).putExtra("achatOuVente",coins[i].getSymbol());
                context.startActivity(intent);
                coinChoisi=coins[i];
            }
        });

        return v;
    }
}
