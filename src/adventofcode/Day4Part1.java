package adventofcode;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

public class Day4Part1 {

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
				if (existingWords.contains(passphrase[i])) {
					validPassPhrase = false;
					//System.out.println(line);
					break;
				} else {
					existingWords.add(passphrase[i]);
				}
			}
			if (validPassPhrase) {
				countValidPassphrases++;
			}
		}
		fileReader.close();
		System.out.println("Number of valid passphrases: " +countValidPassphrases);
	}
}
