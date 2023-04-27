package com.team2.gogame;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    private DatabaseManager DBM;

    @Override
    protected void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        DBM = new DatabaseManager(this);


        Button newGameButton = (Button) findViewById(R.id.New_Game);
        Button resumeGameButton = (Button) findViewById(R.id.Resume_Game);
        Button pastGamesButton = (Button) findViewById(R.id.Past_Games);

        newGameButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v){
                int ideeee = DBM.selectAll().get(DBM.selectAll().size()-1).getId();
                Log.w("main", ""+ideeee);
                DBM.insert(new Game(ideeee,"",""));
                startActivity(new Intent(MainActivity.this, PlayActivity.class));
            }
        });

        resumeGameButton.setOnClickListener(new View.OnClickListener() {
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