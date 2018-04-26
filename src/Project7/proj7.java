package Project7;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

/**
 * @Author: Aaron Loomis, Manoj Vasa
 */
public class proj7{
	private static ArrayList<Integer> weightsList;
	private static ArrayList<Integer> profitList;
	private static ArrayList<Integer> excludeForWeights = new ArrayList<>();
	private static ArrayList<Integer> excludeForBound = new ArrayList<>(); //starts at all 1's
	private static int accumulatedWeightForBound;
	private static int accumulatedProfitForBound;
	private static int accumulatedWeight;
	private static int accumulatedProfit;
	private static int items;
	private static int max;
	private static double bound;

	public static  void main(String[]args){
		proj7 proj7 = new proj7();
		String filename;
		int lineCount = 0;
		int[]weightAndProfit;
		int maxWeight = 0;
		int itemCount = 0;
		weightsList = new ArrayList<>();
		profitList = new ArrayList<>();
		String readLine;
		BufferedReader br;
		FileReader fr;
		Scanner sc = new Scanner(System.in);
		System.out.print("Enter fully qualified file path: ");
		filename = sc.nextLine();
		System.out.println();
		try {
			fr = new FileReader(filename);
			br = new BufferedReader(fr);
			while((readLine = br.readLine())!= null){
					if (lineCount == 0){
						maxWeight = Integer.valueOf(readLine);
					}
					else if (lineCount == 1){
						itemCount = Integer.valueOf(readLine);
					}
					else{
						weightAndProfit = proj7.extractNumbers(readLine);
						profitList.add(weightAndProfit[0]);
						weightsList.add(weightAndProfit[1]);
					}
				lineCount++;
			}
			br.close();
		}
		catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		catch (IOException e) {
			e.printStackTrace();
		}
		proj7.printFormattedKnapsack(profitList,weightsList,maxWeight,itemCount);
		System.out.println("Beginning exploration of the possibilities tree...");
		System.out.println(weightsList);
		System.out.println(profitList);
		for(int i = 0; i< weightsList.size();i++){
			excludeForBound.add(1);
			excludeForWeights.add(0);
		}
		proj7.executeBranchAndBoundKnapsack();

	}

	private void printFormattedKnapsack(ArrayList<Integer>list,ArrayList<Integer>list2,int capacity, int items){
		System.out.println("Capacity of knapsack is "+capacity);
		System.out.println("Items are: ");
		int length = list.size();
		//1: 100 4
		for (int i = 0; i < items; i++){
			System.out.print((i+1)+": "+list.get(i)+" "+list2.get(i));
			System.out.println();
		}
		System.out.println();
	}

	/*
		Extract all weights and profits of the items from a properly formatted text file.
	 */
	private int[] extractNumbers(String s){
		String temp = "";
		int[]weightAndProfit = new int[2];
		for (int i = 0; i < s.length(); i++){
			if (s.charAt(i) == ' '){
				weightAndProfit[0] = Integer.valueOf(temp);
				temp = "";
				continue;
			}
			else if(Character.isDigit(s.charAt(i))){
				temp += s.charAt(i);
			}
		}
		weightAndProfit[1] = Integer.valueOf(temp);
		return weightAndProfit;
	}

	/*
		This method excecuted branch and valuePerWeight on the 0-1 Knapsack problem using a custom Project7.KnapsackNode class and a priority queue
	 */
	private static void executeBranchAndBoundKnapsack(){
		items = 0;
		double boundInclude;
		double boundExclude;
		int weightsForInclude;
		int weightsForExclude;
		int profitsForInclude;
		int profitsForExclude;
		KnapsackNode parent;
		KnapsackNode left;
		KnapsackNode right;
		max = 15;

		for(int i = 0; i < weightsList.size();i++){

			items++;
			excludeForBound.set(i, 1);
			excludeForWeights.set(i,1);
			calculateValuesAtCurrentNode(weightsList, profitList, excludeForBound , max);
			calculateWeightsAndProfitsForNode(weightsList,profitList,excludeForWeights);
			boundInclude = bound;
			weightsForInclude = accumulatedWeight;
			profitsForInclude = accumulatedProfit;
			//add to nodes here
			left = new KnapsackNode(items+1,accumulatedWeight,accumulatedProfit,bound);
			System.out.println(left.toString());
			excludeForBound.set(i, 0);
			excludeForWeights.set(i,0);
			calculateValuesAtCurrentNode(weightsList, profitList, excludeForBound , max);
			calculateWeightsAndProfitsForNode(weightsList,profitList,excludeForWeights);
			boundExclude = bound;
			weightsForExclude = accumulatedWeight;
			profitsForExclude = accumulatedProfit;
			//add to nodes here

			if(boundInclude > boundExclude)
			{
				excludeForBound.set(i, 1);
				excludeForWeights.set(i,1);
			}
			else if(boundExclude > boundInclude)
			{
				continue;
			}

		}
	}

	private static void calculateValuesAtCurrentNode(ArrayList<Integer>weights,ArrayList<Integer>profit,ArrayList<Integer>exclude,int max){
		int weightRemaining = max;
		accumulatedWeightForBound = 0;
		accumulatedProfitForBound = 0;
		bound = 0;
		boolean isFeasible = verifyWeightOfIncludedItems(weights,excludeForWeights);
		if(isFeasible)
		{
			for (int i = 0; i < weights.size(); i++)
			{
				if(exclude.get(i) == 0)
				{
					continue;
				}
				else
				{
					accumulatedWeightForBound += weights.get(i);
					if(accumulatedWeightForBound <= max)
					{
						weightRemaining -= weights.get(i);
						accumulatedProfitForBound += profit.get(i);
						bound = accumulatedProfitForBound;
					}
					else if(accumulatedWeightForBound > max)
					{
						accumulatedWeightForBound -= weights.get(i);
						bound = accumulatedProfitForBound + (profit.get(i)/weights.get(i))*weightRemaining;
						break;
					}
				}
			}
		}
		else
		{
			System.out.println("not fesible\n\n");
		}
	}

	private static boolean verifyWeightOfIncludedItems(ArrayList<Integer>weights, ArrayList<Integer>exclude)
	{
		int thisWeight = 0;
		boolean isFeasible = true;
		for(int i = 0; i< exclude.size();i++)
		{
			if(exclude.get(i) == 1){
				thisWeight += weights.get(i);
			}
		}

		if(thisWeight > max){
			return false;
		}
		return isFeasible;
	}

	private static void calculateWeightsAndProfitsForNode(ArrayList<Integer>weights, ArrayList<Integer>profits, ArrayList<Integer>exclude){
		accumulatedWeight = 0;
		accumulatedProfit = 0;
		for(int i = 0;i < exclude.size();i++)
		{
			if(exclude.get(i) == 1)
			{
				accumulatedWeight += weights.get(i);
				if(accumulatedWeight <= max)
				{
					accumulatedProfit += profits.get(i);
				}
				else if(accumulatedWeight > max)
				{
					accumulatedWeightForBound -= weights.get(i);
					break;
				}
			}
			else
			{
				continue;
			}
		}
	}





	public ArrayList<KnapsackNode>getSortedKnapsackNodeList(ArrayList<Integer>weights,ArrayList<Integer>profit,int itemCount){
		ArrayList<KnapsackNode>items = new ArrayList<>();
		for (int i = 0; i < itemCount; i++){
			items.add(new KnapsackNode(i+1,weights.get(i),profit.get(i),(profit.get(i)/weights.get(i))));
		}
		Collections.sort(items,new KnapsackNodeComparator());
		return items;
	}


}
