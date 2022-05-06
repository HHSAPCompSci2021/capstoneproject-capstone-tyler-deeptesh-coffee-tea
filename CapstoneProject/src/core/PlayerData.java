package core;

public class PlayerData {
	
	public String username;
	public double x, y;
	public int r, g, b;
	public int shapeType;
	
	// One thing that is interesting is that the Firebase database cannot store arrays.
	// So, if you want to use a library class that uses arrays (the Color class is one such example), then
	// you need to store the data a different, simpler way yourself.
	// Note that ArrayLists *can* be stored.
	
	public PlayerData() {
		
	}
	
	public String getUsername() {
		return username;
	}

	public double getX() {
		return x;
	}

	public double getY() {
		return y;
	}

	public int getR() {
		return r;
	}

	public int getG() {
		return g;
	}

	public int getB() {
		return b;
	}

	public int getShapeType() {
		return shapeType;
	}
	
}
