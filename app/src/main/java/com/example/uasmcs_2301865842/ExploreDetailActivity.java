package com.example.uasmcs_2301865842;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.uasmcs_2301865842.Database.DefinitionsHelper;
import com.example.uasmcs_2301865842.Database.WordHelper;

import java.util.ArrayList;

public class ExploreDetailActivity extends AppCompatActivity {

    TextView word;
    Button saveDetail;

    RecyclerView exploreDetailRecycler;
    ExploreDetailAdapter exploreDetailAdapter;
    RecyclerView.LayoutManager exploreDetailLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_explore_detail);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayShowTitleEnabled(false);
        }

        exploreDetailRecycler = findViewById(R.id.exploreDetailRV);
        exploreDetailRecycler.setHasFixedSize(true);
        exploreDetailLayoutManager = new LinearLayoutManager(this);

        word = findViewById(R.id.wordTV);
        saveDetail = findViewById(R.id.saveDetailBtn);

        Intent intent = getIntent();
        word.setText("Word: " + intent.getStringExtra("wWord"));

        String tempWord = intent.getStringExtra("wWord");

        for (int i=0; i<MainActivity.listWord.size(); i++)
        {
            String temp = MainActivity.listWord.get(i).getwName();

            if (temp.matches(tempWord))
            {
                exploreDetailAdapter = new ExploreDetailAdapter(MainActivity.listWord.get(i).getDefinitions());
                exploreDetailRecycler.setLayoutManager(exploreDetailLayoutManager);
                exploreDetailRecycler.setAdapter(exploreDetailAdapter);
                break;
            }
        }

        saveDetail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                WordHelper helper = new WordHelper(getApplicationContext());
                helper.insertItem(intent.getStringExtra("wWord"));

                String wTemp = intent.getStringExtra("wWord");

                for (int i=0; i<MainActivity.listWord.size(); i++) {
                    int len = MainActivity.listWord.get(i).getDefinitions().size();

                    for (int j = 0; j < len; j++) {
                        if (wTemp.matches(MainActivity.listWord.get(i).getDefinitions().get(j).getWordName1())) {
                            String tempUrl = MainActivity.listWord.get(i).getDefinitions().get(j).getImgURL();
                            String tempType = MainActivity.listWord.get(i).getDefinitions().get(j).getType();

                            String tempDef = MainActivity.listWord.get(i).getDefinitions().get(j).getDefinition();

                            int idx = 0;
                            for (int k = 0; k < tempDef.length(); k++) {
                                Character c = tempDef.charAt(k);
                                Character d = '\'';
                                if (c.equals(d)) {
                                    idx = k;
                                }
                            }

                            if (idx != 0) {
                                tempDef = new StringBuffer(tempDef).insert(idx, "'").toString();
                            }

                            String tempWName1 = MainActivity.listWord.get(i).getDefinitions().get(j).getWordName1();

                            DefinitionsHelper helper1 = new DefinitionsHelper(getApplicationContext());
                            helper1.insertItem(tempUrl, tempType, tempDef, tempWName1);
                        }
                    }
                }
                Toast.makeText(ExploreDetailActivity.this, "Data Saved!", Toast.LENGTH_SHORT).show();
                finish();
                overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
            }
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if (item.getItemId() == android.R.id.home) {
            finish();
            overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
            return true;
        }
        return false;
    }
}