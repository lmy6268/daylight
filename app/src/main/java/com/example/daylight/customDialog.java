package com.example.daylight;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.util.DisplayMetrics;
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
            showEmolog();
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
    public void showEmolog()//감정 선택 다이어로그 창 띄움
    {
        final View innerView = getLayoutInflater().inflate(R.layout.emolog, null);
        Button[] btnEmo;

        customDialog octDialog = new customDialog(getContext());
        octDialog.setContentView(innerView);
        octDialog.setCanceledOnTouchOutside(false);// 다이알로그 바깥영역 터치시, 다이알로그 닫힘
        octDialog.setCancelable(true);//Back키로 다이어로그 닫기
        WindowManager.LayoutParams params = octDialog.getWindow().getAttributes();
        params.width = (int) (deviceWidth * 0.8);
        params.height = (int) (deviceHeight * 0.6);
    }
}
