import com.sun.deploy.util.ArrayUtil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Timer;

/**
 * Created by aloom on 3/2/2018.
 */
public class GenericTesterClass {

	int[] firstReverseTry(int[] arr) {
		int temp = arr[arr.length-1];
		arr[arr.length-1] = arr[0];
		arr[0] = temp;
		return arr;
	}

	int[] removeArrayPart(int[] inputArray, int l, int r) {
		int[]newarr = new int[(inputArray.length-1)-(r-l)];
		int index = l;
		if (inputArray.length == 0)return inputArray;
		for (int i = 0; i<l;i++)newarr[i] = inputArray[i];
		for(int j = r+1; j<inputArray.length; j++)
		{
			newarr[index] = inputArray[j];
			index++;
		}
		return newarr;
	}

	boolean isSmooth(int[] arr) {
		int mid = 0;
		if(arr.length%2 == 0)mid = arr[(arr.length/2)]+arr[(arr.length/2)-1];
		else mid = arr[arr.length/2];
		if(mid == arr[0] && mid == arr[arr.length - 1])return true;
		return false;
	}

	int[] replaceMiddle(int[] arr) {
		int evenmid = arr[arr.length/2]+arr[(arr.length/2)-1];
		int[]midreplace = new int[arr.length-1];
		if (arr.length%2==0)
		{
			for (int i = 0; i < arr.length/2-1; i++)midreplace[i] = arr[i];
			midreplace[midreplace.length/2] = evenmid;
			for (int i = arr.length/2; i < midreplace.length; i++)midreplace[i] = arr[i+1];		System.out.println(Arrays.toString(midreplace));
			return midreplace;
		}
		return arr;
	}

	int makeArrayConsecutive2(int[] statues) {
		int min = 0;
		int max = 0;
		for(int i = 0; i < statues.length; i++) {
			if(statues[i] > max)max = statues[i];
		}
		min = max;
		for(int i = 0; i <statues.length; i++){
			if (statues[i]<min)min = statues[i];
		}
		return (max-min)-(statues.length-1);
	}

	boolean isPower(int n) {
		double min = Math.sqrt(n);
		if(min%1==0)return true;
		// Since the maximum value j can take is the square root of any number we can cap off the values we will search
		for(int j = 1; j < (int)min; j++) {
			for (double i = 1; i < (int)min; i += 1) {
				if(Math.pow(j,i) == n){
					return true;
				}
			}
		}
		return false;
	}

	int isSumOfConsecutive2(int n) {
		int[]allconsecintuptoN = new int[n];
		int k = 2;
		int sum = 0;
		int count = 0;
		for(int i = 0;i<n;i++)allconsecintuptoN[i] = i;
		for(int i = 0; i <= allconsecintuptoN.length - k; i++){
			for (int j = 0; j < k; j++){
				sum = sum + allconsecintuptoN[i+j];
				if (sum == n && j == k-1){
					count++;
					sum = 0;
				}
			}
			sum = 0;
			if (i == n-(k+1)){
				i = 0;
				k++;
			}
			if (k == n/2){
				break;
			}
		}
		return count;
	}
	int squareDigitsSequence(int a0){
		int sum = a0;
		String val = String.valueOf(a0);
		int count = 0;
		ArrayList<Integer>calculatedSums = new ArrayList<>();
		while(!calculatedSums.contains(sum)){
			calculatedSums.add(sum);
			int next = 0;
			while(sum > 0){
				next += (int)Math.pow(sum%10,2);
				sum/=10;
			}
			sum = next;
		}
		return calculatedSums.size()+1;
	}

	int pagesNumberingWithInk(int current, int numberOfDigits) {
		int sum = 0;
		int length;
		String val = "";
		for (int i = current; sum < numberOfDigits; i++){
			val= String.valueOf(i);
			length= val.length();
			sum+=length;
			if(sum+String.valueOf(i+1).length() > numberOfDigits)return Integer.valueOf(val);
		}
		return Integer.valueOf(val);
	}
	int pagesNumberingWithInk2(int current, int numberOfDigits) {
		//Apparently this can be done without a single loop.

		return current;
	}

	int comfortableNumbers(int l, int r) {
		ArrayList<Integer>ints;
		HashMap<Integer,ArrayList<Integer>>numSets = new HashMap<>();
		int sum = 0;
		int count = 0;
		int next = l;
		int a = l;
		while(a <= r) {
			while (next > 0) {
				sum += next%10;
				next/=10;
			}
			ints = new ArrayList<>();
			for (int i = (a-sum); i <= (a+sum); i++)ints.add(i);
			numSets.put(a,ints);
			a++;
			next = a;
			sum = 0;
		}
		for (int i = l; i <= r;i++) {
			for (int j = i + 1; j <= r; j++)
				if (numSets.get(j).contains(i) && numSets.get(i).contains(j))
					count++;
		}
		return count;
	}

	int[] weakNumbers(int n) {
		int[]sub  = new int[n];
		int i = n;
		int j = 1;
		while(i >= j)
		{
			sub[i-1] = i;
			sub[j - 1] = j;
			i--;
			j++;
		}
		return getWeakness(sub);
	}

	int[]getWeakness(int[]sub)
	{
		int[]weaknesses = new int[sub.length];
		int count = 0;
		int i = sub.length;
		int j = i;
		int index = sub.length-1;
		while(i > 1)
		{
			if(j == 0) {
				weaknesses[index] = count;
				i--;
				j = i;
				index--;
				count = 0;
			}
			if (i%j == 0){
				count++;
			}
			j--;
		}
		weaknesses[0] = 1;
		return finalWeakness(weaknesses);
	}

	int[]finalWeakness(int[]weak)
	{
		int[]SpecificAndMaximal = new int[2];
		int countNums = 0;
		int i = weak.length - 1;
		int j = i-1;
		int index = weak.length-1;
		while(j > 0){
			if(weak[j] > weak[i])countNums++;
			j--;
		}
		SpecificAndMaximal[0] = countNums;
		countNums = 0;
		i = weak.length - 1;
		j = i-1;

		while(i >= 1)
		{

			if(j == 0){
				weak[index] = countNums;
				i--;
				j = i-1;
				index--;
				countNums = 0;
			}
			if (weak[j] > weak[i])
			{
				countNums++;
			}
			j--;
		}
		System.out.println(Arrays.toString(weak));
		System.out.println("Weak: "+Arrays.toString(weak));
		i = weak.length - 1;
		while(i > 0){
			if(weak[i] == 2)countNums++;
			i--;
		}
		SpecificAndMaximal[1] = countNums;
		return SpecificAndMaximal;
	}


}
