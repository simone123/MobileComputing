package com.mlabs.bbm.firstandroidapp_morningclass;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class register extends AppCompatActivity {
    public SQLiteDatabase myDB;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        try {
            myDB = this.openOrCreateDatabase("Register", MODE_PRIVATE, null);
            myDB.execSQL("CREATE TABLE IF NOT EXISTS Register (Username VARCHAR, Email VARCHAR, Password VARCHAR,Firstname VARCHAR, Lastname VARCHAR);");
            //Toast.makeText(getBaseContext(), "Database Created", Toast.LENGTH_SHORT).show();

        }finally {

        }
    }
    public void sai(View v) {
        final EditText eText = (EditText) findViewById(R.id.email);
        final EditText username = (EditText) findViewById(R.id.username);
        final EditText pText = (EditText) findViewById(R.id.pass);
        final EditText cpText = (EditText) findViewById(R.id.cpass);
        final EditText firstname = (EditText) findViewById(R.id.fname);
        final EditText lastname = (EditText) findViewById(R.id.lname);
        String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
        String emailPattern2 = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+.+[a-z]+";
        String passPattern = "([a-zA-Z0-9]+_?)+";
        switch (v.getId()) {
            case R.id.button:
                String email = eText.getText().toString();
                String user = username.getText().toString();
                String pass= pText.getText().toString();
                String cpass= cpText.getText().toString();
                String fname= firstname.getText().toString();
                String lname= lastname.getText().toString();
                if(email.length()>1 && user.length()>1 && pass.length()>1 && cpass.length()>1&& fname.length()>1&& lname.length()>1){
                    if(pass.equals(cpass)){
                        if(email.trim().matches(emailPattern) || email.trim().matches(emailPattern2)){
                            if(pass.length()>=8 && cpass.length()>=8){
                                if(user.length()>6){
                                    Cursor cursor =myDB.query("Register",null,"Username=?",new String[]{user},null,null,null);
                                    if(cursor.getCount()<1){
                                        cursor.close();
                                        Cursor cursor1 =myDB.query("Register",null,"Email=?",new String[]{email},null,null,null);
                                        if(cursor1.getCount()<1){
                                            cursor1.close();
                                            ContentValues newValues = new ContentValues();
                                            newValues.put("Username",user);
                                            newValues.put("Email",email);
                                            newValues.put("Password",pass);
                                            newValues.put("Firstname",fname);
                                            newValues.put("Lastname",lname);
                                            myDB.insert("Register",null,newValues);
                                            myDB.close();
                                            Toast.makeText(getBaseContext(), "Register Successful!", Toast.LENGTH_SHORT).show();
                                            Intent intent = new Intent(register.this,MainActivity.class );
                                            startActivity(intent);
                                        }else Toast.makeText(getBaseContext(), "Email is already exist", Toast.LENGTH_SHORT).show();
                                    }else Toast.makeText(getBaseContext(), "Username is already exist", Toast.LENGTH_SHORT).show();

                                }else Toast.makeText(getBaseContext(), "Username is too short", Toast.LENGTH_SHORT).show();
                            }else Toast.makeText(getBaseContext(), "Password is too short", Toast.LENGTH_SHORT).show();
                        }else Toast.makeText(getBaseContext(), "Invalid Email", Toast.LENGTH_SHORT).show();
                    }else Toast.makeText(getBaseContext(), "Password doesnt match", Toast.LENGTH_SHORT).show();
                }else Toast.makeText(getBaseContext(), "All Fields should be Filled", Toast.LENGTH_SHORT).show();
                break;

        }
    }
}