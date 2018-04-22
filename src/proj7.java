import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Scanner;

/**
 * Created by aloom on 4/20/2018.
 */
public class proj7{

	public static  void main(String[]args){
		proj7 proj7 = new proj7();
		String filename;
		int lineCount = 0;
		int[]weightAndProfit;
		int maxWeight = 0;
		int itemCount = 0;
		ArrayList<Integer>weights = new ArrayList<>();
		ArrayList<Integer>profits = new ArrayList<>();
		PriorityQueue<Integer>PQ = new PriorityQueue<>();
		String readLine;
		BufferedReader br;
		FileReader fr;
		Scanner sc = new Scanner(System.in);
		System.out.print("Enter fully qualified file path: ");
		filename = sc.nextLine();
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
						profits.add(weightAndProfit[0]);
						weights.add(weightAndProfit[1]);
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
		//proj7.printFormattedKnapsack(profits,weights,maxWeight,itemCount);
		proj7.calcualteBestItemComboByBranchAndBound(maxWeight,itemCount,weights,profits,PQ);
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
	}

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

	private PriorityQueue<Integer>calcualteBestItemComboByBranchAndBound(int maxWeight,int itemCount, ArrayList<Integer>weights,ArrayList<Integer>values,PriorityQueue<Integer>Q){
		KnapsackNode<Integer>newNode = new KnapsackNode<Integer>(12,15,14);
		System.out.println(newNode.getInstance());

		return Q;
	}

	class KnapsackNode<T> extends Node{
		private Integer bound,weight,value;
		private Node knapsackNode;
		public KnapsackNode(int bound, int weight,int value){
			this.bound = bound;
			this.weight = weight;
			this.value = value;
			 knapsackNode = new Node(bound,weight,value);
		}

		public Node getInstance(){
			return knapsackNode;
		}

		public void setBound(int bound){
			this.bound = bound;
		}

		public void setWeight(int weight){
			this.weight = weight;
		}

		public void setValue(int value){
			this.value = value;
		}
	}
}
