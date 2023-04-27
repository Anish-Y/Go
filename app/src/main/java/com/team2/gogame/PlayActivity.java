package com.team2.gogame;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Point;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.TextureView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsoluteLayout;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TableLayout;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.res.ResourcesCompat;

public class PlayActivity extends AppCompatActivity {
    private Game game;
    private BoardView board;
    private DatabaseManager dbm;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        dbm = new DatabaseManager(this);
        int ideeee = dbm.selectAll().get(dbm.selectAll().size()-1).getId();
        game = dbm.selectById(ideeee);

        ButtonHandler bh = new ButtonHandler();
        Point size = new Point();
        getWindowManager().getDefaultDisplay().getSize(size);
        board = new BoardView(this, game.getN(), size.x, bh);

        setContentView(R.layout.activity_play);
        ViewGroup viewGroup = (ViewGroup) ((ViewGroup) this
                .findViewById(android.R.id.content)).getChildAt(0);
        viewGroup.addView(board);

        Button endGameButton = findViewById(R.id.EndGame);
        endGameButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v){
                startActivity(new Intent(PlayActivity.this, MainActivity.class));

            }
        });
    }


    private class ButtonHandler implements View.OnClickListener {
        public void onClick(View v) {

            Button b = (Button) v;

            game.playMoveIncomplete(b.getId(),game.getColor());
            board.update(game.getBoard());
            Log.w("play", game.getBoard());
            Log.w("play", ""+game.getId());
            dbm.updateById(game.getId(), "", game.getMoves());
        }
    }
}
