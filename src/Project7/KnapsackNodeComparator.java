package Project7;

import java.util.Comparator;

class KnapsackNodeComparator implements Comparator<KnapsackNode> {
	public int compare(KnapsackNode o1, KnapsackNode o2) {
		return o2.valuePerWeight.compareTo(o1.valuePerWeight);
	}
}
