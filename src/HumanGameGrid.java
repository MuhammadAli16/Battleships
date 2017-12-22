import java.util.InputMismatchException;
import java.util.Scanner;

public class HumanGameGrid extends GameGrid {

	public HumanGameGrid(int size) {
		super(size);
	}
	
	@Override
//	public void placeShips() {
//		for (int i = 0; i < getShips().size(); i++) {
//			
//			Scanner sc = new Scanner(System.in);
//			System.out.println("\nEnter y:");
//			int y = sc.nextInt();
//			System.out.println("Enter x:");
//			int x = sc.nextInt();
//			System.out.println("Enter direction:");
//			Scanner sc2 = new Scanner(System.in);
//			String direction = sc2.nextLine();
//
//			// If user puts invalid coords then give error and decrement to do same loop again
//			if (!placeDirectionOfShip(getShips().get(i), y, x, direction)) {
//				i--;
//				System.out.println("Error: Space already taken or ship out of bounds. Try again");
//			} else {
//				System.out.println("Placed successfully!");
//				showGrid();
//			}
//			
//
//		}
//	}
	
	// AutoGen Ship placement
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
	
	// Make move on opposite grid
	public boolean makeMove(GameGrid oppGrid) {

		int y = 0;
		int x = 0;
		boolean validInput = true;
		// Keep asking until user enters valid input
		do {
			validInput = true;
			try {
				System.out.println("\nEnter a coord to target!");
				Scanner sc = new Scanner(System.in);

				System.out.println("Y coord");
				y = sc.nextInt();

				System.out.println("X coord");
				x = sc.nextInt();
			} catch (InputMismatchException ar){
				validInput = false;
				System.out.println("Error: Wrong input. Try again!");
			}
		} while (!validInput);
		

		return oppGrid.makeMove(oppGrid, y, x);

	}



}
