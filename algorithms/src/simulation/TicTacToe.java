package simulation;

public class TicTacToe {
    int board[][];
    int player;
    int n;
    boolean finished;
    static final int X = 1;
    static final int O = 2;
    /** Initialize your data structure here. */
    public TicTacToe() {
        n = 3;
        board = new int[n][n];
        player = X;
    }
    
    public boolean move(int row, int col) throws AlreadyTakenException, GameEndException {
        if(finished) {
            throw new GameEndException();
        }

        if(board[row][col] != 0) {
            throw new AlreadyTakenException();
        }

        board[row][col] = player;
        if(validRow(board, row)|| validCol(board, col) 
            || row == col && validDia(board)
            || col == n - 1 - row && validAntiDia(board)){
                finished = true;
                return true;
            }

        player = player == X ? O : X;
        return false;
    }

    private boolean validRow(int[][] board, int row){
        for(int i = 0; i < board.length; i++) {
            if(board[row][i] != player){
                return false;
            }
        }
        return true;
    }

    private boolean validCol(int[][] board, int col){
        for(int i = 0; i < board.length; i++) {
            if(board[i][col] != player){
                return false;
            }
        }
        return true;
    }

    private boolean validDia(int[][] board){
        for(int i = 0; i < board.length; i++) {
            if(board[i][i] != player){
                return false;
            }
        }
        return true;
    }

    private boolean validAntiDia(int[][] board){
        for(int i = 0; i < board.length; i++) {
            if(board[i][n - 1 - i] != player){
                return false;
            }
        }
        return true;
    }
}

class AlreadyTakenException extends Exception{

}

class GameEndException extends Exception{
    
}