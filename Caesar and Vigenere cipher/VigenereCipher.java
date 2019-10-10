package Cipher;

import java.util.Arrays;
import java.util.List;

public class VigenereCipher {
	
	private static Character [] alpha = {'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N',
			'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X' ,'Y', 'Z'};

	private static List<Character>alphaList = Arrays.asList(alpha);
	private static String word;
	private static String key;

	public static void main(String[] args) {
		word = args[0];
		key = args[1];
		String option = args[2];

		if (option.equals("e"))
			encrypt();
		else if (option.equals("d"))
			decrypt();
		else {
			System.err.println("You have to choose between 'e' for encrypt the message or 'd' for decrypt the message.");
			System.exit(0);
		}
	}		


	public static void encrypt() {
		word = word.toUpperCase();
		key = key.toUpperCase();
		String aux = "";
		for (int i = 0, j = 0; i < word.length(); i++) {
			if (Character.isLetter(word.charAt(i))){
				int wordLetterPosition = getIndex(word.charAt(i));
				char keyLetter = key.charAt(j);
				int keyPosition = getIndex(keyLetter);
				
				aux += alphaList.get((wordLetterPosition + keyPosition) % 26);
				j = ++j % key.length();
			}
			else {
				System.err.println("Please, introduce only an alphabetic word.");
				System.exit(0);
			}
		}
		System.out.println(aux);
	}

	public static void decrypt() {
		word = word.toUpperCase();
		key = key.toUpperCase();
		String aux = "";
		for (int i = 0, j = 0; i < word.length(); i++) {
			if (Character.isLetter(word.charAt(i))){
				int wordLetterPosition = getIndex(word.charAt(i));
				char keyLetter = key.charAt(j);
				int keyPosition = getIndex(keyLetter);
				
				aux += alphaList.get(((wordLetterPosition + 26) - keyPosition) % 26);
				j = ++j % key.length();
			}
			else {
				System.err.println("Please, introduce only an alphabetic word.");
				System.exit(0);
			}
		}
		System.out.println(aux);
	}


	
	private static int getIndex(char letter) {
		int index = -1;
		for (int i = 0; i < alphaList.size(); i++) {
			if (letter == alphaList.get(i))
				index = i;
		}
		return index;
	}
	

}
