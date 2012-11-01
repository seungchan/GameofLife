/**
 * Game board that utilize primitive integer type as cell
 * @author Seungchan Lee
 *
 */
public class GameBoard {

	private int[][] board;
	private int row;
	private int col;
	
	/**
	 * Constructor for GameBoard
	 * @param game board to initialize
	 * @param the size of row
	 * @param the size of column
	 */
	public GameBoard(int[][] newBoard, int newRow, int newCol) {
		this.board = newBoard;
		this.row = newRow;
		this.col = newCol;
	}

	/**
	 * Get next generation of a game board
	 * 
	 * @param the array of game board
	 * @return the subsequent generation of a game board
	 */
	public int[][] getNextGen() {
		int[][] newBoard = new int[this.row][this.col];
		int num;
		for (int i = 0; i < this.row; i++) {
			for (int j = 0; j < this.col; j++) {
				num = cntLiveNeighbors(i, j);
				if (isLiveNext(num, this.board[i][j] == 1))
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
	 * @param number of Neighbors
	 * @param the current state
	 * @return boolean of alive or dead
	 */
	public boolean isLiveNext(int cntNeighbors, boolean isCurrentLive) {
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
	 * @param index of row
	 * @param index of column
	 * @return the number of live neighbors for a given cell
	 */
	private int cntLiveNeighbors(int r, int c) {
		int cnt = 0;
		for (int i = r - 1; i <= r + 1; i++)
			for (int j = c - 1; j <= c + 1; j++)
				if (isInbounds(i, j) && this.board[i][j] == 1
						&& !(i == r && j == c))
					cnt++;
		return cnt;
	}

	/**
	 * Check a cell if it is inside of game board
	 * 
	 * @param index of row
	 * @param index of column
	 * @return 
	 */
	private boolean isInbounds(int r, int c) {
		return r >= 0 && r < this.row && c >= 0
				&& c < this.col;
	}

	/**
	 * Getter for game board array
	 * @return
	 */
	public int[][] getBoard() {
		return board;
	}
	
	/**
	 * Setter for game board array
	 * @param board
	 */
	public void setBoard(int[][] board) {
		this.board = board;
	}
	
	/**
	 * Get the row of game board
	 * @return
	 */
	public int getRow() {
		return row;
	}
	
	/**
	 * Set the row of game board
	 * @param row
	 */
	public void setRow(int row) {
		this.row = row;
	}
	
	/**
	 * Get the column of game board
	 * @return
	 */
	public int getCol() {
		return col;
	}
	
	/**
	 * Set the column of game board
	 * @param col
	 */
	public void setCol(int col) {
		this.col = col;
	}
}
