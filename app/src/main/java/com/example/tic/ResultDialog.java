package com.example.tic;

import androidx.annotation.NonNull;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

// Custom dialog class to display the result of the game
public class ResultDialog extends Dialog {

    // Message to be displayed (e.g., "Player 1 wins", "Match Draw")
    private final String message;

    // Reference to the PvPMode activity to allow restarting the match
    private final PvPMode mainActivity;

    // Constructor to initialize dialog with message and activity context
    public ResultDialog(@NonNull Context context, String message, PvPMode mainActivity) {
        super(context);
        this.message = message;
        this.mainActivity = mainActivity;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Set the layout for the dialog
        setContentView(R.layout.activity_result_dialog);

        // Reference to the message TextView and start again Button in the layout
        TextView messageText = findViewById(R.id.messageText);
        Button startAgainButton = findViewById(R.id.startAgainButton);

        // Set the message to display the result
        messageText.setText(message);

        // Restart the match when the button is clicked and dismiss the dialog
        startAgainButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mainActivity.restartMatch();  // Call restart method in PvPMode
                dismiss();  // Close the dialog
            }
        });
    }
}
