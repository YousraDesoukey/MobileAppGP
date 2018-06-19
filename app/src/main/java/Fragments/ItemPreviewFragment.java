package Fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.asu.hebagp.R;
import com.squareup.picasso.Picasso;

import Objects.Item;

/**
 * Created by Mostafa on 2018/01/16.
 */

public class ItemPreviewFragment extends Fragment {

    View view;
    TextView itemNameTV  , itemAboutTV;
    ImageView itemPreview , itemCaptured;
    Item item;
    public void setArguments(Item item)
    {
        this.item = item;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState)
    {
        view = inflater.inflate(R.layout.fragment_item_preview,container,false);

        initilizeComponents();

        return view;
    }

    private void initilizeComponents() {
        itemNameTV = (TextView) view.findViewById(R.id.item_preview_name);
        itemAboutTV = (TextView) view.findViewById(R.id.item_preview_about);

        itemPreview = (ImageView) view.findViewById(R.id.item_preview_image);
        itemCaptured = (ImageView) view.findViewById(R.id.item_preview_captured);

        itemNameTV.setText(item.getName());
        itemAboutTV.setText(item.getAbout());

//        itemPreview.setImageDrawable(item.getImage());
//        itemCaptured.setImageDrawable(item.getCaptured());

        try {

            Picasso.with(getActivity()).load(item.getCaptured()).into(itemCaptured);
        } catch (Exception e) {

        }

        try {

            Picasso.with(getActivity()).load(item.getImage()).into(itemPreview);
        } catch (Exception e) {

        }
    }
}
