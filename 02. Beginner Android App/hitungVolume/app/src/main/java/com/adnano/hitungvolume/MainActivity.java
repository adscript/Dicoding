package com.adnano.hitungvolume;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText edtWidth, edtHeight, edtLength;
    private Button btnCalculate;
    private TextView tvResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        edtWidth = findViewById(R.id.edt_width);
        edtHeight = findViewById(R.id.edt_height);
        edtLength = findViewById(R.id.edt_length);
        btnCalculate = findViewById(R.id.btn_calculate);
        tvResult = findViewById(R.id.tv_result);

        btnCalculate.setOnClickListener(this);
        if(savedInstanceState != null){
            String result = savedInstanceState.getString(STATE_RESULT);
            tvResult.setText(result); 
        }
    }

    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.btn_calculate ){
            String inputLength = edtLength.getText().toString().trim();
            String inputWidth = edtWidth.getText().toString().trim();
            String inputHeight = edtHeight.getText().toString().trim();

            boolean isEmptyFields = false;
            boolean isInvalidDouble = false;

            if(TextUtils.isEmpty(inputHeight)){
                isEmptyFields = true;
                edtHeight.setError("Field Tidak Boleh Kosong");
            }
            if(TextUtils.isEmpty(inputLength)){
                isEmptyFields = true;
                edtLength.setError("Field Tidak Boleh Kosong");
            }
            if(TextUtils.isEmpty(inputWidth)){
                isEmptyFields = true;
                edtWidth.setError("Field Tidak Boleh Kosong");
            }

            Double length = toDouble(inputLength);
            Double height = toDouble(inputHeight);
            Double width = toDouble(inputWidth);
            if (length == null) {
                isInvalidDouble = true;
                edtLength.setError("Field format salah");
            }
            if(height == null){
                isInvalidDouble = true;
                edtHeight.setError("Field format salah");
            }
            if(width == null){
                isInvalidDouble = true;
                edtWidth.setError("Field Format salah");
            }

            if(!isEmptyFields && !isInvalidDouble){
                double vol = length * height * width;

                tvResult.setText(String.valueOf(vol));
            }


        }
    }

    private Double toDouble(String str) {
        try {
            return Double.valueOf(str);
        }catch(NumberFormatException e){
            return null;
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState){
        super.onSaveInstanceState(outState);
        outState.putString(STATE_RESULT, tvResult.getText().toString());
    }
}
