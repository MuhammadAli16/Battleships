import java.util.Scanner;

public class HumanGameGrid extends GameGrid {

	
	
	public HumanGameGrid(int rowSize, int columnSize) {
		super(rowSize, columnSize);
	}
	
	@Override
	public void placeShips() {
		for (int i = 0; i < getShips().size(); i++) {
			
			Scanner sc = new Scanner(System.in);
			System.out.println("\nEnter y:");
			int y = sc.nextInt();
			System.out.println("Enter x:");
			int x = sc.nextInt();
			System.out.println("Enter direction:");
			Scanner sc2 = new Scanner(System.in);
			String direction = sc2.nextLine();

			// If user puts invalid coords then give error and decrement to do same loop again
			if (!placeDirectionOfShip(getShips().get(i), y, x, direction)) {
				i--;
				System.out.println("Error: Space already taken or ship out of bounds. Try again");
			} else {
				System.out.println("Placed successfully!");
				showGrid();
			}
			

		}
	}



}
