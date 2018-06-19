package Fragments;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.asu.hebagp.MainActivity;
import com.asu.hebagp.R;
import com.asu.hebagp.StartApplication;

/**
 * Created by Mostafa on 2018/01/09.
 */

public class LanguageSettingsFragment extends Fragment {

    View view;

    LinearLayout en_ll , ar_ll , de_ll , es_ll ,fr_ll , ru_ll;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_language_settings,container,false);

        MainActivity.activeLanguage();
        initilizeComponents();
        setListiners();


        return view;
    }

    private void setListiners() {
        en_ll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setLanguage(StartApplication.en_lang);
            }
        });

        ar_ll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setLanguage(StartApplication.ar_lang);
            }
        });

        de_ll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                setLanguage(StartApplication.de_lang);
            }
        });

        es_ll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setLanguage(StartApplication.es_lang);
            }
        });

        fr_ll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setLanguage(StartApplication.fr_lang);
            }
        });

        ru_ll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setLanguage(StartApplication.ru_lang);
            }
        });
    }

    private void setLanguage(int lang) {
        StartApplication.language = lang;
        SharedPreferences.Editor editor = StartApplication.sp.edit();

        editor.putInt(StartApplication.languageTag,lang);

        editor.commit();

        Intent intent = new Intent(getActivity(),MainActivity.class);
        getActivity().startActivity(intent);
        getActivity().finish();
    }

    private void initilizeComponents() {
        en_ll = (LinearLayout) view.findViewById(R.id.en_lang_ll);
        ar_ll = (LinearLayout) view.findViewById(R.id.ar_lang_ll);
        de_ll = (LinearLayout) view.findViewById(R.id.de_lang_ll);
        es_ll = (LinearLayout) view.findViewById(R.id.es_lang_ll);
        fr_ll = (LinearLayout) view.findViewById(R.id.fr_lang_ll);
        ru_ll = (LinearLayout) view.findViewById(R.id.ru_lang_ll);
    }


}
