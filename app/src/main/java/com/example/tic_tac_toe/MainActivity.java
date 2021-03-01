package com.example.tic_tac_toe;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import android.os.Bundle;
import android.view.View;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;


import java.util.Arrays;
import java.util.concurrent.atomic.AtomicBoolean;

public class MainActivity extends AppCompatActivity {

    int currentPlayer = 0;
    boolean gameActivity = true;
    int[] gameState = {2, 2, 2, 2, 2, 2, 2, 2, 2};
    int [][] winningPositions = { {0,1,2}, {3,4,5}, {6,7,8}, {0,3,6}, {1,4,7},{2,5,8}, {0,4,8}, {2,4,6}};

    public void dropIn(View view) {
        ImageView inputKey = (ImageView) view;

        int currentCounter = Integer.parseInt(inputKey.getTag().toString());

        if ((gameState[currentCounter] == 2) && (gameActivity)) {

            gameState[currentCounter]=currentPlayer;

            inputKey.setTranslationY(-1000f);

            if (currentPlayer == 0) {

                inputKey.setImageResource(R.drawable.cross1);
                currentPlayer = 1;
            } else {
                inputKey.setImageResource(R.drawable.circle1);
                currentPlayer = 0;
            }

            inputKey.animate().translationYBy(1000f).setDuration(400);

            for (int[] positions:winningPositions) {
                if (gameState[positions[0]]==gameState[positions[1]] && gameState[positions[1]]==gameState[positions[2]] &&
                        gameState[positions[0]] != 2) {

                    gameActivity = false;
                    LinearLayout layout = (LinearLayout)findViewById(R.id.playagain);

                    layout.setVisibility(View.VISIBLE);

                    String winner = "X";
                    if (gameState[positions[0]]==1) {
                        winner = "O";
                    }
                    TextView winnermsg = (TextView)findViewById(R.id.winmsg);
                    winnermsg.setText(String.format("%s has won!", winner));

                }
                else {
                    AtomicBoolean gameIsOver = new AtomicBoolean(true);
                    for (int i : gameState) {
                        if (i == 2) {
                            gameIsOver.set(false);
                            break;
                        }
                    }

                    if (gameIsOver.get()) {

                        TextView winnermsg = (TextView)findViewById(R.id.winmsg);
                        winnermsg.setText("It's a Draw!");
                        LinearLayout layout = (LinearLayout)findViewById(R.id.playagain);

                        layout.setVisibility(View.VISIBLE);
                    }
                }


            }
        }
    }

    public void playagain(View view) {

        gameActivity = true;

        LinearLayout layout = (LinearLayout)findViewById(R.id.playagain);

        layout.setVisibility(View.INVISIBLE);
        currentPlayer = 0;

        Arrays.fill(gameState, 2);
        GridLayout mygrid = (GridLayout)findViewById(R.id.ticGrid);

        for (int i=0;i<mygrid.getChildCount();i++) {
            ((ImageView) mygrid.getChildAt(i)).setImageResource(0);
        }



    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}