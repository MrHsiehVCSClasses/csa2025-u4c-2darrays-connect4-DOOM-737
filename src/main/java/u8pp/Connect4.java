package u8pp;

public class Connect4 {

    public static final int RED_WIN = 0;
    public static final int YELLOW_WIN = 1;
    public static final int NO_WINNER = 2;
    public static final int BOTH_WIN = 3;

    public static final int RED = 1;
    public static final int YELLOW = -1;
    public static final int EMPTY = 0;

    // implementation here

    // It is recommended to use private helper methods

    public static void printBoard(int[][] board) {
        for (int[] row : board) {
            String rowOutput = "";
            for (int space : row) {
                if (space == RED) {
                    rowOutput += "🔴";
                } else if (space == Connect4.YELLOW) {
                    rowOutput += "🟡";
                } else {
                    rowOutput += "⬛";
                }
            }
            System.out.println(rowOutput);
        }
    }

    private int[][] board;
    private int nextPlayer;

    //make a new empty game board
    public Connect4(){
        board = new int[6][7];
        nextPlayer = RED;
    }

    //makes a game using a given board if it is valid
    public Connect4(int[][] board){
        if (isBoardValid(board)){
            this.board = copyBoard(board);

            int redCount = 0;
            int yellowCount = 0;

            for (int r = 0; r < this.board.length; r++){
                for (int c = 0; c < this.board[r].length; c++){
                    if(this.board[r][c] == RED){
                        redCount++;
                    }else if (this.board[r][c] == YELLOW){
                        yellowCount++;
                    }
                }
            }
            if (redCount == yellowCount){
                nextPlayer = RED;
            }else{
                nextPlayer = YELLOW;
            }
        }else{
            this.board = new int[6][7];
            nextPlayer = RED;
        }
    }

    //returns the current board
    public int[][] getBoard(){
        return board;
    }

    //returns whoes turn is next
    public int getNextPlayer(){
        return nextPlayer;
    }

    //returns true if the board has no empty spaces
    public static boolean isFull (int[][] board){
        for (int r = 0; r < board.length; r++){
            for (int c = 0; c < board[r].length; c++){
                if (board[r][c] == EMPTY){
                    return false;
                }
            }
        }
        return true;
    }

    //returns true if the board follows connect 4 rules
    public static boolean isBoardValid(int[][] board){
        int redCount = 0;
        int yellowCount = 0;

        for(int c = 0; c < board[0].length; c++){
            boolean foundEmpty = false;

            for (int r = board.length - 1; r >= 0; r--){
                if (board[r][c] == EMPTY){
                    foundEmpty = true;
                }else{
                    if (foundEmpty){
                        return false;
                    }

                    if (board[r][c] == RED){
                        redCount++;
                    }else if (board[r][c] == YELLOW){
                        yellowCount++;
                    }else{
                        return false;
                    }
                }
            }
        }
        return redCount == yellowCount || redCount == yellowCount + 1;
    }

    //returns who won on the board
    public static int getWinner(int[][] board){
        boolean redWin = false;
        boolean yellowWin = false;

        //horizontal
        for (int r=0; r<board.length; r++){
            for( int c=0; c<=board[r].length-4; c++){
                int value = board[r][c];

                if (value != EMPTY && value == board[r][c+1] && value == board[r][c+2] && value == board[r][c+3]){
                    if (value == RED){
                        redWin = true;
                    }else{
                        yellowWin = true;
                    }
                }
            }
        }

        //vertical
        for (int r = 0; r <= board.length -4; r++){
            for (int c = 0; c<board[r].length; c++){
                int value = board[r][c];

                if(value != EMPTY && value == board[r+1][c] && value == board[r+2][c] && value == board[r+3][c]){
                    if(value == RED){
                        redWin = true;
                    }else{
                    yellowWin = true;
                    }
                }
            }
        }

        //diagonal down right
        for (int r =0; r<= board.length-4; r++){
            for (int c =0; c<= board[r].length-4; c++){
                int value = board[r][c];

                if(value != EMPTY && value == board[r+1][c+1] && value == board[r+2][c+2] && value == board[r+3][c+3]){
                    if(value == RED){
                        redWin = true;
                    }else{
                        yellowWin = true;
                    }
                }
            }
        }

        //diagonal up righ
        for (int r = 3; r < board.length; r++){
            for (int c = 0; c <= board[r].length -4; c++){
                int value = board[r][c];

                if (value !=EMPTY && value == board[r-1][c+1] && value == board[r-2][c+2] && value == board[r-3][c+3]){
                    if(value == RED){
                        redWin =true;
                    }else{
                        yellowWin = true;
                    }
                }
            }
        }

        if (redWin && yellowWin){
            return BOTH_WIN;
        }else if (redWin){
            return RED_WIN;
        }else if (yellowWin){
            return YELLOW_WIN;
        }else{
            return NO_WINNER;
        }
    }

    //drops a piece into a column
    public boolean dropPiece(int col){
        if (col < 0 || col >= board[0].length){
            return false;
        }


        for (int r = board.length -1; r>=0; r--){
            if (board[r][col] == EMPTY){
                board[r][col] = nextPlayer;

                if (nextPlayer == RED){
                    nextPlayer = YELLOW;
                }else{
                    nextPlayer = RED;
                }
                return true;
            }
        }
        return false;
    }

    //plays the full game using scanner input
    public void play(java.util.Scanner sc){
        while (!isFull(board) && getWinner(board) == NO_WINNER && sc.hasNext()){
            printBoard(board);

            if(!sc.hasNextInt()){
                sc.next();
                continue;
            }
            int col = sc.nextInt();
            dropPiece(col);
        }
    }

    //makes a copy of the board
    private int[][] copyBoard(int[][] original){
        int [][] copy = new int[original.length][original[0].length];

        for (int r = 0; r  < original.length; r++){
            for (int c = 0; c < original[r].length; c++){
                copy[r][c]= original[r][c];
            }
        }
        return copy;
    }



    











}
