import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

/**
 * Created by aloom on 3/7/2018.
 */
public class KnapSackGame {
	private Integer[]gameValues;
	private Integer[][] optimalScores;
	private Character[][]firstOrlast;
	private final int X;
	//TODO: Analyze the game method and determine how to add F and L in appropriate location within the optimalValues matrix.
	public KnapSackGame(Character[][]firstOrlast, Integer[]gameValues){
		this.X = gameValues.length;
		this.gameValues = gameValues;
		this.optimalScores = new Integer[X][X];
		this.firstOrlast = firstOrlast;
		System.out.println("Game created with "+Arrays.toString(gameValues)+" as the game values");
	}


	public int maximalValueAndBestChoice()
	{
		int window,i,j,sum1,sum2,sum3,mid;
		System.out.println("____Initial Table____");
		printTable();//The fist print prints the table.
		for(window = 0; window < X; window++){
			for (i = 0,j = window; j < X; i++, j++) {
				mid = window/2;
				if (i+2 <= j){
					sum1 = optimalScores[i+2][j];
					if (i >= mid){
						firstOrlast[i+2][j] = 'L';
					}
					else if (i <= mid){
						firstOrlast[i+2][j] = 'F';
					}
				}else sum1 = 0;
				if (i+1 <= j-1){
					sum2 = optimalScores[i+1][j-1];
					if (i >= mid){
						firstOrlast[i+1][j-1] = 'L';
					}
					else if (i <= mid){
						firstOrlast[i+1][j-1] = 'F';
					}
				}else sum2 = 0;
				if (i <= j-2){
					sum3 = optimalScores[i][j-2];
					if (i >= mid){
						firstOrlast[i][j-2] = 'L';
					}
					else if (i <= mid){
						firstOrlast[i][j-2] = 'F';
					}
				}else sum3 = 0;
				optimalScores[i][j] = Math.max(gameValues[i]+Math.min(sum1,sum2),gameValues[j]+Math.min(sum2,sum3));
				if (i > mid){
					firstOrlast[i][j] = 'L';
				}
				else if (i < mid){
					firstOrlast[i][j] = 'F';
				}
			}
		}
		printTable();
		return optimalScores[0][X-1];
	}


	public void printTable(){
		for (int i = 1; i <= X; i++){
			System.out.print("\t"+i);
		}
		System.out.println();
		for (int i = 1; i <= X; i++){
			System.out.print("\t-");
		}
		for (int i = 0; i < X; i++){
			System.out.print("\n"+(i+1)+"|\t");
			for (int j = 0; j < X; j++){
				System.out.print(optimalScores[i][j]+"\t");
			}
			System.out.println();
			System.out.print("\t");
			for (int j = 0; j < X; j++){
				System.out.print(firstOrlast[i][j]+"\t");
			}
		}
		System.out.println("\n");
	}

	public void initializeTable(){
		int window, i, j,k;
		for (i = 0; i < X; i++){
			for (j = 0; j < X; j++)
				optimalScores[i][j] = 0;
			for (k = 0; k < X; k++){
				if (firstOrlast[i][k] == null)firstOrlast[i][k] = '-';
			}
		}

		for (window = 0; window < X; window++){
			for (i = 0,j = window; j < X; j++,i++){
				optimalScores[i][j] = gameValues[i];
			}
		}

	}
}
