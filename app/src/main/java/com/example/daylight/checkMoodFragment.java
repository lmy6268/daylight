package com.example.daylight;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;


public class checkMoodFragment extends Fragment {

    customDialog  customDialog;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ViewGroup rootView= (ViewGroup) inflater.inflate(R.layout.fragment_check_mood,
                container, false);

        Button btn4 = rootView.findViewById(R.id.btn4);
        Button btn5 = rootView.findViewById(R.id.btn5);
        Button btnSave = rootView.findViewById(R.id.btnSave);

        btn4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        btn5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        return rootView;
    }


}
