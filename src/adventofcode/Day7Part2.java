package adventofcode;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Day7Part2 {

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

		Tree nodeTree = new Tree();
		HashMap<String, Node> nodes = new HashMap<String, Node>();

		// Populate all the node names & weight
		while ((line = bufferedReader.readLine()) != null) {
			line = line.replaceAll(" ", "");
			line = line.replaceAll("->", "");
			String nodeName = line.substring(0, line.indexOf('('));
			int nodeWeight = Integer.valueOf(line.substring(line.indexOf('(') + 1, line.indexOf(')')));
			// String[] childList = line.substring(line.indexOf(')') + 1).split(",");

			// Construct new node
			Node node = new Node();
			node.name = nodeName;
			node.weight = nodeWeight;

			nodes.put(node.name, node);
		}

		// Now perform linkages (relationships)
		fileReader = new FileReader(file);
		bufferedReader = new BufferedReader(fileReader);
		while ((line = bufferedReader.readLine()) != null) {
			line = line.replaceAll(" ", "");
			line = line.replaceAll("->", "");
			String nodeName = line.substring(0, line.indexOf('('));
			String[] childList = line.substring(line.indexOf(')') + 1).split(",");

			Node parentNode = nodes.get(nodeName);
			for (String childItem : childList) {
				Node child = nodes.get(childItem);
				parentNode.children.add(child);
			}
		}

		// Calculate weight of root 1st level children
		Node rootNode = nodes.get("airlri");
		for (Node child : rootNode.children) {
			int weightOfChildSubtree = calculateWeightOfSubTree(child);
			System.out
					.println("[child node name: " + child.name + "] [weight of subtree: " + weightOfChildSubtree + "]");
		}
		rootNode = nodes.get("pidgnp");
		traverse(rootNode, 0);
	}

	/**
	 * 
	 * @param root
	 * @param tabCount
	 */
	public static void traverse(Node root, int tabCount) {

		String prepend = "";
		for (int count = 0; count < tabCount; count++) {
			prepend += "\t";
		}
		System.out.println(
				prepend + "[tw: " + calculateWeightOfSubTree(root) + "" + root.name + "nw: " + root.weight + " ]");
		tabCount++;
		int[] childrenWeights = new int[root.children.size()];
		for (int i = 0; i < root.children.size(); i++) {
			Node child = root.children.get(i);
			if (child != null) {
				traverse(child, tabCount);
				childrenWeights[i] = calculateWeightOfSubTree(child);
			}
		}
	}

	/**
	 * Calculates the weight of the tree for the given node + children
	 * 
	 * @param root
	 * @return
	 */
	public static int calculateWeightOfSubTree(Node root) {
		int weight = root.weight;
		for (Node child : root.children) {
			if (child != null) {
				weight += calculateWeightOfSubTree(child);
			}
		}
		return weight;
	}

	public static class Tree {

		Node root = null;

		public void insert(Node node, String parentName, Node newNode) {
			if (parentName == null || node == null) {
				root = newNode;
				System.out.println("[Adding new root node: " + newNode.name + "]");
				return;
			}
			List<Node> children = node.getChildren();
			for (Node child : children) {
				insert(child, parentName, newNode);
			}
			// We have found our parent
			if (node.name.equals(parentName)) {
				node.children.add(newNode);
				System.out.println("[Adding " + newNode.name + " as child of " + node.name + "]");
			}

		}

		/**
		 * 
		 * @param root
		 * @param path
		 */
		public void traverse(Node root, String path) {
			path += root.getWeight();
			List<Node> children = root.getChildren();
			for (Node child : children)
				traverse(child, path);

			// end of current traversal
			if (root.getChildren().isEmpty())
				System.out.print(path + " ");
		}
	}

	public static class Node {

		public int getWeight() {
			return weight;
		}

		public List getChildren() {
			return children;
		}

		public String name;
		public int weight;
		public List<Node> children = new ArrayList();
	}
}
