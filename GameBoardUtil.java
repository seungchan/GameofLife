import java.io.File;
import java.util.Scanner;

/**
 * Util class processing input file
 * @author Seungchan Lee
 *
 */
public class GameBoardUtil {
	
	private static String filename;
	/**
	 * Get input filename from the console input. Use Scanner that provides more
	 * functionality than BufferedReader
	 */
	public static void setFileName() {
		Scanner consoleInput = new Scanner(System.in);
		System.out.print("Enter file name to open:");
		filename = consoleInput.next();
	}

	/**
	 * Verify input file format, and get the size of board
	 * 
	 * @return the size of a game board
	 */
	public static int[] initBoardSize() {
		System.out.println(filename);
		int[] bSize = new int[2];
		int row = 0, col = 0;
		File file = new File(filename);
		Scanner fileScanner = null;
		try {
			fileScanner = new Scanner(file);
			String sCurrentLine;

			int prevCol = 0;
			while (fileScanner.hasNext()) {
				sCurrentLine = fileScanner.nextLine();
				String[] currentStrs = sCurrentLine.split(" ");
				if (row == 0)
					prevCol = currentStrs.length;
				else {
					col = currentStrs.length;
					if (prevCol != col) {
						System.out.print(filename
								+ " file format is not correct");
						System.exit(0);
					}
					prevCol = col;
				}
				row++;
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
	 * @param board size
	 * @return the array of initialized game board
	 */
	public static int[][] setBoard(int[] boSize) {
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
	 * print a game board
	 * 
	 * @param game board
	 */
	public static void printBoard(int[][] pBoard) {
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
