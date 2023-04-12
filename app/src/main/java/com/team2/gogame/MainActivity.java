package com.team2.gogame;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button newGameButton = (Button) findViewById(R.id.New_Game);
        Button pastGamesButton = (Button) findViewById(R.id.Past_Games);

        newGameButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v){
                startActivity(new Intent(MainActivity.this, PlayActivity.class));
            }
        });

        pastGamesButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v){
                startActivity(new Intent(MainActivity.this, PastGamesActivity.class));
            }
        });
    }
}