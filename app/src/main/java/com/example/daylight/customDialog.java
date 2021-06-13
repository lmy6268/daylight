package com.example.daylight;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;

public class customDialog extends Dialog {
    private Context context;
    private int layoutId;
    public customDialog(@NonNull Context context,int id) {
        super(context);
        layoutId=id;
        this.context = context;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(layoutId);
    }
}