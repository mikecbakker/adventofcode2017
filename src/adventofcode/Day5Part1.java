package adventofcode;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

public class Day5Part1 {

	public static void main(String args[]) throws Exception {

		// Open input file
		File file = new File("input/Day5_input.txt");
		FileReader fileReader = new FileReader(file);
		BufferedReader bufferedReader = new BufferedReader(fileReader);

		// Parse file
		String line;
		List instructionList = new ArrayList<Integer>();
		while ((line = bufferedReader.readLine()) != null) {
			instructionList.add(new Integer(line));
		}

		Object[] instructions = instructionList.toArray();

		int currentLocation = 0;
		int counter = 0;
		while (currentLocation >= 0 && currentLocation < instructions.length) {
			
			int offset = (Integer) instructions[currentLocation];
			instructions[currentLocation] = (Integer) (Integer) instructions[currentLocation] + 1;
			currentLocation = currentLocation + offset;
			counter++;
		}
		System.out.println("Counter: " + counter);
	}
}
