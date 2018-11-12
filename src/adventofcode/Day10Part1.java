package adventofcode;

public class Day10Part1 {

	public static void main(String args[]) throws Exception {

		String inputLengths = "187,254,0,81,169,219,1,190,19,102,255,56,46,32,2,216";

		// Get input into workable format
		String[] inputLengthArray = inputLengths.split(",");
		int[] dataLengths = new int[inputLengthArray.length];
		// Initialized length list
		for (int i = 0; i < inputLengthArray.length; i++) {
			dataLengths[i] = Integer.parseInt(inputLengthArray[i]);
		}

		// Initialized data list
		int size = 256;
		int[] data = new int[size];
		for (int i = 0; i < size; i++) {
			data[i] = i;
		}

		int currentPosition = 0;
		int skipsize = 0;
		for (int length : dataLengths) {

			// For given length, reverse order of elements in sub-list
			if (currentPosition >= data.length) {
				currentPosition = currentPosition - data.length;
			}

			data = reverseData(data, currentPosition, length - 1);
			// Move pointer forward by length + skip size
			currentPosition = currentPosition + length + skipsize;

			// increase Skip size by 1
			skipsize++;
		}
		// compute final hash
		int result = data[0] * data[1];
		System.out.println(result);
	}

	/**
	 * 
	 * @param data
	 * @param startPos
	 * @param length
	 * @return
	 */
	public static int[] reverseData(int[] data, int startPos, int length) throws Exception {
		// System.out.println("-------------");
		int endPos = startPos + length;

		for (int i = 0; i <= length / 2; i++) {
			if (startPos >= data.length) {
				startPos = startPos - data.length;
			} else if (startPos < 0) {
				startPos = data.length + startPos;
			}

			if (endPos >= data.length) {
				endPos = endPos - data.length;
			} else if (endPos < 0) {
				endPos = data.length + endPos;
			}
			int temp = data[startPos];
			data[startPos] = data[endPos];
			data[endPos] = temp;

			startPos++;
			endPos--;
		}
		return data;
	}

	public static void displayArray(int[] data, int currentPosition) {
		int counter = 0;
		for (int d : data) {
			if (counter == currentPosition) {
				System.out.printf("[" + d + "], ");
			} else {
				System.out.printf(d + ", ");
			}
			counter++;
		}
		System.out.println();
	}
}
