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

    
}
