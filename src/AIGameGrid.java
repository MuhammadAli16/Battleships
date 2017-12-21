
public class AIGameGrid extends GameGrid {

	public AIGameGrid(int rowSize, int columnSize) {
		super(rowSize, columnSize);
	}

	// AI METHOD OF PLACING SHIPS
	@Override
	public void placeShips() {

		for (int i = 0; i < getShips().size(); i++) {
			System.out.println("Placing ship: " + getShips().get(i).getShipName());
			int randomRow = (int) (Math.random() * (getRowSize() - 0));
			int randomColumn = (int) (Math.random() * (getColumnSize() - 0));
			int directionInt = 1 + (int)(Math.random() * ((4 - 1) + 1));

			String direction = null;
			switch (directionInt) {
			case 1:
				direction = "north";
				break;
			case 2:
				direction = "east";
				break;
			case 3:
				direction = "south";
				break;
			case 4:
				direction = "west";
				break;
			}
	
			// If user puts invalid coords then give error and decrement to do
			// same loop again
			System.out.println("AI COORDs: " + randomRow + " " + randomColumn + " " + direction);
			if (!placeDirectionOfShip(getShips().get(i), randomRow, randomColumn, direction)) {
				i--;
				System.out.println("Error: Space already taken or ship out of bounds. Try again");
			}else {
				System.out.println("Placed successfully! " + randomRow + " " + randomColumn);
				showGrid();
			}


		}

	}

}
