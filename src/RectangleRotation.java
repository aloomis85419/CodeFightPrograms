/**
 * Created by aloom on 4/5/2018.
 */
public class RectangleRotation {
	double[][]rotationMatrix;
	public static void main(String[]args){
		RectangleRotation rr = new RectangleRotation();
		rr.initializeRotationMatrix();
	}

	void initializeRotationMatrix(){
		rotationMatrix = new double[2][2];
		rotationMatrix[0][0] = 	Math.cos((Math.PI/4));
		rotationMatrix[0][1] = 0 - Math.sin((Math.PI/4));
		rotationMatrix[1][0] = Math.cos((Math.PI/4));
		rotationMatrix[1][1] = Math.sin((Math.PI/4));
		printMatrix(rotationMatrix);
	}

	int rectangleRotation(int a, int b){
		int pointCount = 0;

		return pointCount;
	}

	public void printMatrix(double[][]matrix){
		for (int i = 0; i < matrix.length; i++){
			for (int j = 0; j < matrix.length; j++){
				System.out.print(matrix[i][j]+"\t");
			}
			System.out.println();
		}
	}
}
