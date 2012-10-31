import java.io.File;
import java.util.Scanner;

/**
 * Console application for game of life
 * Input : filename that contains game board.
 * Output : subsequent generation of game board.
 * For details of compilation and running the program, please look at readme.txt file.
 * This program scans the input file twice because we don't know the size of game of board.
 * 
 * @author Seungchan Lee
 * 
 */
public class GameofLife {

	/**
	 * Global variable to hold input filename
	 */
	private static String filename = "";

	/**
	 * Main function of the program
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		getFileName();
		int[] boardSize = initBoardSize();
		int[][] board = setBoard(boardSize);
		int[][] nextBoard = getNextGen(board);
		printBoard(nextBoard);
	}

	/**
	 * Get input filename from the console input. Use Scanner that provides more
	 * functionality than BufferedReader
	 */
	private static void getFileName() {
		Scanner consoleInput = new Scanner(System.in);
		System.out.print("Enter file name to open:");
		filename = consoleInput.next();
	}

	/**
	 * Verify input file format, and get the size of board
	 * 
	 * @return
	 */
	private static int[] initBoardSize() {
		int[] bSize = new int[2];
		int row = 0, col = 0;
		File file = new File(filename);
		Scanner fileScanner = null;
		try {
			fileScanner = new Scanner(file);
			String sCurrentLine;

			int prevRow = 0;
			while (fileScanner.hasNext()) {
				sCurrentLine = fileScanner.nextLine();
				String[] currentStrs = sCurrentLine.split(" ");
				if (col == 0)
					prevRow = currentStrs.length;
				else {
					row = currentStrs.length;
					if (prevRow != row) {
						System.out.print(filename
								+ " file format is not correct");
						System.exit(0);
					}
					prevRow = row;
				}
				col++;
			}
			bSize[0] = row;
			bSize[1] = col;
		} catch (Exception e) {
			System.out.print(file + " does not exist");
			System.exit(0);
		} finally {
			try {
				if (fileScanner != null)
					fileScanner.close();
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}
		return bSize;
	}

	/**
	 * Set the array of the game board
	 * 
	 * @param boSize
	 * @return
	 */
	private static int[][] setBoard(int[] boSize) {
		int row = boSize[0], col = boSize[1];
		int board[][] = new int[row][col];
		File file = new File(filename);
		Scanner fileScanner = null;
		try {
			fileScanner = new Scanner(file);
			String sCurrentLine;

			// set the board
			int i = 0;
			while (fileScanner.hasNext()) {
				sCurrentLine = fileScanner.nextLine();
				String[] currentStrs = sCurrentLine.split(" ");
				for (int j = 0; j < col; j++) {
					board[i][j] = Integer.parseInt(currentStrs[j]);
				}
				i++;
			}
		} catch (Exception e) {
			System.out.print(file + " does not exist");
			System.exit(0);
		} finally {
			try {
				if (fileScanner != null)
					fileScanner.close();
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}
		return board;
	}

	/**
	 * Get next generation of a game board
	 * 
	 * @param board
	 * @return
	 */
	private static int[][] getNextGen(int[][] board) {
		int[][] newBoard = new int[board.length][board[0].length];
		int num;
		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board[0].length; j++) {
				num = cntLiveNeighbors(board, i, j);
				if (isLiveNext(num, board[i][j] == 1))
					newBoard[i][j] = 1;
				else
					newBoard[i][j] = 0;
			}
		}
		return newBoard;
	}

	/**
	 * Check a cell if it is alive (1) or dead (0) for next generation
	 * 
	 * @param cntNeighbors
	 * @param isCurrentLive
	 * @return
	 */
	public static boolean isLiveNext(int cntNeighbors, boolean isCurrentLive) {
		if (isCurrentLive && (cntNeighbors == 2 || cntNeighbors == 3))
			return true;
		else if (!isCurrentLive && cntNeighbors == 3)
			return true;
		else
			return false;
	}

	/**
	 * count the number of live neighbors for a given cell
	 * 
	 * @param board
	 * @param row
	 * @param col
	 * @return
	 */
	private static int cntLiveNeighbors(int[][] board, int row, int col) {
		int cnt = 0;
		for (int i = row - 1; i <= row + 1; i++)
			for (int j = col - 1; j <= col + 1; j++)
				if (isInbounds(board, i, j) && board[i][j] == 1
						&& !(i == row && j == col))
					cnt++;
		return cnt;
	}

	/**
	 * Check a cell if it is inside of game board
	 * 
	 * @param board
	 * @param row
	 * @param col
	 * @return
	 */
	private static boolean isInbounds(int[][] board, int row, int col) {
		return row >= 0 && row < board.length && col >= 0
				&& col < board[0].length;
	}

	/**
	 * print a game board
	 * 
	 * @param pBoard
	 */
	private static void printBoard(int[][] pBoard) {
		String line = "";
		System.out.println("subsequent generation of:");
		for (int i = 0; i < pBoard.length; i++) {
			for (int j = 0; j < pBoard[0].length; j++) {
				line += pBoard[i][j] + " ";
			}
			System.out.println(line);
			line = "";
		}
	}
}
