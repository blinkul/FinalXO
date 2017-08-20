package vvv.xogame;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Arrays;

public class BoardActivity extends AppCompatActivity {

    Player player1;
    Player player2;

    Character markP1 = 'X';
    Character markP2 = 'O';

    boolean switchPlayer = true;
    boolean theEnd = false;

    int tappedLocation = 0;
    int tapped[] = {0, 0, 0, 0, 0, 0, 0, 0 ,0};
    final int tappedFull[] = {1, 1, 1, 1, 1, 1, 1, 1 ,1};
    final int tappedEmpty[] = {0, 0, 0, 0, 0, 0, 0, 0 ,0};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_board);

        Intent intent = getIntent();

        player1 = new Player(intent.getStringExtra("PLAYER1"), markP1);
        player2 = new Player(intent.getStringExtra("PLAYER2"), markP2);

        TextView playerPlaying = (TextView) findViewById(R.id.playerPlaying);
        playerPlaying.setText(player1.getName() + " is playing");

//        Toast.makeText(this, player1.getName() + " vs " +player2.getName() , Toast.LENGTH_SHORT).show();

    }

    /*
    image.animate().translationXBy(10f).setDuration(2000) //misca imaginea pe X cu 10 pixeli in 2 secunde
    image.animate().alpha(0f).setDuration(2000) // vizibilitatea este de la 0f la 1f
    image.animate().scaleX(0.5f).scaleY(0.5f).setDuration(2000)
     */


    public void click(View view) {
        TextView playerPlaying = (TextView) findViewById(R.id.playerPlaying);
        ImageView markView = (ImageView) view;
        Button restartBtn = (Button) findViewById(R.id.restart);

        int tappedLocation = Integer.parseInt(view.getTag().toString());

        if (tapped[tappedLocation] == 0) {
            markView.setScaleX(0f);
            markView.setScaleY(0f);

            if (switchPlayer) {

                markView.setImageResource(R.drawable.xxx);
                markView.animate().scaleX(1f).scaleY(1f).setDuration(500);
                tapped[tappedLocation] = 1;
                player1.addMark(tappedLocation);
                playerPlaying.setText(player2.getName() + " is playing");
                switchPlayer = false;

            } else {

                markView.setImageResource(R.drawable.o);
                markView.animate().scaleX(1f).scaleY(1f).setDuration(500);
                tapped[tappedLocation] = 1;
                playerPlaying.setText(player1.getName() + " is playing");
                player2.addMark(tappedLocation);
                switchPlayer = true;

            }

            if(isWinning(player1.getPlayerBoard())){
                playerPlaying.setText(player1.getName() + " WIN!");
                isClickable(false);
                theEnd=true;

            } else if(isWinning(player2.getPlayerBoard())){
                playerPlaying.setText(player2.getName() + " WIN!");
                isClickable(false);
                theEnd=true;

            } else if (Arrays.equals(tapped, tappedFull)){
                restartBtn.setVisibility(View.VISIBLE);
                playerPlaying.setText("DRAW");
            }

            if(theEnd) {
                restartBtn.setVisibility(View.VISIBLE);
                Log.i("THE END", theEnd + "");
            }

        } else {
            Log.i("TAPPED", "Location was already tapped:" + tappedLocation);
        }

    }

    public void restart(View view) {
        Button restartBtn = (Button) findViewById(R.id.restart);
        restartBtn.setVisibility(View.INVISIBLE);

        TextView playerPlaying = (TextView) findViewById(R.id.playerPlaying);
        playerPlaying.setText(player1.getName());

        player1.refreshBoard();
        player2.refreshBoard();

        tapped = tappedEmpty.clone();

        ImageView A0 = (ImageView) findViewById(R.id.A0);
        ImageView A1 = (ImageView) findViewById(R.id.A1);
        ImageView A2 = (ImageView) findViewById(R.id.A2);
        ImageView B0 = (ImageView) findViewById(R.id.B0);
        ImageView B1 = (ImageView) findViewById(R.id.B1);
        ImageView B2 = (ImageView) findViewById(R.id.B2);
        ImageView C0 = (ImageView) findViewById(R.id.C0);
        ImageView C1 = (ImageView) findViewById(R.id.C1);
        ImageView C2 = (ImageView) findViewById(R.id.C2);

        A0.setImageDrawable(null);
        A1.setImageDrawable(null);
        A2.setImageDrawable(null);
        B0.setImageDrawable(null);
        B1.setImageDrawable(null);
        B2.setImageDrawable(null);
        C0.setImageDrawable(null);
        C1.setImageDrawable(null);
        C2.setImageDrawable(null);

        isClickable(true);
        switchPlayer = true;
        theEnd=false;

    }

    private boolean isWinning(int[] playerBoard){
        boolean status = false;

        // 0 1 2
        // 3 4 5
        // 6 7 8

        if(playerBoard[0]==1 && playerBoard[1]==1 && playerBoard[2]==1){
            status = true;
        }
        if(playerBoard[3]==1 && playerBoard[4]==1 && playerBoard[5]==1){
            status = true;
        }
        if(playerBoard[6]==1 && playerBoard[7]==1 && playerBoard[8]==1){
            status = true;
        }

        if(playerBoard[0]==1 && playerBoard[3]==1 && playerBoard[6]==1){
            status = true;
        }
        if(playerBoard[1]==1 && playerBoard[4]==1 && playerBoard[7]==1){
            status = true;
        }
        if(playerBoard[2]==1 && playerBoard[5]==1 && playerBoard[8]==1){
            status = true;
        }

        if(playerBoard[0]==1 && playerBoard[4]==1 && playerBoard[8]==1){
            status = true;
        }
        if(playerBoard[2]==1 && playerBoard[4]==1 && playerBoard[6]==1){
            status = true;
        }

        return status;
    }

    private void isClickable(Boolean status){
        ImageView A0 = (ImageView) findViewById(R.id.A0);
        ImageView A1 = (ImageView) findViewById(R.id.A1);
        ImageView A2 = (ImageView) findViewById(R.id.A2);
        ImageView B0 = (ImageView) findViewById(R.id.B0);
        ImageView B1 = (ImageView) findViewById(R.id.B1);
        ImageView B2 = (ImageView) findViewById(R.id.B2);
        ImageView C0 = (ImageView) findViewById(R.id.C0);
        ImageView C1 = (ImageView) findViewById(R.id.C1);
        ImageView C2 = (ImageView) findViewById(R.id.C2);

        A0.setClickable(status);
        A1.setClickable(status);
        A2.setClickable(status);
        B0.setClickable(status);
        B1.setClickable(status);
        B2.setClickable(status);
        C0.setClickable(status);
        C1.setClickable(status);
        C2.setClickable(status);
    }

}