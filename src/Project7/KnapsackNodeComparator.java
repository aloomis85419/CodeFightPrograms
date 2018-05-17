package Project7;

import java.util.Comparator;

/**
 * Created by aloom on 5/6/2018.
 */
public class KnapsackNodeComparator implements Comparator<KnapsackNode> {
	@Override
	public int compare(KnapsackNode o1, KnapsackNode o2) {
		if (o1.getBound() < o2.getBound())return 0;
		else return 1;
	}
}
