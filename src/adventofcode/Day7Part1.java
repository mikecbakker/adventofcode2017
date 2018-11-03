package adventofcode;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

public class Day7Part1 {

	/**
	 * Main entry point
	 * 
	 * @param args
	 * @throws Exception
	 */
	public static void main(String args[]) throws Exception {
		File file = new File("input/Day7_input.txt");
		FileReader fileReader = new FileReader(file);
		BufferedReader bufferedReader = new BufferedReader(fileReader);
		String line;

		List children = new ArrayList<String>();
		List processedNodes = new ArrayList<String>();
		while ((line = bufferedReader.readLine()) != null) {
			line = line.replaceAll(" ", "");
			line = line.replaceAll("->", "");
			String programName = line.substring(0, line.indexOf('('));
			int weight = Integer.valueOf(line.substring(line.indexOf('(') + 1, line.indexOf(')')));
			String[] childList = line.substring(line.indexOf(')') + 1).split(",");
			Program prog = new Program();
			prog.name = programName;
			prog.weight = weight;
			prog.children = new ArrayList<Program>();
			for (int i = 0; i < childList.length; i++) {
				prog.children.add(childList[i]);
				children.add(childList[i]);
			}
			processedNodes.add(programName);
		}

		String rootNodeName = null;
		for (int i = 0; i < processedNodes.size(); i++) {
			if (!children.contains(processedNodes.get(i))) {
				rootNodeName = (String) processedNodes.get(i);
				break;
			}
		}
		System.out.println("[rootNodeName]: " + rootNodeName);
	}

	public static class Program {
		public Program() {

		}

		public String name;
		public int weight;
		public List children;
	}
}
