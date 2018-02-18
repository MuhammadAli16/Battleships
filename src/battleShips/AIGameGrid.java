package battleShips;

public class AIGameGrid extends GameGrid {

	public AIGameGrid(int size) {
		super(size);
	}

	// AI METHOD OF PLACING SHIPS
	@Override
	public void placeShips() {

		for (int i = 0; i < getShips().size(); i++) {
			System.out.println("Placing ship: " + getShips().get(i).getShipName());
			int randomRow = (int) (Math.random() * (getSize() - 0));
			int randomColumn = (int) (Math.random() * (getSize() - 0));
			int directionInt = 1 + (int) (Math.random() * ((4 - 1) + 1));

			Direction direction = null;
			switch (directionInt) {
			case 1:
				direction = Direction.NORTH;
				break;
			case 2:
				direction = Direction.EAST;
				break;
			case 3:
				direction = Direction.SOUTH;
				break;
			case 4:
				direction = Direction.WEST;
				break;
			}
			
			System.out.println("AI DIRECTION " + direction);

			// If user puts invalid coords then give error and decrement to do same loop again
			System.out.println("AI COORDs: " + randomRow + " " + randomColumn + " " + direction);
			if (!placeDirectionOfShip(getShips().get(i), randomRow, randomColumn, direction)) {
				i--;
				System.out.println("Error: Space already taken or ship out of bounds. Try again");
			} else {
				System.out.println("Placed successfully! " + randomRow + " " + randomColumn);
				showGrid();
			}

		}

	}

	// Make moves
	@Override
	public boolean genMove(GameGrid oppGrid) {
		int y = 0 + (int) (Math.random() * ((getSize() - 0) + 1));
		int x = 0 + (int) (Math.random() * ((getSize() - 0) + 1));

		System.out.println("AI MAKING MOVES " + y + " " + x);

		return oppGrid.makeMove(oppGrid, y, x);
	}

}
