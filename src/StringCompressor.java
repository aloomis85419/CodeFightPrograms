import java.util.HashMap;

/**
 * Created by aloom on 5/21/2018.
 *Compresses a string so that each distinct character in a string is followed by the count of that character within the string.
 */
public class StringCompressor {

	public static  void main(String[]args){
		StringCompressor sc = new StringCompressor();
		sc.compress("aabcccccaaa");
	}

	StringBuilder compress(String s1){
		StringBuilder sb = new StringBuilder();
		char currentChar = ' ';
		char nextChar = ' ';
		int count = 0;
		for (int i = 0; i < s1.length()-1; i++){
			currentChar = s1.charAt(i);
			nextChar = s1.charAt(i+1);
			count++;
			if (currentChar != nextChar){
				sb.append(String.valueOf(currentChar));
				sb.append(count);
				count = 0;
			}
		}
		sb.append(String.valueOf(currentChar));
		sb.append(++count);
		System.out.println(sb.toString());
		return sb;
	}

}
