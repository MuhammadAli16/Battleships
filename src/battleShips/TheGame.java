package battleShips;

public class TheGame {

	// set dimensions of grid!
	final static int GRIDSIZE = 12;

	private GameGrid player1Grid;
	private GameGrid player2Grid;

	public TheGame() {
		player1Grid = new HumanGameGrid(GRIDSIZE);
		player2Grid = new AIGameGrid(GRIDSIZE);

	}

	public GameGrid getGrid() {
		return player1Grid;
	}

	public GameGrid getGrid2() {
		return player2Grid;
	}

	// Initialise game - create grids and placement of fleets
	public void startGame() {
		// player1Grid = getGrid();
		// player2Grid = getGrid2();

		// Player 1
		System.out.println("PLAYER 1 MAKING GRID LOOK AWAY NOW!");
		player1Grid.createShips();
		player1Grid.placeShips();

		// Player 2
		System.out.println("PLAYER 2 MAKING GRID LOOK AWAY NOW!");
		player2Grid.createShips();
		player2Grid.placeShips();

		// users start to take turns
		gameLoop();

	}

	// Actual game play starts!
	public void gameLoop() {
		// Check if game is finished
		boolean gameWinnerPlyr1 = false;
		boolean gameWinnerPlyr2 = false;
		// Check to see if hit target - if true go again
		boolean player1Redo = false;
		boolean player2Redo = false;

		do {
			// Player 1 move
			do {
				System.out.println("\nPlayer 1, your turn!");
				player1Redo = getGrid().genMove(getGrid2());

				System.out.println("\nPlayer 2 GRID");
				getGrid2().showGrid();
				getGrid2().showHealthOfShips();

				gameWinnerPlyr1 = checkIfAllDestroyed(getGrid2());

			} while (player1Redo && !gameWinnerPlyr1);

			// Player 2 move
			if (!gameWinnerPlyr1) {
				do {
					System.out.println("\nPlayer 2, your turn!");
					player2Redo = getGrid2().genMove((getGrid()));
					
					System.out.println("\nUSER GRID");
					getGrid().showGrid();
					getGrid().showHealthOfShips();
					
					gameWinnerPlyr2 = checkIfAllDestroyed(getGrid());
				} while (player2Redo && !gameWinnerPlyr2);
			}

		} while (!gameWinnerPlyr1 && !gameWinnerPlyr2);

		if (gameWinnerPlyr1) {
			System.out.println("Player 1 is Victorius!");
		} else if (gameWinnerPlyr2) {
			System.out.println("Player 2 is Victorius!");
		} else {
			System.out.println("This should never be shown");
		}
	}

	public boolean checkIfAllDestroyed(GameGrid g) {
		for (int i = 0; i < g.getShips().size(); i++) {
			if (g.getShips().get(i).isAlive()) {
				return false;
			}
		}
		return true;
	}

}
