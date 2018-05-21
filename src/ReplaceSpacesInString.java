import java.util.Arrays;

/**
 * Created by aloom on 5/20/2018.
 */
public class ReplaceSpacesInString {

	String replace(String string,int size){
		StringBuilder stringBuilder = new StringBuilder();
		for (int i = 0; i < string.length(); i++){
			if (string.charAt(i) == ' '){
				stringBuilder.append("%");
				stringBuilder.append("2");
				stringBuilder.append("0");
			}
			else{
				stringBuilder.append(String.valueOf(string.charAt(i)));
			}
		}
		System.out.println("String: "+stringBuilder.toString());
		return string;
	}
}
