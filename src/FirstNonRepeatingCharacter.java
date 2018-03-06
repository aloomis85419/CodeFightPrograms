import com.sun.deploy.util.StringUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

/**
 * Created by aloom on 3/2/2018.
 */
public class FirstNonRepeatingCharacter {
	char firstNotRepeatingCharacter(String s) {
		char count[] = new char[s.length()];
		int index = -1, i;
		for(i = 0; i < s.length(); i++)
		{
			count[toString().charAt(i)]++;
		}
		for (i = 0; i < s.length(); i++)
		{
			if(count[s.charAt(i)] == 1){
				index = i;
			}
		}
		return count[index];
	}
}
