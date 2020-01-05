package com.shizuku.poetry.shijing.ui.poetview;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import android.util.Log;
import android.view.WindowManager;
import android.widget.TextView;

import com.shizuku.poetry.shijing.Poet;
import com.shizuku.poetry.shijing.PoetDBHelper;
import com.shizuku.poetry.shijing.R;

public class PoetViewActivity extends AppCompatActivity {
    private PoetDBHelper db;

    private Poet getPoet(String title) {
        Poet p = null;
        SQLiteDatabase d = db.getReadableDatabase();
        Cursor c = d.rawQuery("select * from shijing where title=?", new String[]{title});
        if (c.moveToFirst()) {
            p = new Poet(c.getInt(c.getColumnIndex("value")),
                    c.getString(c.getColumnIndex("title")),
                    c.getString(c.getColumnIndex("chapter")),
                    c.getString(c.getColumnIndex("section")),
                    c.getString(c.getColumnIndex("content")));
            Log.d("MainActivity", "Get poet: " + p.getTitle());
        } else {
            Log.d("MainActivity", "Get poet: FAIL");
        }
        c.close();
        d.close();
        return p;
    }

    private Poet getPoet(int id) {
        Poet p = null;
        SQLiteDatabase d = db.getReadableDatabase();
        Cursor c = d.rawQuery("select * from shijing where value=?", new String[]{String.valueOf(id)});
        if (c.moveToFirst()) {
            p = new Poet(c.getInt(c.getColumnIndex("value")),
                    c.getString(c.getColumnIndex("title")),
                    c.getString(c.getColumnIndex("chapter")),
                    c.getString(c.getColumnIndex("section")),
                    c.getString(c.getColumnIndex("content")));
            Log.d("MainActivity", "Get poet: " + p.getTitle());
        } else {
            Log.d("MainActivity", "Get poet: FAIL");
        }
        c.close();
        d.close();
        return p;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_poet_view);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        db = new PoetDBHelper(this, getExternalFilesDir(null).getPath() + "/shijing.db", null, 1);

        Intent i = getIntent();
        int id = i.getIntExtra("id", 0);
        Poet p;
        if (id != 0) {
            p = getPoet(id);
        } else {
            p = getPoet(i.getStringExtra("title"));
        }

        TextView tx_title = findViewById(R.id.text_title);
        TextView tx_chapter_section = findViewById(R.id.text_chapter_section);
        TextView tx_content = findViewById(R.id.text_content);

        tx_title.setText(p.getTitle());
        tx_chapter_section.setText(p.getChapter() + " · " + p.getSection());
        String b = "\u3000";
        String s = p.getContent();
        s = b + s;
        s = s.replaceAll("\\n", "$0" + b);
        tx_content.setText(s);
    }
}
