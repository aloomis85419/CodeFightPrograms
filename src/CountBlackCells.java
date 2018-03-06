/**
 * Created by aloom on 3/2/2018.
 */
public class CountBlackCells {
	int countBlackCells(int n, int m) {
		int gcd = 0;
		for(int i = 1; i < m+n; i++)
		{
			if(i > m && i > n)break;
			if(n%i == 0 && m%i == 0)gcd = i;
		}
		return (m+n)+(gcd - 2);
	}
}
