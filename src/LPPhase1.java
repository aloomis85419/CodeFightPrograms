import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Created by Aaron Loomis on 4/12/2018.
 */
public class LPPhase1 {

	/*
		Get input data and call methods to process that data and output it as an input file for lp phase 1.
	 */
	public static void main(String[]args){
		Double cx,cy,vx,vy,dx,dy,wx,wy;
		int [] a1, a2, a3, b1, b2, b3, b4;
		a1 = new int[]{1,-1};
		a2 = new int[]{2,1};
		a3 = new int[]{-1,3};
		b1 = new int[]{-1,-1};
		b2 = new int[]{2,0};
		b3 = new int[]{-1,1};
		b4 = new int[]{-3,0};
		String filename = "LPphase1Input.txt";
		FileWriter fw;
		PrintWriter writer;
		try {
			fw = new FileWriter(filename,false);
			writer = new PrintWriter(fw);
			Scanner sysIn = new Scanner(System.in);
			System.out.print("Enter a value for cx: ");
			cx = sysIn.nextDouble();
			System.out.print("Enter a value for cy: ");
			cy = sysIn.nextDouble();
			System.out.print("Enter a value for vx: ");
			vx = sysIn.nextDouble();
			System.out.print("Enter a value for vy: ");
			vy = sysIn.nextDouble();
			System.out.print("Enter a value for dx: ");
			dx = sysIn.nextDouble();
			System.out.print("Enter a value for dy: ");
			dy = sysIn.nextDouble();
			System.out.print("Enter a value for wx: ");
			wx = sysIn.nextDouble();
			System.out.print("Enter a value for wy: ");
			wy = sysIn.nextDouble();

			fw.close();
			writer.close();
		}
		catch (IOException e) {
			e.printStackTrace();
		}
	}
}
