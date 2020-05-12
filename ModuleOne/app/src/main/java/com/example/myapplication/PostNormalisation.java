package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class PostNormalisation extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post_normalisation);

        TextView textView = findViewById(R.id.tv_decompostion);
        textView.setText(PreNormalisationActivity.hNFPlusOne);
        ListView lv = findViewById(R.id.listview_rlns);
        List<String> arrayList = new ArrayList<>(PreNormalisationActivity.decomposedRlns);
        lv.setAdapter(new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, arrayList));

    }


}
