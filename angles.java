package small_projects;

//code to calculate how much of your screen a block takes up as you walk further and further away from it

//it is correct up to 12 decimal points of accuracy, which is at least equivalent to (1/ 10billion) if im correct

import java.lang.Math;
import java.util.ArrayList;

public class angles {

	public static void main(String[] args) {
		angles a = new angles();
		
		//System.out.println(a.calcsideAngles(3, 0, 1) );
		
		//ArrayList<Float> angles = a.calcVerticalAngles(3, 7, 14);
		
		//System.out.println(calcNearVerticalAngle(3, 7, 14) );
		//double ratio =  angles.get(1) / angles.get(0);
		//a.basic_angle();
		
		a.init();
		System.out.println(a.calcNearHorizontalAngles(3, 7, 14) );

	}
	
	
	
	//5000 so it doesn't get mixed up with any valid angles initially
	
	//will be used to save half of angle calculations
	static ArrayList<Float> nearVerticalAngles;
	
	public void init() {
		nearVerticalAngles = new ArrayList<Float>();
		float num = 5000;
		
		nearVerticalAngles.add(num);
		nearVerticalAngles.add(num);
		
	}
	
	//e1 and g1 will be related to near vertical angle,
	//e2 and g2 will be related to far vertical angle
	//look at documentation in calcNearVerticalAngle to understand
	static float e_one;
	static float e_two;
	static float g_one;
	static float g_two;
	
	//i have saved as ints because doubles will take up more space
	//however, this adds an extra 3 unneccessary commands 
	public static ArrayList<Float> calcNearVerticalAngles(float block_x, float block_y, float block_z) {
		
		
		//player co-ords
		float px=0;
		float py=0;
		float pz=0;
		
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
		
		float a = Math.abs(block_z-pz);
		float b = Math.abs(block_x-px);
		float c = (float) Math.sqrt( (a*a) + (b*b) );
		
		
		/*
		 calculate the hypotenuse of the second RA triangle between player y and block y
		 	
		          
		
				  
			  / |
			 /	|									(by)
			/	|								  /
		c  /	|	a				           /  |
		  /	    |					        /     |
		 /		|					   e /        |d
		/_______|					  /		      |
		    b			           /______________|
											c		(player y)  
		
		 
		 */
		
		
		float d = Math.abs(block_y -py -1); //we want the angle from the bottom to top of block, so -1
		float e = (float) Math.sqrt( (c*c) + (d*d) );
		
		//e, d, c == RA triangle
		//use law of Sines to find angle D, angle E ==90deg
		float angleD = findSin(d, e, 90);
		
		
		
		
		
		float f = d+1;
		float g = (float) Math.sqrt( (c*c) + (f*f) );
		
		e_one=e;
		g_one=g;
		/*
		 * 
		 * 	  / |								 /|				
			 /	|							   /  |h==1
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
		float h=1;
		float angleE = findCos(e, g, h);
		float angleF = 45- angleD - angleE;
		angleD+=45;
		
		ArrayList<Float> nv_angles = new ArrayList<Float>();
		nv_angles.add(angleD);
		nv_angles.add(angleE);
		nv_angles.add(angleF);
		
		
		return nv_angles;
		
	}
	
	public static ArrayList<Float> calcFarVerticalAngles(float block_x, float block_y, float block_z) {
		
		
		//player co-ords
		float px=0;
		float py=0;
		float pz=0;
		
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
		
		float a = Math.abs(block_z-pz);
		float b = Math.abs(block_x-px+1);
		float c = (float) Math.sqrt( (a*a) + (b*b) );
		
		
		/*
		 calculate the hypotenuse of the second RA triangle between player y and block y
		 	
		          
		
				  
			  / |
			 /	|									(by)
			/	|								  /
		c  /	|	a				           /  |
		  /	    |					        /     |
		 /		|					   e /        |d
		/_______|					  /		      |
		    b			           /______________|
											c		(player y)  
		
		 
		 */
		
		
		float d = Math.abs(block_y -py-1); //we want the angle from the bottom to top of block, so -1
		float e = (float) Math.sqrt( (c*c) + (d*d) );
		
		//e, d, c == RA triangle
		//use law of Sines to find angle D, angle E ==90deg
		float angleD = findSin(d, e, 90);
		
		
		
		
		
		float f = d+1;
		float g = (float) Math.sqrt( (c*c) + (f*f) );
		
		e_one=e;
		g_one=g;
		/*
		 * 
		 * 	  / |								 /|				
			 /	|							   /  |h==1
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
		float h=1;
		float angleE = findCos(e, g, h);
		float angleF = 45- angleD - angleE;
		angleD+=45;
		
		ArrayList<Float> fv_angles = new ArrayList<Float>();
		fv_angles.add(angleD);
		fv_angles.add(angleE);
		fv_angles.add(angleF);
		
		
		return fv_angles;
		
	}
	
	
	
	public ArrayList< ArrayList<Float>> calcVerticalAngles(float block_x, float block_y, float block_z) {
		//angle documentation is above
		
		
		if (nearVerticalAngles.get(1)==5000) {
			nearVerticalAngles= calcNearVerticalAngles(block_x, block_y, block_z);
		}

		ArrayList< ArrayList<Float>> VAs = new ArrayList<ArrayList<Float>>();
		VAs.add(nearVerticalAngles);
		VAs.add(calcFarVerticalAngles(block_x, block_y, block_z));
		
		
		
		return VAs;
	}
	
	
	public static ArrayList<Float> calcFarHorizontalAngles(float bx, float by, float bz) {
		//remember to change e2 and g2 to e1 and g1 after angles calculated
		
		
		
		float px=0;
		float py=0;
		float pz=0;
		
		/*
		double cosE = ( (e_one*e_one)+(e_two*e_two) - 1  ) / (2*e_one*e_two);
		double resultRadians = Math.acos(cosE);
		float nearHorizontalAngles= (float) Math.toDegrees(resultRadians);
		
		double cosG = ( (g_one*g_one)+(g_two*g_two) - 1  ) / (2*g_one*g_two);
		resultRadians = Math.acos(cosG);
		float farHorizontalAngle= (float) Math.toDegrees(resultRadians);
		
		e_one = e_two;
		g_one = g_two;
		*/
		
		float a = Math.abs(bz - pz);
		float b = Math.abs(by - py+1);
		float c = (float) Math.sqrt( (a*a) + (b*b) );
		
		float d = Math.abs(bx - px-1);
		float e = (float) Math.sqrt( (d*d) + (c*c) );
		float angleD = findCos(c, e, d);
		
		float f = d+1;
		float g = (float) Math.sqrt( (e*e) + (f*f) );
		
		float angleE = findCos(g, e, 1);
		
		/*
		
		|  /		/				/
		|		  /				/
		|  /	/			/
		|	  /			/
		|D/E/ F		/
   45deg|45deg		/
		
		D will equal angleD + 45deg, because the width of the screen is 90deg
		E is the angle to the block
		F is the rest of the 45 deg
		
		
		*/
		float angleF = 45 - angleD - angleE;
		angleD+=45;
		
		ArrayList<Float> angles = new ArrayList<Float>();
		angles.add(angleD);
		angles.add(angleE);
		angles.add(angleF);
		
		return angles;
	}
	
	
	public static ArrayList<Float> calcNearHorizontalAngles(float bx, float by, float bz) {
		//remember to change e2 and g2 to e1 and g1 after angles calculated
		
		
		
		float px=0;
		float py=0;
		float pz=0;
		
		/*
		double cosE = ( (e_one*e_one)+(e_two*e_two) - 1  ) / (2*e_one*e_two);
		double resultRadians = Math.acos(cosE);
		float nearHorizontalAngles= (float) Math.toDegrees(resultRadians);
		
		double cosG = ( (g_one*g_one)+(g_two*g_two) - 1  ) / (2*g_one*g_two);
		resultRadians = Math.acos(cosG);
		float farHorizontalAngle= (float) Math.toDegrees(resultRadians);
		
		e_one = e_two;
		g_one = g_two;
		*/
		
		float a = Math.abs(bz - pz);
		float b = Math.abs(by - py);
		float c = (float) Math.sqrt( (a*a) + (b*b) );
		
		float d = Math.abs(bx - px-1);
		float e = (float) Math.sqrt( (d*d) + (c*c) );
		float angleD = findSin(d, e, 90);
		
		float f = d+1;
		float g = (float) Math.sqrt( (e*e) + (f*f) );
		
		float angleE = findCos(g, e, 1);
		
		/*
		
		|  /		/				/
		|		  /				/
		|  /	/			/
		|	  /			/
		|D/E/ F		/
   45deg|45deg		/
		
		D will equal angleD + 45deg, because the width of the screen is 90deg
		E is the angle to the block
		F is the rest of the 45 deg
		
		
		*/
		float angleF = 45 - angleD - angleE;
		angleD+=45;
		
		ArrayList<Float> angles = new ArrayList<Float>();
		angles.add(angleD);
		angles.add(angleE);
		angles.add(angleF);
		
		return angles;
	}
	
	public static ArrayList< ArrayList< Float>> calcHorizontalAngles(float bx, float by, float bz) {
		ArrayList<Float> nearHA = calcNearHorizontalAngles(bx, by, bz);
		ArrayList<Float> farHA = calcFarHorizontalAngles(bx, by, bz);
		
		ArrayList< ArrayList< Float>> HAs = new ArrayList< ArrayList< Float>>();
		HAs.add(nearHA);
		HAs.add(farHA);
		
		return HAs;
	}
	
	
	
	public ArrayList< ArrayList<Float>> calcAngles(float x, float y, float z) {
		
		//this will return the angles from a player to a block
		//going from near vertical angle, far vertical angle, near horizontal angle, far horizontal angle, ie,
		
		
		
		/*
		 * 
		 * 
		 * 	player
		 * 
		 * 							n-h
		 * 						 _________
		 * 						|		  |
		 * 					n-v	|		  |  f-v
		 * 						|		  |
		 * 						|_________|
		 * 							f-v
		 * 
		 * likewise,
		 * 
		 * 							f-h
		 * 						 _________
		 * 						|		  |
		 * 					f-v	|		  |  n-v
		 * 						|		  |							
		 * 						|_________|
		 * 							n-h			player
		 * 
		 *    
		 * 
		 */
		angles a = new angles();
		
		//near angle first, far angle second
		ArrayList< ArrayList<Float>> verticalAngles = a.calcVerticalAngles(x, y, z);
		nearVerticalAngles = verticalAngles.get(1);
		//by changing the value of nearVerticalAngle here, it will not change the value of angles[0] above
		//because that is an new, individual variable, and not a pointer to nearVerticalAngle
		
		ArrayList< ArrayList<Float>> horizontalAngles = a.calcHorizontalAngles(x, y, z);
		
		return new ArrayList< ArrayList<Float>>();
	}
	  
	  //using law of Cosines to find angle C
	  public static float findCos(float a, float b, float c) {
		  //c is the important length, that is the length that the angle we are
		  //finding is facing
		  
		  //a^2 + b^2 - c^2 / 2ab
		  
		  double res = (a*a) + (b*b) - (c*c);
		  res/= (2*a*b);
		  
		  double resultRadians = Math.acos(res);
		  float angle= (float) Math.toDegrees(resultRadians);
		  
		  return angle;
	  }
	  
	//using law of Sines
	  public static float findSin(float length_a, float length_b, float angleB) {
		  //angleA is the important angle, that is the length that the angle we are
		  //finding is facing
		  
	  
		  double resultRadians = Math.asin( (length_a / length_b));
		  float angle= (float) Math.toDegrees(resultRadians);
		  
		  return angle;
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
				
//				double res = angleA+angleB+angleC;
				
//				String mes;
//				if ( (res) != 180) {
//					mes= i+": failure ";
////					double t = Math.abs(res-180);
////					if (t>0.00000000000001) {
////						System.out.println(i);
////					}
//					
//				}
//				else {
//					mes= i+": success";
//				}
//				
//				System.out.print(mes);
//				System.out.println(" res=="+res);
				System.out.println( angleB);
				
			}
			

			
		}

}








