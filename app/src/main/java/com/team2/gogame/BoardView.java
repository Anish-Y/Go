package com.team2.gogame;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;

import androidx.core.content.res.ResourcesCompat;

public class BoardView extends GridLayout {
    private int side;
    private Button[][] buttons;
    private Drawable clear, white, black;

    public BoardView(Context context, int s, int sWidth, View.OnClickListener listener) {
        super(context);
        side = s;
        int width = (sWidth)/s;
        setColumnCount(side);
        setRowCount(side);

        Resources res = getResources();
        clear = ResourcesCompat.getDrawable(res, R.drawable.clear_piece, null);
        white = ResourcesCompat.getDrawable(res, R.drawable.white_piece, null);
        black = ResourcesCompat.getDrawable(res, R.drawable.black_piece, null);


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

                buttons[row][col].setBackground(clear);

                buttons[row][col].setOnClickListener(listener);

                buttons[row][col].setId(col + row*side);
            }
        }




    }
    public void update(String b){
        for(int i = 0; i < side; i++) {
            for(int j = 0; j < side; j++) {
                char c = b.charAt(i*side + j);
                switch (c){
                    case ' ': buttons[i][j].setBackground(clear);
                        break;
                    case '0' : buttons[i][j].setBackground(black);
                        break;
                    case '1' : buttons[i][j].setBackground(white);
                        break;
                }

            }
        }
    }

    public int getSide() {
        return side;
    }
}
