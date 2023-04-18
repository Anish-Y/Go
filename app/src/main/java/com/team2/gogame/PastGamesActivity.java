package com.team2.gogame;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class PastGamesActivity extends AppCompatActivity {
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        updateView();
    }

    public void updateView() {
        RelativeLayout r = new RelativeLayout(this);
        ScrollView s = new ScrollView(this);
        setContentView(r);
        r.addView(s);

        DatabaseManager d = new DatabaseManager(this);
        ArrayList<Game> games = d.selectAll();
        GridLayout group = new GridLayout(this);
        group.setColumnCount(1);
        for(Game game : games) {
            Button b = new Button(this);
            b.setId(game.getId());
            b.setText(game.toString());
            b.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    Intent i = new Intent(PastGamesActivity.this, PastGamesActivity.class);
                    int id = 0;
                    i.putExtra("game.getId()", id);
                    startActivity(i);
                }
            });
            group.addView(b);
        }
        s.addView(group);
    }
}
