package com.mlabs.bbm.firstandroidapp_morningclass;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.PasswordTransformationMethod;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.StringTokenizer;
import java.util.regex.Pattern;

public class MainActivity extends AppCompatActivity {
    int x = 0;
    public SQLiteDatabase myDB;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        try {
            myDB = this.openOrCreateDatabase("Register", MODE_PRIVATE, null);
            myDB.execSQL("CREATE TABLE IF NOT EXISTS Register (Username VARCHAR, Email VARCHAR, Password VARCHAR,Firstname VARCHAR, Lastname VARCHAR);");
            //Toast.makeText(getBaseContext(), "Database Created", Toast.LENGTH_SHORT).show();

        }finally {

        }

    }
    public void sai(View v) {
        final EditText eText=(EditText)findViewById(R.id.editText);
        final EditText pText=(EditText)findViewById(R.id.editText2);
        Button show = (Button) findViewById(R.id.button3);
        String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
        String passPattern = "([a-zA-Z0-9]+_?)+";
        switch (v.getId()) {
            case R.id.button:

                String email = eText.getText().toString();
                String passw = pText.getText().toString();

                if (pText.getText().length() >= 1 && eText.getText().length() >= 1) {
                    if (pText.getText().toString().trim().matches(passPattern)) {
                        if (pText.getText().length() >= 8) {
                            Cursor cursor1 = myDB.query("Register", null, "Username=?", new String[]{email}, null, null, null);
                            Cursor cursor2 = myDB.query("Register", null, "Email=?", new String[]{email}, null, null, null);
                            if (cursor1.getCount() > 0) {
                                cursor1.moveToFirst();
                                String password = cursor1.getString(cursor1.getColumnIndex("Password"));
                                if (passw.equals(password)) {
                                    Intent intent = new Intent(MainActivity.this, generate.class);
                                    startActivity(intent);
                                } else
                                    Toast.makeText(getBaseContext(), "Wrong Password!", Toast.LENGTH_SHORT).show();
                            } else if (cursor2.getCount() > 0) {
                                cursor2.moveToFirst();
                                String passwords = cursor2.getString(cursor2.getColumnIndex("Password"));
                                if (passw.equals(passwords)) {
                                    Intent intent = new Intent(MainActivity.this, generate.class);
                                    startActivity(intent);
                                } else
                                    Toast.makeText(getBaseContext(), "Wrong Password!", Toast.LENGTH_SHORT).show();
                            } else
                                Toast.makeText(getBaseContext(), "Wrong Username", Toast.LENGTH_SHORT).show();
                        } else
                            Toast.makeText(getBaseContext(), "Password is too short!", Toast.LENGTH_SHORT).show();
                    } else
                        Toast.makeText(getBaseContext(), "Invalid Password!", Toast.LENGTH_SHORT).show();
                } else
                    Toast.makeText(getBaseContext(), "Email or Password field must not be empty!", Toast.LENGTH_SHORT).show();

                break;
            case R.id.button2:
                Intent intent = new Intent(MainActivity.this, register.class);
                startActivity(intent);
                break;

        }
        show.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent e) {
                switch (e.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        pText.setTransformationMethod(null);
                        return true;
                    case MotionEvent.ACTION_CANCEL:
                    case MotionEvent.ACTION_UP:
                        pText.setTransformationMethod(PasswordTransformationMethod.getInstance());
                        return true;
                }
                return false;
            }

        });
    }
    public int Login(String username,String password)
    {
        String[] selectionArgs = new String[]{username, password};
        try
        {
            int i = 0;
            Cursor c = null;
            c = myDB.rawQuery("select * from Register where Username=? and Password=?", selectionArgs);
            c.moveToFirst();
            i = c.getCount();
            c.close();
            return i;
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        return 0;
    }

}