package Project7;


import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.PriorityQueue;
import java.util.Scanner;

/**
 * @Author: Aaron Loomis, Manoj Vasa
 */
public class proj7{

	private static ArrayList<Integer> weightsList;
	private static ArrayList<Integer> profitList;
	ArrayList<KnapsackNode>items = new ArrayList<>();
	double achievableProfit = 0;
	boolean weightExceeded = false;

	/*
		TODO: Check out the formatted print method at the bottom of the file. This prints all node information required for the assignment.
		TODO: Add logic necessary so that the nodes store the items in their itemsInNode list.
	 */
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
		System.out.println(profitList);
		System.out.println(weightsList);
		proj7.buildItemsList(weightsList,profitList);
		proj7.sortItemListByValuePerWeight();
		System.out.println(proj7.items+"\n");
		proj7.printFormattedKnapsack(profitList,weightsList,maxWeight,itemCount);
		proj7.executeBranchAndBoundKnapsack(maxWeight);
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
		This method excecuted branch and valuePerWeight on the 0-1 Knapsack problem using a custom proj7.KnapsackNode class and a priority queue
	 */
	private void executeBranchAndBoundKnapsack(int maxWeight) {
		PriorityQueue<KnapsackNode> pq = new PriorityQueue<>(new KnapsackNodeComparator());//Use to order nodes by their calculated bounds allowing for easy tracking of existing bounds.
		KnapsackNode parent = new KnapsackNode(),leftchild = new KnapsackNode(),rightchild = new KnapsackNode(),maxnode = new KnapsackNode();
		parent.setNodeLevel(-1);
		parent.profit = parent.weight = 0;
		double maxProfit = 0;
		int nodeNum = 0;
		pq.add(parent);
		System.out.println("Beginning Exploration....\n");
		while(!pq.isEmpty()){
			parent = pq.poll();
			if (parent.nodeLevel == -1){
				leftchild.setNodeLevel(0);
				rightchild.setNodeLevel(0);
			}

			if (parent.nodeLevel == items.size()-2)continue;
			leftchild.nodeLevel = parent.nodeLevel+1;
			rightchild.nodeLevel = parent.nodeLevel+1;
			leftchild.profit = parent.profit;
			leftchild.weight = parent.weight;
			rightchild.profit = parent.profit + items.get(rightchild.nodeLevel).profit;
			rightchild.weight = parent.weight + items.get(rightchild.nodeLevel).weight;
			if (rightchild.weight <= maxWeight && rightchild.profit > maxProfit){
				maxnode = rightchild;
				maxProfit = rightchild.profit;
			}
			else if (leftchild.weight <= maxWeight && leftchild.profit > maxProfit){
				maxnode = leftchild;
				maxProfit = leftchild.profit;
			}
			leftchild.bound = calculateBound(leftchild,maxWeight);
			rightchild.bound = calculateBound(rightchild,maxWeight);
			System.out.print("Exploring ");
			printFormattedNodeInformation(parent,nodeNum);
			System.out.print("Left child is ");
			printFormattedNodeInformation(leftchild,nodeNum+1);
			System.out.print("Right child is ");
			printFormattedNodeInformation(rightchild,nodeNum+2);
			if (rightchild.bound > maxProfit){
				pq.add(rightchild);
				System.out.print("Right child chosen: ");
				printFormattedNodeInformation(leftchild,nodeNum+1);
				System.out.println("explore further\n");
			}
			else if (leftchild.bound > maxProfit){
				pq.add(leftchild);
				System.out.print("Left child chosen: ");
				printFormattedNodeInformation(rightchild,nodeNum+2);
				System.out.println("explore further\n");
			}
		}
		System.out.println("\nMax Profit: "+maxProfit);
	}

	public static void printFormattedNodeInformation(KnapsackNode knapsackNode, int nodeNum) {
		System.out.println( "<Node "+nodeNum+":\titems: "+knapsackNode.itemsInNode+" level: "+knapsackNode.nodeLevel+" profit: "+knapsackNode.profit+" weight: "+knapsackNode.weight+" bound: "+knapsackNode.getBound());
	}

	public void sortItemListByValuePerWeight(){
		int i = 0;
		double max = 0;
		int indexOfMax = 0;
		ArrayList<KnapsackNode>temp = new ArrayList<>();
		while(temp.size() != items.size()){
			if (items.get(i).valuePerWeight > max && !temp.contains(items.get(i))){
				max = items.get(i).valuePerWeight;
				indexOfMax = i;
			}
			if (i == items.size()-1 && !temp.contains(items.get(i))){
				temp.add(items.get(indexOfMax));
				max = 0;
				i = 0;
			}
			else{
				i++;
			}
		}
		for (i = 0; i < temp.size(); i++){
			temp.get(i).setItemID(i+1);
		}
		items.clear();
		items.addAll(temp);
	}

	public void buildItemsList(ArrayList<Integer>weightsList,ArrayList<Integer>profits){
		for (int i = 0; i < weightsList.size(); i++){
			int valuePerWeight = profits.get(i)/weightsList.get(i);
			items.add(new KnapsackNode(i+1,weightsList.get(i),profitList.get(i),valuePerWeight));
		}
	}

	double calculateBound(KnapsackNode child, int maxWeight){
		double bound = child.profit;
		int weightSum = child.weight;
		int i = child.nodeLevel;
		if (child.weight >= maxWeight){
			System.out.println("pruned...exceeded max weight\n");
			return 0;//weight too large. Do not pursue.
		}
		while (weightSum + items.get(i).weight <= maxWeight && i < items.size()/2){
			bound += items.get(i).profit;
			weightSum += items.get(i).weight;
			i++;
		}
		if (weightSum < maxWeight){
			bound += (items.get(i).valuePerWeight*(maxWeight - weightSum));
		}
		return bound;
	}
}
