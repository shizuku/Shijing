package com.shizuku.poetry.shijing.ui;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.TextView;
import android.widget.Toast;

import com.shizuku.poetry.shijing.R;
import com.shizuku.poetry.shijing.ui.contents.ContentsActivity;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;

public class MainActivity extends AppCompatActivity {

    void random() {
        Intent i = new Intent("com.shizuku.poetry.shijing.POET_VIEW");
        i.putExtra("id", ((int) (Math.random() * 305)) + 1);
        i.putExtra("title","");
        startActivity(i);
    }

    void contents() {
        Intent i = new Intent(this, ContentsActivity.class);
        i.putExtra("level", 0);
        i.putExtra("item", "");
        startActivity(i);
    }

    void search() {

    }

    void settings() {

    }

    void about() {
        Intent i = new Intent("com.shizuku.poetry.shijing.ABOUT_VIEW");
        startActivity(i);
    }

    void copyResource(Context context, String fileName) {
        try {
            InputStream is = context.getAssets().open(fileName);
            FileOutputStream fos = new FileOutputStream(new File(getExternalFilesDir(null), fileName));
            byte[] buffer = new byte[1024];
            int byteCount = 0;
            while ((byteCount = is.read(buffer)) != -1) {// 循环从输入流读取
                // buffer字节
                fos.write(buffer, 0, byteCount);// 将读取的输入流写入到输出流
            }
            fos.flush();// 刷新缓冲区
            is.close();
            fos.close();
        } catch (Exception e) {
            Toast.makeText(getBaseContext(), e.toString(), Toast.LENGTH_LONG).show();
        }
    }

    private void firstRun() {
        SharedPreferences sharedPreferences = getSharedPreferences("FirstRun", 0);
        Boolean first_run = sharedPreferences.getBoolean("First", true);
        if (first_run) {
            copyResource(this, "shijing.db");
            Log.d("MainActivity", "First");
            sharedPreferences.edit().putBoolean("First", false).commit();
            return;
        } else {
            Log.d("MainActivity", "Not First");
            return;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        firstRun();

        TextView tx_contents = findViewById(R.id.text_contents);
        TextView tx_random = findViewById(R.id.text_random);
        TextView tx_search = findViewById(R.id.text_search);
        TextView tx_settings = findViewById(R.id.text_settings);
        TextView tx_exit = findViewById(R.id.text_exit);
        TextView tx_about = findViewById(R.id.text_about);

        tx_contents.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                contents();
            }
        });
        tx_random.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                random();
            }
        });
        tx_search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                search();
            }
        });
        tx_settings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                settings();
            }
        });
        tx_about.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                about();
            }
        });
        tx_exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
