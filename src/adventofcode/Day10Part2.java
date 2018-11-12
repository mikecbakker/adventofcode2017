package adventofcode;

public class Day10Part2 {

	public static void main(String args[]) throws Exception {

		 //displayArray(new int[] {4, 3, 0, 1, 2 }, 1);
		 //int[] ddata = reverseData(new int[] {4, 3, 0, 1, 2 }, 1, 5);
		 //displayArray(ddata, 1);
		// System.exit(0);
		 String inputLengths = "187,254,0,81,169,219,1,190,19,102,255,56,46,32,2,216";
		//String inputLengths = "3,4,1,5";

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
			System.out.println("-------------");
			displayArray(data, currentPosition);
			System.out.println("length: " + length + " currentPosition: " + currentPosition + " skipSize: " + skipsize);
			
			data = reverseData(data, currentPosition, length - 1);
			displayArray(data, currentPosition);
			// Move pointer forward by length + skip size
			System.out.println("advancing position by : " + (int) (length+skipsize));
			currentPosition = currentPosition + length + skipsize;

			// increase Skip size by 1
			skipsize++;

		}
		displayArray(data, currentPosition);
		System.out.println("currentPosition: " + currentPosition + " skipSize: " + skipsize);
		System.out.println("-------------");
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
			// System.out.println("BEFORE: startPos: " + startPos + " endPos: " + endPos);
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
			//System.out.println("AFTER: startPos: " + startPos + " endPos: " + endPos);
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
