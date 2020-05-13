package com.example.myapplication;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.ScrollView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.apache.commons.lang3.ArrayUtils;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class PreNormalisationActivity extends AppCompatActivity {

    HashMap<String, String> h=MainActivity.h;
    String rls=MainActivity.rls;
    public static Set<String> decomposedRlns = new HashSet<>();
    public static String hNFPlusOne = "";

    @SuppressLint("SetTextI18n")
    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pre_normalisation);
//        TextView textView = findViewById(R.id.tv_closure);
        TextView nf = findViewById(R.id.tv_HNFResult);
        Intent intent = getIntent();

        ListView lv = findViewById(R.id.lv);

        CandidateKey candidateKey = new CandidateKey();
        Set<String> ckSet = candidateKey.findCandidateKey(h,rls);

        List<String> arrayList = new ArrayList<>(ckSet);
        lv.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, arrayList));


        Test_2NF test_2NF = new Test_2NF();
        Test_3NF test_3NF = new Test_3NF();
        Test_BCNF test_bcnf = new Test_BCNF();

        Boolean twoNf = test_2NF.test2nf(h,rls,ckSet);
        Boolean threeNf = test_3NF.test3nf(h,rls,ckSet);
        Boolean bcNf = test_bcnf.testbcnf(h,rls,ckSet);



        if(twoNf && threeNf && bcNf){
            nf.setText("BCNF");
//            decomposedRlns.add(rls);
            hNFPlusOne = "No Need To Decompose :)";
        }
        else if(twoNf && threeNf){
            nf.setText("3NF");
            DecompBCNf decompBCNf = new DecompBCNf();
            decomposedRlns = decompBCNf.decompbcnf(h,rls,ckSet);
            hNFPlusOne = "Decomposition into BCNF";
        }
        else if(twoNf){
            nf.setText("2NF");
            Decomp3Nf decomp3Nf = new Decomp3Nf();
            decomposedRlns = decomp3Nf.decomp3nf(h,rls,ckSet);
            hNFPlusOne = "Decomposition into 3NF";
        }
        else{
            nf.setText("1NF");
            Decomp2Nf decomp2Nf = new Decomp2Nf();
            decomposedRlns = decomp2Nf.decomp2nf(h,rls,ckSet);
            hNFPlusOne = "Decomposition into 2NF";
        }

    }

    public void onDecompose(View view){
        Intent intent = new Intent(this, PostNormalisation.class);
        startActivity(intent);
    }

}
