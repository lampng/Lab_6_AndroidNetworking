package com.example.lab6network;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    EditText edtFC;
    Button btnF, btnC;
    String strFC;
    TextView tvResult;
    int convertStyle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        edtFC = findViewById(R.id.edtF_C);
        btnF = findViewById(R.id.btnF);
        btnC = findViewById(R.id.btnC);
        tvResult = findViewById(R.id.tvResult);
        btnF.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                invokeAsyncTask("Fahrenheit", Constants.F_TO_C_SOAP_ACTION, Constants.F_TO_C_METHOD_NAME);
                convertStyle = 1;
            }
        });
        btnC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                invokeAsyncTask("Celsius", Constants.C_TO_F_SOAP_ACTION, Constants.C_TO_F_METHOD_NAME);
                convertStyle = 0;
            }
        });
    }

    private void invokeAsyncTask(String convertParams, String soapAction, String methodName) {
        new ConvertTemperatureTask(this, soapAction, methodName, convertParams).execute(edtFC.getText().toString().trim());
    }

    public void callBackDataFromAsyncTask(String s) {
        double resultTemperature = Double.parseDouble(s); //parse String to double
        if (convertStyle == 0) {// C degree to F degree
            tvResult.setText(edtFC.getText().toString().trim() + " degree Celsius = " + String.format("%.2f", resultTemperature) + " degree Fahrenheit");
        } else {// F degree to C degree
            tvResult.setText(edtFC.getText().toString().trim() + " degree Fahrenheit = " + String.format("%.2f", resultTemperature) + " degree Celsius");
        }
    }


}