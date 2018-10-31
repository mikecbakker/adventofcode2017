package adventofcode;

import java.util.ArrayList;
import java.util.List;

public class Day6Part2 {

	/**
	 * Main entry point
	 * 
	 * @param args
	 * @throws Exception
	 */
	public static void main(String args[]) throws Exception {
		String input = "2 8 8 5 4 2 3 1 5 5 1 2 15 13 5 14";
		String output = detectCycle(input.split(" "));
		detectCycle(output.split(" "));
	}

	/**
	 * Governs the realloction cycles until such time that a loop is detected.
	 * 
	 * @param inputData String array representing the memory blocks allocated per
	 *                  memory bank
	 * @return a String representing the resultant memory allocation once all
	 *         reallocation cycles are complete.
	 */
	public static String detectCycle(String[] inputData) {
		List<String> previousMemoryModels = new ArrayList<String>();
		String currentMemoryModel = serializeArray(inputData);
		int count = 0;
		while (!previousMemoryModels.contains(currentMemoryModel)) {

			// Add memory model
			previousMemoryModels.add(currentMemoryModel);

			// Reorganise memory
			String[] reallocatedMemory = reallocate(currentMemoryModel.split(" "));
			currentMemoryModel = serializeArray(reallocatedMemory);
			count++;
		}
		System.out.println("[count]: " + count);
		return currentMemoryModel;
	}

	/**
	 * Performs a reallocate of memory blocks.
	 * 
	 * @param memoryModel A string array representing the current memory state
	 * 
	 * @return an array representing the realloacted memory model.
	 */
	public static String[] reallocate(String[] memoryModel) {

		if (memoryModel == null) {
			return null;
		}

		// Find the block with most elements
		int maxMemoryBlockLocation = 0;
		int maxMemoryBlocks = 0;
		for (int i = 0; i < memoryModel.length; i++) {
			if (Integer.valueOf(memoryModel[i]) > maxMemoryBlocks) {
				maxMemoryBlocks = Integer.valueOf(memoryModel[i]);
				maxMemoryBlockLocation = i;
			}
		}

		// Clear the max block before redistribution
		memoryModel[maxMemoryBlockLocation] = String.valueOf(0);

		// Redistribute blocks
		int currentAllocationPosition = maxMemoryBlockLocation;

		for (int i = 1; i <= maxMemoryBlocks; i++) {
			currentAllocationPosition++;
			// Loop around array
			if (currentAllocationPosition == memoryModel.length) {
				currentAllocationPosition = 0;
			}

			// Obtain value at location & increase by number of blocks
			String countObjects = memoryModel[currentAllocationPosition];
			memoryModel[currentAllocationPosition] = String.valueOf((Integer.valueOf(countObjects) + 1));
		}

		return memoryModel;
	}

	/**
	 * Serializes an array into a singular space padded string
	 * 
	 * @param inputData The array to be serialized into a space separated String
	 * @return
	 */
	public static String serializeArray(String[] inputData) {
		if (inputData == null) {
			return null;
		}
		String returnString = "";
		for (int i = 0; i < inputData.length; i++) {
			returnString = returnString + inputData[i] + " ";
		}
		return returnString;
	}
}
