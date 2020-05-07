package com.example.myapplication;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class MainActivity extends AppCompatActivity {
    public static Map<Set<Character>, Set<Character>> fds = new HashMap<>();
    Gson gson = new Gson();
    public static final String EXTRA_MESSAGE = "com.example.ModuleOne.MESSAGE";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    public void onNext(View view){
        EditText editText= findViewById(R.id.pt_fds);
        String[] string =(editText.getText().toString()).split("->");

        Set<Character> lhs = string[0].chars().mapToObj(c -> (char) c).collect(Collectors.toSet());
        Set<Character> rhs = string[1].chars().mapToObj(c -> (char) c).collect(Collectors.toSet());

        if(fds.containsKey(lhs)) fds.get(lhs).addAll(rhs);
        else fds.put(lhs, rhs);

        editText.setText("");
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    public void onFinish(View view){
        boolean changed = false;

        fds.forEach((k, v) -> v.addAll(k));

        List<Boolean> changes = new ArrayList<>();

        while(true) {
            changes.clear();
            fds.forEach((k, v) ->
                    fds.forEach((k1, v1) -> {
                        if(k.containsAll(k1))
                            if (v.addAll(v1)) changes.add(true);
                    })
            );
            if(changes.isEmpty()) break;
        }

        String fdString = gson.toJson(fds);
        Intent intent = new Intent(this, PreNormalisationActivity.class);
        intent.putExtra(EXTRA_MESSAGE, fdString);
        startActivity(intent);
    }
}
