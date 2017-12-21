import java.util.ArrayList;

public abstract class GameGrid {

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

	public static void printRow(int[] row) {
		for (int i = 0; i < row.length; i++) { // : row) {
			if (row[i] == 1) {
				System.out.print("x" + row[i]);
			} else if (row[i] == -1) {
				System.out.print("~" + row[i]);
			} else {
				System.out.print("*" + row[i]);
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

	public boolean placeDirectionOfShip(Ship ship, int y, int x, String direction) {

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

				if (direction.equals("north")) {
					y--;
				} else if (direction.equals("east")) {
					x++;
				} else if (direction.equals("south")) {
					y++;
				} else if (direction.equals("west")) {
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

	public boolean errorCheckingForPlacementsOfShips(Ship ship, int y, int x, String direction) {

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

		// check if ship will go out of bounds
		int check = 0;
		if (direction.equals("north")) {
			check = (y-1) - shipSize;
		} else if (direction.equals("east")) {
			check = (x-1) + shipSize;
		} else if (direction.equals("south")) {
			check = (y-1) + shipSize;
		} else if (direction.equals("west")) {
			check = (x-1) - shipSize;
		} else {
			System.out.println(check);
			return false;
		}

		// Out of bounds check
		if (check > getSize() || check < 0) {
			System.out.println("WUT4 " + check);
			return false;
		}

		// check if each coord after initial is available
		for (int i = 1; i < ship.getSize(); i++) {

			if (direction.equals("north")) {
				y--;
			} else if (direction.equals("east")) {
				x++;
			} else if (direction.equals("south")) {
				y++;
			} else if (direction.equals("west")) {
				x--;
			}

			if (x > getSize() || x < 0 || y > getSize() || y < 0 || getGrid()[y][x] != 0) {
				return false;
			}

		}

		return true;
	}

	
	public abstract void placeShips();

	

}
