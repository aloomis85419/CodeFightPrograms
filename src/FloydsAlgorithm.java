import java.awt.*;
import java.awt.font.TextAttribute;
import java.text.AttributedString;
import java.util.Arrays;

/**
 * Created by aloom on 3/10/2018.
 */
public class FloydsAlgorithm {
	int X;
	private static final Character INFINITY = '-';
	public void floydAlgorithm(Integer[][]Dgraph){
		X = Dgraph.length;
		int chartCount = 1;
		Integer[][]paths = new Integer[X][X];

		for (int k = 0; k < X; k++){
			for (int i = 0; i < X; i++) {
				for (int f = 0; f < X; f++) {
					if (Dgraph[i][f] == Floyd.INF) {
						Dgraph[i][f] = (int) '-';
					}
				}
			}
			for (int i = 0; i < X; i++){
				for (int j = 0; j < X; j++){
					int result = Dgraph[i][k] + Dgraph[k][j];
					if (result < Dgraph[i][j])
					{
						Dgraph[i][j] = result;
						paths[i][j] = k+1;
					}
				}
			}
			for (int f = 0; f < X; f++){
				for (int g = 0; g < X; g++){
					if (Dgraph[f][g] == '-')Dgraph[f][g] = -1;
				}
			}
			System.out.println("\tD"+"("+chartCount+")");
			printPathsAndMinDist(Dgraph,paths);
			System.out.println("\n\n");
			chartCount++;
		}
	}

	public void printPathsAndMinDist(Integer[][]arrToPrint,Integer[][]arrToPrint2){
		int spaceCorrect = getCharArrLength(arrToPrint[0]);
		int i,j,k;
		for (i = 0; i < X; i++) {
			for (j = 0; j < X; j++) {
				if (arrToPrint[i][j] == Floyd.INF)arrToPrint[i][j] = Floyd.INF;
				if (arrToPrint2[i][j] == null)arrToPrint2[i][j] = 0;
			}
		}
		System.out.print("\t");
		for (i = 1; i <= spaceCorrect; i++)
		{
			System.out.print(i+" ");
		}
		System.out.println();
		System.out.print("\t");
		for (i = 0; i < spaceCorrect; i++)
		{
			System.out.print("- ");
		}
		System.out.println();
		for (k = 0; k < X; k ++){
			System.out.print((k+1)+"|\t");
			for (i = 0; i < X; i++){
				if (arrToPrint[k][i] == Floyd.INF)System.out.print(INFINITY+" ");
				else System.out.print(arrToPrint[k][i]+" ");
			}
			System.out.println();
			System.out.print(" |"+"\t");
			for(j = 0; j < X; j++){
				System.out.print(" "+arrToPrint2[k][j]);
			}
			System.out.println();
		}
		System.out.print("\t");
		for (i = 0; i <= spaceCorrect; i++)
		{
			System.out.print("- ");
		}
	}

	public void spaceCorrect(Integer[]arr1,Integer[]arr2)
	{
		for (int i = 0; i < getCharArrLength(arr1) - getCharArrLength(arr2); i++)
			System.out.print(" ");
	}

	private int getCharArrLength(Integer[]arr){
		String nums = "";
		for (int i = 0;i < X; i++)
			nums+=String.valueOf(arr[i]);
		return nums.length();
	}
}
