package com.team2.gogame;

import android.graphics.Point;
import android.os.Bundle;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

public class PlayActivity extends AppCompatActivity {
    private Game game;
    private BoardView board;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        game = new Game();
        board = new BoardView(this, 9);

        Point size = new Point();
        getWindowManager().getDefaultDisplay().getSize(size);
        int w = size.x / board.getSide();

    }

}
