/**
 * Created by aloom on 3/7/2018.
 */
public class Project1AlgorithmsAndAlgorithmAnalysis {

	public static void printAllSums(int n, int max, String sum)
	{
		if(n == 0) System.out.println(sum);
		else
		{
			if(max > 1){
				printAllSums(n,max - 1, sum);
			}
			if(max <= n){
				printAllSums(n - max, max, max +" "+sum);
			}
		}
	}

}
