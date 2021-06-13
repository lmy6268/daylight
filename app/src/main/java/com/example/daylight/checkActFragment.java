package com.example.daylight;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;


public class checkActFragment extends Fragment {

    customDialog  customDialog;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ViewGroup rootView= (ViewGroup) inflater.inflate(R.layout.fragment_diary,
                container, false);

        Button btn4 = rootView.findViewById(R.id.btn4);
        Button btn5 = rootView.findViewById(R.id.btn5);
        Button btnSave = rootView.findViewById(R.id.btnSave);

        btn4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDiarylog();
            }
        });
        btn5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDiarylog();
            }
        });

        return rootView;
    }

    public void showDiarylog(){
        customDialog octDialog = new customDialog(getContext(),R.layout.dialog_diary);
        octDialog.setCanceledOnTouchOutside(true);
        octDialog.setCancelable(true);
        octDialog.getWindow().setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.MATCH_PARENT);
        octDialog.show();
    }
}
