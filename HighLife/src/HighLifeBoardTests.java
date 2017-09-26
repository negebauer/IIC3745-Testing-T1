import static org.junit.Assert.*;

import org.junit.BeforeClass;
import org.junit.Test;

public class HighLifeBoardTests {

	static int length;
	static int width;
	static boolean[][] board;
	static HighLifeBoard highLifeBoard;

	@BeforeClass
	/**
	 *  Setamos valores que seran usados por otros tests
	 *  para reducir el código repetido.
	 *  En partícular nos interesa el board que se crea
	 *  a partir del predefinido, con lo cual podemos hacer
	 *  las pruebas necesarias.
	 *  La creación del board es probada en otro test
	 *  (shouldCreateBoardWithBoardTest)
	 *  @author @negebauer
	 */
	static public void setUp() {
		length = 10;
		width = 10;
		board = new boolean[][]{
			{ false, true, false, false, false, false, false, false, false, false },
			{ false, false, false, false, false, false, false, false, false, false },
			{ false, false, false, false, false, false, false, false, false, false },
			{ false, false, false, false, false, false, false, false, false, false },
			{ false, false, false, false, false, false, false, false, false, false },
			{ false, false, false, false, false, false, false, false, false, false },
			{ false, false, false, false, false, false, false, false, false, false },
			{ false, false, false, false, false, false, false, false, false, false },
			{ true, true, false, false, false, false, false, false, false, false },
			{ false, true, false, false, false, false, false, false, false, false }
		};
		highLifeBoard = new HighLifeBoard(board);
	}

	@Test
	/**
	 *  Revisa que un board tenga el tamaño adecuado
	 *  cuando se crea sin otras opciones
	 *  @author @negebauer
	 */
	public void shouldCreateBoardWithSizeTest() {
		HighLifeBoard highLifeBoard = new HighLifeBoard(length, width);
		boolean[][] instanceBoard = highLifeBoard.getData();
		assertEquals("Correct length", instanceBoard.length, length);
		assertEquals("Correct width",instanceBoard[0].length, width);
	}

	@Test
	/**
	 *  Revisa que un board tenga el tamaño adecuado,
	 *  también que este vacío cuando random es falso
	 *  @author @negebauer
	 */
	public void shouldCreateBoardWithSizeNoRandomTest() {
		HighLifeBoard highLifeBoard = new HighLifeBoard(length, width, false);
		boolean[][] instanceBoard = highLifeBoard.getData();
		assertEquals("Correct length", instanceBoard.length, length);
		assertEquals("Correct width",instanceBoard[0].length, width);
		for (int i = 0; i < instanceBoard.length; i++) {
			for (int j = 0; j < instanceBoard[i].length; j++) {
				assertFalse("Cell is dead", instanceBoard[i][j]);
			}
		}
	}

	@Test
	/**
	 *  Revisa que un board tenga el tamaño adecuado,
	 *  también que no este vacío cuando random es true
	 *  @author @negebauer
	 */
	public void shouldCreateBoardWithSizeAndRandomTest() {
		HighLifeBoard highLifeBoard = new HighLifeBoard(length, width, true);
		boolean[][] instanceBoard = highLifeBoard.getData();
		assertEquals("Correct length", instanceBoard.length, length);
		assertEquals("Correct width",instanceBoard[0].length, width);
		int aliveCount = 0;
		for (int i = 0; i < instanceBoard.length; i++) {
			for (int j = 0; j < instanceBoard[i].length; j++) {
				if (instanceBoard[i][j]) {
					aliveCount += 1;
				}
			}
		}
		assertTrue("Not empty", aliveCount > 0);
	}

	@Test
	/**
	 *  Revisa que un board es creado correctamente
	 *  a partir de un board predefinido. También revisa
	 *  que la referencia sea al mismo board.
	 *  @author @negebauer
	 */
	public void shouldCreateBoardWithBoardTest() {
		HighLifeBoard highLifeBoard = new HighLifeBoard(board);
		boolean[][] instanceBoard = highLifeBoard.getData();
		for (int i = 0; i < instanceBoard.length; i++) {
			for (int j = 0; j < instanceBoard[i].length; j++) {
				assertEquals("Equal board to instanceBoard", board[i][j], instanceBoard[i][j]);
			}
		}
		assertSame("Same board", board, instanceBoard);
	}

	@Test
	/**
	 *  Revisa que se sete correctamente el valor de una celda.
	 *  Revisa los casos bordes, donde se debe usar la celda borde.
	 *  @author @negebauer
	 */
	public void shouldSetCellTest() {
		int i = 0, j = 0;
		highLifeBoard.setCell(i, j, true);
		highLifeBoard.setCell(-1, -1, false);
		assertFalse("Set cell false", highLifeBoard.getData()[i][j]);
		highLifeBoard.setCell(i, j, true);
		assertTrue("Set cell true", highLifeBoard.getData()[i][j]);

		i = board.length - 1;
		j = board[i].length - 1;
		highLifeBoard.setCell(i, j, true);
		highLifeBoard.setCell(i + 1, j + 1, false);
		assertFalse("Set cell false", highLifeBoard.getData()[i][j]);
		highLifeBoard.setCell(i, j, true);
		assertTrue("Set cell true", highLifeBoard.getData()[i][j]);
	}

	@Test
	/**
	 *  Revisa si una celda esta viva siguiendo las siguientes reglas:
	 *  1. Dentro del board, retornar valor del board
	 *  2. Fuera del board, asumir que está muerta
	 *  @author @negebauer
	 */
	public void shouldCheckCellAlive() {
		assertFalse("0,0 is dead", highLifeBoard.isAlive(0,0));
		assertTrue("0,1 is alive", highLifeBoard.isAlive(0,1));
		assertFalse("Out of board is dead", highLifeBoard.isAlive(0,-1));
		assertFalse("Out of board is dead", highLifeBoard.isAlive(-1,0));
		assertFalse("Out of board is dead", highLifeBoard.isAlive(0,width));
		assertFalse("Out of board is dead", highLifeBoard.isAlive(length,0));
	}

	@Test
	/**
	 *  Revisa que se cuenta correctamente la cantidad de vecinos vivos
	 *  @author @negebauer
	 */
	public void shouldCountAliveNeighborsTest() {
		assertEquals("0,1 has 0 neighbors", 0, highLifeBoard.countAliveNeighbors(0,1));
		assertEquals("0,0 has 1 neighbors", 1, highLifeBoard.countAliveNeighbors(0,0));
		assertEquals("9,0 has 3 neighbors", 3, highLifeBoard.countAliveNeighbors(9,0));
		assertEquals("8,1 has 2 neighbors", 2, highLifeBoard.countAliveNeighbors(8,1));
	}

	@Test
	/**
	 *  Revisa que se decida correctamente si una célula sobrevive
	 *  @author @negebauer
	 */
	public void shouldDecideIfSurviveTest() {
		assertFalse("0,0 should die", highLifeBoard.shouldSurvive(0,0));
		assertFalse("0,1 should die", highLifeBoard.shouldSurvive(0,1));
		assertTrue("9,0 should survive", highLifeBoard.shouldSurvive(9,0));
		assertTrue("8,1 should survive", highLifeBoard.shouldSurvive(8,1));
	}

}
