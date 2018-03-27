/**
 * Created by aloom on 3/14/2018.
 */
import java.util.Scanner;
public class SequenceAlignmentProblem {
	private static Integer SEQ1LENGTH;
	private static Integer SEQ2LENGTH;
	private static int min;
	public static void main (String[]args){
		Scanner sc = new Scanner(System.in);
		String seq1 = "CATCATTAG-";
		String seq2 = "GATCAATTAC-";
		Integer[][]seqAlign;
		Integer[][]initMatrix;
		System.out.println("+++++++++++++++++++++++++++");
		System.out.println("Welcome to Sequence Aigner");
		System.out.println("+++++++++++++++++++++++++++\n");
		System.out.println("Input restrictions: Must enter the larger of two sequences first.");
		System.out.println("Enter genetic sequence 1 followed by a dash: ");
		seq1 = sc.nextLine();
		System.out.println("Enter genetic sequence 2 followed by a dash: ");
		seq2 = sc.nextLine();
		SEQ1LENGTH = seq1.length();
		SEQ2LENGTH = seq2.length();
		seqAlign = new Integer[SEQ1LENGTH][SEQ2LENGTH];
		initMatrix = initializeMatrix(seqAlign);
		print2DMatrix(initMatrix,seq1,seq2);
		print2DMatrix(minPenalty(initMatrix,seq1,seq2),seq1,seq2);
		System.out.println("Minimum penalty: "+ min);
	}

	public static void print2DMatrix(Integer[][]printout,String seq1, String seq2){
		int i, j;
		for (i = 0; i < SEQ2LENGTH; i++) {
			System.out.print("\t"+seq2.charAt(i));
		}
		System.out.println();
		for (i = 0; i < SEQ1LENGTH; i++){
			System.out.print(seq1.charAt(i)+"\t");
			for (j = 0; j < SEQ2LENGTH; j++){
				System.out.print(printout[i][j]+"\t");
			}
			System.out.println();
		}
	}

	public static Integer[][]minPenalty(Integer[][]initMatrix,String seq1,String seq2) {
		int i, j;
		int topleft, top, left;
		System.out.println("\n\n");
		for (i = SEQ1LENGTH - 1; i > 0; i--) {
			for (j = SEQ2LENGTH - 1; j > 0; j--) {
				//initMatrix[i-1][j-1]
				topleft = initMatrix[i][j];
				top = initMatrix[i][j - 1];
				left = initMatrix[i - 1][j];
				initMatrix[i-1][j-1] = getmin(topleft+1,left+2,top+2);
				if (seq1.charAt(i-1) == seq2.charAt(j-1))initMatrix[i-1][j-1] = topleft;
			}
		}
		min = initMatrix[0][0];
		return initMatrix;
	}

	//seq1 aligned along rows, seq2 aligned along columns
	public static Integer[][]initializeMatrix(Integer[][]initMatrix){
		int i, j;
		int index = 0;
		for (i = SEQ1LENGTH - 1; i >= 0; i--) {
			initMatrix[i][SEQ2LENGTH - 1] = index;
			index+=2;
		}
		index = 0;
		for (i = SEQ2LENGTH - 1; i >= 0; i--) {
			initMatrix[SEQ1LENGTH - 1][i] = index;
			index+=2;
		}

		for (i = 0; i  < SEQ1LENGTH; i++){
			for (j = 0; j < SEQ2LENGTH; j++){
				if (initMatrix[i][j] == null)initMatrix[i][j] = 0;
			}
		}
		return initMatrix;
	}

	public static int getmin(int v1, int v2, int v3){
		return Math.min(v1, Math.min(v2,v3));
	}
}
