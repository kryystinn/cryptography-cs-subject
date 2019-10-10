package Cipher;

import java.util.Arrays;
import java.util.List;

public class CaesarCipher {
	
	private static Character [] alpha = {'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N',
						'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X' ,'Y', 'Z'};

	private static List<Character>alphaList = Arrays.asList(alpha);
	
	private static String word;
	private static int key;
	
	public static void main(String[] args) {
		word = args[0];
		key = Integer.parseInt(args[1]);
		String option = args[2];
		
		if (key >= 0) {
			if (option.equals("e"))
				encrypt();
			else if (option.equals("d"))
				decrypt();
			else {
				System.err.println("You have to choose between 'e' for encrypt the message or 'd' for decrypt the message.");
				System.exit(0);
			}
		}
		else {
			System.err.println("Key must be 0 or higher.");	
			System.exit(0);
		}
	}

	public static void encrypt() {
		word = word.toUpperCase();
		String aux = "";
		for (int i = 0; i < word.length(); i++) {
			if (Character.isLetter(word.charAt(i))){
				int letterPosition = getIndex(word.charAt(i));
				aux += alphaList.get((letterPosition + key) % 26);
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
		String aux = "";
		for (int i = 0; i < word.length(); i++) {
			if (Character.isLetter(word.charAt(i))){
				int letterPosition = getIndex(word.charAt(i));
				aux += alphaList.get((letterPosition - key) % 26);
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
