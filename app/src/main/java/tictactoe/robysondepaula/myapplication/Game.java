package tictactoe.robysondepaula.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Game extends AppCompatActivity {


    private GameBoard  ticTacToeGame;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        Button playAgainBtn = findViewById(R.id.playAgainBtn);
        Button homeBtn = findViewById(R.id.homeScreenBtn);
        TextView playerTurn = findViewById(R.id.playerInfo);


        playAgainBtn.setVisibility(View.GONE);
        homeBtn.setVisibility(View.GONE);

        String[] playerNames = getIntent().getStringArrayExtra("Players");

        if(playerNames != null){
            playerTurn.setText((playerNames[0] + "Plays!"));
        }

        ticTacToeGame = findViewById(R.id.gameBoard3);


        ticTacToeGame.settingGameUp(playAgainBtn, homeBtn, playerTurn, playerNames);
    }

    public void playAgainButtonClick(View view){

        ticTacToeGame.resetGame();
        ticTacToeGame.invalidate();

    }

    public void homeScreenButtonClick(View view){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}