package com.team2.gogame;

import android.graphics.Point;
import android.os.Bundle;
import android.view.View;
import android.widget.AbsoluteLayout;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

public class PlayActivity extends AppCompatActivity {
    private Game game;
    private BoardView board;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        game = new Game();
        ButtonHandler bh = new ButtonHandler();
        Point size = new Point();
        getWindowManager().getDefaultDisplay().getSize(size);
        board = new BoardView(this, 9, size.x, bh);

        ImageView background = new ImageView(this);
        background.setImageResource(R.drawable.board);
        background.setScaleX((float)2);
        background.setScaleY((float)2);

        RelativeLayout layout = new RelativeLayout(this);
        layout.addView(background);
        layout.addView(board);


        setContentView(layout);

    }


    private class ButtonHandler implements View.OnClickListener {
        public void onClick(View v) {}
    }
}
