package vvv.xogame;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    String player1;
    String player2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void startGame(View view) {

        Intent intent = new Intent(this, BoardActivity.class);

        EditText player1Text = (EditText) findViewById(R.id.player1);
        EditText player2Text = (EditText) findViewById(R.id.player2);

        if(player1Text.getText().toString().trim().isEmpty() ||
                player2Text.getText().toString().trim().isEmpty()){

            Toast.makeText(this, "Player 1 or Player 2 name is invalid", Toast.LENGTH_SHORT).show();

        } else {

            player1 = player1Text.getText().toString().trim();
            player2 = player2Text.getText().toString().trim();

            intent.putExtra("PLAYER1", player1);
            intent.putExtra("PLAYER2", player2);

            startActivity(intent);

            //aaa

        }
    }
}
