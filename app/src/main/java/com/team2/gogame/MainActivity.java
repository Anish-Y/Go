package com.team2.gogame;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class MainActivity extends AppCompatActivity {
    private DatabaseManager DBM;
    private Calendar calendar;
    private SimpleDateFormat dateFormat;
    private String date;

    @Override
    protected void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        DBM = new DatabaseManager(this);

        Button newGameButton = (Button) findViewById(R.id.New_Game);
        Button resumeGameButton = (Button) findViewById(R.id.Resume_Game);
        Button pastGamesButton = (Button) findViewById(R.id.Past_Games);
        calendar = Calendar.getInstance();

        newGameButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v){
                if(DBM.selectAll().size() > 0) {
                    int id = DBM.selectAll().get(DBM.selectAll().size()-1).getId();
                    Log.w("main", ""+id);
                    dateFormat = new SimpleDateFormat("MM/dd/yyyy");
                    date = dateFormat.format(calendar.getTime());
                    DBM.insert(new Game(id,date,"", ""));
                } else {
                    dateFormat = new SimpleDateFormat("MM/dd/yyyy");
                    date = dateFormat.format(calendar.getTime());
                    DBM.insert(new Game(1,date,"", ""));
                }
                startActivity(new Intent(MainActivity.this, PlayActivity.class));
                Toast.makeText(MainActivity.this, "Good luck!", Toast.LENGTH_SHORT).show();
            }
        });

        resumeGameButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v){
                String moves = DBM.selectAll().get(DBM.selectAll().size()-1).getMoves();
                if(moves.length() > 0) {
                    startActivity(new Intent(MainActivity.this, PlayActivity.class));
                }
            }
        });

        pastGamesButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v){
                startActivity(new Intent(MainActivity.this, PastGamesActivity.class));
            }
        });
    }
}