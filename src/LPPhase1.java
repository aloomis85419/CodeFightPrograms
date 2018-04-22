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
		Double[]a1,a2,a3,b1,b2,b3,b4,cCenter,dCenter;
		a1 = new Double[]{1.0,-1.0};
		a2 = new Double[]{2.0,1.0};
		a3 = new Double[]{-1.0,3.0};
		b1 = new Double[]{-1.0,-1.0};
		b2 = new Double[]{2.0,0.0};
		b3 = new Double[]{-1.0,1.0};
		b4 = new Double[]{-3.0,0.0};
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
			cCenter = new Double[]{cx,cy};
			dCenter = new Double[]{dx,dy};
			fw.close();
			writer.close();
		}
		catch (IOException e) {
			e.printStackTrace();
		}
	}

	public Double[][]generateTableau(int rows, int columns){
		Double[][]tableau = new Double[rows][columns];



		return tableau;
	}
}
