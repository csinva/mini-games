   import javax.swing.*;
   import java.awt.*;
   import java.awt.event.*;
	import java.text.*;
public class Scoreboard2 extends JPanel{
	JLabel info,score;
	public Scoreboard2(){
		setBackground(Color.black);
		
		info=new JLabel("Level 1 isn't really hard");
		info.setForeground(Color.green);
		add(info);
		
		JLabel label4=new JLabel("Score: ");
		label4.setForeground(Color.white);
		add(label4);
		score=new JLabel(""+0);
		score.setForeground(Color.white);
		add(score);
		
		
	}
	
	public void update(int level, int s){
		switch(level){
			case 2:info.setText("Meet the Zombies");
				break;
			case 3:info.setText("Coming from Both Sides");
				break;
			case 4:info.setText("Meet the shadow snipers");
				break;
			case 5:info.setText("Meet a bunch of shadow snipers");
				break;
			case 6:info.setText("Shadow snipers and zombies");
				break;
			case 7:info.setText("Meet the speed warriors");
				break;
			case 8:info.setText("Shadow snipers in the middle");
				break;
			default:info.setText("Going to get harder");
				break;
			
		}
		score.setText(""+s);
	}	
}