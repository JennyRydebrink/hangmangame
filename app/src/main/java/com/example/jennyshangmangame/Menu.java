package com.example.jennyshangmangame;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class Menu extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
    }

    public void openDeveloperPagePressed(View view) {
        Intent openDeveloperPage = new Intent(this, DeveloperName.class);
        startActivity(openDeveloperPage);
    }
    public void openMainMenuPressed(View view) {
        Intent openManinMenuPage = new Intent(this, MainActivity.class);
        startActivity(openManinMenuPage);

    }
}