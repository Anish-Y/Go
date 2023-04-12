package com.team2.gogame;

import android.content.Context;
import android.graphics.Point;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsoluteLayout;
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
        setContentView(R.layout.activity_play);
        game = new Game(1,"","");
        ButtonHandler bh = new ButtonHandler();
        Point size = new Point();
        getWindowManager().getDefaultDisplay().getSize(size);
        board = new BoardView(this, 9, size.x, bh);

//        this.addView(board);


//        layout.addView(background);
////        background.setX();
//        background.setScaleX(size.x);
//        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(size.x,size.x);
//
//        params.addRule(RelativeLayout.CENTER_HORIZONTAL);
//        layout.setLayoutParams(params);
//
////        background.setScaleX(2);



        setContentView(board);

    }


    private class ButtonHandler implements View.OnClickListener {
        public void onClick(View v) {}
    }
}
