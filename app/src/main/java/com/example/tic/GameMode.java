package com.example.tic;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class GameMode extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_mode); // Set the layout for the GameMode screen

        // Initialize buttons for selecting game mode
        Button pvpButton = findViewById(R.id.pvpButton); // Button for Player vs Player mode
        Button pvcButton = findViewById(R.id.pvcButton); // Button for Player vs Computer mode

        // Handle Player vs Player button click
        pvpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Start the AddPlayers activity to input player names
                Intent intent = new Intent(GameMode.this, AddPlayers.class);
                startActivity(intent);
            }
        });

        // Handle Player vs Computer button click
        pvcButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Start the PvCMode activity (player vs computer mode)
                Intent intent = new Intent(GameMode.this, PvCMode.class);
                startActivity(intent);
            }
        });

    }
}
