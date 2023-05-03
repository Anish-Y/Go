package com.team2.gogame;

import android.content.Intent;
import android.graphics.Point;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class ReviewActivity extends AppCompatActivity {
    private Game game;
    private BoardView board;
    private DatabaseManager dbm;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        dbm = new DatabaseManager(this);
        String s;
        if(savedInstanceState == null) {
            Bundle extras = getIntent().getExtras();
            if(extras == null) {
                s = null;
            } else {
                s = extras.getString("id");
            }
        } else {
            s = (String) savedInstanceState.getSerializable("id");
        }
        game = dbm.selectById(Integer.parseInt(s));

        ReviewActivity.ButtonHandler bh = new ReviewActivity.ButtonHandler();
        Point size = new Point();
        getWindowManager().getDefaultDisplay().getSize(size);
        board = new BoardView(this, game.getN(), size.x, bh);

        setContentView(R.layout.activity_review);
        ViewGroup viewGroup = (ViewGroup) ((ViewGroup) this
                .findViewById(android.R.id.content)).getChildAt(0);
        viewGroup.addView(board);

        board.update(game.getBoard());

        Button endGameButton = findViewById(R.id.EndGame);
//        endGameButton.setOnClickListener(new View.OnClickListener() {
//            public void onClick(View v){
//                int id = dbm.selectAll().get(dbm.selectAll().size()-1).getId();
//                Log.w("main", ""+id);
//                dbm.insert(new Game(id,"","", ""));
//                startActivity(new Intent(ReviewActivity.this, MainActivity.class));
//            }
//        });
    }

    private class ButtonHandler implements View.OnClickListener {
        public void onClick(View v) {
//
//            Button b = (Button) v;
//
//            game.playMoveIncomplete(b.getId(),game.getColor());
//            board.update(game.getBoard());
//            Log.w("play", game.getBoard());
//            Log.w("play", ""+game.getId());
//            dbm.updateById(game.getId(), "", game.getMoves(), game.getBoard());
        }
    }
}
