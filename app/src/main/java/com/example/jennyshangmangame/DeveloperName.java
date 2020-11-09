package com.example.jennyshangmangame;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class DeveloperName extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_developer_name);
    }

    public void openMainMenuPressed(View view) {
        Intent openManinMenuPage = new Intent(this, MainActivity.class);
        startActivity(openManinMenuPage);

    }
}