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
	// Window size
	static final int wid = 800, hei = 800;
	// Grid size
	int size;
	// List
	List<Triomino> TriominoList;
	// Draw tetromino border?
	static boolean drawBorder = true;

	public Application(int n, int x, int y) {
		// Store array size
		size = (int) Math.pow(2, n);
		// If n is too large, don't draw border
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
		setBounds(0, 0, wid, hei);
		// Improves rendering
		setDoubleBuffered(true);
		// Default bgcolor
		setBackground(Color.BLACK);
		// Allow freeform positioning
		setLayout(null);
		// Make window
		JFrame window = new JFrame();
		// Add app
		window.add(this);
		// Let user select (to get key inputs)
		window.setFocusable(true);
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
		// Scale tetrominos to window size
		g.scale((float) wid / (Triomino.size * (size)), (float) hei / (Triomino.size * (size)));
		// Draw tetrominos
		for (Triomino t : TriominoList) {
			t.draw(g);
		}

	}

	void genTriominos(int x, int y) {
		// Create list
		TriominoList = new ArrayList<Triomino>();
		// Bottom right is empty
		TriominoList.add(new Triomino(Triomino.EMPTY, x, y));

		// Existing block locations
		int loff = 1, roff = 1, uoff = 1, doff = 1;
		// Recursively fill triomino shapes building off of existing block
		for (int i = 1; i < size; i *= 2) {
			// Get location modded by scale
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
		// Depending on shape, call recursive function to fill.
		switch (ID) {
		default: // Unidentified. Oh well.
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
