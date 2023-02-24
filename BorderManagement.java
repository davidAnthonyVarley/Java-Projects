//playing around with frames, panels and border layout attributes


import javax.swing.JFrame;
import javax.swing.JPanel;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;

public class BorderManagement {
	
	public static void main(String[] args) {
	JFrame frame = new JFrame();
	
	frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	frame.setSize(500, 500);
	frame.setLayout( new BorderLayout());
	frame.setVisible(true);
	
	JPanel redP = new JPanel();
	JPanel blueP = new JPanel();
	JPanel yellowP = new JPanel();
	JPanel greenP = new JPanel();
	JPanel purpleP = new JPanel();
	
	JPanel[] panels = {redP, blueP, yellowP, greenP, purpleP};
	JPanel[] subpanels = new JPanel[5];
	String[] locations = {BorderLayout.NORTH, BorderLayout.SOUTH, BorderLayout.EAST, BorderLayout.WEST, BorderLayout.CENTER};
	Color[] colours = {Color.blue, Color.red, Color.green, Color.yellow, Color.black};

	
	redP.setBackground(Color.red);
	blueP.setBackground(Color.blue);
	yellowP.setBackground(Color.yellow);
	greenP.setBackground(Color.green);
	purpleP.setBackground(Color.magenta);
	
	
	for (int i =0; i< panels.length; i++) {
		panels[i].setPreferredSize(new Dimension(100, 100));
	}
	
	
	
	
	
	frame.add(redP, BorderLayout.NORTH);
	frame.add(blueP, BorderLayout.SOUTH);
	frame.add(yellowP, BorderLayout.EAST);
	frame.add(greenP, BorderLayout.WEST);
	frame.add(purpleP, BorderLayout.CENTER);
	
	purpleP.setLayout(new BorderLayout( 3, 3));
	
	for (int i =0; i< subpanels.length; i++) {
		subpanels[i] = new JPanel();
		subpanels[i].setPreferredSize(new Dimension(100, 100));
		subpanels[i].setBackground(colours[i]);
		purpleP.add(subpanels[i], locations[i]);
	}
	
	//System.out.println(yellowP.WIDTH);
	
	}

}
