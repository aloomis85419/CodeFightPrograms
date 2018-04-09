/**
 * Created by aloom on 3/14/2018.
 */
import java.util.Scanner;
public class SequenceAlignmentProblem {
	private static Integer SEQ1LENGTH;
	private static Integer SEQ2LENGTH;
	private static int min;
	private static Character[][]dirArrows;
	private static Character diagArrow = '\u2198';
	private static Character downArrow = '\u2193';
	private static Character rightArrow = '\u2192';
	private static Integer[]posInS1;
	private static Integer[]posInsS2;
	public static void main (String[]args){
		Scanner sc = new Scanner(System.in);
		String seq1 = "CATAATTAT-";
		String seq2 = "GATGAATCAC-";
		Integer[][]seqAlign;
		Integer[][]initMatrix;
		/*
		System.out.println("+++++++++++++++++++++++++++");
		System.out.println("Welcome to Sequence Aligner");
		System.out.println("+++++++++++++++++++++++++++\n");
		System.out.println("Input restrictions: Must enter the larger of two sequences first.");
		System.out.println("Enter genetic sequence 1 followed by a dash: ");
		seq1 = sc.nextLine();
		System.out.println("Enter genetic sequence 2 followed by a dash: ");
		seq2 = sc.nextLine();
		*/
		SEQ1LENGTH = seq1.length();
		SEQ2LENGTH = seq2.length();
		seqAlign = new Integer[SEQ1LENGTH][SEQ2LENGTH];
		dirArrows = new Character[SEQ1LENGTH][SEQ2LENGTH];
		initMatrix = initializeMatrix(seqAlign);
		initializeDirMatrix();
		print2DMatrix(minPenalty(initMatrix,seq1,seq2),seq1,seq2);
		System.out.println("\n");
		print2DMatrix(dirArrows,seq1,seq2);
		System.out.println("Minimum penalty: "+ min);
		printOptimalStrings(seq1,seq2);
	}

	public static void print2DMatrix(Character[][]printout,String seq1, String seq2){
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
				min = getmin(topleft+1,left+2,top+2);
				initMatrix[i-1][j-1] = min;
				if (seq1.charAt(i-1) == seq2.charAt(j-1)) {
					initMatrix[i - 1][j - 1] = topleft;
					dirArrows[i-1][j-1] = diagArrow;
				}
				else {
					if (min == left+2)dirArrows[i - 1][j - 1] = rightArrow;
					if (min == top+2)dirArrows[i - 1][j - 1] = downArrow;
					if (min == topleft + 1)dirArrows[i - 1][j - 1] = diagArrow;
				}
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
	public static void initializeDirMatrix(){
		int i, j;
		for (i = 0; i  < SEQ1LENGTH; i++){
			for (j = 0; j < SEQ2LENGTH; j++){
				if (dirArrows[i][j] == null)dirArrows[i][j] = 'x';
			}
		}
		for (i = SEQ1LENGTH - 2; i >= 0; i--) {
			dirArrows[i][SEQ2LENGTH - 1] = downArrow;
		}
		for (i = SEQ2LENGTH - 2; i >= 0; i--) {
			dirArrows[SEQ1LENGTH - 1][i] = rightArrow ;
		}
	}

	public static void printOptimalStrings(String original1, String original2){
		int i,j;
		i = j = 0;
		String optimals2 = "";
		String optimals1 = "";
		while (i < SEQ1LENGTH-1 && j < SEQ2LENGTH-1){
			if (dirArrows[i][j]==diagArrow){
				optimals1+=original1.charAt(i);
				optimals2+=original2.charAt(j);
				i+=1;
				j+=1;
			}
			else if (dirArrows[i][j]==rightArrow){
				optimals1+=original2.charAt(i);
				optimals2+='-';
				j+=1;
			}
			else if (dirArrows[i][j]==downArrow){
				optimals1+=original1.charAt(i);
				optimals2+=original2.charAt(j);
				i+=1;
			}
		}
		System.out.println(optimals2);
		System.out.println(optimals1);
	}

	public static int getmin(int v1, int v2, int v3){
		return Math.min(v1, Math.min(v2,v3));
	}
}
