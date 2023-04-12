package com.team2.gogame;

import android.content.Context;
import android.graphics.Point;
import android.os.Bundle;
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

public class PlayActivity extends AppCompatActivity {
    private Game game;
    private BoardView board;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        game = new Game(1,"","");
        ButtonHandler bh = new ButtonHandler();
        Point size = new Point();
        getWindowManager().getDefaultDisplay().getSize(size);
        board = new BoardView(this, 9, size.x, bh);

        setContentView(R.layout.activity_play);
        ViewGroup viewGroup = (ViewGroup) ((ViewGroup) this
                .findViewById(android.R.id.content)).getChildAt(0);
        viewGroup.addView(board);
    }


    private class ButtonHandler implements View.OnClickListener {
        public void onClick(View v) {
            Button b = (Button) v;
            game.editStone(game.getColor(), b.getId());
        }
    }
}
