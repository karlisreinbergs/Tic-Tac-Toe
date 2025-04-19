package com.example.tic;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Minimax {

    /**
     * Determines the best move for the computer using the Minimax algorithm.
     * Occasionally returns a random move (20% of the time) to make it easier.
     *
     * @param board The current game board represented as an array.
     *              0 = empty, 1 = player, 2 = computer.
     * @return The index of the best move.
     *
     * chatgpt was used for the making of the minmax algorithm, the prompt was: help me make a minmax
     * algorithm that works with this code (PvCmode.java)
     */
    public static int getBestMove(int[] board) {
        Random rand = new Random();

        // 20% chance to make a random move to simulate human-like imperfection
        if (rand.nextDouble() < 0.2) {
            List<Integer> availableMoves = new ArrayList<>();
            for (int i = 0; i < board.length; i++) {
                if (board[i] == 0) { // Find all empty spots
                    availableMoves.add(i);
                }
            }

            // If there are any available moves, pick one at random
            if (!availableMoves.isEmpty()) {
                return availableMoves.get(rand.nextInt(availableMoves.size()));
            }
        }

        // Otherwise, use the Minimax algorithm to choose the optimal move
        int bestScore = Integer.MIN_VALUE;
        int bestMove = -1;

        for (int i = 0; i < board.length; i++) {
            if (board[i] == 0) {
                board[i] = 2; // Try computer's move
                int score = minimax(board, false); // Simulate player's response
                board[i] = 0; // Undo move

                if (score > bestScore) {
                    bestScore = score;
                    bestMove = i;
                }
            }
        }

        return bestMove;
    }

    /**
     * Minimax recursive function to evaluate the board and find the best move.
     *
     * @param board        The current board state.
     * @param isMaximizing True if the current player is the computer (maximizing).
     * @return The evaluation score.
     */
    private static int minimax(int[] board, boolean isMaximizing) {
        // Check if the game is over and return the score
        Integer result = evaluateBoard(board);
        if (result != null) {
            return result;
        }

        if (isMaximizing) {
            int bestScore = Integer.MIN_VALUE;
            // Try all possible moves for the computer (2)
            for (int i = 0; i < board.length; i++) {
                if (board[i] == 0) {
                    board[i] = 2;
                    int score = minimax(board, false); // Simulate player's turn
                    board[i] = 0; // Undo move
                    bestScore = Math.max(score, bestScore);
                }
            }
            return bestScore;
        } else {
            int bestScore = Integer.MAX_VALUE;
            // Try all possible moves for the player (1)
            for (int i = 0; i < board.length; i++) {
                if (board[i] == 0) {
                    board[i] = 1;
                    int score = minimax(board, true); // Simulate computer's turn
                    board[i] = 0; // Undo move
                    bestScore = Math.min(score, bestScore);
                }
            }
            return bestScore;
        }
    }

    /**
     * Evaluates the board to determine if there's a winner or a draw.
     *
     * @param board The current game board.
     * @return 100 if computer wins, -100 if player wins, 0 if draw, null if game is ongoing.
     */
    private static Integer evaluateBoard(int[] board) {
        // Define all possible win conditions (rows, columns, diagonals)
        int[][] winConditions = {
                {0, 1, 2}, {3, 4, 5}, {6, 7, 8}, // Rows
                {0, 3, 6}, {1, 4, 7}, {2, 5, 8}, // Columns
                {0, 4, 8}, {2, 4, 6}             // Diagonals
        };

        // Check for a win
        for (int[] condition : winConditions) {
            int a = condition[0], b = condition[1], c = condition[2];
            if (board[a] != 0 && board[a] == board[b] && board[b] == board[c]) {
                return (board[a] == 2) ? 100 : -100; // Computer wins = 100, Player wins = -100
            }
        }

        // Check for draw (no empty cells)
        boolean isDraw = true;
        for (int cell : board) {
            if (cell == 0) {
                isDraw = false;
                break;
            }
        }

        return isDraw ? 0 : null; // 0 if draw, null if game is still in progress
    }
}
