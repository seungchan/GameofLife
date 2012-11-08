/**
 * Game board that utilize primitive integer type as cell
 * @author Seungchan Lee
 *
 */
public class GameBoard {

	private int[][] board;
	private int rowSize;
	private int colSize;
	
	public GameBoard(int[][] newBoard, int rowSize, int colSize) {
		this.board = newBoard;
		this.rowSize = rowSize;
		this.colSize = colSize;
	}

	/**
	 * @return the subsequent generation of a game board
	 */
	public int[][] getNextGeneration() {
		int[][] newBoard = new int[this.rowSize][this.colSize];
		int num;
		for (int i = 0; i < this.rowSize; i++) {
			for (int j = 0; j < this.colSize; j++) {
				num = countLiveNeighborsForCell(i, j);
				if (isCellLiveNext(num, this.board[i][j] == 1))
					newBoard[i][j] = 1;
				else
					newBoard[i][j] = 0;
			}
		}
		return newBoard;
	}

	/**
	 * Check a cell if it is alive (1) or dead (0) for next generation
	 * @return boolean of alive or dead
	 */
	public boolean isCellLiveNext(int numberOfLiveNeighbors, boolean isCellLiveCurrent) {
		if (isCellLiveCurrent && (numberOfLiveNeighbors == 2 || numberOfLiveNeighbors == 3))
			return true;
		else if (!isCellLiveCurrent && numberOfLiveNeighbors == 3)
			return true;
		else
			return false;
	}

	/**
	 * 
	 * @return the number of live neighbors for a given cell
	 */
	private int countLiveNeighborsForCell(int rowIndex, int columnIndex) {
		int cnt = 0;
		for (int i = rowIndex - 1; i <= rowIndex + 1; i++)
			for (int j = columnIndex - 1; j <= columnIndex + 1; j++)
				if (isCellInbounds(i, j) && this.board[i][j] == 1
						&& !(i == rowIndex && j == columnIndex))
					cnt++;
		return cnt;
	}

	/**
	 * Check a cell if it is inside of game board
	 */
	private boolean isCellInbounds(int rowIndex, int columnIndex) {
		return rowIndex >= 0 && rowIndex < this.rowSize && columnIndex >= 0
				&& columnIndex < this.colSize;
	}

	public int[][] getBoard() {
		return board;
	}
	
	public void setBoard(int[][] board) {
		this.board = board;
	}
	
	public int getRowSize() {
		return rowSize;
	}
	
	public void setRowSize(int row) {
		this.rowSize = row;
	}
	
	public int getColSize() {
		return colSize;
	}
	
	public void setColSize(int col) {
		this.colSize = col;
	}
}
