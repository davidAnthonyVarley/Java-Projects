import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.Scanner;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.JButton;

public class xs_and_os_button implements ActionListener {
	
	 //make sure to close clips
	static Clip previousClip;
	
	public final static int SCREEN_X = 600;
	public final static int SCREEN_Y = 600;
	
	int number;
	String letter;
	JButton button;
	
	public xs_and_os_button (int buttonx, int buttony, int number) {
		
		int label = number +1;
		JButton b = new JButton(Integer.toString(label));
		b.addActionListener(this);
		b.setBounds( buttonx,  buttony, SCREEN_X/3, SCREEN_Y/3);
		b.setVisible(true);
		
		b.setOpaque(false);
		
		this.number = number;
		this.letter= " ";
		this.button = b;

		
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		//System.out.println("button " + this.number + "pressed");
		mainn.playGame(this);
		
		try {
			buttonPressedAudio();
		} catch (UnsupportedAudioFileException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (LineUnavailableException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
	}
	
	public static void buttonPressedAudio() throws UnsupportedAudioFileException, IOException, LineUnavailableException {
		
		//if not the first turn, close the previous audio clip
		if (mainn.turn!=3) {
			previousClip.stop();
			previousClip.close();
		}
		
		AudioInputStream ais = AudioSystem.getAudioInputStream(new File("mixkit-cool-interface-click-tone-2568.wav"));
		Clip c = AudioSystem.getClip();
		
		previousClip = c; //assign pointer to new clip
		
		
		c.open(ais);
		
		c.start();
		
		
	}
	

}
