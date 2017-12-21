
public class TheGame {

	// set dimensions of grid!
	final int GRIDSIZE = 12;

	
	private GameGrid grid;
	private GameGrid grid2;
	
	Player player;
	Player player2;

	
	
	public TheGame() {
		grid = new AIGameGrid(GRIDSIZE);
		grid2 = new AIGameGrid(GRIDSIZE);
		
//		player = new Player();
//		player2 = new Player();

	}
	

	public GameGrid getGrid() {
		return grid;
	}

	public GameGrid getGrid2() {
		return grid2;
	}

	public boolean generateAiMove(GameGrid g){
		int y = 0 + (int)(Math.random() * ((GRIDSIZE-1 - 0) + 1));
		int x = 0 + (int)(Math.random() * ((GRIDSIZE-1 - 0) + 1));
		

		
		System.out.println("AI MAKING MOVES " + y + " " + x );
		
		return makeMove(g, y, x);
	}


	public boolean makeMove(GameGrid g, int y, int x){
		// if hit mark spot as hit and reduce health of ship
		if (g.getGrid()[y][x] > 1){
			System.out.println("ENEMY HIT");
			int value = g.getGrid()[y][x];
			
			Ship ship = g.getShips().stream().filter(u ->((Integer) u.getId()).equals(value)).findAny().get();
			ship.decrementHealth();
			
			g.getGrid()[y][x] = 1;
			
			if (ship.getHealth() == 0){
				System.out.println("SHIP DESTROYED");
				ship.setAlive(false);
				
			}
			return true;
		} else if (g.getGrid()[y][x] == 1 || g.getGrid()[y][x] == -1){
			System.out.println("Spot already hit");
		} else {
			g.getGrid()[y][x] = -1;
			System.out.println("You hit the ocean you twat");
		}
		
		return false;
	}
	
	public boolean checkIfAllDestroyed(GameGrid g){
		for(int i = 0; i < g.getShips().size(); i++){
			if (g.getShips().get(i).isAlive()){
				return false;
			}
		}
		return true;
	}
	
	

}
