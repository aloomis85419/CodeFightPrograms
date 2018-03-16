import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

/**
 * Created by aloom on 3/7/2018.
 */
public class KnapSackGame {
	private Integer[]gameValues;
	HashMap<Integer, Integer[]>bestChoices = new HashMap<>();
	private String[][] optimalScores;
	private final int X;
	//TODO: Analyze the game method and determine how to add F and L in appropriate location within the optimalValues matrix.
	public KnapSackGame(Integer[]gameValues, int goal){
		this.X = gameValues.length;
		this.gameValues = gameValues;
		this.optimalScores = new String[X][X];
		System.out.println("Game created with "+Arrays.toString(gameValues)+" as the game values");
	}


	public int game()
	{
		int window,i,j;
		String p1sum,p2sum,sum;
		Integer p1,p2,s,max;
		String c1 = "F";
		String c2 = "L";
		System.out.println("____Initial Table____");
		printTable();//The fist print prints the table.
		/*The window algorithm is needed to solve this problem.
			We calculate the max p1Sum for i to j. The window size is helf in the window variable.
			Thus the size of the window gradually increases.
		 */
		for(window = 0; window < X; ++window){
			for (i = 0,j = window; j < X; i++ ,j++){
				if (i+2 <= j){
						p1sum = optimalScores[i+2][j];
				}else p1sum = "0";
				if (i+1 <= j-1){
					p2sum = optimalScores[i+1][j-1];
				}else p2sum = "0";
				if (i <= j-2)
				{
					sum = String.valueOf(optimalScores[i][j-2]);
				}else sum = "0";
				p1 = Integer.valueOf(p1sum);
				p2 = Integer.valueOf(p2sum);
				s = Integer.valueOf(sum);
				max = Integer.valueOf(Math.max(gameValues[i]+Math.min(p1,p2),gameValues[j]+Math.min(p2,s)));
				optimalScores[i][j] = String.valueOf(max);
		}
		}
		printTable();
		return 1;
	}


	public void printTable(){
		System.out.println("0's are empty spots");
		for (int i = 0; i <= X+1; i++){
			System.out.print("-  ");
		}
		for (int i = 0; i < X; i++){
			System.out.print("\n|");
			for (int j = 0; j < X; j++){
				System.out.print(optimalScores[i][j]);
				if (j < X -1)System.out.print("   ");
			}
			System.out.print(" |\n");
		}
		for (int i = 0; i <= X+1; i++){
			System.out.print("-  ");
		}
		System.out.println("\n");
	}

	public void initializeTable(){
		int i, j;
		for (i = 0; i < X; i++){
			for (j = 0; j < X; j++)
				optimalScores[i][j] = "0";
		}
	}

	public void calculateOptimalInRange(){


	}
}
