package com.example.daylight;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import android.os.Bundle;
import android.view.MenuItem;
import com.google.android.material.bottomnavigation.BottomNavigationView;
public class MainActivity extends AppCompatActivity {


    private FragmentManager fm;
    private FragmentTransaction ft;
    private passwordFragment passwordfragment;

    private mainFragment mainfragment;
    private settingFragment settingFragment;
    private MainActivity MainActivity;
    private BottomNavigationView bottomNavigationView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mainfragment= new mainFragment();
        passwordfragment = new passwordFragment();

        settingFragment= new settingFragment();

        bottomNavigationView = findViewById(R.id.bottomNavi);
        bottomNavigationView.setOnNavigationItemSelectedListener(item -> {
            switch (item.getItemId()) {
                case R.id.active_home:
                    changeFragemnt(0);
                    break;

                case R.id.active_achievements:
                    changeFragemnt(1);
                    break;
                case R.id.active_setup:
                    changeFragemnt(2);
                    break;
            }
            return true;
        });

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
            case 2://설정 화면
                getSupportFragmentManager().beginTransaction().replace(R.id.container,settingFragment).commit();
                break;
        }
    }
}