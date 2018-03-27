import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

/**
 * Created by aloom on 3/2/2018.
 */
public class RunKnapSack {

	public static void main(String[]args){
		//{3,1,7,5,8,4}; test values
		Integer[]values;
		Character[][]FOL;
		KnapSackGame knapSackGame;
		int num;
		int gameSize = 6;
		System.out.println("+++++++++++++++++++++++++++++");
		System.out.println("Welcome to the KnapSack Game.");
		System.out.println("+++++++++++++++++++++++++++++\n");
		System.out.print("Lets begin.\n");
		Scanner sc = new Scanner(System.in);
		System.out.print("Enter game size (# of values):");
		gameSize = sc.nextInt();
		System.out.print("Enter game values:\n");
		values = new Integer[gameSize];
		for (int i = 0; i < values.length; i++){
			num = sc.nextInt();
			values[i] = num;
		}
		System.out.println("Input Finished.");
		FOL = new Character[values.length][values.length];
		knapSackGame = new KnapSackGame(FOL,values);
		knapSackGame.initializeTable();
		System.out.println("Best value out of "+ values.length+" choices: "+knapSackGame.maximalValueAndBestChoice());
	}
}
