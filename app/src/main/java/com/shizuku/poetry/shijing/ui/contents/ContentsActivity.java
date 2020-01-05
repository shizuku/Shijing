package com.shizuku.poetry.shijing.ui.contents;


import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.shizuku.poetry.shijing.Poet;
import com.shizuku.poetry.shijing.PoetDBHelper;
import com.shizuku.poetry.shijing.R;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.View;
import android.view.WindowManager;

import java.util.ArrayList;
import java.util.List;

public class ContentsActivity extends AppCompatActivity {
    List<ContentItem> contentsList = new ArrayList<>();
    private PoetDBHelper db;

    void initContents(int level, String item) {
        List<ContentItem> str = query(level, item);
        for (ContentItem i : str) {
            contentsList.add(i);
        }
    }

    List<ContentItem> query(int level, String item) {
        ContentItem ci = new ContentItem(0, "");
        String ss;
        switch (level){

            case 1:
                ss = "section";
                break;
            case 2:
                ss = "title";
                break;
            case 0:
            default:
                ss = "chapter";
                break;
        }

        String s;
        SQLiteDatabase d = db.getReadableDatabase();
        List<ContentItem> r = new ArrayList<>();
        Cursor c;
        if(level==0){
            c = d.rawQuery("select chapter from shijing", null);
        }else if(level==1){
            c = d.rawQuery("select section from shijing where chapter=?", new String[]{item});
        }else{
            c = d.rawQuery("select title from shijing where section=?",  new String[]{item});
        }
        if (c.moveToFirst()) {
            do {
                s = c.getString(c.getColumnIndex(ss));
                if (s.compareTo(ci.getItem()) !=0) {
                    ci = new ContentItem(level, s);
                    r.add(ci);
                }
            } while (c.moveToNext());
            Log.d("MainActivity", "Get level: " + ci.getItem());
        } else {
            Log.d("MainActivity", "Get level: FAIL");
        }
        c.close();
        d.close();
        return r;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contents);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        db = new PoetDBHelper(this, getExternalFilesDir(null).getPath() + "/shijing.db", null, 1);

        Intent i = getIntent();
        initContents(i.getIntExtra("level", 0), i.getStringExtra("item"));

        RecyclerView recyclerView = findViewById(R.id.contents_recycle);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
        ContentAdapter contentAdapter = new ContentAdapter(contentsList);
        recyclerView.setAdapter(contentAdapter);
    }
}
