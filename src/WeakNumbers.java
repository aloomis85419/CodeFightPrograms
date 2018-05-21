import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by aloom on 4/5/2018.
 */
public class WeakNumbers {
	private ArrayList<Integer>weaknesses = new ArrayList<Integer>();
	int weakest;
	public static void main(String[]args){
		WeakNumbers wn = new WeakNumbers();
		System.out.println(Arrays.toString(wn.weakNumbers(4)));
	}

	/*
		Get the total number of divisors of a number.
	 */
	public int countDivisors(int num){
		int count = 0;
		for (int i = 1; i <= num; i++){
			if (num % i == 0) count++;
		}
		return count;
	}

	/*
		Calculate the Weakness of a number
	 */
	public int getWeakness(int num){
		int weakness = 0;
		int divCount = countDivisors(num);
		for (int i = 1; i < num; i++){
			if (countDivisors(i) > divCount)weakness++;
		}
		return weakness;
	}
	/*
		Get the weakest number in the range 1 to n
	 */
	public int getWeakestNumberInRange(int max){
		int maxWeakness = 0;
		for (int i = 1; i <= max; i++){
			if(getWeakness(i) >= maxWeakness){
				maxWeakness = getWeakness(i);
				weaknesses.add(maxWeakness);
			}
		}
		weakest = maxWeakness;
		return maxWeakness;
	}


	int[]weakNumbers(int n){
		int[]weakestNums = new int[2];
		weakestNums[0] = getWeakestNumberInRange(n);
		weakestNums[1] = countOccur(weakest);
		return weakestNums;
	}

	/*
		count the number of occurrences of the maximal weakness
	 */
	public int countOccur(int maximal){
		int count = 0;
		for (int i = 0; i < weaknesses.size(); i++){
			if (weaknesses.get(i) == maximal)count++;
		}
		return count;
	}

}
