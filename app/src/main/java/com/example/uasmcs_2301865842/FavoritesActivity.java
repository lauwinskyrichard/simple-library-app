package com.example.uasmcs_2301865842;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.strictmode.WebViewMethodCalledOnWrongThreadViolation;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.uasmcs_2301865842.Database.DefinitionsHelper;
import com.example.uasmcs_2301865842.Database.WordHelper;

import java.util.ArrayList;

public class FavoritesActivity extends AppCompatActivity {

    Button explore;
    TextView mark1;

    RecyclerView favoritesRecycler;
    FavoritesAdapter favoritesAdapter;
    RecyclerView.LayoutManager favoritesLayoutManager;

    ArrayList<Word> words = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favorites);
        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }

        mark1 = findViewById(R.id.marker1);
        explore = findViewById(R.id.explore);

        explore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(FavoritesActivity.this, ExploreActivity.class);
                startActivity(intent);
                finish();
                FavoritesActivity.this.overridePendingTransition(0,0);
            }
        });

        words.clear();

        WordHelper helper = new WordHelper(getApplicationContext());
        words = helper.getAllWord();

        favoritesRecycler = findViewById(R.id.favRVS);
        favoritesRecycler.setHasFixedSize(true);
        favoritesLayoutManager = new LinearLayoutManager(this);
        favoritesAdapter = new FavoritesAdapter(this);

        if (words == null) {
            mark1.setVisibility(View.VISIBLE);
        }
        else {
            mark1.setVisibility(View.INVISIBLE);
        }

        if (words != null) {
            favoritesAdapter.setWordsList(words);
            favoritesAdapter.notifyDataSetChanged();
        }

        favoritesRecycler.setLayoutManager(favoritesLayoutManager);
        favoritesRecycler.setAdapter(favoritesAdapter);

        favoritesAdapter.setOnItemClickListener(new FavoritesAdapter.OnItemClickListener() {
            @Override
            public void OnDeleteClick(int position) {

                WordHelper helper1 = new WordHelper(getApplicationContext());
                ArrayList<Word> word1 = helper1.getAllWord();
                helper1.deleteWord(word1.get(position).getId());

                DefinitionsHelper helper2 = new DefinitionsHelper(getApplicationContext());
                helper2.deleteDef(word1.get(position).getwName());

                favoritesAdapter.notifyDataSetChanged();
                favoritesAdapter.notifyItemRemoved(position);

                words.remove(position);

                if (words == null)
                {
                    mark1.setVisibility(View.VISIBLE);
                }
                else if (words.size() == 0)
                {
                    mark1.setVisibility(View.VISIBLE);
                }
                else {
                    mark1.setVisibility(View.INVISIBLE);
                }

                Toast.makeText(FavoritesActivity.this, "Dictionary Removed!", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void OnItemClick(int position) {
                Intent intent = new Intent(FavoritesActivity.this, FavoritesDetailActivity.class);
                intent.putExtra("wID1", words.get(position).getId());
                intent.putExtra("wWord1", words.get(position).getwName());
                startActivity(intent);
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