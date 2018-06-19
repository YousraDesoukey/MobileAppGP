package Fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.asu.hebagp.MainActivity;
import com.asu.hebagp.R;

import java.util.ArrayList;

import Adapters.MyItemsAdapter;
import Objects.Item;

/**
 * Created by Mostafa on 2018/01/16.
 */

public class MyItemsFragment extends Fragment {

    View view;
    ListView myItemsLV;
    MyItemsAdapter myItemsAdapter;
    ArrayList<Item> myItemsArrayList;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_my_items,container,false);

        MainActivity.activeMyITems();
        initilizeComponents();

        return view;
    }

    private void initilizeComponents() {
        myItemsLV = (ListView) view.findViewById(R.id.myItemsLV);
        myItemsArrayList = new ArrayList<Item>();
        setDummyData();

        myItemsAdapter = new MyItemsAdapter(getActivity(),myItemsArrayList);

        myItemsLV.setAdapter(myItemsAdapter);

        myItemsLV.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Item item = myItemsAdapter.getItem(position);

                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                Fragment fragment = fragmentManager.findFragmentById(R.id.FragmentProvider);
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

                String tag = fragment.getTag();

                if(!tag.equals(MainActivity.itemPreviewFragmentTag))
                {
                    ItemPreviewFragment fragment2 = new ItemPreviewFragment();
                    fragment2.setArguments(item);
                    fragmentManager.popBackStack(MainActivity.itemPreviewFragmentTag, android.app.FragmentManager.POP_BACK_STACK_INCLUSIVE);
                    fragmentTransaction.replace(R.id.FragmentProvider,fragment2,MainActivity.itemPreviewFragmentTag)
                            .setTransition(android.app.FragmentTransaction.TRANSIT_FRAGMENT_FADE)
                            .addToBackStack(MainActivity.itemPreviewFragmentTag).commit();
                }

            }
        });
    }

    private void setDummyData() {
        Item item = new Item(1,"Nefertiti","Neferneferuaten Nefertiti (c. 1370 – c. 1330 BC) was an Egyptian queen and the Great Royal Wife (chief consort) of Akhenaten, an Egyptian Pharaoh. Nefertiti and her husband were known for a religious revolution, in which they worshiped one god only, Aten, or the sun disc. With her husband, she reigned at what was arguably the wealthiest period of Ancient Egyptian history.","https://image.ibb.co/giismm/cartouches_img.jpg","https://image.ibb.co/i0WOsR/captured.jpg","Monday , 22 - jan - 2018");

        myItemsArrayList.add(item);
    }


}
