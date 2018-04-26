package Project7;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

class KnapsackNode<T> {

	protected Integer itemID,weight;
	Double valuePerWeight,bound, profit;
	ArrayList<KnapsackNode>itemsInNode = new ArrayList<>();
	KnapsackNode<T>parent = null;
	List<KnapsackNode<T>> children = new LinkedList<>();

	public KnapsackNode(){

	}

	public KnapsackNode(int itemID,int weight,double value,double bound){
		this.itemID = itemID;
		this.weight = weight;
		this.profit = value;
		this.bound = bound;
	}

	public void addChild(KnapsackNode<T> child) {
		if (children.size() < 2) {
			child.parent = this;
			this.children.add(child);
		}
		else System.out.println("Max number of children reached for this node.");
	}

	public boolean isRoot(){
		if (parent == null)return true;
		return false;
	}

	public String toString(){
		return itemID+" "+weight+" "+ profit +" "+ valuePerWeight;
	}

	public void printNodeValues(int weight,int profit,double bound, ArrayList<Integer>exclude, int items)
	{
		System.out.println("weight: "+this.weight+"\nprofit "+this.profit+"\ncurrent Bound: " +this.bound+"\n\n");
	}

}


