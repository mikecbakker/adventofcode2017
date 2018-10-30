package adventofcode;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Day4Part2 {

	// Main method
	public static void main(String args[]) throws Exception {

		File file = new File("input/Day4_input.txt");
		FileReader fileReader = new FileReader(file);
		BufferedReader bufferedReader = new BufferedReader(fileReader);
		String line;
		int countValidPassphrases = 0;
		while ((line = bufferedReader.readLine()) != null) {
			List existingWords = new ArrayList<String>();
			String[] passphrase = line.split(" ");
			boolean validPassPhrase = true;
			for (int i = 0; i < passphrase.length; i++) {
				String word = passphrase[i];
				char[] wordCharacters = word.toCharArray();
				Arrays.sort(wordCharacters);
				String sortedWord = new String(wordCharacters);
				if (existingWords.contains(sortedWord)) {
					validPassPhrase = false;
					break;
				} else {
					existingWords.add(sortedWord);
				}
			}
			if (validPassPhrase) {
				countValidPassphrases++;
			}
		}
		fileReader.close();
		System.out.println("Number of valid passphrases: " + countValidPassphrases);
	}
}
