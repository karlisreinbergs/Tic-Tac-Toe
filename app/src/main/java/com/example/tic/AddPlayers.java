package com.example.tic;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class AddPlayers extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_players); // Set the layout for this activity

        // Initialize UI elements from the layout
        EditText playerOne = findViewById(R.id.playerOne);       // Input field for Player 1 name
        EditText playerTwo = findViewById(R.id.playerTwo);       // Input field for Player 2 name
        Button startGameButton = findViewById(R.id.startGameButton); // Button to start the game
        Button backButton = findViewById(R.id.backButton);           // Button to go back to the previous screen

        // Set click listener for the "Back" button
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish(); // Close this activity and return to the previous one
            }
        });

        // Set click listener for the "Start Game" button
        startGameButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // Get text input from both players
                String getPlayerOneName = playerOne.getText().toString();
                String getPlayerTwoName = playerTwo.getText().toString();

                // Check if either name is empty
                if (getPlayerOneName.isEmpty() || getPlayerTwoName.isEmpty()) {
                    Toast.makeText(AddPlayers.this, "Please enter player name", Toast.LENGTH_SHORT).show(); // Show error message
                } else {
                    // Start the PvPMode activity and pass the player names
                    Intent intent = new Intent(AddPlayers.this, PvPMode.class);
                    intent.putExtra("playerOne", getPlayerOneName);
                    intent.putExtra("playerTwo", getPlayerTwoName);
                    startActivity(intent); // Launch the game
                }
            }
        });

    }
}
