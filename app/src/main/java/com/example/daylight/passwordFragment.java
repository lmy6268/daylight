package com.example.daylight;


import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import static android.content.Context.MODE_PRIVATE;

public class passwordFragment extends Fragment {
    EditText pwd1, pwd2, pwd3, pwd4;
    private String pwd;
    Button btnAccess, btnRegister;
    int checked;
    BottomNavigationView bottomNavi;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.fragment_password, container, false);
        pwd1 = rootView.findViewById(R.id.pwd1);
        pwd2 = rootView.findViewById(R.id.pwd2);
        pwd3 = rootView.findViewById(R.id.pwd3);
        pwd4 = rootView.findViewById(R.id.pwd4);
        btnAccess = rootView.findViewById(R.id.btnAccess);
        btnRegister = rootView.findViewById(R.id.btnRegister);


//        GradientDrawable bgShape = (GradientDrawable) pwd1.getBackground();
//        bgShape.setColor(Color.BLUE);
//        GradientDrawable bgShape2 = (GradientDrawable) pwd2.getBackground();
//        bgShape.setColor(Color.BLUE);
//        GradientDrawable bgShape3 = (GradientDrawable) pwd3.getBackground();
//        bgShape.setColor(Color.BLUE);
//        GradientDrawable bgShape4 = (GradientDrawable) pwd4.getBackground();
//        bgShape.setColor(Color.BLUE);
        btnAccess.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if (pwd1.getText().toString().equals("") || pwd2.getText().toString().equals("")
                        || pwd3.getText().toString().equals("")||pwd4.getText().toString().equals("")) {
                    Toast.makeText(getContext(), "빈값이 있습니다", Toast.LENGTH_SHORT).show();
                } else {
                    pwd = pwd1.getText().toString() + pwd2.getText().toString() + pwd3.getText().toString() + pwd4.getText().toString();
                }
                try {
                    checked = checkPassword(pwd);

                } catch (IOException e) {
                    e.printStackTrace();
                }
                switch (checked) {
                    case 0:
                        Toast.makeText(getContext(), "비밀번호를 등록해 주세요", Toast.LENGTH_SHORT).show();
                        break;
                    case 1:
                        Toast.makeText(getContext(), "접속에 성공하였습니다", Toast.LENGTH_SHORT).show();
                        MainActivity main = ( MainActivity) getActivity();
                        bottomNavi=main.findViewById(R.id.bottomNavi);
                        bottomNavi.setVisibility(View.VISIBLE);
                        main.changeFragemnt(1);

                        break;
                    case 2:
                        Toast.makeText(getContext(), "비밀번호가 옳지 않습니다. 다시 입력해 주십시오. ", Toast.LENGTH_SHORT).show();
                        break;

                }
            }
        });
        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (pwd1.getText().toString().equals("") || pwd2.getText().toString().equals("")
                        || pwd3.getText().toString().equals("")||pwd4.getText().toString().equals("")) {
                    Toast.makeText(getContext(), "빈값이 있습니다", Toast.LENGTH_SHORT).show();
                } else {
                    pwd = pwd1.getText().toString() + pwd2.getText().toString() + pwd3.getText().toString() + pwd4.getText().toString();
                }
                try {
                    checked = checkPassword(pwd);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                if (checked == 0) {
                    try {
                        System.out.println(pwd);
                        savePassword(pwd);
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                        System.out.println("테스트입니다");
                    }
                } else {
                    Toast.makeText(getContext(), "이미 비밀번호가 등록되어 있습니다", Toast.LENGTH_SHORT).show();
                }
            }
        });


        return rootView;
    }

    public int checkPassword(String passwd) throws IOException {
        int isChecked = 0;
        FileInputStream fis = null;
        try {
            fis = getContext().openFileInput("myFile.dat");
        } catch (FileNotFoundException e) {
            isChecked=0;
            return isChecked;
        }
        DataInputStream dis = new DataInputStream(fis);
        String data2 = dis.readUTF();
        if (data2.equals(passwd)) {
            isChecked = 1;
        } else {
            isChecked = 2;
        }
        dis.close();
        return isChecked;
    }

    public void savePassword(String passwd) throws FileNotFoundException{
        try {
            FileOutputStream fos = getContext().openFileOutput("myFile.dat", MODE_PRIVATE);
            DataOutputStream dos = new DataOutputStream(fos);
            dos.writeUTF(passwd);
            dos.flush();
            dos.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        catch (IOException e) {
            FileOutputStream fos = getContext().openFileOutput("myFile.dat", Context.MODE_APPEND);
        }

    }
}



