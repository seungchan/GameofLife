/**
 * Console application for game of life
 * Input : filename that contains game board.
 * Output : subsequent generation of game board.
 * For details of compilation and running the program, please look at README file.
 * This program scans the input file twice because we don't know the size of game of board.
 * 
 * @author Seungchan Lee
 * 
 */
public class GameofLife {
	
	/**
	 * Main function of the program
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		/* Set up initial board size and board cells from input file*/
		GameBoardUtil.setFileName();
		int[] boardSize = GameBoardUtil.initBoardSize();
		int[][] initBoard = GameBoardUtil.setBoard(boardSize);
		
		/* initialize game board */
		GameBoard board = new GameBoard(initBoard, boardSize[0],boardSize[1]);

		/* get the subsequent game board */
		int[][] nextBoard = board.getNextGen();
		
		/* print the game board in console */
		GameBoardUtil.printBoard(nextBoard);
	}
}
