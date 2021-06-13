package com.example.daylight;

import android.app.Dialog;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.Toast;


public class mainFragment extends Fragment {

    Button btn1, btn2;
    customDialog  customDialog;
    ViewGroup rootView;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootView = (ViewGroup) inflater.inflate(R.layout.fragment_main, container, false);

        Button btn1 = rootView.findViewById(R.id.btn1);
        Button btn2 = rootView.findViewById(R.id.btn2);
        customDialog = new customDialog(getContext());
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                customDialog.showDiarylog();
            }
        });
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainActivity main =(MainActivity)getActivity();
                main.changeFragemnt(3);
            }
        });
        return rootView;
    }


}

