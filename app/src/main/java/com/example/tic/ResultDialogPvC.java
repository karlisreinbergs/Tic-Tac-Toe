package com.example.tic;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;

// Custom dialog for displaying game result in Player vs Computer mode
public class ResultDialogPvC extends Dialog {

    // The result message to display (e.g., "You Win!", "Computer Wins!", "Draw")
    private final String message;

    // Reference to the PvCMode activity to restart the game when needed
    private final PvCMode pvcMode;

    // Constructor to initialize the dialog with context, message, and activity reference
    public ResultDialogPvC(@NonNull Context context, String message, PvCMode pvcMode) {
        super(context);
        this.message = message;
        this.pvcMode = pvcMode;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Set the layout defined in activity_result_dialog.xml
        setContentView(R.layout.activity_result_dialog);

        // Get references to the TextView and Button from the layout
        TextView messageText = findViewById(R.id.messageText);
        Button startAgainButton = findViewById(R.id.startAgainButton);

        // Display the result message in the dialog
        messageText.setText(message);

        // Set a click listener for the "Start Again" button
        startAgainButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pvcMode.restartMatch(); // Restart the game in PvCMode
                dismiss();              // Close the dialog
            }
        });
    }
}
