package tictactoe.robysondepaula.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class Players extends AppCompatActivity {

    
    private EditText player1;
    private EditText player2;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_players);

       player1 = findViewById(R.id.editTextTextPersonName);
       player2 = findViewById(R.id.editTextTextPersonName2);

    }

    public void submitButtonClick(View view){
        String Player1 = player1.getText().toString();
        String Player2 = player2.getText().toString();

        Intent intent = new Intent(this, Game.class);
        intent.putExtra("Players", new String[] {Player1, Player2});

        startActivity(intent);
    }
}