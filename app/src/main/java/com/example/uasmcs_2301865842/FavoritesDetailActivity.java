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

public class FavoritesDetailActivity extends AppCompatActivity {

    TextView word1;

    RecyclerView favoritesDetailRecycler;
    FavoritesDetailAdapter favoritesDetailAdapter;
    RecyclerView.LayoutManager favoritesDetailLayoutManager;

    ArrayList<Definitions> allDef = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favorites_detail);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayShowTitleEnabled(false);
        }

        favoritesDetailRecycler = findViewById(R.id.favoritesDetailRV);
        favoritesDetailRecycler.setHasFixedSize(true);
        favoritesDetailLayoutManager = new LinearLayoutManager(this);

        word1 = findViewById(R.id.wordTV1);

        Intent intent = getIntent();
        word1.setText("Word: " + intent.getStringExtra("wWord1"));

        String tempWord = intent.getStringExtra("wWord1");

        DefinitionsHelper helper = new DefinitionsHelper(getApplicationContext());
        allDef = helper.getDefinitions(tempWord);

        favoritesDetailAdapter = new FavoritesDetailAdapter(allDef);
        favoritesDetailRecycler.setLayoutManager(favoritesDetailLayoutManager);
        favoritesDetailRecycler.setAdapter(favoritesDetailAdapter);
        favoritesDetailAdapter.notifyDataSetChanged();
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