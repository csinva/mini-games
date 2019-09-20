   import javax.swing.*;
   import java.awt.*;
   import java.awt.event.*;
	import java.text.*;
public class ScoreboardRightMatch extends JPanel{
	int numGuns;
	JPanel[] pArray;
	JLabel[] Ammos;
	public ScoreboardRightMatch(){
		numGuns=3;
		pArray=new JPanel[numGuns];
		Ammos=new JLabel[numGuns];
		setBackground(Color.black);
		setLayout(new GridLayout(numGuns*2+1,1));
		
		JLabel label=new JLabel("Weapons");
		label.setForeground(Color.orange);
		label.setFont(new Font("Serif",Font.BOLD,24));
		add(label);
		
		for(int x=1;x<numGuns;x++){
			pArray[x]=new JPanel();
			pArray[x].setBackground(Color.black);
			add(pArray[x]);
			
			Ammos[x]=new JLabel("    Ammo: ");
			Ammos[x].setForeground(Color.orange);
			add(Ammos[x]);
		}
		pArray[1].setBackground(new Color(244,161,19,175));
		ImageIcon uzi=new ImageIcon("pics/uzi.jpg");
		pArray[1].add(new JLabel(uzi));
		ImageIcon grenade=new ImageIcon("pics/grenade.jpg");
		pArray[2].add(new JLabel(grenade));
	}
	
	public void update(){
		for(int x=1;x<numGuns;x++)
			pArray[x].setBackground(Color.black);
		pArray[1].setBackground(new Color(244,161,19,175));
		
		Ammos[1].setText("    Ammo: INFINITY");
		Ammos[2].setText("    Ammo: INFINITY");
	}
}