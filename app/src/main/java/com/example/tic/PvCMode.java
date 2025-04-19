package com.example.tic;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.Button;
import com.example.tic.databinding.ActivityMainBinding;
import java.util.ArrayList;
import java.util.List;

public class PvCMode extends AppCompatActivity {

    ActivityMainBinding binding;

    // All possible winning combinations
    private final List<int[]> combinationList = new ArrayList<>();

    // Represents the game board; 0 = empty, 1 = player, 2 = computer
    private int[] boxPositions = {0,0,0,0,0,0,0,0,0};

    // Indicates whose turn it is (1 = player, 2 = computer)
    private int playerTurn = 1;

    // Counter for total number of moves made
    private int totalSelectedBoxes = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // Back button to return to previous screen
        Button backButton = findViewById(R.id.backButton);
        binding.backButton.setOnClickListener(v -> finish());

        // Initialize win combinations (rows, columns, diagonals)
        combinationList.add(new int[] {0,1,2});
        combinationList.add(new int[] {3,4,5});
        combinationList.add(new int[] {6,7,8});
        combinationList.add(new int[] {0,3,6});
        combinationList.add(new int[] {1,4,7});
        combinationList.add(new int[] {2,5,8});
        combinationList.add(new int[] {2,4,6});
        combinationList.add(new int[] {0,4,8});

        // Set player names
        String getPlayerOneName = "Player";
        String getComputerName = "Computer";
        binding.playerOneName.setText(getPlayerOneName);
        binding.playerTwoName.setText(getComputerName);

        // Set click listeners for each board cell
        binding.image1.setOnClickListener(v -> onCellClicked((ImageView) v, 0));
        binding.image2.setOnClickListener(v -> onCellClicked((ImageView) v, 1));
        binding.image3.setOnClickListener(v -> onCellClicked((ImageView) v, 2));
        binding.image4.setOnClickListener(v -> onCellClicked((ImageView) v, 3));
        binding.image5.setOnClickListener(v -> onCellClicked((ImageView) v, 4));
        binding.image6.setOnClickListener(v -> onCellClicked((ImageView) v, 5));
        binding.image7.setOnClickListener(v -> onCellClicked((ImageView) v, 6));
        binding.image8.setOnClickListener(v -> onCellClicked((ImageView) v, 7));
        binding.image9.setOnClickListener(v -> onCellClicked((ImageView) v, 8));
    }

    // Handle player click on a cell
    private void onCellClicked(ImageView imageView, int position) {
        if (isBoxSelectable(position)) {
            performAction(imageView, position);
        }
    }

    // Performs action when a player (human or computer) selects a cell
    private void performAction(ImageView imageView, int selectedBoxPosition) {
        boxPositions[selectedBoxPosition] = playerTurn;

        if (playerTurn == 1) { // Player
            imageView.setImageResource(R.drawable.ximage);
            if (checkResults()) {
                showResult(binding.playerOneName.getText().toString() + " is a Winner!");
            } else if (totalSelectedBoxes == 9) {
                showResult("Match Draw");
            } else {
                changePlayerTurn(2);
                totalSelectedBoxes++;
                computerMove(); // Let the AI play
            }
        } else { // Computer
            imageView.setImageResource(R.drawable.oimage);
            if (checkResults()) {
                showResult(binding.playerTwoName.getText().toString() + " is a Winner!");
            } else if (totalSelectedBoxes == 9) {
                showResult("Match Draw");
            } else {
                changePlayerTurn(1);
                totalSelectedBoxes++;
            }
        }
    }

    // Switch turn between player and computer, update UI background highlight
    private void changePlayerTurn(int currentPlayerTurn) {
        playerTurn = currentPlayerTurn;
        if (playerTurn == 1) {
            binding.playerOneLayout.setBackgroundResource(R.drawable.black_border);
            binding.playerTwoLayout.setBackgroundResource(R.drawable.white_box);
        } else {
            binding.playerTwoLayout.setBackgroundResource(R.drawable.black_border);
            binding.playerOneLayout.setBackgroundResource(R.drawable.white_box);
        }
    }

    // Checks for win condition
    private boolean checkResults() {
        for (int[] combination : combinationList) {
            if (boxPositions[combination[0]] == playerTurn &&
                    boxPositions[combination[1]] == playerTurn &&
                    boxPositions[combination[2]] == playerTurn) {
                return true;
            }
        }
        return false;
    }

    // Check if a box is available
    private boolean isBoxSelectable(int boxPosition) {
        return boxPositions[boxPosition] == 0;
    }

    // Reset the board for a new match
    public void restartMatch() {
        boxPositions = new int[] {0,0,0,0,0,0,0,0,0};
        playerTurn = 1;
        totalSelectedBoxes = 1;

        binding.image1.setImageResource(R.drawable.white_box);
        binding.image2.setImageResource(R.drawable.white_box);
        binding.image3.setImageResource(R.drawable.white_box);
        binding.image4.setImageResource(R.drawable.white_box);
        binding.image5.setImageResource(R.drawable.white_box);
        binding.image6.setImageResource(R.drawable.white_box);
        binding.image7.setImageResource(R.drawable.white_box);
        binding.image8.setImageResource(R.drawable.white_box);
        binding.image9.setImageResource(R.drawable.white_box);
    }

    // Uses the Minimax algorithm to calculate and perform the AI's move
    private void computerMove() {
        int bestMove = Minimax.getBestMove(boxPositions);
        if (bestMove != -1) {
            ImageView selectedImage = getImageViewByPosition(bestMove);
            performAction(selectedImage, bestMove);
        }
    }

    // Maps a board position to its corresponding ImageView
    private ImageView getImageViewByPosition(int position) {
        switch (position) {
            case 0: return binding.image1;
            case 1: return binding.image2;
            case 2: return binding.image3;
            case 3: return binding.image4;
            case 4: return binding.image5;
            case 5: return binding.image6;
            case 6: return binding.image7;
            case 7: return binding.image8;
            case 8: return binding.image9;
            default: return null;
        }
    }

    // Shows the result dialog
    private void showResult(String message) {
        ResultDialogPvC resultDialog = new ResultDialogPvC(PvCMode.this, message, PvCMode.this);
        resultDialog.setCancelable(false);
        resultDialog.show();
    }
}
