package adventofcode;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

public class Day9Part2 {

	public static void main(String args[]) throws Exception {
		File file = new File("input/Day9_input.txt");
		FileReader fileReader = new FileReader(file);
		BufferedReader bufferedReader = new BufferedReader(fileReader);
		String line;

		int groupTotalCount = 0;
		int groupDepth = 0;
		int garbageCount = 0;
		int enterGarbageCount = 0;

		boolean negativeFlag = false;
		boolean inGarbage = false;

		while ((line = bufferedReader.readLine()) != null) {

			for (int i = 0; i < line.length(); i++) {
				char currentChar = line.charAt(i);

				// Process negative flag
				if (currentChar == '!') {
					negativeFlag = !negativeFlag;
				}
				// Enter garbage
				if (currentChar == '<' && !negativeFlag) {
					inGarbage = true;
				}
				if (currentChar == '<' && !negativeFlag && !inGarbage) {
					enterGarbageCount++;
				}

				// Exit garbage
				if (currentChar == '>' && inGarbage && !negativeFlag) {
					inGarbage = false;
					garbageCount--;
				}
				// Enter new group
				if (currentChar == '{' && !inGarbage) {
					groupDepth++;
				}
				// Exit group
				if (currentChar == '}' && !inGarbage) {
					groupTotalCount = groupTotalCount + groupDepth;
					groupDepth--;
				}

				// Process Garbage count
				if (inGarbage && (currentChar != '!' && !negativeFlag)) {
					garbageCount++;
				}
				
				// Reset negative flag
				if (currentChar != '!' && negativeFlag) {
					negativeFlag = !negativeFlag;
				}
			}
			System.out.println("Group total count: " + groupTotalCount);
			System.out.println("Garbage count: " + (garbageCount - (enterGarbageCount)));
		}
	}
}
