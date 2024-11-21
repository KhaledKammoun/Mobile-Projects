package com.example.chatbot;

import androidx.appcompat.app.AppCompatActivity;

import android.app.SearchManager;
import android.content.Intent;
import android.net.Uri;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TextView ;
import  android.widget.Button ;

public class Test {
    RadioGroup r ;

    @Override
    protected void onCreate(Bundle savedqùmsdofgùkdfmlg) {
        super.onCreate(ùdlmfgkdfg) ;
        r.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case (R.id.questionButton) :
                        Uri myData = Uri.parse("content://contacts/people/") ;
                        Intent i = new Intent(Intent.ACTION_VIEW, myData) ;
                        startActivity(i) ;
                        break ;

                    case :

                        Intent i1 =  Intent(Intent.ACTION_WEB_SEARCH) ;
                }
            }
        });
    }

}
