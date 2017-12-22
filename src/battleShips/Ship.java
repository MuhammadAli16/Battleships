package battleShips;

public class Ship {

	private static int counter = 1; 
	
	private String shipName;
	private int id;
	private int size;
	private int health;
	private boolean alive;
	
	public Ship(String shipName, int size) {
		super();
		this.id = ++counter;
		this.shipName = shipName;
		this.size = size;
		this.health = size;
		this.alive = true;
	}

	public int getHealth() {
		return health;
	}

	public void decrementHealth() {
		--this.health;
	}

	public boolean isAlive() {
		return alive;
	}

	public void setAlive(boolean alive) {
		this.alive = alive;
	}

	public String getShipName() {
		return shipName;
	}

	public int getId() {
		return id;
	}

	public int getSize() {
		return size;
	}
	
	
	
	
	
}
