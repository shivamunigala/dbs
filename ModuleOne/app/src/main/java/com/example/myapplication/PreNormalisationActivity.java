package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.Map;
import java.util.Set;

public class PreNormalisationActivity extends AppCompatActivity {

    Gson gson = new Gson();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pre_normalisation);
        TextView textView= findViewById(R.id.tv_closure);
        Intent intent = getIntent();
        String fdString = intent.getStringExtra(MainActivity.EXTRA_MESSAGE);

       try {
           Type type = new TypeToken<Map<Set<Character>, Set<Character>>>(){}.getType();
           Map<Set<Character>, Set<Character>> fds = MainActivity.fds;//gson.fromJson(fdString, type);
           textView.setText(fds.toString());
       }catch (Exception e){
           textView.setText(fdString+"------------"+e.toString());
       }
    }
}
