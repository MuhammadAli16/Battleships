package battleShips;
import java.util.ArrayList;

public abstract class GameGrid {

	private boolean HAX = true;
	
	private int size;
	private int[][] grid;
	private ArrayList<Ship> ships = new ArrayList<>();

	public GameGrid(int size) {
		this.grid = new int[size][size];
		this.size = size;
	}
	
	public int getSize() {
		return size-1;
	}


	public int[][] getGrid() {
		return grid;
	}

	public void setGrid(int[][] grid) {
		this.grid = grid;
	}

	public ArrayList<Ship> getShips() {
		return ships;
	}
	

	// Functions of grid
	public void showGrid() {
		int twoDm[][] = getGrid();
		
		// print side numbers and row
		for (int x = 0; x < twoDm.length; x++) {
			System.out.print(x + "\t");
			printRow(twoDm[x]);
		}
		
		// print bottom numbers of grid
		System.out.print("\t");
		for (int x = 0; x < twoDm.length; x++) {
			System.out.print(x + "\t");
		}
		System.out.println();
		System.out.println();
	}

	public void printRow(int[] row) {
		for (int i = 0; i < row.length; i++) { // : row) {
			if (HAX){
				if (row[i] == 1) {
					System.out.print("x" + row[i]);
				} else if (row[i] == -1) {
					System.out.print("~" + row[i]);
				} else {
					System.out.print("*" + row[i]);
				}
			} else {
				if (row[i] == 1) {
					System.out.print("x");
				} else if (row[i] == -1) {
					System.out.print("~");
				} else {
					System.out.print("*");
			}
			
			}

			System.out.print("\t");
		}
		System.out.println();
	}

	public void createShips() {
		Ship patrol1 = new Ship("Patrol", 2);
		Ship patrol2 = new Ship("Patrol", 2);
		Ship battleship1 = new Ship("battleship", 3);
		Ship battleship2 = new Ship("battleship", 3);
		Ship submarine = new Ship("submarine", 3);
		Ship destroyer = new Ship("destroyer", 4);
		Ship carrier = new Ship("carrier", 5);

		
		// Add larger ships 1st to avoid complications!
		ships.add(carrier);
		ships.add(destroyer);
		ships.add(submarine);
		ships.add(battleship2);
		ships.add(battleship1);
		ships.add(patrol2);
		ships.add(patrol1);
	}

	public boolean placeDirectionOfShip(Ship ship, int y, int x, Direction direction) {

		int id = ship.getId();
		int shipSize = ship.getSize();

		boolean canPlace = errorCheckingForPlacementsOfShips(ship, y, x, direction);

		if (canPlace) {

			// place inital
			if (getGrid()[y][x] == 0) {
				getGrid()[y][x] = id;
			} else {
				return false;
			}

			// Do the Placements !!
			for (int i = 1; i < shipSize; i++) {

				if (direction.equals(Direction.NORTH)) {
					y--;
				} else if (direction.equals(Direction.EAST)) {
					x++;
				} else if (direction.equals(Direction.SOUTH)) {
					y++;
				} else if (direction.equals(Direction.WEST)) {
					x--;
				}
				// only place if nothing on the spot
				if (getGrid()[y][x] == 0) {
					getGrid()[y][x] = id;
				} else {
					return false;
				}

			}
		} else {
			return false;
		}
		return true;

	}

	public boolean errorCheckingForPlacementsOfShips(Ship ship, int y, int x, Direction direction) {

		int shipSize = ship.getSize();

		// check if coords within bounds
		if (x > getSize() || x < 0 || y > getSize() || y < 0) {
			System.out.println("WUT");
			return false;
		}
		// check if current coord is available
		if (getGrid()[y][x] != 0) {
			System.out.println("WUT2");
			return false;
		}

		// check if each coord after initial is available
		for (int i = 1; i < ship.getSize(); i++) {
			
			switch (direction){
			case NORTH:
				y--;
				break;
			case EAST:
				x++;
				break;
			case SOUTH:
				y++;
				break;
			case WEST:
				x--;
				break;
			default:
				break;
			
			}

			if (x > getSize() || x < 0 || y > getSize() || y < 0 || getGrid()[y][x] != 0) {
				return false;
			}

		}

		return true;
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
			System.out.println("You hit the ocean");
		}
		
		return false;
	}

	public void showHealthOfShips(){
		for (int i = 0; i < ships.size(); i++){
			Ship ship = ships.get(i);
			System.out.println(ship.getShipName() + ": " + ship.getHealth() + "/" + ship.getSize());
		}
	}
	
	public abstract void placeShips();
	public abstract boolean genMove(GameGrid oppGrid);

	

}
