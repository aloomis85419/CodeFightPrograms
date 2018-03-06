import java.util.Arrays;
import java.util.Scanner;

/**
 * Created by aloom on 3/2/2018.
 */
public class Run {

	public static void main(String[]args){
		int[]arr = {0,3};
		int l;
		int r;
		GenericTesterClass gtc = new GenericTesterClass();
		System.out.print("Enter Value for l: ");
		Scanner sc = new Scanner(System.in);
		l = sc.nextInt();
		System.out.print("Enter value for r: ");
		r = sc.nextInt();
		long starttime = System.nanoTime();
		gtc.comfortableNumbers(l,r);
		long endTime = System.nanoTime();
		System.out.println("Start Time: "+starttime+"\tEnd Time: "+endTime+"\tTotal Time: "+(endTime-starttime));
	}
}
