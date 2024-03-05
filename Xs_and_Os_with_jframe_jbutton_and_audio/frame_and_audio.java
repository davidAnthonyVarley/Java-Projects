import java.awt.Color;
import java.io.File;
import java.io.IOException;
import java.util.Scanner;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.ImageIcon;
import javax.swing.JFrame;

public class frame_and_audio {
	
	public final static int SCREEN_X = 600;
	public final static int SCREEN_Y = 600;

    public static JFrame createFrame() {
        JFrame frame = new JFrame();

        frame.setSize(SCREEN_X, SCREEN_Y);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setBackground(new Color(205, 0, 255));
        frame.setResizable(false);
        
        ImageIcon i = new ImageIcon("header.png");
        frame.setIconImage(i.getImage());

        return frame;
    }
    
    public static Clip playSong() throws UnsupportedAudioFileException, IOException, LineUnavailableException {
    	AudioInputStream ais = AudioSystem.getAudioInputStream(new File("Lotus Pond - Aakash Gandhi.wav"));
    	Clip cl = AudioSystem.getClip();
    	cl.open(ais);
    	
    	Scanner i = new Scanner(System.in);
		cl.start();
		
		return cl;
    }
}

























