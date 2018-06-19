package Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.asu.hebagp.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import Objects.Item;

/**
 * Created by Mostafa on 2018/01/16.
 */

public class MyItemsAdapter extends BaseAdapter {

    private Context context;
    private ArrayList<Item> arrayList;

    public MyItemsAdapter(Context context, ArrayList<Item> arrayList) {
        this.context = context;
        this.arrayList = arrayList;
    }

    @Override
    public int getCount() {
        return arrayList.size();
    }

    @Override
    public Item getItem(int position) {
        return arrayList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return getItem(position).getId();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        convertView = inflater.inflate(R.layout.list_item_my_items,parent,false);

        TextView name , date ;
        ImageView img;

        name = (TextView) convertView.findViewById(R.id.list_item_name);
        date = (TextView) convertView.findViewById(R.id.list_item_date);
        img = (ImageView) convertView.findViewById(R.id.list_item_img);

        Item item = getItem(position);

        name.setText(item.getName());
        date.setText(item.getDate());

        try {

                Picasso.with(context).load(item.getCaptured()).into(img);


            } catch (Exception e) {

            }
//        img.setImageDrawable(item.getCaptured());

        return convertView;
    }
}
