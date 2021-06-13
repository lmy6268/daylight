package com.example.daylight;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.DrawableContainer;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.LayerDrawable;
import android.graphics.drawable.StateListDrawable;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;

import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;


import static android.content.Context.MODE_PRIVATE;

public class customDialog extends Dialog {
    private Context context;
    DisplayMetrics disp = getContext().getResources().getDisplayMetrics();
    int deviceWidth = disp.widthPixels;
    int deviceHeight = disp.heightPixels;

    public customDialog(@NonNull Context context) {
        super(context);
        this.context = context;
    }

    //이어인픽셀을 통해 일기입력 추가 받기
    public void showDiarylog() {

        Button btnSave, btnImage, btnBgm, btnEmo;
        EditText editDiary;
        Calendar cal = Calendar.getInstance();
        String format = "yyyy-MM-dd";
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        String date = sdf.format(cal.getTime());//날짜 입력, 파일명 지정

        final View innerView = getLayoutInflater().inflate(R.layout.dialog_diary, null);
        customDialog octDialog = new customDialog(getContext());
        btnSave = innerView.findViewById(R.id.btnSave);
        btnImage = innerView.findViewById(R.id.btnImage);
        btnBgm = innerView.findViewById(R.id.btnBgm);
        btnEmo = innerView.findViewById(R.id.btnEmo);
        editDiary = innerView.findViewById(R.id.editDiary);
        octDialog.setContentView(innerView);
        octDialog.setCanceledOnTouchOutside(false);// 다이알로그 바깥영역 터치시, 다이알로그 닫힘
        octDialog.setCancelable(true);//Back키로 다이어로그 닫기
        WindowManager.LayoutParams params = octDialog.getWindow().getAttributes();
        params.width = (int) (deviceWidth * 0.8);
        params.height = (int) (deviceHeight * 0.6);
        octDialog.getWindow().setAttributes((WindowManager.LayoutParams) params);
        try {
            editDiary.setText(loadDiary(date));
        } catch (FileNotFoundException e) {
        } catch (IOException e) {
        } finally {
            octDialog.show();
        }
        btnSave.setOnClickListener(v -> {
            try {
                saveDiary(editDiary.getText().toString(), date);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        btnEmo.setOnClickListener(v -> {
            showEmolog(date,btnEmo);
        });

    }

    public void saveDiary(String text, String Date) throws IOException {
        FileOutputStream fos = null;
        try {
            fos = getContext().openFileOutput(Date + ".txt", MODE_PRIVATE);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        DataOutputStream dos = new DataOutputStream(fos);
        dos.writeUTF(text);
        dos.flush();
        dos.close();
        Toast.makeText(getContext(), "저장되었습니다", Toast.LENGTH_SHORT).show();
    }

    public String loadDiary(String Date) throws IOException {
        String result;
        FileInputStream fis = null;
        fis = getContext().openFileInput(Date + ".txt");
        DataInputStream dis = new DataInputStream(fis);
        result = dis.readUTF();
        return result;
    }

    public void  showEmolog(String Date,Button parentEmo)//감정 선택 다이어로그 창 띄움
    {
        final View innerView = getLayoutInflater().inflate(R.layout.emolog, null);
        Button[] btnEmo = {innerView.findViewById(R.id.btnEmo1), innerView.findViewById(R.id.btnEmo2), innerView.findViewById(R.id.btnEmo3), innerView.findViewById(R.id.btnEmo4),
                innerView.findViewById(R.id.btnEmo5), innerView.findViewById(R.id.btnEmo6)};
        String[] color={"#00FF00","#FF0000","#0000FF","#FFA500","#FFC0CB","#FFFF00"};
        for (int i = 0; i < btnEmo.length; i++) {
            final int index = i;

            btnEmo[index].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    try {
                        Log.d("color",color[index]);
                        parentEmo.setBackgroundColor(Color.parseColor(color[index]));
                        saveMood(index,btnEmo[index].getTag().toString(),color[index],Date);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            });

        }
        customDialog octDialog = new customDialog(getContext());
        octDialog.setContentView(innerView);
        octDialog.setCanceledOnTouchOutside(false);// 다이알로그 바깥영역 터치시, 다이알로그 닫힘
        octDialog.setCancelable(true);//Back키로 다이어로그 닫기
        WindowManager.LayoutParams params = octDialog.getWindow().getAttributes();
        params.width = (int) (deviceWidth * 0.8);
        params.height = (int) (deviceHeight * 0.6);
        octDialog.show();
    }

    public void saveMood(int index,String mood,String color,String Date) throws IOException {
        FileOutputStream fos = null;
        try {
            fos = getContext().openFileOutput(Date + "Mood.txt", MODE_PRIVATE);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        DataOutputStream dos = new DataOutputStream(fos);
        String text= index +"/"+mood +" / " +color ;
        try {
            dos.writeUTF(text);
        } catch (IOException e) {
            e.printStackTrace();
        }
        dos.flush();
        dos.close();
        Toast.makeText(getContext(), "저장되었습니다", Toast.LENGTH_SHORT).show();

    }
    public void loadMood(String Date,Button[] btnEmo) throws IOException {
        String result;
        FileInputStream fis = null;
        fis = getContext().openFileInput(Date + "Mood.txt");
        DataInputStream dis = new DataInputStream(fis);
        result = dis.readUTF();
    }
}