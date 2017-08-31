
public class Main {

	/**
	 * @param args
	 * @throws InterruptedException 
	 * @throws Exception 
	 */
	public static void main(String[] args) throws InterruptedException {

		HighLifeBoard board = new HighLifeBoard(20, 20, true);
		
		for(int i = 0; i < 600; i++) {
			System.out.println(board);
			board.simulate();
			Thread.sleep(33);
		}
		
	}

}
