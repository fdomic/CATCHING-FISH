package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {


    EditText mIme;
    EditText mPass;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


         mIme =      findViewById(R.id.Ime);
         mPass =      findViewById(R.id.pass);



    }

    public void otvori(View view)  {

        if( mIme != null  && mPass != null ){
            Intent intent = new Intent(this, MapsActivity.class);
            startActivity(intent);
        }

    }

}

