package adventofcode;

public class Day3Part1 {

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

		int closedLoopCounter = -1;
		boolean initiateRight = true;
		boolean moveUp = false;
		boolean moveLeft = false;
		boolean moveDown = false;
		boolean moveRight = false;

		int n = 1;
		while (n < number) {

			// Move Right
			if (initiateRight) {
				x++;
				closedLoopCounter++;
				moveUp = true;
				initiateRight = false;

				n++;
				if (n == number) {
					int steps = Math.abs(x) + Math.abs(y);
					System.out.println("n " + n + " @:(" + x + ", " + y + ") steps to origin:" + steps);
					break;
				}
			}
			// Move Up
			else if (moveUp) {
				for (int i = 0; i < (2 * closedLoopCounter) - 1; i++) {
					y++;
					n++;
					if (n == number) {
						int steps = Math.abs(x) + Math.abs(y);
						System.out.println("n " + n + " @:(" + x + ", " + y + ") steps to origin:" + steps);
						break;
					}
				}
				moveLeft = true;
				moveUp = false;
			}
			// Move Left
			else if (moveLeft) {
				for (int i = 0; i < 2 * closedLoopCounter; i++) {
					x--;
					n++;
					if (n == number) {
						int steps = Math.abs(x) + Math.abs(y);
						System.out.println("n " + n + " @:(" + x + ", " + y + ") steps to origin:" + steps);
						break;
					}
				}
				moveDown = true;
				moveLeft = false;
			}

			// Move Down
			else if (moveDown) {
				for (int i = 0; i < 2 * closedLoopCounter; i++) {
					y--;
					n++;
					if (n == number) {
						int steps = Math.abs(x) + Math.abs(y);
						System.out.println("n " + n + " @:(" + x + ", " + y + ") steps to origin:" + steps);
						break;
					}
				}
				moveRight = true;
				moveDown = false;
			}

			// Move Right
			else if (moveRight) {
				for (int i = 0; i < 2 * closedLoopCounter; i++) {
					x++;
					n++;
					if (n == number) {
						int steps = Math.abs(x) + Math.abs(y);
						System.out.println("n " + n + " @:(" + x + ", " + y + ") steps to origin:" + steps);
						break;
					}
				}
				moveRight = false;
				initiateRight = true;
			}
		}
	}
}
