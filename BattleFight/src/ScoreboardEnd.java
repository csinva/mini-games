   import javax.swing.*;
   import java.awt.*;
   import java.awt.event.*;
	import java.text.*;
public class ScoreboardEnd extends JPanel{////////sort before displaying
	JLabel kill, label4, health, minutes,secs, death, ov;
	JPanel sub;
	String eName;
	public ScoreboardEnd(){
		setBackground(Color.black);
		setLayout(new BorderLayout());
		Color tr=new Color(244,161,19,175);
		
		JPanel north=new JPanel();
		north.setBackground(tr);
		label4=new JLabel("KillStreaks: ");
		label4.setForeground(Color.orange);
		label4.setFont(new Font("Serif", Font.BOLD, 30));
		north.add(label4);
		health=new JLabel(""+100);
		health.setForeground(Color.green);
		health.setFont(new Font("Serif", Font.BOLD, 30));
		north.add(health);
		
		JLabel label3=new JLabel("Kills: ");
		label3.setForeground(Color.orange);
		label3.setFont(new Font("Serif", Font.BOLD, 30));
		north.add(label3);
		kill=new JLabel(""+0);
		kill.setForeground(Color.orange);
		kill.setFont(new Font("Serif", Font.BOLD, 30));
		north.add(kill);
		
		JLabel label7=new JLabel("Deaths: ");
		label7.setForeground(Color.orange);
		label7.setFont(new Font("Serif", Font.BOLD, 30));
		north.add(label7);
		death=new JLabel(""+0);
		death.setForeground(Color.orange);
		death.setFont(new Font("Serif", Font.BOLD, 30));
		north.add(death);
		
		JLabel label8=new JLabel("Overall: ");
		label8.setForeground(Color.orange);
		label8.setFont(new Font("Serif", Font.BOLD, 30));
		north.add(label8);
		ov=new JLabel(""+0);
		ov.setForeground(Color.orange);
		ov.setFont(new Font("Serif", Font.BOLD, 30));
		north.add(ov);
		
		add(north, BorderLayout.NORTH);
		
		sub=new JPanel();
		sub.setBackground(tr);
		sub.setLayout(new GridLayout(0,5));
		add(sub, BorderLayout.CENTER);
	}
	
	public int update(int kills,int h, int deaths, int[] eKills, int[] eDeaths, int numE,Color[] cols){// kills and deaths[5] are the player's
		int change=0;
		Color c=Color.orange;
		if(kills>deaths)
			c=Color.green;
		else if(deaths>kills)
			c=Color.red;
		kill.setForeground(c);
		death.setForeground(c);
		ov.setForeground(c);
		health.setForeground(c);
		
		kill.setText(""+kills);
		health.setText(""+h);
		death.setText(""+deaths);
		ov.setText(""+(kills-deaths));
		
		int[] eSpread=new int[numE+1];
		for(int x=0;x<=numE;x++)
			eSpread[x]=eKills[x]-eDeaths[x];
				
		JLabel blank=new JLabel(" ");
		JLabel name=new JLabel("Name");
		JLabel one=new JLabel("Kills");
		JLabel two=new JLabel("Deaths");
		JLabel spread= new JLabel("Overall");
		
		blank.setFont(new Font("Serif", Font.BOLD, 30));
		name.setFont(new Font("Serif", Font.BOLD, 30));
		one.setFont(new Font("Serif", Font.BOLD, 30));
		two.setFont(new Font("Serif", Font.BOLD, 30));
		spread.setFont(new Font("Serif", Font.BOLD, 30));
		
		sub.add(blank);
		sub.add(name);
		sub.add(one);
		sub.add(two);
		sub.add(spread);
		
		int[] tracker=new int[eKills.length];
		for(int x=0;x<tracker.length;x++)
			tracker[x]=0;
		tracker[eSpread.length-1]=1;
				
         int maxPos;
         for(int k = 0; k < eSpread.length; k++)
         {
            maxPos = findMax(eSpread, eSpread.length - k - 1);
            swap(eSpread, maxPos, eSpread.length - k - 1);
				swap(eKills, maxPos, eSpread.length - k - 1);
				swap(eDeaths, maxPos, eSpread.length - k - 1);
				swap(tracker, maxPos, eSpread.length - k - 1);
				swap(cols,maxPos,eSpread.length-k-1);
         }
  		int pos=0;
		for(int x=0;x<tracker.length;x++){
			if(tracker[x]==1)
				pos=x;
		}
		change=eSpread[pos];
		change*=4;
		change+=((1-pos)*10);
		
		String[] alphabet={"a","b","c","d","e","f","g","h","i","j","k","l","m","n","o","p","q","r","s","t","u","v","w","x","y","z","1","2","3","4","5","6","7","8","9"};
		for(int x=0;x<=numE;x++){//////////////////////////////change to 6
			int length=(int)(Math.random()*5+3);
			eName="";
			for(int a=0;a<length;a++){
				int random=(int)(Math.random()*34);
				eName+=alphabet[random];
			}
			JLabel p=new JLabel(""+(x+1));
			p.setForeground(cols[x]);
			p.setFont(new Font("Serif",Font.BOLD, 50));
			sub.add(p);
			
			if(x!=pos){
				JLabel t=new JLabel(eName);
				t.setFont(new Font("Serif",Font.BOLD, 30));
				t.setForeground(cols[x]);
				sub.add(t);
			}
			else{
				JLabel t=new JLabel("YOU");
				t.setFont(new Font("Serif",Font.BOLD, 30));
				t.setForeground(c);
				p.setForeground(c);
				t.setBackground(new Color(0,0,0,150));
				t.setOpaque(true);
				p.setBackground(new Color(0,0,0,150));
				p.setOpaque(true);
				sub.add(t);
			}
			JLabel t1=new JLabel(""+eKills[x]);
			t1.setFont(new Font("Serif",Font.BOLD, 30));
			t1.setForeground(cols[x]);
			sub.add(t1);
		
			JLabel t2=new JLabel(""+eDeaths[x]);
			t2.setFont(new Font("Serif",Font.BOLD, 30));
			t2.setForeground(cols[x]);
			sub.add(t2);	
			
			JLabel t3=new JLabel(""+eSpread[x]);
			t3.setFont(new Font("Serif",Font.BOLD, 30));
			t3.setForeground(cols[x]);
			sub.add(t3);	
			if(x==pos){
				t1.setForeground(c);
				t2.setForeground(c);
				t3.setForeground(c);
				t1.setBackground(new Color(0,0,0,150));
				t1.setOpaque(true);
				t2.setBackground(new Color(0,0,0,150));
				t2.setOpaque(true);
				t3.setBackground(new Color(0,0,0,150));
				t3.setOpaque(true);
			}
		}
		return change;		
	}	
	
		public int[] update(int kills2, int deaths2, int kills,int h, int deaths, int[] eKills, int[] eDeaths, int numE,Color[] cols){// kills and deaths[5] are the player's
		int[] change=new int[2];
		Color c=Color.orange;
		if(kills+kills2>deaths+deaths2)
			c=Color.green;
		else if(deaths+deaths2>kills+kills2)
			c=Color.red;
		kill.setForeground(c);
		death.setForeground(c);
		ov.setForeground(c);
		health.setForeground(c);
		
		kill.setText(""+kills+kills2);
		health.setText(""+h);
		death.setText(""+deaths+deaths2);
		ov.setText(""+(kills+kills2-deaths-deaths2));
		
		int[] eSpread=new int[numE+1];
		for(int x=0;x<=numE;x++)
			eSpread[x]=eKills[x]-eDeaths[x];
				
	
		JLabel blank=new JLabel(" ");
		JLabel name=new JLabel("Name");
		JLabel one=new JLabel("Kills");
		JLabel two=new JLabel("Deaths");
		JLabel spread= new JLabel("Overall");
		
		blank.setFont(new Font("Serif", Font.BOLD, 30));
		name.setFont(new Font("Serif", Font.BOLD, 30));
		one.setFont(new Font("Serif", Font.BOLD, 30));
		two.setFont(new Font("Serif", Font.BOLD, 30));
		spread.setFont(new Font("Serif", Font.BOLD, 30));
		
		sub.add(blank);
		sub.add(name);
		sub.add(one);
		sub.add(two);
		sub.add(spread);
		
		int[] tracker=new int[eKills.length];
		for(int x=0;x<tracker.length;x++)
			tracker[x]=0;
		tracker[eSpread.length-1]=1;
				
         int maxPos;
         for(int k = 0; k < eSpread.length; k++)
         {
            maxPos = findMax(eSpread, eSpread.length - k - 1);
            swap(eSpread, maxPos, eSpread.length - k - 1);
				swap(eKills, maxPos, eSpread.length - k - 1);
				swap(eDeaths, maxPos, eSpread.length - k - 1);
				swap(tracker, maxPos, eSpread.length - k - 1);
				swap(cols,maxPos,eSpread.length-k-1);
         }
  		int pos=0;
		for(int x=0;x<tracker.length;x++){
			if(tracker[x]==1)
				pos=x;
		}
		
		
		
		String[] alphabet={"a","b","c","d","e","f","g","h","i","j","k","l","m","n","o","p","q","r","s","t","u","v","w","x","y","z","1","2","3","4","5","6","7","8","9"};
		for(int x=0;x<=numE;x++){//////////////////////////////change to 6
			int length=(int)(Math.random()*5+3);
			eName="";
			for(int a=0;a<length;a++){
				int random=(int)(Math.random()*34);
				eName+=alphabet[random];
			}
			JLabel p=new JLabel(""+(x+1));
			p.setForeground(cols[x]);
			p.setFont(new Font("Serif",Font.BOLD, 50));
			sub.add(p);
			
			if(x!=pos){
				JLabel t=new JLabel(eName);
				t.setFont(new Font("Serif",Font.BOLD, 30));
				t.setForeground(cols[x]);
				sub.add(t);
			}
			else{
				JLabel t=new JLabel("YOU");
				t.setFont(new Font("Serif",Font.BOLD, 30));
				t.setForeground(c);
				p.setForeground(c);
				sub.add(t);
			}
			JLabel t1=new JLabel(""+eKills[x]);
			t1.setFont(new Font("Serif",Font.BOLD, 30));
			t1.setForeground(cols[x]);
			sub.add(t1);
		
			JLabel t2=new JLabel(""+eDeaths[x]);
			t2.setFont(new Font("Serif",Font.BOLD, 30));
			t2.setForeground(cols[x]);
			sub.add(t2);	
			
			JLabel t3=new JLabel(""+eSpread[x]);
			t3.setFont(new Font("Serif",Font.BOLD, 30));
			t3.setForeground(cols[x]);
			sub.add(t3);	
			if(x==pos){
				t1.setForeground(c);
				t2.setForeground(c);
				t3.setForeground(c);
			}
		}		
		return change;
	}
	
	public int[] swap(int[] array, int a, int b){
		int temp=array[a];
		array[a]=array[b];
		array[b]=temp;
		return array;
	}
	
	public Color[] swap(Color[] array, int a,int b){
		Color temp=array[a];
		array[a]=array[b];
		array[b]=temp;
		return array;
	}
	
	public static int findMax(int[] array, int upper){
			int high=array[0];
			int maxIndex=0;
         for(int a=0;a<=upper;a++){
               if(array[a]<high){
                  high=array[a];
                  maxIndex=a;
               }
            }
			return maxIndex;
      }
}