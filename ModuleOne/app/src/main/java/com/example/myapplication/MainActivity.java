package com.example.myapplication;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

//import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    public static HashMap<String, String> h = new HashMap<>();
    public static String rls = new String();
//    ListView lv = findViewById(R.id.fdListview);
//    List<String> arrayList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    public void onNext(View view){
        String lfd = ((EditText) findViewById(R.id.pt_Lfd)).getText().toString();
        String rfd = ((EditText) findViewById(R.id.pt_Rfd)).getText().toString();

        if(h.containsKey(lfd)) h.put(lfd,rfd.concat(h.get(lfd)));
        else h.put(lfd,rfd);

//        arrayList.add(editText.getText().toString());
//        lv.setAdapter(new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, arrayList));
        ((EditText) findViewById(R.id.pt_Lfd)).setText("");
        ((EditText) findViewById(R.id.pt_Rfd)).setText("");
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    public void onFinish(View view){
        EditText editText= findViewById(R.id.pt_R);
        rls=editText.getText().toString();
        Intent intent = new Intent(this, PreNormalisationActivity.class);
        startActivity(intent);
    }

    public void onClear(View view){
//        arrayList.clear();
        h.clear();
    }

}
