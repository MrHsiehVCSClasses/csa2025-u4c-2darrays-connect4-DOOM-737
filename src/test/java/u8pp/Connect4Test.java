package u8pp;

import static org.junit.jupiter.api.Assertions.*;

import java.time.Duration;
import java.util.Arrays;
import java.util.Scanner;

import org.junit.jupiter.api.*;

public class Connect4Test {

	private final int R = Connect4.RED;
	private final int Y = Connect4.YELLOW;
	private final int E = Connect4.EMPTY;

	private int[][] BOARD_EMPTY = new int[6][7];

	private int[][] BOARD_FULL_RED = {
			{ R, R, R, R, R, R, R },
			{ R, R, R, R, R, R, R },
			{ R, R, R, R, R, R, R },
			{ R, R, R, R, R, R, R },
			{ R, R, R, R, R, R, R },
			{ R, R, R, R, R, R, R },
	};

	private int[][] BOARD_FULL_YELLOW = {
			{ Y, Y, Y, Y, Y, Y, Y },
			{ Y, Y, Y, Y, Y, Y, Y },
			{ Y, Y, Y, Y, Y, Y, Y },
			{ Y, Y, Y, Y, Y, Y, Y },
			{ Y, Y, Y, Y, Y, Y, Y },
			{ Y, Y, Y, Y, Y, Y, Y },
	};

	private int[][] BOARD_FULL_CHECKERED = {
			{ Y, R, Y, R, Y, R, Y },
			{ R, Y, R, Y, R, Y, R },
			{ Y, R, Y, R, Y, R, Y },
			{ R, Y, R, Y, R, Y, R },
			{ Y, R, Y, R, Y, R, Y },
			{ R, Y, R, Y, R, Y, R },
	};

	@Test
	public void isFull_fullBoard_returnsTrue() {
		assertTrue(Connect4.isFull(BOARD_FULL_CHECKERED));
		assertTrue(Connect4.isFull(BOARD_FULL_YELLOW));
		assertTrue(Connect4.isFull(BOARD_FULL_RED));
	}

	@Test
	public void isFull_emptyBoard_returnsFalse() {
		assertFalse(Connect4.isFull(BOARD_EMPTY));
	}

	@Test
	public void isFull_almostFullBoard_returnsFalse() {
		int[][] board1 = copyBoard(BOARD_FULL_YELLOW);
		board1[0][0] = E;
		int[][] board2 = copyBoard(BOARD_FULL_RED);
		board2[5][6] = E;

		assertFalse(Connect4.isFull(board1));
		assertFalse(Connect4.isFull(board2));
	}

	@Test
	public void isBoardValid_fullBoard_returnsTrue() {
		assertTrue(Connect4.isBoardValid(BOARD_FULL_CHECKERED));
	}

	@Test
	public void isBoardValid_emptyBoard_returnsTrue() {
		assertTrue(Connect4.isBoardValid(BOARD_EMPTY));
	}

	@Test
	public void isBoardValid_stairsBoard_returnsTrue() {
		int[][] boardStairs = {
				{ Y, E, E, E, E, E, E },
				{ R, R, E, E, E, E, E },
				{ Y, Y, R, E, E, E, E },
				{ R, R, Y, Y, E, E, E },
				{ Y, Y, R, R, Y, E, E },
				{ R, R, Y, Y, R, R, E },
		};

		assertTrue(Connect4.isBoardValid(boardStairs));
	}

	@Test
	public void isBoardValid_floatingPieceBoard_returnsFalse() {
		int[][] board1 = {
				{ R, E, E, E, E, E, E },
				{ E, E, E, E, E, E, E },
				{ E, E, E, E, E, E, E },
				{ E, E, E, E, E, E, E },
				{ E, E, E, E, E, E, E },
				{ E, E, E, E, E, E, E },
		};

		int[][] board2 = {
				{ E, E, E, E, E, E, E },
				{ E, E, E, R, E, E, E },
				{ E, E, E, E, E, E, E },
				{ E, E, E, E, E, E, E },
				{ E, E, E, E, E, E, E },
				{ E, E, E, E, E, E, E },
		};

		int[][] board3 = {
				{ E, E, E, E, E, E, E },
				{ E, E, E, E, E, E, E },
				{ E, E, E, E, E, E, E },
				{ E, E, E, E, E, E, E },
				{ E, E, E, E, E, E, R },
				{ E, E, E, E, E, E, E },
		};

		assertFalse(Connect4.isBoardValid(board1));
		assertFalse(Connect4.isBoardValid(board2));
		assertFalse(Connect4.isBoardValid(board3));
	}

	@Test
	public void isBoardValid_nearlyFullBoard_returnsFalse() {
		int[][] board1 = {
				{ R, R, R, R, R, R, R },
				{ E, R, R, R, R, R, R },
				{ R, R, R, R, R, R, R },
				{ R, R, R, R, R, R, R },
				{ R, R, R, R, R, R, R },
				{ R, R, R, R, R, R, R },
		};

		int[][] board2 = {
				{ R, R, R, R, R, R, R },
				{ R, R, R, R, R, R, R },
				{ R, R, R, R, R, R, R },
				{ R, R, R, R, R, R, R },
				{ R, R, R, R, R, R, R },
				{ R, R, R, R, R, R, E },
		};

		int[][] board3 = {
				{ R, R, R, R, R, R, R },
				{ R, R, R, R, R, R, R },
				{ R, R, R, R, R, R, R },
				{ R, R, R, E, R, R, R },
				{ R, R, R, R, R, R, R },
				{ R, R, R, R, R, R, R },
		};

		assertFalse(Connect4.isBoardValid(board1));
		assertFalse(Connect4.isBoardValid(board2));
		assertFalse(Connect4.isBoardValid(board3));
	}

	@Test
	public void isBoardValid_tooManyOneColor_returnsFalse() {
		int[][] board1 = {
			{ E, E, E, E, E, E, E },
			{ E, E, E, E, E, E, E },
			{ E, E, E, E, E, E, E },
			{ E, E, E, E, E, E, E },
			{ E, E, E, E, E, E, E },
			{ E, E, E, R, R, E, E },
		};
		int[][] board2 = {
			{ E, E, E, E, E, E, E },
			{ E, E, E, E, E, E, E },
			{ E, E, E, E, E, E, E },
			{ E, E, E, E, E, E, E },
			{ E, E, E, E, E, E, E },
			{ E, E, E, Y, E, E, E },
		};

		int[][] board3 = {
			{ E, E, E, E, E, E, E },
			{ E, E, E, E, E, E, E },
			{ E, E, E, E, E, E, E },
			{ E, E, E, E, E, E, E },
			{ E, E, E, E, E, E, E },
			{ E, E, Y, R, R, R, E },
		};
		int[][] board4 = {
			{ E, E, E, E, E, E, E },
			{ E, E, E, E, E, E, E },
			{ E, E, E, E, E, E, E },
			{ E, E, E, E, E, E, E },
			{ E, E, E, E, E, E, E },
			{ E, E, Y, Y, R, E, E },
		};

		assertFalse(Connect4.isBoardValid(BOARD_FULL_YELLOW));
		assertFalse(Connect4.isBoardValid(BOARD_FULL_RED));
		assertFalse(Connect4.isBoardValid(board1));
		assertFalse(Connect4.isBoardValid(board2));
		assertFalse(Connect4.isBoardValid(board3));
		assertFalse(Connect4.isBoardValid(board4));
	}

	@Test
	public void getWinner_horizontalRedWin_returnsRedWin() {
		int[][] board1 = {
				{ R, R, R, R, E, E, E },
				{ E, E, E, E, E, E, E },
				{ E, E, E, E, E, E, E },
				{ E, E, E, E, E, E, E },
				{ E, E, E, E, E, E, E },
				{ E, E, E, E, E, E, E },
		};

		int[][] board2 = {
				{ E, E, E, E, E, E, E },
				{ E, E, E, E, E, E, E },
				{ E, R, R, R, R, R, E },
				{ E, E, E, E, E, E, E },
				{ E, E, E, E, E, E, E },
				{ E, E, E, E, E, E, E },
		};

		int[][] board3 = {
				{ R, E, E, E, E, E, E },
				{ E, E, E, E, E, E, E },
				{ E, E, E, E, E, E, E },
				{ E, E, E, E, E, E, E },
				{ E, E, E, E, E, E, E },
				{ E, E, E, R, R, R, R },
		};

		assertEquals(Connect4.RED_WIN, Connect4.getWinner(board1));
		assertEquals(Connect4.RED_WIN, Connect4.getWinner(board2));
		assertEquals(Connect4.RED_WIN, Connect4.getWinner(board3));
	}

	@Test
	public void getWinner_verticalRedWin_returnsRedWin() {
		int[][] board1 = {
				{ R, E, E, E, E, E, E },
				{ R, E, E, E, E, E, E },
				{ R, E, E, E, E, E, E },
				{ R, E, E, E, E, E, E },
				{ E, E, E, E, E, E, E },
				{ E, E, E, E, E, E, E },
		};

		int[][] board2 = {
				{ E, E, E, E, E, E, E },
				{ E, E, E, R, E, E, E },
				{ E, E, E, R, E, E, E },
				{ E, E, E, R, E, E, E },
				{ E, E, E, R, E, E, E },
				{ E, E, E, E, E, E, E },
		};

		int[][] board3 = {
				{ E, E, E, E, E, E, E },
				{ E, E, E, E, E, E, E },
				{ E, E, E, E, E, E, R },
				{ E, E, E, E, E, E, R },
				{ E, E, E, E, E, E, R },
				{ E, E, E, E, E, E, R },
		};

		assertEquals(Connect4.RED_WIN, Connect4.getWinner(board1));
		assertEquals(Connect4.RED_WIN, Connect4.getWinner(board2));
		assertEquals(Connect4.RED_WIN, Connect4.getWinner(board3));
	}

	@Test
	public void getWinner_diagonalUpRedWin_returnsRedWin() {
		int[][] board1 = {
				{ R, E, E, E, E, E, E },
				{ R, R, E, E, E, E, E },
				{ E, E, R, E, E, E, E },
				{ R, E, E, R, E, E, E },
				{ E, E, E, E, E, E, E },
				{ E, E, E, E, E, E, E },
		};

		int[][] board2 = {
				{ E, E, E, E, E, E, E },
				{ E, E, E, R, E, E, E },
				{ R, E, E, R, E, E, E },
				{ E, R, E, E, E, E, E },
				{ E, E, R, R, E, E, E },
				{ E, E, E, R, E, E, E },
		};

		int[][] board3 = {
				{ E, E, E, E, E, E, E },
				{ E, E, E, E, E, E, E },
				{ E, E, E, R, E, E, R },
				{ E, E, E, E, R, E, R },
				{ E, E, E, E, E, R, E },
				{ E, E, E, E, E, E, R },
		};

		int[][] board4 = {
				{ E, E, E, R, E, E, E },
				{ E, E, E, E, R, E, E },
				{ E, E, E, R, E, R, R },
				{ E, E, E, E, E, E, R },
				{ E, E, E, E, E, R, E },
				{ E, E, E, E, E, E, R },
		};

		assertEquals(Connect4.RED_WIN, Connect4.getWinner(board1));
		assertEquals(Connect4.RED_WIN, Connect4.getWinner(board2));
		assertEquals(Connect4.RED_WIN, Connect4.getWinner(board3));
		assertEquals(Connect4.RED_WIN, Connect4.getWinner(board4));
	}

	@Test
	public void getWinner_diagonalDownRedWin_returnsRedWin() {
		int[][] board1 = {
				{ R, E, E, R, E, E, E },
				{ R, E, R, E, E, E, E },
				{ E, R, R, E, E, E, E },
				{ R, E, E, R, E, E, E },
				{ E, E, E, E, E, E, E },
				{ E, E, E, E, E, E, E },
		};

		int[][] board2 = {
				{ E, E, E, E, E, E, E },
				{ E, E, E, R, E, E, E },
				{ R, E, E, R, E, E, E },
				{ E, E, R, E, E, E, E },
				{ E, R, E, R, E, E, E },
				{ R, E, E, R, E, E, E },
		};

		int[][] board3 = {
				{ E, E, E, E, E, E, E },
				{ E, E, E, E, E, E, E },
				{ E, E, E, R, E, E, R },
				{ E, E, E, E, E, R, R },
				{ E, E, E, E, R, E, E },
				{ E, E, E, R, E, E, R },
		};

		int[][] board4 = {
				{ E, E, E, R, E, E, R },
				{ E, E, E, E, R, R, E },
				{ E, E, E, R, R, E, R },
				{ E, E, E, R, E, E, R },
				{ E, E, E, E, E, R, E },
				{ E, E, E, E, E, E, R },
		};

		assertEquals(Connect4.RED_WIN, Connect4.getWinner(board1));
		assertEquals(Connect4.RED_WIN, Connect4.getWinner(board2));
		assertEquals(Connect4.RED_WIN, Connect4.getWinner(board3));
		assertEquals(Connect4.RED_WIN, Connect4.getWinner(board4));
	}

	@Test
	public void getWinner_horizontalYellowWin_returnsYellowWin() {
		int[][] board1 = {
				{ Y, Y, Y, Y, E, E, E },
				{ E, E, E, E, E, E, E },
				{ E, E, E, E, E, E, E },
				{ E, E, E, E, E, E, E },
				{ E, E, E, E, E, E, E },
				{ E, E, E, E, E, E, E },
		};

		int[][] board2 = {
				{ E, E, E, E, E, E, E },
				{ E, E, E, E, E, E, E },
				{ E, Y, Y, Y, Y, Y, E },
				{ E, E, E, E, E, E, E },
				{ E, E, E, E, E, E, E },
				{ E, E, E, E, E, E, E },
		};

		int[][] board3 = {
				{ Y, E, E, E, E, E, E },
				{ E, E, E, E, E, E, E },
				{ E, E, E, E, E, E, E },
				{ E, E, E, E, E, E, E },
				{ E, E, E, E, E, E, E },
				{ E, E, E, Y, Y, Y, Y },
		};

		assertEquals(Connect4.YELLOW_WIN, Connect4.getWinner(board1));
		assertEquals(Connect4.YELLOW_WIN, Connect4.getWinner(board2));
		assertEquals(Connect4.YELLOW_WIN, Connect4.getWinner(board3));
	}

	@Test
	public void getWinner_verticalYellowWin_returnsYellowWin() {
		int[][] board1 = {
				{ Y, E, E, E, E, E, E },
				{ Y, E, E, E, E, E, E },
				{ Y, E, E, E, E, E, E },
				{ Y, E, E, E, E, E, E },
				{ E, E, E, E, E, E, E },
				{ E, E, E, E, E, E, E },
		};

		int[][] board2 = {
				{ E, E, E, E, E, E, E },
				{ E, E, E, Y, E, E, E },
				{ E, E, E, Y, E, E, E },
				{ E, E, E, Y, E, E, E },
				{ E, E, E, Y, E, E, E },
				{ E, E, E, E, E, E, E },
		};

		int[][] board3 = {
				{ E, E, E, E, E, E, E },
				{ E, E, E, E, E, E, E },
				{ E, E, E, E, E, E, Y },
				{ E, E, E, E, E, E, Y },
				{ E, E, E, E, E, E, Y },
				{ E, E, E, E, E, E, Y },
		};

		assertEquals(Connect4.YELLOW_WIN, Connect4.getWinner(board1));
		assertEquals(Connect4.YELLOW_WIN, Connect4.getWinner(board2));
		assertEquals(Connect4.YELLOW_WIN, Connect4.getWinner(board3));
	}

	@Test
	public void getWinner_diagonalUpYellowWin_returnsYellowWin() {
		int[][] board1 = {
				{ Y, E, E, E, E, E, E },
				{ Y, Y, E, E, E, E, E },
				{ E, E, Y, E, E, E, E },
				{ Y, E, E, Y, E, E, E },
				{ E, E, E, E, E, E, E },
				{ E, E, E, E, E, E, E },
		};

		int[][] board2 = {
				{ E, E, E, E, E, E, E },
				{ E, E, E, Y, E, E, E },
				{ Y, E, E, Y, E, E, E },
				{ E, Y, E, E, E, E, E },
				{ E, E, Y, Y, E, E, E },
				{ E, E, E, Y, E, E, E },
		};

		int[][] board3 = {
				{ E, E, E, E, E, E, E },
				{ E, E, E, E, E, E, E },
				{ E, E, E, Y, E, E, Y },
				{ E, E, E, E, Y, E, Y },
				{ E, E, E, E, E, Y, E },
				{ E, E, E, E, E, E, Y },
		};

		int[][] board4 = {
				{ E, E, E, Y, E, E, E },
				{ E, E, E, E, Y, E, E },
				{ E, E, E, Y, E, Y, Y },
				{ E, E, E, E, E, E, Y },
				{ E, E, E, E, E, Y, E },
				{ E, E, E, E, E, E, Y },
		};

		assertEquals(Connect4.YELLOW_WIN, Connect4.getWinner(board1));
		assertEquals(Connect4.YELLOW_WIN, Connect4.getWinner(board2));
		assertEquals(Connect4.YELLOW_WIN, Connect4.getWinner(board3));
		assertEquals(Connect4.YELLOW_WIN, Connect4.getWinner(board4));
	}

	@Test
	public void getWinner_diagonalDownYellowWin_returnsYellowWin() {
		int[][] board1 = {
				{ Y, E, E, Y, E, E, E },
				{ Y, E, Y, E, E, E, E },
				{ E, Y, Y, E, E, E, E },
				{ Y, E, E, Y, E, E, E },
				{ E, E, E, E, E, E, E },
				{ E, E, E, E, E, E, E },
		};

		int[][] board2 = {
				{ E, E, E, E, E, E, E },
				{ E, E, E, Y, E, E, E },
				{ Y, E, E, Y, E, E, E },
				{ E, E, Y, E, E, E, E },
				{ E, Y, E, Y, E, E, E },
				{ Y, E, E, Y, E, E, E },
		};

		int[][] board3 = {
				{ E, E, E, E, E, E, E },
				{ E, E, E, E, E, E, E },
				{ E, E, E, Y, E, E, Y },
				{ E, E, E, E, E, Y, Y },
				{ E, E, E, E, Y, E, E },
				{ E, E, E, Y, E, E, Y },
		};

		int[][] board4 = {
				{ E, E, E, Y, E, E, Y },
				{ E, E, E, E, Y, Y, E },
				{ E, E, E, Y, Y, E, Y },
				{ E, E, E, Y, E, E, Y },
				{ E, E, E, E, E, Y, E },
				{ E, E, E, E, E, E, Y },
		};

		assertEquals(Connect4.YELLOW_WIN, Connect4.getWinner(board1));
		assertEquals(Connect4.YELLOW_WIN, Connect4.getWinner(board2));
		assertEquals(Connect4.YELLOW_WIN, Connect4.getWinner(board3));
		assertEquals(Connect4.YELLOW_WIN, Connect4.getWinner(board4));
	}

	@Test
	public void getWinner_tie_returnsBothWin() {
		int[][] board1 = {
				{ Y, E, E, Y, E, E, E },
				{ Y, E, Y, E, E, E, E },
				{ E, Y, Y, E, E, E, E },
				{ Y, E, E, Y, E, E, E },
				{ R, R, R, R, R, R, R },
				{ E, E, E, E, E, E, E },
		};

		int[][] board2 = {
				{ E, E, E, E, E, E, R },
				{ E, E, E, Y, E, R, E },
				{ Y, E, E, E, R, E, E },
				{ E, E, Y, R, E, E, E },
				{ Y, Y, Y, Y, E, E, E },
				{ Y, E, E, Y, E, E, E },
		};

		int[][] board3 = {
				{ R, E, E, E, E, E, E },
				{ E, R, E, E, E, E, E },
				{ E, E, R, Y, E, E, Y },
				{ E, E, E, R, E, Y, Y },
				{ E, E, E, E, Y, E, E },
				{ E, E, E, Y, E, E, Y },
		};

		assertEquals(Connect4.BOTH_WIN, Connect4.getWinner(board1));
		assertEquals(Connect4.BOTH_WIN, Connect4.getWinner(board2));
		assertEquals(Connect4.BOTH_WIN, Connect4.getWinner(board3));
		assertEquals(Connect4.BOTH_WIN, Connect4.getWinner(BOARD_FULL_CHECKERED));
	}

	@Test
	public void getWinner_noWinnerBoard_returnsNoWinner() {
		int[][] board1 = {
			{ R, R, R, E, Y, Y, Y },
			{ Y, Y, Y, E, R, R, R },
			{ R, R, R, E, Y, Y, Y },
			{ Y, Y, Y, E, R, R, R },
			{ R, R, R, E, Y, Y, Y },
			{ Y, Y, Y, E, R, R, R },
		};
		assertEquals(Connect4.NO_WINNER, Connect4.getWinner(BOARD_EMPTY));
		assertEquals(Connect4.NO_WINNER, Connect4.getWinner(board1));
	}

	@Test
	public void dropPiece_middleColumn_areCorrect() {
		int[][] board1 = {
			{ E, E, E, Y, E, E, E },
			{ E, E, E, R, E, E, E },
			{ E, E, E, Y, E, E, E },
			{ E, E, E, R, E, E, E },
			{ E, E, E, Y, E, E, E },
			{ E, E, E, R, E, E, E },
		};

		Connect4 c4 = new Connect4();
		assertTrue(c4.dropPiece(3));
		assertTrue(c4.dropPiece(3));
		assertTrue(c4.dropPiece(3));
		assertTrue(c4.dropPiece(3));
		assertTrue(c4.dropPiece(3));
		assertTrue(c4.dropPiece(3));
		
		assertTrue(Arrays.deepEquals(c4.getBoard(), board1));
	}

	@Test
	public void dropPiece_bottomRow_areCorrect() {
		int[][] board1 = {
			{ E, E, E, E, E, E, E },
			{ E, E, E, E, E, E, E },
			{ E, E, E, E, E, E, E },
			{ E, E, E, E, E, E, E },
			{ E, E, E, E, E, E, E },
			{ R, Y, R, Y, R, Y, R },
		};

		Connect4 c4 = new Connect4();
		assertTrue(c4.dropPiece(0));
		assertTrue(c4.dropPiece(1));
		assertTrue(c4.dropPiece(2));
		assertTrue(c4.dropPiece(3));
		assertTrue(c4.dropPiece(4));
		assertTrue(c4.dropPiece(5));
		assertTrue(c4.dropPiece(6));
		
		assertTrue(Arrays.deepEquals(c4.getBoard(), board1));
	}

	@Test
	public void dropPiece_wholeBoard_isCorrect() {
 

		Connect4 c4 = new Connect4();
		assertTrue(c4.dropPiece(0));
		assertTrue(c4.dropPiece(0));
		assertTrue(c4.dropPiece(0));
		assertTrue(c4.dropPiece(0));
		assertTrue(c4.dropPiece(0));
		assertTrue(c4.dropPiece(0));
		assertFalse(c4.dropPiece(0));

		assertTrue(c4.dropPiece(1));
		assertTrue(c4.dropPiece(1));
		assertTrue(c4.dropPiece(1));
		assertTrue(c4.dropPiece(1));
		assertTrue(c4.dropPiece(1));
		assertTrue(c4.dropPiece(1));
		assertFalse(c4.dropPiece(1));

		assertTrue(c4.dropPiece(2));
		assertTrue(c4.dropPiece(2));
		assertTrue(c4.dropPiece(2));
		assertTrue(c4.dropPiece(2));
		assertTrue(c4.dropPiece(2));
		assertTrue(c4.dropPiece(2));
		assertFalse(c4.dropPiece(2));
		
		assertTrue(c4.dropPiece(3));
		assertTrue(c4.dropPiece(3));
		assertTrue(c4.dropPiece(3));
		assertTrue(c4.dropPiece(3));
		assertTrue(c4.dropPiece(3));
		assertTrue(c4.dropPiece(3));
		assertFalse(c4.dropPiece(3));

		assertTrue(c4.dropPiece(4));
		assertTrue(c4.dropPiece(4));
		assertTrue(c4.dropPiece(4));
		assertTrue(c4.dropPiece(4));
		assertTrue(c4.dropPiece(4));
		assertTrue(c4.dropPiece(4));
		assertFalse(c4.dropPiece(4));

		assertTrue(c4.dropPiece(5));
		assertTrue(c4.dropPiece(5));
		assertTrue(c4.dropPiece(5));
		assertTrue(c4.dropPiece(5));
		assertTrue(c4.dropPiece(5));
		assertTrue(c4.dropPiece(5));
		assertFalse(c4.dropPiece(5));

		assertTrue(c4.dropPiece(6));
		assertTrue(c4.dropPiece(6));
		assertTrue(c4.dropPiece(6));
		assertTrue(c4.dropPiece(6));
		assertTrue(c4.dropPiece(6));
		assertTrue(c4.dropPiece(6));
		assertFalse(c4.dropPiece(6));

		int[][] board = {
			{Y,Y,Y,Y,Y,Y,Y},
			{R,R,R,R,R,R,R},
			{Y,Y,Y,Y,Y,Y,Y},
			{R,R,R,R,R,R,R},
			{Y,Y,Y,Y,Y,Y,Y},
			{R,R,R,R,R,R,R},
		};

		assertTrue(Arrays.deepEquals(c4.getBoard(), board));
	}

	@Test
	public void dropPiece_outOfBounds_returnsFalse() {
		Connect4 c4 = new Connect4();
		assertFalse(c4.dropPiece(-1));
		assertFalse(c4.dropPiece(7));
		assertTrue(Arrays.deepEquals(c4.getBoard(), BOARD_EMPTY));
	}

	@Test
	public void dropPiece_fullColumn_returnsFalse() {
		Connect4 c4 = new Connect4();

		c4.dropPiece(3);
		c4.dropPiece(3);
		c4.dropPiece(3);
		c4.dropPiece(3);
		c4.dropPiece(3);
		c4.dropPiece(3);
		assertFalse(c4.dropPiece(3));
	}

	@Test
	public void constructor_givenInvalidBoard_initializesToEmptyBoard() {
		int[][] invalidBoard = {
			{R,R,R,R,R,R,R},
			{E,E,E,E,E,E,E},
			{E,E,E,E,E,E,E},
			{E,E,E,Y,E,E,E},
			{E,E,E,E,E,E,E},
			{E,E,E,E,E,E,E},
		};
		Connect4 c4 = new Connect4(invalidBoard);
		assertTrue(Arrays.deepEquals(c4.getBoard(), BOARD_EMPTY));
	}

	@Test
	public void constructor_givenValidBoard_initializesToGivenBoard() {
		Connect4 c4 = new Connect4(BOARD_FULL_CHECKERED);
		assertTrue(Arrays.deepEquals(c4.getBoard(), BOARD_FULL_CHECKERED));
	}

	@Test
	public void constructor_givenEmptyBoard_initializesToGivenBoard() {
		Connect4 c4 = new Connect4(BOARD_EMPTY);
		assertTrue(Arrays.deepEquals(c4.getBoard(), BOARD_EMPTY));
	}

	@Test
	public void constructor_givenBoardRedPlaysNext_initializesToCorrectNextPlayer() {
		int[][] board = {
			{E,E,E,E,E,E,E},
			{E,E,E,E,E,E,E},
			{E,E,E,E,E,E,E},
			{E,E,E,E,E,E,E},
			{E,E,E,E,E,E,E},
			{R,Y,R,Y,E,E,E}
		};
		
		Connect4 c4 = new Connect4(board);
		assertEquals(Connect4.RED, c4.getNextPlayer());
	}

	@Test
	public void constructor_givenBoardYellowPlaysNext_initializesToCorrectNextPlayer() {
		int[][] board = {
			{E,E,E,E,E,E,E},
			{E,E,E,E,E,E,E},
			{E,E,E,E,E,E,E},
			{E,E,E,E,E,E,E},
			{E,E,E,E,E,E,E},
			{R,Y,R,Y,R,E,E}
		};
		Connect4 c4 = new Connect4(board);
		assertEquals(Connect4.YELLOW, c4.getNextPlayer());
	}


	@Test
	public void play_invalidInputs_doesNothing() {
		Connect4 c4 = new Connect4();
		assertTimeoutPreemptively(Duration.ofSeconds(10), () -> {
			Scanner sc = new Scanner("hi\n7\n-1\nasdf\n \n");
			c4.play(sc);
		});
		assertTrue(Arrays.deepEquals(c4.getBoard(), BOARD_EMPTY));
	}

	@Test
	public void play_RedWinsInputs_worksAsExpected() {
		Connect4 c4 = new Connect4();
		assertTimeoutPreemptively(Duration.ofSeconds(10), () -> {
			Scanner sc = new Scanner("0\n0\n1\n1\n2\n2\n3\n3\n");
			c4.play(sc);
		});

		int[][] board = {
			{E,E,E,E,E,E,E},
			{E,E,E,E,E,E,E},
			{E,E,E,E,E,E,E},
			{E,E,E,E,E,E,E},
			{Y,Y,Y,E,E,E,E},
			{R,R,R,R,E,E,E}
		};

		assertTrue(Arrays.deepEquals(c4.getBoard(), board));
	}

	@Test
	public void play_LongGameYellowWinsInputs_worksAsExpected() {
		Connect4 c4 = new Connect4();
		assertTimeoutPreemptively(Duration.ofSeconds(10), () -> {
			Scanner sc = new Scanner(
				"0\n0\n0\n0\n0\n0\n" +
				"1\n1\n1\n1\n1\n1\n" + 
				"2\n2\n2\n2\n2\n2\n" +
				"4\n4\n4\n4\n4\n4\n" + 
				"5\n5\n5\n5\n5\n5\n" +
				"6\n3\n6\n3\n"
				);
			c4.play(sc);
		});

		int[][] board = {
			{Y,Y,Y,E,Y,Y,E},
			{R,R,R,E,R,R,E},
			{Y,Y,Y,E,Y,Y,E},
			{R,R,R,E,R,R,E},
			{Y,Y,Y,Y,Y,Y,R},
			{R,R,R,Y,R,R,R},
		};

		assertTrue(Arrays.deepEquals(c4.getBoard(), board));

	}

	private int[][] copyBoard(int[][] board) {
		int[][] output = new int[board.length][board[0].length];
		for (int r = E; r < board.length; r++) {
			for (int c = E; c < board[r].length; c++) {
				output[r][c] = board[r][c];
			}
		}
		return output;
	}
}
