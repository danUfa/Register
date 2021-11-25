package com.example.registr;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

    public class SingUp extends AppCompatActivity {

    public EditText Surname;
    public EditText Mail;
    public EditText Name;
    public EditText Middle_name;
    public EditText Password;
    public EditText RePassword;
    Button RegistrButton;
    DBHelper DB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sing_up);
        Surname = findViewById(R.id.surname);
        RegistrButton = findViewById(R.id.LogInInReg);
        Name = findViewById(R.id.name);
        Mail = findViewById(R.id.mail);
        Password = findViewById(R.id.password);
        RePassword = findViewById(R.id.rePass);
        Middle_name = findViewById(R.id.middle_name);
        DB = new DBHelper(this);
    }

    public void onButtonClick(View view) {
        String mail = Mail.getText().toString();
        String password = Password.getText().toString();
        String name = Name.getText().toString();
        String surname = Surname.getText().toString();
        String middle_name = Middle_name.getText().toString();
        if ((Name.getText().length() == 0) || (Surname.getText().length() == 0)
                || (Middle_name.getText().length() == 0) || (Password.getText().length() == 0)
                || (RePassword.getText().length() == 0))
            Toast.makeText(getApplicationContext(), "Все поля должны быть заполнены!", Toast.LENGTH_SHORT).show();
        else if(Password.getText() != RePassword.getText()) {
            Toast.makeText(getApplicationContext(), "Пароли должны совпадать", Toast.LENGTH_SHORT).show();
        }
        if(!mail.contains("@")) {
                Toast.makeText(getApplicationContext(), "Допишите почту, например : example@mail.ru", Toast.LENGTH_SHORT).show();
            }
              else {
            Boolean checklogin = DB.checkLogin(mail);
            if (checklogin == false) {
                Boolean insert = DB.insertData(mail, password, name, surname, middle_name);
                if (insert == true) {
                    Toast.makeText(getApplicationContext(), "Регистрация выполнена", Toast.LENGTH_SHORT).show();
                    this.finish();
                } else {
                    Toast.makeText(getApplicationContext(), "Не удалось зарегистрировать", Toast.LENGTH_SHORT).show();
                }
            } else {
                Toast.makeText(getApplicationContext(), "Пользователь уже существует", Toast.LENGTH_SHORT).show();
            }
        }
    }
}