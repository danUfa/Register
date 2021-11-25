package com.example.registr;

import androidx.appcompat.app.AppCompatActivity;


import android.app.AlertDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    EditText mail, password;
    Button signIn, signUp;
    DBHelper DB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mail = (EditText) findViewById(R.id.mail);
        password = (EditText) findViewById(R.id.pwd);
        signIn = (Button) findViewById(R.id.signIn);
        signUp = (Button) findViewById(R.id.singUp);
        DB = new DBHelper(this);
        //AlertDialog.Builder alert = new AlertDialog.Builder(this);

    }


    public void onButtonClick(View view){
        String login = mail.getText().toString();
        String pass = password.getText().toString();

        if (mail.getText().length() == 0 || password.getText().length() == 0) {
            String toastMessage = "Поля не должны быть пустыми!";
            ShowDialog(toastMessage);
        }
        else {
            Boolean checklogpass = DB.checkUserPassword(login, pass);
            if(checklogpass == true){
                Toast.makeText(getApplicationContext(), "Успешный вход", Toast.LENGTH_SHORT).show();
                Intent menu = new Intent(this, Menu.class);
                startActivity(menu);
            }else {
               String toastMessage = "Неверный логин или пароль";
                ShowDialog(toastMessage);
            }
        }
    }

    public void ShowDialog(String messege) {
        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
        builder.setTitle("Ошибка")
                .setMessage(messege)
                .setPositiveButton("OK", (dialog, which) -> {
                    dialog.cancel();
                });
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }
}
