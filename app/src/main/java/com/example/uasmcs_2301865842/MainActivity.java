package com.example.uasmcs_2301865842;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;
import androidx.viewpager2.widget.ViewPager2;

import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.uasmcs_2301865842.Database.DefinitionsHelper;
import com.example.uasmcs_2301865842.Database.WordHelper;
import com.google.android.material.tabs.TabLayout;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    /*
        Richard Lauwinsky
        2301865842
        BA03
    */

    //AVD = Pixel 3 XL API 25

    private RequestQueue mQueue;

    static ArrayList<Word> listWord = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }

        listWord.clear();

        mQueue = Volley.newRequestQueue(getApplicationContext());
        String url = "https://myawesomedictionary.herokuapp.com/words?q=";
        JsonArrayRequest request = new JsonArrayRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        try {
                            for (int i=0; i<response.length(); i++)
                            {
                                JSONObject jsonObject = response.getJSONObject(i);
                                String word = jsonObject.getString("word");
                                int id = listWord.size() + 1;
                                Word newWord = new Word(id, word);
                                listWord.add(newWord);

                                JSONArray jsonArray = jsonObject.getJSONArray("definitions");
                                for (int j=0; j<jsonArray.length(); j++)
                                {
                                    JSONObject jsonObject1 = jsonArray.getJSONObject(j);
                                    String imageurl = jsonObject1.getString("image_url");
                                    String type = jsonObject1.getString("type");
                                    String definition = jsonObject1.getString("definition");
                                    int id2 = listWord.get(i).definitions.size() + 1;
                                    listWord.get(i).definitions.add(new Definitions(id2, imageurl, type, definition, word));
                                }
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }},
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        error.printStackTrace();
                    }}
        );
        mQueue.add(request);

        Intent intent = new Intent(MainActivity.this, ExploreActivity.class);
        startActivity(intent);
        finish();
        MainActivity.this.overridePendingTransition(0, 0);
    }
}