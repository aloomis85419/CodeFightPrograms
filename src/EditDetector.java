import com.sun.deploy.util.StringUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

/**
 * Created by aloom on 5/20/2018.
 * This program detects whether two words are one replace, insert, or remove away from being the same word. From Cracking The Coding Interview
 */
public class EditDetector {

	public static void main(String[]args){
		EditDetector ed = new EditDetector();
		System.out.println(ed.isOneAway("ple","pale"));
		System.out.println(ed.isOneAway("pale","pales"));
		System.out.println(ed.isOneAway("bale","pale"));
		System.out.println(ed.isOneAway("bake","pale"));

	}

	/*
		For different length strings: S1 and S2 are combined into one string and then the characters in this string are counted.
									*Exactly one count that is odd in the hashmap indicates that the strings are off by one in this case.
		For same length strings: This means a remove or replace was not performed. Thus all we have to do is iterate through the strings
								and compare characters at equal index positions. If the diff counter gets above 1 return false, 0 return false, 1 return true.

	 */
	boolean isOneAway(String s1, String s2){
		int diff = 0;
		String mapString = s1+s2;
		HashMap<String,Integer>count = new HashMap<>();
		if (s1.length()-s2.length() > 1 || s2.length() - s1.length() > 1)return false;
			for (int i = 0; i < mapString.length(); i++) {
				count = incrementCount(count, String.valueOf(mapString.charAt(i)));
			}
			if (s1.length() != s2.length()) {
				for (String s : count.keySet()) {
					if (count.get(s) % 2 == 1)
						diff++;
					if (diff > 1 ) return false;
				}
			}
			else if (s1.length()== s2.length()){
				for (int i = 0; i < s2.length();i++){
					if (s1.charAt(i) != s2.charAt(i)){
						diff++;
					}
					if (diff>1)return false;
				}
			}
			if (diff == 0)return false; // no difference
		return true;
	}

	HashMap incrementCount(HashMap<String,Integer> map,String key){
		Integer count = map.get(key);
		if (count == null){
			map.put(key.toLowerCase(),1);
		}
		else{
			map.put(key.toLowerCase(),count+1);
		}
		return map;
	}
}
