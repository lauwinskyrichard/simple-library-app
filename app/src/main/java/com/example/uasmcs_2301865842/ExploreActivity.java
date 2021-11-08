package com.example.uasmcs_2301865842;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.uasmcs_2301865842.Database.DefinitionsHelper;
import com.example.uasmcs_2301865842.Database.WordHelper;

import java.util.ArrayList;

public class ExploreActivity extends AppCompatActivity {

    EditText searchField;
    ImageButton searchBtn;
    Button favorites;
    TextView mark;

    RecyclerView exploreRecycler;
    ExploreAdapter exploreAdapter;
    RecyclerView.LayoutManager exploreLayoutManager;

    ArrayList<Word> searchWordList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_explore);
        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }

        searchField = findViewById(R.id.searchET1);
        searchBtn = findViewById(R.id.searchBtn1);
        favorites = findViewById(R.id.favorites);
        mark = findViewById(R.id.marker);

        exploreRecycler = findViewById(R.id.exploreRV);
        exploreRecycler.setHasFixedSize(true);
        exploreLayoutManager = new LinearLayoutManager(this);
        exploreAdapter = new ExploreAdapter(this);

        if (searchWordList.isEmpty()) {
            mark.setVisibility(View.VISIBLE);
        }
        else{
            mark.setVisibility(View.INVISIBLE);
        }

        searchBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                searchWordList.clear();

                for (int i=0; i<MainActivity.listWord.size(); i++)
                {
                    String temp = MainActivity.listWord.get(i).getwName();

                    if (temp.startsWith(searchField.getText().toString()))
                    {
                        searchWordList.add(new Word(i+1, temp));
                    }
                }
                exploreAdapter.setWordList(searchWordList);
                exploreRecycler.setLayoutManager(exploreLayoutManager);
                exploreRecycler.setAdapter(exploreAdapter);

                if (searchWordList.isEmpty()) {
                    mark.setVisibility(View.VISIBLE);
                }
                else{
                    mark.setVisibility(View.INVISIBLE);
                }
            }
        });

        exploreAdapter.setOnItemClickListener(new ExploreAdapter.OnItemClickListener() {
            @Override
            public void OnSaveClick(int position) {
                WordHelper helper = new WordHelper(getApplicationContext());
                helper.insertItem(searchWordList.get(position).getwName());

                String wTemp = searchWordList.get(position).getwName();

                for (int i=0; i<MainActivity.listWord.size(); i++)
                {
                    int len = MainActivity.listWord.get(i).getDefinitions().size();

                    for (int j=0; j<len; j++)
                    {
                        if (wTemp.matches(MainActivity.listWord.get(i).getDefinitions().get(j).getWordName1()))
                        {
                            String tempUrl = MainActivity.listWord.get(i).getDefinitions().get(j).getImgURL();
                            String tempType = MainActivity.listWord.get(i).getDefinitions().get(j).getType();

                            String tempDef = MainActivity.listWord.get(i).getDefinitions().get(j).getDefinition();

                            int idx = 0;
                            for (int k=0; k<tempDef.length(); k++)
                            {
                                Character c = tempDef.charAt(k);
                                Character d = '\'';
                                if (c.equals(d))
                                {
                                    idx = k;
                                }
                            }
                            Log.e("IDX", String.valueOf(idx));

                            if (idx != 0)
                            {
                                tempDef = new StringBuffer(tempDef).insert(idx, "'").toString();
                            }

                            String tempWName1 = MainActivity.listWord.get(i).getDefinitions().get(j).getWordName1();

                            DefinitionsHelper helper1 = new DefinitionsHelper(getApplicationContext());
                            helper1.insertItem(tempUrl, tempType, tempDef, tempWName1);
                        }
                    }
                }
                exploreAdapter.notifyDataSetChanged();
                Toast.makeText(ExploreActivity.this, "Data Saved!", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void OnItemClick(int position) {
                Intent intent = new Intent(ExploreActivity.this, ExploreDetailActivity.class);
                intent.putExtra("wID", searchWordList.get(position).getId());
                intent.putExtra("wWord", searchWordList.get(position).getwName());
                startActivity(intent);
            }
        });

        favorites.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ExploreActivity.this, FavoritesActivity.class);
                startActivity(intent);
                finish();
                ExploreActivity.this.overridePendingTransition(0,0);
            }
        });
    }

    boolean doubleBackToExitPressedOnce = false;

    @Override
    public void onBackPressed() {
        if (doubleBackToExitPressedOnce) {
            super.onBackPressed();
            return;
        }

        this.doubleBackToExitPressedOnce = true;
        Toast.makeText(this, "Press back again to exit", Toast.LENGTH_SHORT).show();

        new Handler(Looper.getMainLooper()).postDelayed(new Runnable() {

            @Override
            public void run() {
                doubleBackToExitPressedOnce=false;
            }
        }, 2000);
    }
}