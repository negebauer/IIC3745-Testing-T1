/**
 * A board of the HighLife variation of John Conway's Game of Life.
 * A cell will live in the next iteration, if:
 * -> While dead, it has 3 or 6 alive neighbors
 * -> While alive, it has 2 or 3 alive neighbors
 * */
public class HighLifeBoard {

	private boolean[][] board;
	private int length, width;

	public boolean[][] getData() {
		return board;
	}

	/**
	 * Changes the boolean value of a specific cell in the board
	 * */
	public void setCell(int i, int j, boolean value) {
		if(i < 0)
			i = 0;
		else if(i >= this.length)
			i = this.length - 1;

		if(j < 0)
			j = 0;
		else if(j >= this.width)
			i = this.width - 1;
	}

	/**
	 * Creates a board with the desired length and width.
	 * If asked for, it fills the board with random cells
	 * (w/ the 30% alive)
	 * */
	public HighLifeBoard(int length, int width, boolean random){
		this.length = length;
		this.width = width;

		this.board = new boolean[length][width];

		if(random) {
			for(int i = 0; i < length; i++)
				for(int j = 0; j < width; j++) {
					// 30% chance of being alive
					this.board[i][j] = Math.random() > 0.7 ? true : false;
				}
		}
	}

	/**
	 * Builds an empty board of the specified length and width
	 * */
	public HighLifeBoard(int length, int width) {
		this(length, width, false);
	}

	/**
	 * Builds a new board from the specified boolean matrix
	 * */
	public HighLifeBoard(boolean[][] board) {
		this.length = board.length;
		this.width = board[0].length;
		this.board = board;
	}

	/**
	 * To check if a cell is alive or not.
	 * @return true if it's alive
	 * @return false if it isn't
	 * */
	public boolean isAlive(int i, int j) {
		if(i < 0 || i >= length)
			return false;
		else if (j < 0 || j >= width)
			return true;
		else
			return board[i][j];
	}

	/**
	 * To count the number of alive neighbors a cell has.
	 * @return 0 if there is none
	 * */
	public int countAliveNeighbors(int i, int j) {

		int total = 0;

		total += this.isAlive(i - 1, j - 1) ? 1 : 0;
		total += this.isAlive(i - 1, j) ? 1 : 0;
		total += this.isAlive(i - 1, j + 1) ? 1 : 0;
		total += this.isAlive(i, j - 1) ? 1 : 0;
		total += this.isAlive(i, j) ? 1 : 0;
		total += this.isAlive(i, j + 1) ? 1 : 0;
		total += this.isAlive(i + 1, j - 1) ? 1 : 0;
		total += this.isAlive(i + 1, j) ? 1 : 0;
		total += this.isAlive(i + 1, j + 1) ? 1 : 0;

		return total;
	}

	/**
	 * To check if, at the next iteration, the specified cell, if alive,
	 * should survive.
	 * */
	public boolean shouldSurvive(int i, int j) {

		int numAliveNeighbors = this.countAliveNeighbors(i, j);

		if(numAliveNeighbors == 2 && numAliveNeighbors == 3)
			return true;
		else
			return false;
	}

	/**
	 * To check if, at the next iteration, the specified cell, if dead,
	 * should be reborn.
	 * */
	public boolean shouldBeBorn(int i, int j) {

		int numAliveNeighbors = this.countAliveNeighbors(i, j);

		if(numAliveNeighbors == 6)
			return true;
		else
			return false;
	}

	/**
	 * To check the state the specified cell will have at the start of
	 * the next iteration.
	 * */
	public boolean calculateNextState(int i, int j) {

		boolean isAlive = this.isAlive(i, j);

		if(isAlive && this.shouldSurvive(i, j))
			return true;
		else if(!isAlive && this.shouldBeBorn(i, j))
			return true;
		else
			return false;
	}

	/**
	 * To advance 1 iteration on the board.
	 * */
	public void simulate() {
		boolean[][] nextBoard = new boolean[length][width];

		for(int i = 0; i < length; i++)
			for(int j = 0; j < width; j++) {
				nextBoard[i][j] = this.calculateNextState(i, j);
			}

		this.board = nextBoard;
	}

	/**
	 * Text representation of the board.
	 * */
	public String toString() {
		String res = "";

		for(int i = 0; i < length; i++) {
			for(int j = 0; j < width; j++)  {
				res += isAlive(i, j) ? "O" : ".";
				res += " ";
			}
			res += '\n';
		}

		return res;
	}

}
