   import javax.swing.*;
   import java.awt.*;
   import java.awt.event.*;
	import java.text.*;
public class ScoreboardMatch extends JPanel{
	JLabel kill, label4, label3,health, minutes,secs, death;
	public ScoreboardMatch(){
		setBackground(Color.black);
		
		label4=new JLabel("Health: ");
		label4.setForeground(Color.green);
		add(label4);
		health=new JLabel(""+100);
		health.setForeground(Color.green);
		add(health);
		
		JLabel label3=new JLabel("Kills: ");
		label3.setForeground(Color.orange);
		add(label3);
		kill=new JLabel(""+0);
		kill.setForeground(Color.orange);
		add(kill);
		
		JLabel label7=new JLabel("Deaths: ");
		label7.setForeground(Color.orange);
		add(label7);
		death=new JLabel(""+0);
		death.setForeground(Color.orange);
		add(death);
		
		JLabel label5=new JLabel("Time: ");
		label5.setForeground(Color.white);
		add(label5);
		minutes=new JLabel(""+5+":");
		minutes.setForeground(Color.white);
		add(minutes);
		DecimalFormat d = new DecimalFormat("00");
		secs=new JLabel(""+d.format(00));
		secs.setForeground(Color.white);
		add(secs);
	}
	public ScoreboardMatch(int x){
		setBackground(Color.black);
		
		label4=new JLabel("Skill: ");
		label4.setForeground(Color.green);
		label4.setFont(new Font("Serif",Font.BOLD,40));
		add(label4);
		health=new JLabel(""+x);
		health.setForeground(Color.green);
		health.setFont(new Font("Serif",Font.BOLD,40));
		add(health);
		
		JLabel label5=new JLabel("Time: ");
		label5.setForeground(Color.white);
		label5.setFont(new Font("Serif",Font.BOLD,40));
		add(label5);
		minutes=new JLabel(""+5+":");
		minutes.setForeground(Color.white);
		minutes.setFont(new Font("Serif",Font.BOLD,40));
		add(minutes);
		DecimalFormat d = new DecimalFormat("00");
		secs=new JLabel(""+d.format(00));
		secs.setForeground(Color.white);
		secs.setFont(new Font("Serif",Font.BOLD,40));
		add(secs);
	}
	public ScoreboardMatch(int x,int y){
		setBackground(Color.black);
		
		label4=new JLabel("Skill: ");
		label4.setForeground(Color.green);
		label4.setFont(new Font("Serif",Font.BOLD,40));
		add(label4);
		health=new JLabel(""+x);
		health.setForeground(Color.green);
		health.setFont(new Font("Serif",Font.BOLD,40));
		add(health);
		
		label3=new JLabel("Skill 2: ");
		label3.setForeground(new Color(255,0,255));
		label3.setFont(new Font("Serif",Font.BOLD,40));
		add(label3);
		kill=new JLabel(""+y);
		kill.setForeground(new Color(255,0,255));
		kill.setFont(new Font("Serif",Font.BOLD,40));
		add(kill);
		
		JLabel label5=new JLabel("Time: ");
		label5.setForeground(Color.white);
		label5.setFont(new Font("Serif",Font.BOLD,40));
		add(label5);
		minutes=new JLabel(""+5+":");
		minutes.setForeground(Color.white);
		minutes.setFont(new Font("Serif",Font.BOLD,40));
		add(minutes);
		DecimalFormat d = new DecimalFormat("00");
		secs=new JLabel(""+d.format(00));
		secs.setForeground(Color.white);
		secs.setFont(new Font("Serif",Font.BOLD,40));
		add(secs);
	}
	public void update(int time,int skill){
		minutes.setText(""+time/60+":");
		DecimalFormat d = new DecimalFormat("00");
		secs.setText(""+d.format(time%60));
		health.setText(""+skill);
	}
	public void update(int time,int skill,int skill2){
		kill.setText(""+skill2);
		minutes.setText(""+time/60+":");
		DecimalFormat d = new DecimalFormat("00");
		secs.setText(""+d.format(time%60));
		health.setText(""+skill);
	}
	public void update(int time,int kills,int h, int deaths){
		minutes.setText(""+time/60+":");
		DecimalFormat d = new DecimalFormat("00");
		secs.setText(""+d.format(time%60));
		kill.setText(""+kills);
		health.setText(""+h/10);
		death.setText(""+deaths);
		
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