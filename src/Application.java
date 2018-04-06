import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class Application extends JPanel {
	// Serial ID
	private static final long serialVersionUID = 1L;
	// Display size
	static final int wid = 1024, hei = 1024;
	// Grid size
	int size;
	// Triomino list
	List<Triomino> TriominoList;
	// Draw tetromino border?
	static boolean drawBorder = true;

	public Application(int n, int x, int y) {
		// Store grid size
		size = (int) Math.pow(2, n);
		// If n is too large, don't draw borders on triominos
		if (n > 8) {
			drawBorder = false;
		}
		// Generate tetrominos
		genTriominos(x, y);
		// Create window
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				initWindow();
			}
		});
	}

	// Make container window
	void initWindow() {
		// Set size
		setPreferredSize(new Dimension(wid, hei));
		// Improves rendering
		setDoubleBuffered(true);
		// Default bgcolor
		setBackground(Color.BLACK);
		// Make window
		JFrame window = new JFrame();
		// Add app
		window.add(this);
		// Not resizable
		window.setResizable(false);
		// Window header
		window.setTitle("Discrete Math Demo");
		// Close app on close
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		// Pack dimensions
		window.pack();
		// Center
		window.setLocationRelativeTo(null);
		// Display
		window.setVisible(true);
	}

	// Overrride draw
	@Override
	protected void paintComponent(Graphics graphics) {
		// Graphics2D for increased access to functions
		Graphics2D g = (Graphics2D) graphics;
		// Scale triominos to window size
		g.scale((float) wid / (Triomino.size * (size)), (float) hei / (Triomino.size * (size)));
		// Draw triominos
		for (Triomino t : TriominoList) {
			t.draw(g);
		}

	}

	void genTriominos(int x, int y) {
		// Create list
		TriominoList = new ArrayList<Triomino>();
		// Add empty square at location
		TriominoList.add(new Triomino(Triomino.EMPTY, x, y));

		// Relative locations to build off of
		int loff = 1, roff = 1, uoff = 1, doff = 1;
		// Recursively fill triomino shapes building off of existing block
		for (int i = 1; i < size; i *= 2) {
			// Get location to build off
			final boolean left = (x & i) == 0;
			final boolean up = (y & i) == 0;
			if (left) { // Left side
				if (up) { // Top side
					fillTriomino(Triomino.UL, x+loff, y+uoff, i);
					loff += i;
					uoff += i;
				} else { // Bottom side
					fillTriomino(Triomino.DL, x+loff, y-doff, i);
					loff += i;
					doff += i;
				}
			} else { // Right side
				if (up) { // Top side
					fillTriomino(Triomino.UR, x-roff, y+uoff, i);
					roff += i;
					uoff += i;
				} else { // Bottom side
					fillTriomino(Triomino.DR, x-roff, y-doff, i);
					roff += i;
					doff += i;
				}

			}
		}
	}

	void fillTriomino(int ID, int x, int y, int scale) {
		// Break recursion at scale = 1.
		if (scale == 1) {
			TriominoList.add(new Triomino(ID, x, y));
			return;
		}
		// Get relative scale for recursion use
		int halfOff = scale / 2, fullOff = scale / 2 + 1;
		// A size 2n triomino is composed of 4 size n triominos.
		// One at the inside corner, and 3 touching each edge.
		// Depending on shape, call recursive function to fill.
		switch (ID) {
		default: // Unidentified. Catch bad case.
			break;
		case Triomino.UL: // _| shape
			// Fill core triomino
			fillTriomino(Triomino.UL, x, y, scale / 2);
			// Fill top right triomino
			fillTriomino(Triomino.DL, x + halfOff, y - fullOff, scale / 2);
			// Fill bottom right triomino
			fillTriomino(Triomino.UL, x + halfOff, y + halfOff, scale / 2);
			// Fill bottom left triomino
			fillTriomino(Triomino.UR, x - fullOff, y + halfOff, scale / 2);
			break;

		case Triomino.UR: // |_ shape
			// Fill core triomino
			fillTriomino(Triomino.UR, x, y, scale / 2);
			// Fill top left triomino
			fillTriomino(Triomino.DR, x - halfOff, y - fullOff, scale / 2);
			// Fill bottom left triomino
			fillTriomino(Triomino.UR, x - halfOff, y + halfOff, scale / 2);
			// Fill bottom right triomino
			fillTriomino(Triomino.UL, x + fullOff, y + halfOff, scale / 2);
			break;

		case Triomino.DL: // --| shape
			// Fill core triomino
			fillTriomino(Triomino.DL, x, y, scale / 2);
			// Fill top right triomino
			fillTriomino(Triomino.UL, x + halfOff, y + fullOff, scale / 2);
			// Fill bottom right triomino
			fillTriomino(Triomino.DL, x + halfOff, y - halfOff, scale / 2);
			// Fill bottom left triomino
			fillTriomino(Triomino.DR, x - fullOff, y - halfOff, scale / 2);
			break;
		case Triomino.DR: // |-- shape
			// Fill core triomino
			fillTriomino(Triomino.DR, x, y, scale / 2);
			// Fill top right triomino
			fillTriomino(Triomino.UR, x - halfOff, y + fullOff, scale / 2);
			// Fill bottom right triomino
			fillTriomino(Triomino.DR, x - halfOff, y - halfOff, scale / 2);
			// Fill bottom left triomino
			fillTriomino(Triomino.DL, x + fullOff, y - halfOff, scale / 2);
			break;

		}
	}
}
