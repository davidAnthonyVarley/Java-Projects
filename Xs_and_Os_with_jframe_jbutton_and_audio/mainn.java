
import java.awt.Color;
import java.awt.Font;
import java.io.IOException;

import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class mainn {
    static int turn =2;
    static String currentLetter;
    static Clip mainClip;
    
    //the button currently selected
    static int i;
    static JFrame frame;

    public static void main(String[] args) throws UnsupportedAudioFileException, IOException, LineUnavailableException {
    	frame = frame_and_audio.createFrame();
        mainClip = frame_and_audio.playSong();
    	
        addButtons(frame);
        
    }

    public static void playGame(xs_and_os_button b) {

        if (turn%2==0)
            currentLetter="X";
        else
            currentLetter="O";
        turn++;
        
        char letter = currentLetter.charAt(0);
        b.button.setText(currentLetter);
        
        //change the board and simutaneously check the result
        char result =functionality.changeBoard(b.number, letter);
        
        if (result=='X' || result=='O')
        	gameOver(result);
        
    }

    
    
     static void addButtons(JFrame frame) {
    	
    	int colcount=0;
    	int bx=0;
    	int by=0;
    	
    	for (i=0;i<9;i++, bx+=frame_and_audio.SCREEN_X/3, colcount++ ) {
    		
    		if (colcount==3) {
    			colcount=0;
    			
    			//reformat where buttons are placed
    			by+=frame_and_audio.SCREEN_Y/3;
    			bx=0;
    		}
    		xs_and_os_button b = new xs_and_os_button(bx-10, by, i);
    		frame.add(b.button);
    	}
    	
    }
     
    static void gameOver(char winner) {
    	//close main frame
    	//xs_and_os_button.previousClip.stop();
    	//xs_and_os_button.previousClip.close();
    	
    	mainClip.stop();
    	mainClip.close();
    	
    	frame.dispose();
    	
    	
    	JLabel label = new JLabel();
    	
    	label.setSize(frame_and_audio.SCREEN_X/2, frame_and_audio.SCREEN_Y/2);
    	label.setFont( new Font("Arial", Font.BOLD, 24));
    	
    	label.setHorizontalAlignment(JLabel.CENTER);
    	label.setVerticalTextPosition( JLabel.CENTER);
    	
    	label.setText("Player "+ winner + " wins!");
    	
    	
    	
    	JFrame end = new JFrame();
    	end.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    	end.setSize(frame_and_audio.SCREEN_X, frame_and_audio.SCREEN_Y);
    	
    	ImageIcon i = new ImageIcon("header.png");
        end.setIconImage(i.getImage());

    	
    	if (winner=='X') {
    		end.getContentPane().setBackground(new Color(185, 0, 0));
    		label.setForeground(Color.GREEN);
    	}
    	else {
    		end.getContentPane().setBackground(new Color(0, 0, 185));
    		label.setForeground(Color.ORANGE);
    	}
    	
    	
    	end.setVisible(true);
    	
    	
    	
    	
    	//end.getContentPane().setBackground(new Color(0, 0, 0));
    	
    	
    	end.add(label);
    	
    }
    
}





















