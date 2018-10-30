package adventofcode;

import java.util.ArrayList;
import java.util.List;

public class Day6Part1 {

	public static void main(String args[]) throws Exception {
		String input = "2 8 8 5 4 2 3 1 5 5 1 2 15 13 5 14";
		//String input = "0 2 7 0";
		String[] inputData = input.split(" ");
		List<String> previousMemoryModels = new ArrayList<String>();

		String currentMemoryModel = serializeArray(inputData);
		System.out.println("[currentMemoryModel]: " + currentMemoryModel);
		int count = 0;
		while (!previousMemoryModels.contains(currentMemoryModel)) {

			// Add memory model
			previousMemoryModels.add(currentMemoryModel);

			// Reorganise memory
			String[] reallocatedMemory = reallocate(currentMemoryModel.split(" "));
			currentMemoryModel = serializeArray(reallocatedMemory);
			System.out.println("[currentMemoryModel]: " + currentMemoryModel);
			count++;
		}
		System.out.println("[count]: " + count);
	}

	/**
	 * 
	 * @param memoryModel
	 * @return
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
	 * 
	 * @param inputData
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
