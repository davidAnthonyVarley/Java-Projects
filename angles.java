package small_projects;

//code to calculate how much of your screen a block takes up as you walk further and further away from it

//it is correct up to 12 decimal points of accuracy, which is at least equivalent to (1/ 10billion) if im correct

import java.lang.Math;

public class angles {

	public static void main(String[] args) {
		angles a = new angles();
		
		System.out.println(a.calcAngle(1, 1, 0) );
		a.basic_angle();

	}
	
	public void basic_angle() {
		
		final double angleC=90;
		final double lengthB=1;
		//double length=1;
		
		
		for (int i=0; i<1; i++) {
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
			System.out.println( angleB);
		}
		

		
	}
	
	
	public double calcAngle(int block_x, int block_y, int block_z) {
		
		double b_x = (double) block_x;
		double b_y = (double) block_y;
		double b_z = (double) block_z;
		
		//player co-ords
		double px=0;
		double py=0;
		double pz=0;
		
		/*
		 calculate the hypotenuse of the first RA triangle between player (x, z) and block (x, z)
		 	b
		 ------ (bx, bz)
		 |	  /
	  a	 |	 / 
		 |	/ c
		 | /
		 |/
		 (player x, player z)
		 
		 
		 */
		
		//since the numerically lowest co-ords will be (0, 0, 0)
		//there is no need to change every co-ord to its abs value
		
		double a = Math.abs(b_z-pz);
		double b = Math.abs(b_x-px);
		double c = Math.sqrt( (a*a) + (b*b) );
		
		
		/*
		 calculate the hypotenuse of the second RA triangle between player y and block y
		 	
		          
		
				  
			  / |
			 /	|									(by)
			/	|								  /
		c   /	|	a				           /  |
		  /	    |					        /     |
		 /		|					   e /        |d
		/_______|					  /		      |
		    b			           /______________|
											c		(player y)  
		
		 
		 */
		
		
		double d = Math.abs(b_y -py -1); //we want the angle from the bottom to top of block, so -1
		double e = Math.sqrt( (c*c) + (d*d) );
		
		double f = d+1;
		double g = Math.sqrt( (c*c) + (f*f) );
		
		
		/*
		 * 
		 * 	  / |								 /|				
			 /	|							   /  |
		c	/	|						g	 /	 /|
		   /	|	a				       /    / |  f
		  /	    |					     /   /    |
		 /		|					   / e /      |
		/_______|					 / /	      |
		   b	                   /______________|
											c		
		 */
		
		//now find angle using law of Cosines
		
		//g = hypotenuse
		//e = adjacent
		//h = opposite = 1, because every block is 1x1x1
		
		//cos(A) = (b^2 + c^2 - a^2) / (2 * b * c)
		double h=1;
		
		double cosH = ( (g*g) + (e*e) - (h*h) ) / (2*g*e);
		double resultRadians = Math.acos(cosH);
		double angleFromPlayerToBlock= Math.toDegrees(resultRadians);
		
		return angleFromPlayerToBlock;
	}
	
	
	
	
	
	

}
