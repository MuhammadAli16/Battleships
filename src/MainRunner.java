import java.util.Scanner;

public class MainRunner {

	public static void main(String[] args) throws InterruptedException {
		TheGame game = new TheGame();

		// Player 1
		 game.getGrid().createShips();
		 game.getGrid().placeShips();
		// game.getGrid().showGrid();

		// Player 2
		System.out.println("AI MAKING GRID LOOK AWAY NOW!");
		Thread.sleep(2000);
		game.getGrid2().createShips();
		game.getGrid2().placeShips();
		// game.getGrid2().showGrid();

		// Check if game is finished
		boolean gameOver = false;
		// Check to see if hit target - if true go again
		boolean player1Redo = false;
		boolean player2Redo = false;
		do {
			// Player 1 move
			do {
//				System.out.println("\nEnter a coord to target!");
//				Scanner sc = new Scanner(System.in);
//
//				System.out.println("Y coord");
//				int y = sc.nextInt();
//
//				System.out.println("X coord");
//				int x = sc.nextInt();

				//player1Redo = game.makeMove(game.getGrid2(), y, x);
				player1Redo = game.generateAiMove(game.getGrid2());
				System.out.println("AI GRID");
				game.getGrid2().showGrid();
				gameOver = game.checkIfAllDestroyed(game.getGrid2());
			} while (player1Redo && !gameOver);

			// Player 2 move
			if (!gameOver){
				do {
					System.out.println("\nAi choosing target!");
					
					player2Redo = game.generateAiMove(game.getGrid());
					System.out.println("USER GRID");
					game.getGrid().showGrid();
					gameOver = game.checkIfAllDestroyed(game.getGrid());
				} while (player2Redo);
			}
			

		} while (!gameOver);
		System.out.println("GAME OVER!");
	}

}
