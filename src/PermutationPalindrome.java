import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by aloom on 5/20/2018.
 */
public class PermutationPalindrome {


	public boolean hasPermutationThatIsPalindrome(String perm){
		if (perm.length() == 0)return false;
		HashMap<String, Integer>characterCount = new HashMap<>();
		int oddCount = 0;
		for (int i = 0; i < perm.length(); i++) {
			characterCount = incrementCount(characterCount, String.valueOf(perm.charAt(i)));
		}
		for(String key : characterCount.keySet()){
			if (oddCount%2 == 0)continue;
			if (characterCount.get(key)%2 == 1){
				oddCount++;
			}
		}
		if (oddCount > 1)return false;
		return true;
	}

	HashMap incrementCount(HashMap<String,Integer>map,String key){
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
