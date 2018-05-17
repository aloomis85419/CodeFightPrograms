import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.Map;
import java.util.TreeMap;

/**
 * Created by aloom on 5/17/2018.
 */
/*
		A challenge from the book "Cracking the Coding Interview".
		The challenge requires finding permutations of a shorter string within a longer string.
		The challenge also requires that I find a solution that runs in O(n) time.
		A worst case runtime for this algorithm is something like O(((shorter string length)!)*length of longer string).
*/
public class permutationsMatcher {

	int charCount = 0;
	public static void main(String[]args){
		permutationsMatcher pm = new permutationsMatcher();
		String s1 = "abbc";
		String s2 = "cbabadcbbabbcbabaabccbabc";
		long startTime = System.nanoTime();
		pm.countMatches(s1,s2);
		long endTime = System.nanoTime();
		System.out.println("Total time: "+(endTime-startTime)+" nanoseconds");
	}

	/*
		countMatches counts the number of permutations of the shorter string that are contained within the longer string.
		From my calculations, it does this in O(s1.length()+s2.length+substring.length * length of substring array)
		This simplifies: For each substring created we iterate through it once creating a hashmap count of each character.
		The number of substrings = s2.length - (s1.length-1). # of characters to iterate through is 4 * number of substrings. Each substring is iterated through once.
		The length of this long string can be considered n. Thus overall the algorithm time complexity can be considered O(4n) or O(n).
	 */
	int countMatches(String s1, String s2){
		int count = 0,i;
		HashMap<String,Integer> counts = new HashMap<>(0);
		ArrayList<String>subStrings = new ArrayList<>(0);
		for (i = 0; i < s1.length(); i++){
			String key = String.valueOf(s1.charAt(i));
			counts = incrementCount(counts,key);
		}
		for (i = 0; i < s2.length() - s1.length()+1; i++){
			String subString = s2.substring(i,i+s1.length());
			subStrings.add(subString);
		}
		for (i = 0; i < subStrings.size(); i++){
			if(counts.equals(countSubOccurMap(subStrings.get(i)))){
				count++;
				System.out.print(subStrings.get(i)+"|");
			}
		}
		System.out.println("\nCount: "+count);
		return count;
	}

	/*
		Increments a specified key value
	 */
	HashMap incrementCount(HashMap<String,Integer>map,String key){
		Integer count = map.get(key);
		if (count == null){
			map.put(key,1);
		}
		else{
			map.put(key,count+1);
		}
		return map;
	}

	/*
		Any substring is a permutation of s1 if it contains the same number of the same characters as s1 but in different order.
	 */
	HashMap countSubOccurMap(String sub){
		HashMap<String,Integer>h1 = new HashMap();
		for (int i = 0; i < sub.length();i++){
			String key = String.valueOf(sub.charAt(i));
			h1 = incrementCount(h1,key);
			charCount++;
		}
		return h1;
	}
}
