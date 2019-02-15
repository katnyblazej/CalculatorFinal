package com.example.katny.calculatorfinal;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Intent;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import org.mariuszgromada.math.mxparser.*;

public class MainActivity extends AppCompatActivity {
    TextView result;
    EditText editText;

    DatabaseHelper myDb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        result = findViewById(R.id.text);
        editText =  findViewById(R.id.editText);
        myDb = new DatabaseHelper(this);
    }

    public void sendMessage(View view) {
        Intent intent = new Intent(this, History.class);
        startActivity(intent);
    }


    public void doMath (View view) {
        Expression math = new Expression(editText.getText().toString());
        result.setText(String.valueOf(math.calculate()));
        if(result.getText()!="NaN"){
            if(myDb.insertData(editText.getText().toString())){
                Toast.makeText(MainActivity.this, "Saved", Toast.LENGTH_LONG).show();
            }
            else
                Toast.makeText(MainActivity.this, "Error", Toast.LENGTH_LONG).show();
        }
        else
            Toast.makeText(MainActivity.this, "Wrong input!", Toast.LENGTH_LONG).show();

    }

    public void exit(){

    }
}
