package edu.purdue.wang2542.hackny;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.widget.Button;
import android.widget.Toast;

/**
 * Created by jingzhouwang on 16/4/3.
 */
public class TicTacToeView implements TicTacToeViewInterface{
    Button[][] myButtons; //The 3x3 matrix of buttons
    Button newGameButton; //The "New Game" button
    Context a;
    int colour = 0;
    boolean PvE;
    MediaPlayer KO;

    /**
     * Constructor. Initializes the instance variables.
     */
    public TicTacToeView(Button[][] myButtons, Button newGameButton, Context a,
                         int colour, boolean PvE) {
        this.myButtons = myButtons;
        this.newGameButton = newGameButton;
        this.a = a;
        this.colour = colour;
        this.PvE = PvE;

    }

    @Override
    public void update(int x, int y, char player) {
        //TODO: Complete this method.
        //Step 1: Update the text of button at index (x, y) to the player's symbol. In order to convert a char to a String, you can do (charVariable + "")
        //Example usage : myButtons[x][y].setText("" + player);
        myButtons[x][y].setText("" + player);
        //Step 2: Disable the button at index (x, y) from being clicked further.
        //Example usage : myButtons[x][y].setEnabled(false);
        myButtons[x][y].setEnabled(false);
        //Step 3: Set text color of the button to BLUE for O or RED for X.
        //Example usage : myButtons[x][y].setTextColor(Color.BLUE);
        if (player == 'O') {
            if (colour == 0)
                myButtons[x][y].setTextColor(Color.BLUE);
            else if (colour == 1)
                myButtons[x][y].setTextColor(Color.WHITE);
            else if (colour == 2)
                myButtons[x][y].setTextColor(Color.BLACK);
            else if (colour == 3)
                myButtons[x][y].setTextColor(Color.RED);
            else if (colour == 4)
                myButtons[x][y].setTextColor(Color.CYAN);
            else if (colour == 5)
                myButtons[x][y].setTextColor(Color.GREEN);
            else if (colour == 6)
                myButtons[x][y].setTextColor(Color.MAGENTA);
        }
        if (player == 'X')
            myButtons[x][y].setTextColor(Color.RED);
    }

    @Override
    public void showWinner(String winner) {
        //NOT REQUIRED TO BE WRITTEN FOR THIS LAB

        if (winner.equals("X")) {
            Toast.makeText(a, "CROSS is win", Toast.LENGTH_LONG).show();
            if (PvE) {
                Intent win = new Intent(a, Loser.class);
                win.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                a.startActivity(win);
            } else {
                Intent win = new Intent(a, Winner2.class);
                win.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                a.startActivity(win);
            }
        } else if (winner.equals("O")) {
            Toast.makeText(a, "CIRCLE is win", Toast.LENGTH_LONG).show();


            Intent win = new Intent(a, Winner.class);
            win.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            a.startActivity(win);

        } else if (winner.equals("1")){
            Toast.makeText(a, "Draw", Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(a, "some thing wrong", Toast.LENGTH_LONG).show();
        }
        gameOver();
    }


    @Override
    public void resetButtons() {
        //TODO: Complete this method.
        //Step 1: Iterate through the myButton matrix.
        //Step 2: Reset the text of the current button.
        //Example usage : myButtons[i][j].setText("");
        //Step 3: Reset the color of text for the current button
        //Example usage : myButtons[x][y].setTextColor(Color.BLACK);
        //Step 4: Make the button clickable again
        //Example usage : myButtons[x][y].setEnabled(true);
        for (int i = 0; i < myButtons.length; i++) {
            for (int j = 0; j < myButtons[0].length; j++) {
                myButtons[i][j].setText("");
                myButtons[i][j].setTextColor(Color.BLACK);
                myButtons[i][j].setEnabled(true);
            }
        }


    }

    @Override
    public void disableButtons() {
        //TODO: Complete this method.
        //Step 1: Iterate through the myButton matrix.
        //Step 2: Make the current button un-clickable
        //Example usage : myButtons[x][y].setEnabled(false);
        for (int i = 0; i < myButtons.length; i++) {
            for (int j = 0; j < myButtons[0].length; j++) {

                myButtons[i][j].setEnabled(false);

            }
        }
    }

    @Override
    public void gameOver() {
        //NOT REQUIRED TO BE WRITTEN FOR THIS LAB

        disableButtons();


        KO = MediaPlayer.create(a, R.raw.ko);
        KO.start();

        KO.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                mp.release();
            }
        });

        Toast.makeText(a, "game over", Toast.LENGTH_LONG).show();

    }

}
