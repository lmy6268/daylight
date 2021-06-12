package com.example.daylight;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class MainActivity extends AppCompatActivity {
    diaryFragment diaryfragment;
    passwordFragment passwordfragment;
    checkActFragment checkActfragment;
    mainFragment mainfragment;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        diaryfragment =new diaryFragment();
        passwordfragment = new passwordFragment();
        checkActfragment = new checkActFragment();
        mainfragment= new mainFragment();
        changeFragemnt(0);
    }

    public void changeFragemnt(int index) {
        switch (index) {
            case 0: // 비밀번호 화면
                getSupportFragmentManager().beginTransaction().replace(R.id.container,passwordfragment).commit();
                break;
            case 1://메인화면
                getSupportFragmentManager().beginTransaction().replace(R.id.container,mainfragment).commit();
                break;
            case 2: // 오늘의 기분 선택화면
                getSupportFragmentManager().beginTransaction().replace(R.id.container,checkActfragment).addToBackStack(null).commit();
                break;
            case 3://일기 작성 화면
                getSupportFragmentManager().beginTransaction().replace(R.id.container,diaryfragment).addToBackStack(null).commit();
                break;
        }
    }
}