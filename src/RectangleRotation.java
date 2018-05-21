import com.sun.org.apache.regexp.internal.RE;

import java.util.Arrays;

/**
 * Created by aloom on 4/5/2018.
 * TODO: Solution in progress.
 */

public class RectangleRotation {
	int[]xPoints;
	int[]yPoints;
	public static void main(String[]args)
	{
		RectangleRotation rectangleRotation = new RectangleRotation();
		rectangleRotation.setxPoints(6);
		rectangleRotation.setyPoints(4);
		int[][]points = new int[2][4];
		points = rectangleRotation.fillPoints(points);

		for (int i = 0; i < points.length; i++){
			for (int j = 0; j < points.length; j++)System.out.print(points[i][j]+" ");
			System.out.println();
		}
	}

	public void setxPoints(int width)//width = a
	{
		xPoints = new int[2];
		xPoints[0] = 0 - width;
		xPoints[1] = width;
		System.out.println("x points: "+Arrays.toString(xPoints));
	}

	public void setyPoints(int height){
		yPoints = new int[2];
		yPoints[0] = 0 - height;
		yPoints[1] = height;
		System.out.println("y points: "+Arrays.toString(yPoints));
	}

	public int[][] fillPoints(int[][]points){
		for (int i = 0; i < points.length; i++){
		}
		return points;
	}




}
