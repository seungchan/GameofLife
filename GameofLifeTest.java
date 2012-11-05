import static org.junit.Assert.*;
import java.util.Arrays;
import org.junit.Test;

public class GameofLifeTest {

	@Test
	public void testIsLiveNext() {
		// Test based on the number of neighbors and current state
		GameBoard board = new GameBoard(new int[1][1],1,1);
		assertEquals(true,board.isLiveNext(2,true));
		assertEquals(true,board.isLiveNext(3,true));
		assertEquals(true,board.isLiveNext(3,false));
		assertEquals(false,board.isLiveNext(0,true));
		assertEquals(false,board.isLiveNext(1,true));
		assertEquals(false,board.isLiveNext(4,true));
		assertEquals(false,board.isLiveNext(5,true));
		assertEquals(false,board.isLiveNext(6,true));
		assertEquals(false,board.isLiveNext(7,true));
		assertEquals(false,board.isLiveNext(8,true));
		assertEquals(false,board.isLiveNext(0,false));
		assertEquals(false,board.isLiveNext(1,false));
		assertEquals(false,board.isLiveNext(2,false));
		assertEquals(false,board.isLiveNext(4,false));
		assertEquals(false,board.isLiveNext(5,false));
		assertEquals(false,board.isLiveNext(6,false));
		assertEquals(false,board.isLiveNext(7,false));
		assertEquals(false,board.isLiveNext(8,false));
	}
	
	@Test
	public void testGetNextGen() {
		int[][] nextBoard = getNextBoard("Testcases\\input.txt");
		int[][] outBoard = getOutBoard("Testcases\\output.txt");
		assertTrue(Arrays.deepEquals(nextBoard,outBoard));
		nextBoard = getNextBoard("Testcases\\input2.txt");
		outBoard = getOutBoard("Testcases\\output2.txt");
		assertTrue(Arrays.deepEquals(nextBoard,outBoard));
		nextBoard = getNextBoard("Testcases\\input3.txt");
		outBoard = getOutBoard("Testcases\\output3.txt");
		assertTrue(Arrays.deepEquals(nextBoard,outBoard));
		nextBoard = getNextBoard("Testcases\\input4.txt");
		outBoard = getOutBoard("Testcases\\output4.txt");
		assertTrue(Arrays.deepEquals(nextBoard,outBoard));
		nextBoard = getNextBoard("Testcases\\input5.txt");
		outBoard = getOutBoard("Testcases\\output5.txt");
		assertTrue(Arrays.deepEquals(nextBoard,outBoard));
		nextBoard = getNextBoard("Testcases\\input6.txt");
		outBoard = getOutBoard("Testcases\\output6.txt");
		assertTrue(Arrays.deepEquals(nextBoard,outBoard));
	}
	
	private int[][] getNextBoard(String inputfile) {
		GameBoardUtil.setFileName(inputfile);
		int[] boardSize = GameBoardUtil.initBoardSize();
		int[][] initBoard = GameBoardUtil.setBoard(boardSize);
		GameBoard board = new GameBoard(initBoard, boardSize[0],boardSize[1]);
		int[][] nextBoard = board.getNextGen();
		return nextBoard;
	}
	
	private int[][] getOutBoard(String outputfile) {
		GameBoardUtil.setFileName(outputfile);
		int[] outBoardSize = GameBoardUtil.initBoardSize();
		int[][] outBoard = GameBoardUtil.setBoard(outBoardSize);
		return outBoard;
	}

}
