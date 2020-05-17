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

import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class MainActivity extends AppCompatActivity {
    public static HashMap<String, String> h = new HashMap<>();
    public static String rls = "";
    public static List<String> list = new ArrayList<>();
    public Set<Character> rFromFds= new HashSet<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    public void onAdd(View view){
        String lfd = ((EditText) findViewById(R.id.pt_Lfd)).getText().toString();
        String rfd = ((EditText) findViewById(R.id.pt_Rfd)).getText().toString();
        rFromFds.addAll((lfd+rfd).chars().mapToObj(c -> (char) c).collect(Collectors.toSet()));
        if(StringUtils.isBlank(lfd) || StringUtils.isBlank(rfd)) {
            ErrorMessageMain errorMessageMain = new ErrorMessageMain();
            errorMessageMain.show(getSupportFragmentManager(),"Error message");
        }
        else{

            if (h.containsKey(lfd)) h.put(lfd, rfd.concat(h.get(lfd)));
            else h.put(lfd, rfd);

            ListView listView = findViewById(R.id.fdlv);
            list.add(lfd + "->" + rfd);

            listView.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, list));
            ((EditText) findViewById(R.id.pt_Lfd)).setText("");
            ((EditText) findViewById(R.id.pt_Rfd)).setText("");
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    public void onFinish(View view){

        EditText editText = findViewById(R.id.pt_R);
        rls = editText.getText().toString();

        Set<Character> enteredR= rls.chars().mapToObj(c-> (char) c).collect(Collectors.toSet());
        if(enteredR.size()!=rls.length()||!enteredR.containsAll(rFromFds)||(rFromFds.isEmpty() && rls.equals(""))){
            ErrorMessageMain errorMessageMain = new ErrorMessageMain();
            errorMessageMain.show(getSupportFragmentManager(),"Error message");
        }
        else {
            Intent intent = new Intent(this, PreNormalisationActivity.class);
            startActivity(intent);
        }
    }

    public void onClear(View view){
//        arrayList.clear();
        h.clear();
        list.clear();
        rFromFds.clear();
        ListView listView = findViewById(R.id.fdlv);
        listView.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, list));
    }

}
