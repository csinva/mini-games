   import javax.swing.*;
   import java.awt.*;
   import java.awt.event.*;
	import java.text.*;
public class Scoreboard extends JPanel{
	JLabel kill, lev, label4, health, numRemaining, accuracy;
	public Scoreboard(){
		setBackground(Color.black);
		
		label4=new JLabel("Health: ");
		label4.setForeground(Color.green);
		add(label4);
		health=new JLabel(""+100);
		health.setForeground(Color.green);
		add(health);
		
		JLabel label2=new JLabel("Level: ");
		label2.setForeground(Color.orange);
		label2.setFont(new Font("Serif", Font.BOLD, 30));
		add(label2);
		lev=new JLabel(""+1);
		lev.setForeground(Color.orange);
		lev.setFont(new Font("Serif", Font.BOLD, 30));
		add(lev);
		
		JLabel label3=new JLabel("Kills: ");
		label3.setForeground(Color.orange);
		add(label3);
		kill=new JLabel(""+0);
		kill.setForeground(Color.orange);
		add(kill);
		
		JLabel label5=new JLabel("Enemies Alive: ");
		label5.setForeground(Color.white);
		add(label5);
		numRemaining=new JLabel(""+0);
		numRemaining.setForeground(Color.white);
		add(numRemaining);
		
		JLabel label6=new JLabel("Accuracy: ");
		label6.setForeground(Color.orange);
		add(label6);
		accuracy=new JLabel("");
		accuracy.setForeground(Color.orange);
		add(accuracy);
	}
	public Scoreboard(int x){
		setBackground(Color.black);
		
		JLabel label2=new JLabel("Skill: ");
		label2.setForeground(Color.orange);
		label2.setFont(new Font("Serif", Font.BOLD, 50));
		add(label2);
		lev=new JLabel(""+x);
		lev.setForeground(Color.orange);
		lev.setFont(new Font("Serif", Font.BOLD, 30));
		add(lev);
		
		JLabel label3=new JLabel("Kills: ");
		label3.setForeground(Color.orange);
		add(label3);
		kill=new JLabel(""+0);
		kill.setForeground(Color.orange);
		add(kill);
		
		JLabel label6=new JLabel("Deaths: ");
		label6.setForeground(Color.orange);
		add(label6);
		accuracy=new JLabel("");
		accuracy.setForeground(Color.orange);
		add(accuracy);
	}
	public void update(int s,int k,int d){
		kill.setText(""+k);
		accuracy.setText(""+d);
		lev.setText(""+s);
	}
	
	public void update(int n, int b, int h, int l, double ac){
		numRemaining.setText(""+n);
		kill.setText(""+b);
		health.setText(""+h);
		lev.setText(""+l);
      DecimalFormat d = new DecimalFormat("0.00");

		accuracy.setText(""+d.format(ac));
		if(h<0)
			health.setText(""+0);
			if(h>=80){
				label4.setForeground(Color.green);
				health.setForeground(Color.green);
			}
			if(h<80){
				label4.setForeground(Color.yellow);
				health.setForeground(Color.yellow);
			}
			if(h<=30){
				label4.setForeground(Color.red);
				health.setForeground(Color.red);
			}
	}	
}