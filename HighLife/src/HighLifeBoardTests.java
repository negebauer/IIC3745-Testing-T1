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
			{ true, true, false, false, false, true, true, false, false, false },
			{ false, true, false, false, false, true, true, false, false, false },
			{ true, true, false, false, false, true, true, false, false, false },
			{ false, true, false, false, false, true, true, false, false, false },
			{ true, true, false, false, false, true, true, false, false, false },
			{ true, true, false, false, false, true, true, false, false, false },
			{ true, true, false, false, false, true, true, false, false, false },
			{ true, true, false, false, false, true, true, false, false, false },
			{ true, true, false, false, false, true, true, false, false, false },
			{ true, true, false, false, false, true, true, false, false, false }
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
	 *  a partir de un board predefinido
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
	}

}
