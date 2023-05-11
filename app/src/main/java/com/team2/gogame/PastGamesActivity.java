package com.team2.gogame;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.Toast;

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
        group.setColumnCount(2);
        for(Game game : games) {
            Button b = new Button(this);
            b.setId(game.getId());
            b.setText(game.toString());
            b.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    Intent i = new Intent(PastGamesActivity.this, ReviewActivity.class);
                    String s = "" + game.getId();
                    i.putExtra("id", s);
                    startActivity(i);
                }
            });
            group.addView(b);
            Button delete = new Button(this);
            delete.setId(game.getId()+1000);
            delete.setText("X");
            delete.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    d.deleteById(game.getId());
                    updateView();
                    Toast.makeText(PastGamesActivity.this, "Deleted Game " + game.getId(), Toast.LENGTH_SHORT).show();
                }
            });
            group.addView(delete);
        }
        s.addView(group);
    }
}
