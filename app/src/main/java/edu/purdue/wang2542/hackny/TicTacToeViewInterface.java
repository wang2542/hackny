package edu.purdue.wang2542.hackny;

/**
 * Created by jingzhouwang on 16/4/3.
 */
public interface TicTacToeViewInterface {
    void update(int x, int y, char player); //Updates button at (x, y) with the player's symbol
    void showWinner(String winner); //Shows the winner
    void resetButtons(); //Resets all the buttons
    void disableButtons(); //Disables all the buttons from being clicked
    void gameOver(); //Disables any further actions in the game
}
