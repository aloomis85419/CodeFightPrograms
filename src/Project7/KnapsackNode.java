package Project7;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

/*
	A a recursive node structure. Allows for creation of single node or nodes within nodes. Can contain any data type.
 */
class KnapsackNode<T> {

	protected int itemID,weight,nodeLevel;
	double bound, profit,valuePerWeight;
	ArrayList<Integer>itemsInNode = new ArrayList<>();
	KnapsackNode<T>parent = null;
	List<KnapsackNode<T>> children = new LinkedList<>();

	public KnapsackNode(){

	}

	/*
		Constructor that allows valuePer weight to be calculated and set upon creation of the node.
	 */
	public KnapsackNode(int itemID,int weight,double value,int valuePerWeight){
		this.itemID = itemID;
		this.weight = weight;
		this.profit = value;
		this.valuePerWeight = valuePerWeight;
	}

	public void addChild(KnapsackNode<T> child) {
		if (children.size() < 2) {
			this.children.add(child);
		}
		else System.out.println("Max number of children reached for this node.");
	}

	public void setParent(KnapsackNode parent){
		this.parent = parent;
	}

	public String toString(){
		return itemID+" "+weight+" "+ profit +" "+ valuePerWeight;
	}

	public void setNodeLevel(int level){
		this.nodeLevel = level;
	}

	public double getBound(){
		return this.bound;
	}

	public void setItemID(int itemID){
		this.itemID = itemID;
	}
}


