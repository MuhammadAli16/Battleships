import java.util.ArrayList;

public class TheGame {

	// set dimensions of grid!
	final int NUM = 5;
	final int COLUMN = NUM;
	final int ROW = NUM;
	
	private GameGrid grid;
	private GameGrid grid2;
	
	Player player;
	Player player2;

	
	
	public TheGame() {
		grid = new HumanGameGrid(ROW, COLUMN);
		grid2 = new AIGameGrid(ROW, COLUMN);
		
		player = new Player();
		player2 = new Player();

	}
	

	public GameGrid getGrid() {
		return grid;
	}



	public GameGrid getGrid2() {
		return grid2;
	}

	public boolean generateAiMove(GameGrid g){
		int y = 0 + (int)(Math.random() * ((NUM-1 - 0) + 1));
		int x = 0 + (int)(Math.random() * ((NUM-1 - 0) + 1));
		

		
		System.out.println("AI MAKING MOVES " + y + " " + x );
		
		return makeMove(g, y, x);
	}


	public boolean makeMove(GameGrid g, int y, int x){
		// if hit mark spot as hit and reduce health of ship
		if (g.getGrid()[y][x] != 0 && g.getGrid()[y][x] != 1){
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
		} else if (g.getGrid()[y][x] == 1){
			System.out.println("Spot already hit");
		} else {
			g.getGrid()[y][x] = -1;
			System.out.println("You hit the ocean you twat");
		}
		
		return false;
	}
	
//	public boolean makeMoveAi(GameGrid g){
//		// min + ran * max - min + 1
//		int y = NUM + (int)(Math.random() * ((NUM - 0) + 1));
//		int x = NUM + (int)(Math.random() * ((NUM - 0) + 1));
//		
//		// if hit mark spot as hit and reduce health of ship
//		if (grid.getGrid()[y][x] != 0 && grid.getGrid()[y][x] != 1){
//			System.out.println("ENEMY HIT");
//			int value = grid.getGrid()[y][x];
//			
//			Ship ship = grid.getShips().stream().filter(u ->((Integer) u.getId()).equals(value)).findAny().get();
//			ship.decrementHealth();
//			
//			grid.getGrid()[y][x] = 1;
//			
//			if (ship.getHealth() == 0){
//				System.out.println("SHIP DESTROYED");
//				ship.setAlive(false);
//				
//			}
//			return true;
//		} else if (grid.getGrid()[y][x] == 1){
//			System.out.println("Spot already hit");
//		} else if (grid.getGrid()[y][x] == 0){
//			grid.getGrid()[y][x] = -1;
//			System.out.println("You hit the ocean you twat");
//		}
//		return false;
//	}
	
	public boolean checkIfAllDestroyed(GameGrid g){
		for(int i = 0; i < g.getShips().size(); i++){
			if (g.getShips().get(i).isAlive()){
				return false;
			}
		}
		return true;
	}
	
	

}
