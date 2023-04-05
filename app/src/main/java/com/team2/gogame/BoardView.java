package com.team2.gogame;

import android.content.Context;
import android.widget.GridLayout;

public class BoardView extends GridLayout {
    private int side;
    public BoardView(Context context, int s) {
        super(context);
        side = s;
    }

    public int getSide() {
        return side;
    }
}
