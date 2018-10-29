package adventofcode;

public class Day3Part1 {

	public static void main(String args[]) {

		mapNumbersOntoPlane(277678);
	}

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

			// sequence
			// n = 0 => (0, 0)
			// Right
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
			// Up
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
			// Left
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

			// Down
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

			// Right 2n+1
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
