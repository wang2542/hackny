package edu.purdue.wang2542.hackny;

import android.content.Context;
import android.media.MediaPlayer;

/**
 * Created by jingzhouwang on 16/4/3.
 */
public class TicTacToe {


    TicTacToeViewInterface view; //The view interface used to make modifications to the view
    char[][] board; //The 3x3 board represented with characters
    char currentPlayer; //Keeps track of whose turn it is currently
    int cellsOccupied; //Keeps track of how many cells on the board are occupied
    char winner; //Keeps track of who won
    boolean PvE;
    Context a;
    MediaPlayer round;

    private static final char CROSS = 'X';
    private static final char CIRCLE = 'O';
    private static final char EMPTY = ' ';


    /**
     * Constructor. Initializes the instance variables.
     */
    public TicTacToe(TicTacToeViewInterface view, boolean PvE, Context a) {
        //TODO: Initialize instance variables.
        this.view = view;
        cellsOccupied = 0;
        this.currentPlayer = CIRCLE;
        board = new char[3][3];
        winner = 0;
        this.PvE = PvE;
        this.a = a;

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                board[i][j] = EMPTY;
            }
        }


        round = MediaPlayer.create(a, R.raw.round);
        round.start();
        round.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                mp.release();
            }
        });
    }

    /**
     * This function is called to start a new game.
     */
    public void newGame() {
        //TODO: Complete this method.
        //Hint : Call reset(). That's it :)
        reset();


    }

    /**
     * This function should reset each button on the grid.
     */
    public void reset() {
        //TODO: Complete this method.
        //Every time a new game begins, make sure to reset the view as well
        //as the board and the other instance variables.
        this.board = new char[3][3];
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                board[i][j] = EMPTY;
            }
        }
        cellsOccupied = 0;
        this.currentPlayer = CIRCLE;
        winner = 0;
        view.resetButtons();


    }

    /**
     * This function checks if the given player has won the game or not. The checks have to be made in each column, row, forward and backward diagonals. If a player has won, use a message alert box, toast, etc. to print the winner.
     */
    public boolean checkWinner(char player) {

        //TODO: Complete this method.
        //Iterate through the board matrix and check if you find a sequence of winning patters and set winner to the 'X' or 'O' accordingly. Further, return true if there is a winning pattern.
        // 妯潃鐨勪笁鎺
        if (board[0][0] == player && board[0][1] == player && board[0][2] == player) {
            winner = player;
            return true;
        } else if (board[1][0] == player && board[1][1] == player && board[1][2] == player) {
            winner = player;
            return true;
        } else if (board[2][0] == player && board[2][1] == player && board[2][2] == player) {
            winner = player;
            return true;
        }
        //绔栫潃涓夋帓
        else if (board[0][0] == player && board[1][0] == player && board[2][0] == player) {
            winner = player;
            return true;
        } else if (board[0][1] == player && board[1][1] == player && board[2][1] == player) {
            winner = player;
            return true;
        } else if (board[0][2] == player && board[1][2] == player && board[2][2] == player) {
            winner = player;
            return true;
        }
        //鏂滅潃鐨
        else if (board[0][0] == player && board[1][1] == player && board[2][2] == player) {
            winner = player;
            return true;
        } else if (board[0][2] == player && board[1][1] == player && board[2][0] == player) {
            winner = player;
            return true;
        } else {
            return false;
        }
    }


    /**
     * This function updates the game board whenever a user clicks on any of the game buttons, inside this function you need to call checkWinner to check if a player has won the last click on the board
     */
    public void updateGameBoard(int x, int y) {
        if (checkEmpty(board, x, y)) {
            if (winner != 0) {
                return;
            }
            //TODO: Complete this method.
            //Step 1: Check if current player is a winner in which case, you'd not do further updates.
            if (checkWinner(currentPlayer)) {
                winner = currentPlayer;
                view.disableButtons();
                view.showWinner(winner + "");
                return;
            }
            //Step 2: Check if the count of cellsOccupied is 9, in which case the game would be over. You can perhaps make an additional method void gameOver() to handle this scenario and call it here.
            if (cellsOccupied == 9) {
                winner = currentPlayer;
                view.disableButtons();
                view.showWinner("1");
                return;
            }
            //Step 3: Update the view by setting the text of button at position (x,y) to the current player's symbol. Call the update method of the view object to do so.
            view.update(x, y, currentPlayer);
            //Step 4: Update instance variables of this class as needed
            this.board[x][y] = currentPlayer;
            cellsOccupied++;


            if (checkWinner(currentPlayer)) {
                winner = currentPlayer;
                view.disableButtons();
                view.showWinner(winner + "");
                return;
            }
            if (cellsOccupied == 9) {
                winner = currentPlayer;
                view.disableButtons();
                view.showWinner("1");
                return;
            }

            if (PvE == false) {
                //Step 5: If current player is X, change it to O and vice-versa.
                if (currentPlayer == CROSS) currentPlayer = CIRCLE;
                else if (currentPlayer == CIRCLE) currentPlayer = CROSS;
                //Step 6: Check again if the current player is a winner
            } else {
                // 这里要写 AI的方法
                AI(board);
                if (checkWinner(CROSS)) {
                    winner = CROSS;
                    view.disableButtons();
                    view.showWinner(winner + "");
                    return;
                }

            }
        } else {
            return;
        }
    }


    public boolean AI(char[][] board) {


        // 第一优先级
        if (board[1][1] == EMPTY) {
            view.update(1, 1, CROSS);
            board[1][1] = CROSS;
            cellsOccupied++;
            return true;
        }

        if (CheckWin(board)) return true;

        if (CheckCondition(board)) return true;

        if (specialmove3(board)) return true;

        if (specialmove1(board)) return true;

        if (specialmove2(board)) return true;

        // 第二优先级
        if (board[0][0] == EMPTY || board[0][2] == EMPTY ||
                board[2][0] == EMPTY || board[2][2] == EMPTY) {
            if (board[0][0] == EMPTY) {
                view.update(0, 0, CROSS);
                board[0][0] = CROSS;
                cellsOccupied++;
                return true;
            } else if (board[0][2] == EMPTY) {
                view.update(0, 2, CROSS);
                board[0][2] = CROSS;
                cellsOccupied++;
                return true;
            } else if (board[2][0] == EMPTY) {
                view.update(2, 0, CROSS);
                board[2][0] = CROSS;
                cellsOccupied++;
                return true;
            } else if (board[2][2] == EMPTY) {
                view.update(2, 2, CROSS);
                board[2][2] = CROSS;
                cellsOccupied++;
                return true;
            }
        }

        if (CheckWin(board)) return true;

        if (CheckCondition(board)) return true;

        if (specialmove1(board)) return true;

        if (specialmove2(board)) return true;




        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board[i][j] == EMPTY) {
                    view.update(i, j, CROSS);
                    board[i][j] = CROSS;
                    cellsOccupied++;
                    return true;
                }
            }
        }

        if (CheckWin(board)) return true;

        if (CheckCondition(board)) return true;

        if (specialmove1(board)) return true;

        if (specialmove2(board)) return true;




        return false;
    }

    public boolean specialmove3(char[][] board) {
        char corner1 = board[0][0];
        char corner2 = board[0][2];
        char corner3 = board[2][0];
        char corner4 = board[2][2];

        if (board[1][2] == CIRCLE && board[2][1] == CIRCLE && corner1 == EMPTY && corner4 == EMPTY) {
            view.update(2, 2, CROSS);
            board[2][2] = CROSS;
            cellsOccupied++;
            return true;
        }
        return true;
    }

    public boolean specialmove2(char[][] board) {
        char corner1 = board[0][0];
        char corner2 = board[0][2];
        char corner3 = board[2][0];
        char corner4 = board[2][2];
        if (corner1 == CIRCLE && board[1][0] == EMPTY && corner3 == EMPTY) {
            if (corner4 == EMPTY && board[2][1] == CIRCLE) {
                view.update(2, 0, CROSS);
                board[2][0] = CROSS;
                cellsOccupied++;
                return true;
            } else if (corner4 == CIRCLE && board[2][1] == EMPTY) {
                view.update(2, 1, CROSS);
                board[2][1] = CROSS;
                cellsOccupied++;
                return true;
            }
        }
        if (corner1 == CIRCLE && board[0][1] == EMPTY && corner2 == EMPTY) {
            if (corner4 == EMPTY && board[1][2] == CIRCLE) {
                view.update(0, 2, CROSS);
                board[0][2] = CROSS;
                cellsOccupied++;
                return true;
            } else if (corner4 == CIRCLE && board[1][2] == EMPTY) {
                view.update(1, 2, CROSS);
                board[1][2] = CROSS;
                cellsOccupied++;
                return true;
            }
        }
        if (corner3 == CIRCLE && board[2][1] == EMPTY && corner4 == EMPTY) {
            if (corner2 == EMPTY && board[1][2] == CIRCLE) {
                view.update(2, 2, CROSS);
                board[2][2] = CROSS;
                cellsOccupied++;
                return true;
            } else if (corner2 == CIRCLE && board[1][2] == EMPTY) {
                view.update(1, 2, CROSS);
                board[1][2] = CROSS;
                cellsOccupied++;
                return true;
            }
        }
        if (corner3 == CIRCLE && board[1][0] == EMPTY && corner1 == EMPTY) {
            if (corner2 == EMPTY && board[0][1] == CIRCLE) {
                view.update(0, 0, CROSS);
                board[0][0] = CROSS;
                cellsOccupied++;
                return true;
            } else if (corner2 == CIRCLE && board[0][1] == EMPTY) {
                view.update(0, 1, CROSS);
                board[0][1] = CROSS;
                cellsOccupied++;
                return true;
            }
        }
        if (corner2 == CIRCLE && board[0][1] == EMPTY && corner1 == EMPTY) {
            if (corner3 == EMPTY && board[1][0] == CIRCLE) {
                view.update(0, 0, CROSS);
                board[0][0] = CROSS;
                cellsOccupied++;
                return true;
            } else if (corner3 == CIRCLE && board[1][0] == EMPTY) {
                view.update(1, 0, CROSS);
                board[1][0] = CROSS;
                cellsOccupied++;
                return true;
            }
        }
        if (corner2 == CIRCLE && board[1][2] == EMPTY && corner4 == EMPTY) {

            if (corner3 == EMPTY && board[2][1] == CIRCLE) {

                view.update(2, 2, CROSS);
                board[2][2] = CROSS;
                cellsOccupied++;
                return true;
            } else if (corner3 == CIRCLE && board[2][1] == EMPTY) {
                view.update(2, 1, CROSS);
                board[2][1] = CROSS;
                cellsOccupied++;
                return true;
            }
        }
        if (corner4 == CIRCLE && board[1][2] == EMPTY && corner2 == EMPTY) {
            if (corner1 == EMPTY && board[0][1] == CIRCLE) {
                view.update(0, 2, CROSS);
                board[0][2] = CROSS;
                cellsOccupied++;
                return true;
            } else if (corner1 == CIRCLE && board[0][1] == EMPTY) {
                view.update(0, 1, CROSS);
                board[0][1] = CROSS;
                cellsOccupied++;
                return true;
            }
        }
        if (corner4 == CIRCLE && board[2][1] == EMPTY && corner3 == EMPTY) {
            if (corner1 == EMPTY && board[1][0] == CIRCLE) {
                view.update(2, 0, CROSS);
                board[2][0] = CROSS;
                cellsOccupied++;
                return true;
            } else if (corner1 == CIRCLE && board[1][0] == EMPTY) {
                view.update(1, 0, CROSS);
                board[1][0] = CROSS;
                cellsOccupied++;
                return true;
            }
        }

        return false;
    }


    public boolean specialmove1(char[][] board) {
        char corner1 = board[0][0];
        char corner2 = board[0][2];
        char corner3 = board[2][0];
        char corner4 = board[2][2];
        if (corner1 == CIRCLE && corner4 == CIRCLE && corner2 == EMPTY && corner3 == EMPTY) {
            if (CheckCondition(board)) return true;

            view.update(2, 1, CROSS);
            board[2][1] = CROSS;
            cellsOccupied++;
            return true;

        } else if (corner2 == CIRCLE && corner3 == CIRCLE && corner1 == EMPTY && corner4 == EMPTY) {
            if (CheckCondition(board)) return true;

            view.update(1, 2, CROSS);
            board[1][2] = CROSS;
            cellsOccupied++;
            return true;

        }
        return false;
    }

    public boolean CheckCondition(char[][] board) {

        // 横排
        for (int i = 0; i < 3; i++) {
            int count = 0;
            int circleNum = 0;
            int empty = 0;
            for (int j = 0; j < 3; j++) {

                if (board[i][j] != EMPTY) count++;
                if (board[i][j] == CIRCLE) {
                    circleNum++;
                } else {
                    empty = j;
                }
            }
            if (count == 3) {
            } else if (circleNum == 2) {
                view.update(i, empty, CROSS);
                board[i][empty] = CROSS;
                cellsOccupied++;
                return true;
            }

        }

        // 竖排
        for (int i = 0; i < 3; i++) {
            int count = 0;
            int circleNum = 0;
            int empty = 0;
            for (int j = 0; j < 3; j++) {

                if (board[j][i] != EMPTY) count++;
                if (board[j][i] == CIRCLE) {
                    circleNum++;
                } else {
                    empty = j;
                }
            }
            if (count == 3) {
            } else if (circleNum == 2) {
                view.update(empty, i, CROSS);
                board[empty][i] = CROSS;
                cellsOccupied++;
                return true;
            }
        }

        // 对角线
        int count = 0;
        int circleNum = 0;
        int empty = 0;
        if (board[0][0] != EMPTY) count++;
        if (board[0][0] == CIRCLE) circleNum++;
        else {
            empty = 0;
        }
        if (board[1][1] != EMPTY) count++;
        if (board[1][1] == CIRCLE) circleNum++;
        else {
            empty = 1;
        }
        if (board[2][2] != EMPTY) count++;
        if (board[2][2] == CIRCLE) circleNum++;
        else {
            empty = 2;
        }

        if (count == 3) {

        } else if (circleNum == 2) {
            view.update(empty, empty, CROSS);
            board[empty][empty] = CROSS;
            cellsOccupied++;
            return true;
        }

        int count2 = 0;
        int circleNum2 = 0;
        int emptyX = 0;
        int emptyY = 0;

        if (board[0][2] != EMPTY) count2++;
        if (board[0][2] == CIRCLE) circleNum2++;
        else {
            emptyX = 0;
            emptyY = 2;
        }

        if (board[1][1] != EMPTY) count2++;
        if (board[1][1] == CIRCLE) circleNum2++;
        else {
            emptyX = 1;
            emptyY = 1;
        }

        if (board[2][0] != EMPTY) count2++;
        if (board[2][0] == CIRCLE) circleNum2++;
        else {
            emptyX = 2;
            emptyY = 0;
        }

        if (count2 == 3) {

        } else if (circleNum2 == 2) {
            view.update(emptyX, emptyY, CROSS);
            board[emptyX][emptyY] = CROSS;
            cellsOccupied++;
            return true;
        }
        return false;
    }

    public boolean CheckWin(char[][] board) {

        // 横排
        for (int i = 0; i < 3; i++) {
            int count = 0;
            int circleNum = 0;
            int empty = 0;
            for (int j = 0; j < 3; j++) {

                if (board[i][j] != EMPTY) count++;
                if (board[i][j] == CROSS) {
                    circleNum++;
                } else {
                    empty = j;
                }
            }
            if (count == 3) {
            } else if (circleNum == 2) {
                view.update(i, empty, CROSS);
                board[i][empty] = CROSS;
                cellsOccupied++;
                return true;
            }

        }

        // 竖排
        for (int i = 0; i < 3; i++) {
            int count = 0;
            int circleNum = 0;
            int empty = 0;
            for (int j = 0; j < 3; j++) {

                if (board[j][i] != EMPTY) count++;
                if (board[j][i] == CROSS) {
                    circleNum++;
                } else {
                    empty = j;
                }
            }
            if (count == 3) {
            } else if (circleNum == 2) {
                view.update(empty, i, CROSS);
                board[empty][i] = CROSS;
                cellsOccupied++;
                return true;
            }
        }

        // 对角线
        int count = 0;
        int circleNum = 0;
        int empty = 0;
        if (board[0][0] != EMPTY) count++;
        if (board[0][0] == CROSS) circleNum++;
        else {
            empty = 0;
        }
        if (board[1][1] != EMPTY) count++;
        if (board[1][1] == CROSS) circleNum++;
        else {
            empty = 1;
        }
        if (board[2][2] != EMPTY) count++;
        if (board[2][2] == CROSS) circleNum++;
        else {
            empty = 2;
        }

        if (count == 3) {

        } else if (circleNum == 2) {
            view.update(empty, empty, CROSS);
            board[empty][empty] = CROSS;
            cellsOccupied++;
            return true;
        }

        int count2 = 0;
        int circleNum2 = 0;
        int emptyX = 0;
        int emptyY = 0;

        if (board[0][2] != EMPTY) count2++;
        if (board[0][2] == CROSS) circleNum2++;
        else {
            emptyX = 0;
            emptyY = 2;
        }

        if (board[1][1] != EMPTY) count2++;
        if (board[1][1] == CROSS) circleNum2++;
        else {
            emptyX = 1;
            emptyY = 1;
        }

        if (board[2][0] != EMPTY) count2++;
        if (board[2][0] == CROSS) circleNum2++;
        else {
            emptyX = 2;
            emptyY = 0;
        }

        if (count2 == 3) {

        } else if (circleNum2 == 2) {
            view.update(emptyX, emptyY, CROSS);
            board[emptyX][emptyY] = CROSS;
            cellsOccupied++;
            return true;
        }
        return false;
    }

    public boolean checkEmpty(char[][] b, int x, int y) {
        if (b[x][y] == EMPTY) {
            return true;
        } else {
            return false;
        }

    }
}