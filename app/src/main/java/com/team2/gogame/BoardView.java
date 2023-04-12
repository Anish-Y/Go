package com.team2.gogame;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.ImageView;

import androidx.core.content.res.ResourcesCompat;

public class BoardView extends GridLayout {
    private int side;
    private Button[][] buttons;

    public BoardView(Context context, int s, int sWidth, View.OnClickListener listener) {
        super(context);
        side = s;
        int width = (sWidth)/s;
        setColumnCount(side);
        setRowCount(side);

        Spec rowSpec = spec(side, 1);
        Spec columnSpec = spec(0, side);
        LayoutParams lp = new LayoutParams(rowSpec, columnSpec);
        buttons = new Button[side][side];



        for (int row = 0; row < side; row++) {
            for (int col = 0; col < side; col++) {
                buttons[row][col] = new Button(context);
                buttons[row][col].setAlpha(1);
                buttons[row][col].setScaleX(.9f);
                buttons[row][col].setScaleY(.9f);
                addView(buttons[row][col], width, width);
                Resources res = getResources();
                Drawable drawable = ResourcesCompat.getDrawable(res, R.drawable.round_button, null);
                buttons[row][col].setBackground(drawable);
                buttons[row][col].setOnClickListener(listener);
            }
        }




    }

    public int getSide() {
        return side;
    }
}
