import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.geom.Area;

public class Triomino {
	// Size of 1 "block" in pixels
	public static final int size = 32;
	// Constants for identifier in construction
	public static final int EMPTY = -1, UL = 0, UR = 1, DL = 2, DR = 3;
	// Coordinates
	int x, y;
	// Shape
	Area shape;
	// Color
	Color drawCol;

	// Constructor: Pass in type and coordinates
	public Triomino(int ID, int x, int y) {
		// Save coords
		this.x = x;
		this.y = y;
		// Store location of base block
		// tets[x][y] = this;
		// Create base shape
		shape = new Area();
		addShapeLoc(x, y);
		// Set remaining shape and color based off type
		switch (ID) {
		default:
		case EMPTY: // No more shape, color black
			drawCol = Color.white;
			break;
		case UL: // Red, add to up and left
			addShapeLoc(x - 1, y);
			// tets[x-1][y] = this;
			addShapeLoc(x, y - 1);
			// tets[x][y-1] = this;
			drawCol = Color.red;
			break;
		case UR: // Green, add to up and right
			addShapeLoc(x + 1, y);
			// tets[x+1][y] = this;
			addShapeLoc(x, y - 1);
			// tets[x][y-1] = this;
			drawCol = Color.green;
			break;
		case DL: // Blue, add to down and left
			addShapeLoc(x - 1, y);
			// tets[x-1][y] = this;
			addShapeLoc(x, y + 1);
			// tets[x][y+1] = this;
			drawCol = Color.blue;
			break;
		case DR: // Green, add to down and right
			addShapeLoc(x + 1, y);
			// tets[x+1][y] = this;
			addShapeLoc(x, y + 1);
			// tets[x][y+1] = this;
			drawCol = Color.yellow;
			break;
		}
	}

	// Add to shape (used in constructor)
	void addShapeLoc(int x, int y) {
		shape.add(new Area(new Rectangle(x * size, y * size, size, size)));

	}

	// Draw triomino
	void draw(Graphics2D g) {
		// Draw shape
		g.setColor(drawCol);
		g.fill(shape);
		// Check if we should draw the border
		if (Application.drawBorder) {
			// Draw border
			g.setColor(Color.black);
			g.draw(shape);
		}
	}
}
