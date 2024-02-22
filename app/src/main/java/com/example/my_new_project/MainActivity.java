package com.example.my_new_project;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    EditText N1;
    EditText N2;
    TextView addResult;
    Button btnAdd, btnSous;
    double num1,num2,sum;
    String sum_string ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toast.makeText(MainActivity.this, "Welcome to Calcul App")

        N1=(EditText)findViewById(R.id.editText1);
        N2=(EditText)findViewById(R.id.editText2);
        addResult=(TextView) findViewById(R.id.textView);
        btnAdd=(Button) findViewById(R.id.buttonAdd);
        btnSous=(Button) findViewById(R.id.buttonSous);
        btnAdd.setOnClickListener((new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                num1=Double.parseDouble(N1.getText().toString());
                num2=Double.parseDouble(N2.getText().toString());
                sum= num1 + num2;
                sum_string = Double.toString(sum) ;
                addResult.setText(sum_string);
            }
        }));

        btnSous.setOnClickListener((new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                num1=Double.parseDouble(N1.getText().toString());
                num2=Double.parseDouble(N2.getText().toString());
                sum= num1 - num2;
                sum_string = Double.toString(sum) ;
                addResult.setText(sum_string);
            }
        }));

    }


}