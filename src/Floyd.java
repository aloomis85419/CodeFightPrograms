import java.util.Arrays;
import java.util.Scanner;

/**
 * Created by aloom on 3/10/2018.
 */
public class Floyd {
	static final int INF = -1;
	public static void main(String[]args){
		int noV;
		Integer[][]vertixTable; //= {{0,1,INF,1,5},{9,0,3,2,INF},{INF,INF,0,4,INF},{INF,INF,2,0,3},{3,INF,INF,INF,0}};;
		Scanner sc = new Scanner(System.in);
		System.out.println("++++++++++++++++");
		System.out.println("Floyds Algorithm");
		System.out.println("++++++++++++++++");
		System.out.print("Enter the total number of vertices:\n");
		noV = sc.nextInt();
		vertixTable = new Integer[noV][noV];
		for (int i = 0; i < vertixTable.length; i++){
			System.out.print("\nRow "+(i+1)+" of vertix optimalScores:\n");
			for (int j = 0; j < vertixTable.length; j++){
				System.out.print("Enter weight from "+(i+1)+" to "+(j+1)+"?"+" ('-1' for no edge)\n");
				vertixTable[i][j] = (int)sc.nextInt();
			}
			System.out.print(Arrays.toString(vertixTable[i]));
		}
		System.out.println("\n");
		for (int i = 0; i <  vertixTable.length; i++){
			System.out.println(Arrays.toString(vertixTable[i]));
		}
		System.out.println("\n");
		double start = System.nanoTime()*.0000000001;
		FloydsAlgorithm fa = new FloydsAlgorithm();
		fa.floydAlgorithm(vertixTable);
		double end = System.nanoTime()*.0000000001;
		System.out.println("Total Time: "+(end-start)+"seconds.");
	}
}
