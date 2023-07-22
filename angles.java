package small_projects;

//code to calculate how much of your screen a block takes up as you walk further and further away from it

//it is correct up to 12 decimal points of accuracy, which is at least equivalent to (1/ 10billion) if im correct

import java.lang.Math;

public class angles {

	public static void main(String[] args) {
		angles a = new angles();
		a.angle();

	}
	
	public void angle() {
		
		final double angleC=90;
		final double lengthB=1;
		//double length=1;
		
		
		for (int i=0; i<10; i++) {
			double lengthA=i+1;
			double lengthC;
			//lengthC = hypotenuse, lengthB = opposite, lengthA= adjacent

			
			double angleA;
			double angleB;
			
			lengthC = Math.sqrt( (lengthA*lengthA) + (lengthB*lengthB) );			
			//h = sqrt( o^2 + a^2)
			
			//find angle B, the angle facing the opposite side
			double resultRadians = Math.asin(lengthB / lengthC);
			angleB = Math.toDegrees(resultRadians);
			
			//find angle A, facing the adjacent side
			resultRadians = Math.asin(lengthA / lengthC);
			angleA = Math.toDegrees(resultRadians);
			
			
			//check if all angles add up to 180 deg
			
//			double res = angleA+angleB+angleC;
			
//			String mes;
//			if ( (res) != 180) {
//				mes= i+": failure ";
////				double t = Math.abs(res-180);
////				if (t>0.00000000000001) {
////					System.out.println(i);
////				}
//				
//			}
//			else {
//				mes= i+": success";
//			}
//			
//			System.out.print(mes);
//			System.out.println(" res=="+res);
		}
		
	}

}
