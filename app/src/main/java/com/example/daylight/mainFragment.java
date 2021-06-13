package com.example.daylight;

import android.app.Dialog;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;


public class mainFragment extends Fragment {

    Button btn1, btn2;
    customDialog  customDialog;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.fragment_main, container, false);

        Button btn1 = rootView.findViewById(R.id.btn1);
        Button btn2 = rootView.findViewById(R.id.btn2);

        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              showDiarylog();
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

    //이어인픽셀을 통해 일기입력 추가 받기
    public void showDiarylog(){
        Button btnSave,btnImage;
        customDialog octDialog = new customDialog(getContext(),R.layout.dialog_diary);
        octDialog.setCanceledOnTouchOutside(true);// 다이알로그 바깥영역 터치시, 다이알로그 닫힘
        octDialog.setCancelable(true);//Back키로 다이어로그 닫기

        octDialog.getWindow().setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.MATCH_PARENT);
        octDialog.show();
    }
}

