import java.util.Scanner;

public class HumanPlayer implements Player {

	public GameGrid playerGrid;
    public GameGrid oppGrid;
    
    
	@Override
	public void makeMove() {
		// TODO Auto-generated method stub

		System.out.println("\nEnter a coord to target!");
		Scanner sc = new Scanner(System.in);

		System.out.println("Y coord");
		int y = sc.nextInt();

		System.out.println("X coord");
		int x = sc.nextInt();

		player1Redo = oppGrid.makeMove(oppGrid.getGrid(), y, x);
		player1Redo = game.generateAiMove(game.getGrid2());

	}
	
	

}
