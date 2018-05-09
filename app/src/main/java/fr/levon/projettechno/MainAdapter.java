package fr.levon.projettechno;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class MainAdapter extends BaseAdapter {

    Coin[] coins;
    Context context;
    LayoutInflater layoutInflater;
    private final String[] itemname;
    private final int[] imgIds;

    public MainAdapter(Coin[] coins, Context context, String[] itemname, int[] imgIds) {
        super();
        this.coins = coins;
        this.context = context;
        layoutInflater = LayoutInflater.from(context);
        this.itemname = itemname;
        this.imgIds = imgIds;
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
    public View getView(int i, View view, ViewGroup parent) {
        View v;
        if(view==null) {
             v = layoutInflater.inflate(R.layout.item_layout, null);
        }
        else{
            v=view;
        }
        ImageView img = v.findViewById(R.id.img);
        TextView text=v.findViewById(R.id.text);
        text.setText(coins[i].getName());
        img.setImageResource(imgIds[i]);

        return v;
    }
}
