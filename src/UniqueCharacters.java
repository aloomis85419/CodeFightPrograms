/**
 * Created by aloom on 5/20/2018.
 */

/*
	Part of the cracking the coding interview exercises.
	This class contains a method that determines whether a string contains all unique characters or not. Solved without using any data structures.
 */
public class UniqueCharacters {

	public static void main(String[]args){
		String string = new String();
		UniqueCharacters uc = new UniqueCharacters();
		uc.fasterIsUnique("kabcderty");
	}

	/*
		Best solution without sorting that handles all characters.
	 */
	boolean isUnique(String string){
		if (string.length()>128)return false; //longest unique string length possible. Max ASCII value of 128.
		for (int i = 0; i < string.length(); i++){
			for (int j = i+1; j < string.length(); j++){
				if (string.charAt(i) == string.charAt(j)){
					System.out.println("NOT UNIQUE");
					return false;
				}
			}
		}
		System.out.println("UNIQUE");
		return true;
	}

	/*
		An optimized isUnique method. This version only works for characters a - z all lowercase.
	 */
	boolean fasterIsUnique(String string){
		int check = 0;
		if (string.length()>128)return false;
		for (int i = 0; i < string.length(); i++){
			int value = string.charAt(i) - 'a';
			if ((check & (1<<value)) > 0){
				System.out.println("NOT UNIQUE");
				return false;
			}
			check |= (1<<value);
		}
		System.out.println("UNIQUE");
		return true;
	}
}
