package com.example.android.courtcounter;

import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    int scoreTeamA = 0;
    int scoreTeamB = 0;
    int outs = 0;
    int strikes = 0;
    int balls = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void displayForTeamA(int score) {
        TextView scoreView = (TextView) findViewById(R.id.team_a_score);
        scoreView.setText(String.valueOf(score));
    }

    public void addOneForTeamA(View v) {
        scoreTeamA = scoreTeamA +1;
        displayForTeamA(scoreTeamA);
    }
    public void restOneForTeamA(View v) {
        scoreTeamA = scoreTeamA -1;
        if (scoreTeamA<0) {
            scoreTeamA=0;
        }
        displayForTeamA(scoreTeamA);
    }

    public void displayForTeamB(int score) {
        TextView scoreView = (TextView) findViewById(R.id.team_b_score);
        scoreView.setText(String.valueOf(score));
    }

    public void addOneForTeamB(View v) {
        scoreTeamB = scoreTeamB +1;
        displayForTeamB(scoreTeamB);
    }
    public void restOneForTeamB(View v) {
        scoreTeamB = scoreTeamB -1;
        if (scoreTeamB<0) {
            scoreTeamB=0;
        }
        displayForTeamB(scoreTeamB);
    }


// OUTS
    public void displayForOuts(int outs) {
        TextView outsView = (TextView) findViewById(R.id.team_outs);
        outsView.setText(String.valueOf(outs));
    }

    public void addOneOut(View v) {
        outs = outs +1;
        strikes=0;
        balls=0;
        if (outs>2) {
            Toast.makeText(MainActivity.this,
                    "Inning has finish - Resetting Values", Toast.LENGTH_SHORT).show();
            outs=3;
            Handler handler = new Handler();
            handler.postDelayed(new Runnable() {

                @Override
                public void run() {
                    // change image
                    outs=0;
                    balls=0;
                    strikes=0;

                    displayForOuts(outs);
                    displayForBalls(balls);
                    displayForStrikes(strikes);
                }

            }, 5000); // 5000ms delay
        }
        displayForOuts(outs);
        displayForBalls(balls);
        displayForStrikes(strikes);
    }
    public void restOneOut(View v) {
        outs = outs -1;
        if (outs<0) {
            outs=0;
        }
        displayForOuts(outs);
    }

//STRIKES
    public void displayForStrikes(int strikes) {
        TextView strikesView = (TextView) findViewById(R.id.team_strikes);
        strikesView.setText(String.valueOf(strikes));
    }

    public void addOneStrike(View v) {
        strikes = strikes +1;
        if (strikes>2) {
            Toast.makeText(MainActivity.this,
                    "Strike 3, Batter is Out", Toast.LENGTH_SHORT).show();
            outs=outs+1;
            balls=0;
            strikes=0;
            if (outs>2) {
                Toast.makeText(MainActivity.this,
                        "Inning has finish - Resetting Values", Toast.LENGTH_SHORT).show();
                outs=3;
                Handler handler = new Handler();
                handler.postDelayed(new Runnable() {

                    @Override
                    public void run() {
                        // change image
                        outs=0;
                        balls=0;
                        strikes=0;

                        displayForOuts(outs);
                        displayForBalls(balls);
                        displayForStrikes(strikes);
                    }

                }, 5000); // 5000ms delay
            }
            displayForOuts(outs);
            displayForBalls(balls);
            displayForStrikes(strikes);
        }
        displayForStrikes(strikes);

    }
    public void restOneStrike(View v) {
        strikes = strikes -1;
        if (strikes<0) {
            strikes=0;
        }
        displayForStrikes(strikes);
    }


    //BALLS
    public void displayForBalls(int balls) {
        TextView ballsView = (TextView) findViewById(R.id.team_balls);
        ballsView.setText(String.valueOf(balls));
    }

    public void addOneBall(View v) {
        balls = balls +1;
        if (balls==4) {
            Toast.makeText(MainActivity.this,
                    "Ball 4, Batter going to 1st Base", Toast.LENGTH_SHORT).show();

            strikes=0;
            balls=0;
            displayForStrikes(strikes);
        }
        displayForBalls(balls);

    }
    public void restOneBall(View v) {
        balls = balls -1;
        if (balls<0) {
            balls=0;
        }
        displayForBalls(balls);
    }

    //RESET
    public void reset(View v) {
        scoreTeamA = 0;
        scoreTeamB = 0;
        outs=0;
        balls=0;
        strikes=0;
        displayForTeamA(scoreTeamA);
        displayForTeamB(scoreTeamB);
        displayForOuts(outs);
        displayForBalls(balls);
        displayForStrikes(strikes);
    }
}
