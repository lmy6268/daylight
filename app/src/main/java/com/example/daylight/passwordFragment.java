package com.example.daylight;


import android.content.Context;
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
//import java.io.UnsupportedEncodingException;
//import java.security.Key;
//import java.security.InvalidAlgorithmParameterException;
//import java.security.InvalidKeyException;
//import java.security.NoSuchAlgorithmException;
//import javax.crypto.BadPaddingException;
//import javax.crypto.Cipher;
//import javax.crypto.IllegalBlockSizeException;
//import javax.crypto.NoSuchPaddingException;
//import javax.crypto.spec.IvParameterSpec;
//import javax.crypto.spec.SecretKeySpec;
//import java.util.Base64; import java.util.Base64.Decoder; import java.util.Base64.Encoder;


public class passwordFragment extends Fragment {
    EditText pwd1, pwd2, pwd3, pwd4;
    private String pwd;
    Button btnAccess, btnRegister;
    int checked;
    BottomNavigationView bottomNavi;
//    private String privateKey;
//    private UserDatabaseHelper userDatabaseHelper;
//    public static final String TABLE_NAME = "user";
//    SQLiteDatabase database;

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
//암호 시스템
//    public String savePassword(String passwd)
//            throws NoSuchPaddingException, InvalidAlgorithmParameterException, UnsupportedEncodingException, IllegalBlockSizeException, BadPaddingException, NoSuchAlgorithmException, InvalidKeyException {
//    {
//        String encKey;
//        AES256Util aes = new AES256Util(privateKey);
//        encKey = aes.aesEncode(passwd);
//        return encKey;
//    }
//}
//class AES256Util {
//    private String iv;
//    private Key keySpec;
//
//    public AES256Util(String key) throws UnsupportedEncodingException {
//        this.iv = key.substring(0, 16);
//
//        byte[] keyBytes = new byte[16];
//        byte[] b = key.getBytes("UTF-8");
//        int len = b.length;
//        if (len > keyBytes.length) {
//            len = keyBytes.length;
//        }
//        System.arraycopy(b, 0, keyBytes, 0, len);
//        SecretKeySpec keySpec = new SecretKeySpec(keyBytes, "AES");
//
//        this.keySpec = keySpec;
//    }
//
//
//    // 암호화
//    public String aesEncode(String str) throws java.io.UnsupportedEncodingException,
//            NoSuchAlgorithmException,
//            NoSuchPaddingException,
//            InvalidKeyException,
//            InvalidAlgorithmParameterException,
//            IllegalBlockSizeException,
//            BadPaddingException {
//        Cipher c = Cipher.getInstance("AES/CBC/PKCS5Padding");
//        c.init(Cipher.ENCRYPT_MODE, keySpec, new IvParameterSpec(iv.getBytes()));
//
//        byte[] encrypted = c.doFinal(str.getBytes("UTF-8"));
//        Encoder encoder = Base64.getEncoder();
//        byte[] encodedBytes = encoder.encode(encrypted);
//        String enStr = new String(encodedBytes);
//
//        return enStr;
//    }
//
//    //복호화
//    public String aesDecode(String str) throws java.io.UnsupportedEncodingException,
//            NoSuchAlgorithmException,
//            NoSuchPaddingException,
//            InvalidKeyException,
//            InvalidAlgorithmParameterException,
//            IllegalBlockSizeException,
//            BadPaddingException {
//        Cipher c = Cipher.getInstance("AES/CBC/PKCS5Padding");
//        c.init(Cipher.DECRYPT_MODE, keySpec, new IvParameterSpec(iv.getBytes("UTF-8")));
//        Decoder decoder = Base64.getDecoder();
//        byte[] byteStr = decoder.decode(str.getBytes());
//
//        return new String(c.doFinal(byteStr),"UTF-8");
//    }



