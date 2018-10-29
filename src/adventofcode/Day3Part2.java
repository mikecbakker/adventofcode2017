package adventofcode;

import java.util.HashMap;

public class Day3Part2 {

	// Used to store the element values on the cartesian plane, indexed by xy coordinate string.
	protected static HashMap data = new HashMap();

	/**
	 * 
	 * @param args
	 */
	public static void main(String args[]) {
		mapNumbersOntoPlane(277678);
	}

	/**
	 * 
	 * @param number
	 *            The number we want to find on the spiral cartesian plane.
	 */
	public static void mapNumbersOntoPlane(int number) {

		int x = 0;
		int y = 0;

		int closedLoopCounter = 0;
		boolean initiateRight = true;
		boolean moveUp = false;
		boolean moveLeft = false;
		boolean moveDown = false;
		boolean moveRight = false;

		int valueInserted = 1;

		// Initial seed element at (0, 0) set to value of 1
		data.put(String.valueOf(0) + String.valueOf(0), new Integer(1));
		while (valueInserted < number) {

			// Move Right
			if (initiateRight) {
				x++;
				System.out.println("[Starting new loop]");
				closedLoopCounter++;
				moveUp = true;
				initiateRight = false;

				valueInserted = insertElement(x, y);

				if (valueInserted > number) {
					int steps = Math.abs(x) + Math.abs(y);
					System.out.println(
							"valueInserted: " + valueInserted + " @:(" + x + ", " + y + ") steps to origin:" + steps);
					break;
				}
			}
			// Move Up
			else if (moveUp) {
				System.out.println("[Moving up]");
				for (int i = 0; i < (2 * closedLoopCounter) - 1; i++) {
					y++;
					valueInserted = insertElement(x, y);
					if (valueInserted > number) {
						int steps = Math.abs(x) + Math.abs(y);
						System.out.println("valueInserted: " + valueInserted + " @:(" + x + ", " + y
								+ ") steps to origin:" + steps);
						break;
					}
				}
				moveLeft = true;
				moveUp = false;
			}
			// Move Left
			else if (moveLeft) {
				System.out.println("[Moving left]");
				for (int i = 0; i < 2 * closedLoopCounter; i++) {
					x--;
					valueInserted = insertElement(x, y);
					if (valueInserted > number) {
						int steps = Math.abs(x) + Math.abs(y);
						System.out.println("valueInserted: " + valueInserted + " @:(" + x + ", " + y
								+ ") steps to origin:" + steps);
						break;
					}
				}
				moveDown = true;
				moveLeft = false;
			}
			// Move Down
			else if (moveDown) {
				System.out.println("[Moving down]");
				for (int i = 0; i < 2 * closedLoopCounter; i++) {
					y--;
					valueInserted = insertElement(x, y);
					if (valueInserted > number) {
						int steps = Math.abs(x) + Math.abs(y);
						System.out.println("valueInserted: " + valueInserted + " @:(" + x + ", " + y
								+ ") steps to origin:" + steps);
						break;
					}
				}
				moveRight = true;
				moveDown = false;
			}
			// Move Right
			else if (moveRight) {
				System.out.println("[Moving right]");
				for (int i = 0; i < 2 * closedLoopCounter; i++) {
					x++;
					valueInserted = insertElement(x, y);
					if (valueInserted > number) {
						int steps = Math.abs(x) + Math.abs(y);
						System.out.println("valueInserted: " + valueInserted + " @:(" + x + ", " + y
								+ ") steps to origin:" + steps);
						break;
					}
				}
				moveRight = false;
				initiateRight = true;
			}
		}
	}

	/**
	 * 
	 * @param x
	 *            The x coordinate
	 * @param y
	 *            The y Coordinate
	 * @return The value inserted at this coordinate after summing adjacent elements
	 */
	public static int insertElement(int x, int y) {

		// Determine elements around x,y
		// Top
		Integer valTop = (Integer) data.get(String.valueOf(x) + String.valueOf(y + 1));

		// Top-left
		Integer valTopLeft = (Integer) data.get(String.valueOf(x - 1) + String.valueOf(y + 1));

		// Left
		Integer valLeft = (Integer) data.get(String.valueOf(x - 1) + String.valueOf(y));

		// Bottom-left
		Integer valBottomLeft = (Integer) data.get(String.valueOf(x - 1) + String.valueOf(y - 1));

		// Bottom
		Integer valBottom = (Integer) data.get(String.valueOf(x) + String.valueOf(y - 1));

		// Bottom-right
		Integer valBottomRight = (Integer) data.get(String.valueOf(x + 1) + String.valueOf(y - 1));

		// Right
		Integer valRight = (Integer) data.get(String.valueOf(x + 1) + String.valueOf(y));

		// Top-right
		Integer valTopRight = (Integer) data.get(String.valueOf(Integer.valueOf(x + 1) + String.valueOf(y + 1)));

		Integer valueToInsert = 0;
		if (valTop != null) {
			System.out.println("valTop: " + valTop);
			valueToInsert = valueToInsert + valTop;
		}
		if (valTopLeft != null) {
			System.out.println("valTopLeft: " + valTopLeft);
			valueToInsert = valueToInsert + valTopLeft;
		}
		if (valLeft != null) {
			System.out.println("valLeft: " + valLeft);
			valueToInsert = valueToInsert + valLeft;
		}
		if (valBottomLeft != null) {
			System.out.println("valBottomLeft: " + valBottomLeft);
			valueToInsert = valueToInsert + valBottomLeft;
		}
		if (valBottom != null) {
			System.out.println("valBottom: " + valBottom);
			valueToInsert = valueToInsert + valBottom;
		}
		if (valBottomRight != null) {
			System.out.println("valBottomRight: " + valBottomRight);
			valueToInsert = valueToInsert + valBottomRight;
		}
		if (valRight != null) {
			System.out.println("valRight: " + valRight);
			valueToInsert = valueToInsert + valRight;
		}
		if (valTopRight != null) {
			System.out.println("valTopRight: " + valTopRight);
			valueToInsert = valueToInsert + valTopRight;
		}

		// Add entry to hashmap
		data.put(String.valueOf(x) + String.valueOf(y), valueToInsert);
		System.out.println("valueInserted: " + valueToInsert + " @:(" + x + ", " + y + ")");
		return valueToInsert.intValue();
	}
}
