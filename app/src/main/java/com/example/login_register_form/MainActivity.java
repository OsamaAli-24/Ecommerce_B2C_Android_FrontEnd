package com.example.login_register_form;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    //creating variables
    EditText username, password, repassword;
    Button signup, signin;
    DBHelper DB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //this will place the value in the in the variables
        username = (EditText) findViewById(R.id.username);
        password =  (EditText) findViewById(R.id.password);
        signin = (Button) findViewById(R.id.btnsign);
        signup = (Button) findViewById(R.id.btnsignup);
        DB = new DBHelper(this);


        //listerner for the singin button
        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String user = username.getText().toString();
                String pass = password.getText().toString();
                String repass = repassword.getText().toString();

                if(user.equals("")|| pass.equals("") || repass.equals(""))
                {
                    Toast.makeText(MainActivity.this, "Please Enter all of the Information", Toast.LENGTH_LONG).show();
                }
                else
                {
                    if(pass.equals(repass))
                    {
                        Boolean checkusername = DB .checkusername(user);
                        if(checkusername==false)
                        {
                            Boolean insert = DB.insertData(user,pass);
                            if(insert==true)
                            {
                                Toast.makeText(MainActivity.this, "Registered Successfully", Toast.LENGTH_LONG).show();
                                Intent intent = new Intent(getApplicationContext(),HomeActivity.class);
                                startActivity(intent);
                            }
                            else
                            {
                                Toast.makeText(MainActivity.this, "Registered Failed", Toast.LENGTH_LONG).show();
                            }
                        }
                        else
                        {
                            Toast.makeText(MainActivity.this, "User Already Exist Please signin", Toast.LENGTH_LONG).show();
                        }

                    }
                    else
                    {
                        Toast.makeText(MainActivity.this, "Password are not Matching", Toast.LENGTH_LONG).show();
                    }
                }

            }
        });

        //listener for the sign in button
        signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(getApplicationContext(),LoginActivity.class);
                startActivity(intent);
            }
        });




    }
}