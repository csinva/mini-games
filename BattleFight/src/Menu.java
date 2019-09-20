   import javax.swing.*;
   import java.awt.*;
   import java.awt.event.*;
   import java.awt.image.*;
	import java.text.*;
	import java.io.*;
	import java.util.*;
   public class Menu extends JPanel{
      private static final int FRAME = 800;
      private BufferedImage buff,buff2;
      private Graphics g,g2;
   	JPanel temp, selectDifficulty,selectDifficulty2, selectDifficulty3,accounts,pick,pick4,picker;
		boolean ranked=false;
		JButton scores,scores2,scores3;
		Scanner scanman;
		JPanel s,in;
		JLabel instructions,instructions2;
		ImageIcon robot;
		int players=0;
		AccountListener AL1;
		AccountListener AL2;
		boolean added1,added2=false;
      public static void main(String[] args) {                          
         JFrame frame = new JFrame("BattleFight");
         frame.setSize(800, 800);   
         frame.setLocation(200, 50);
         frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
         frame.setContentPane(new Menu());
         frame.setVisible(true);
      }
      public Menu(){
			//buttons are part of a subpanel in a border layout
			setLayout(new BorderLayout());
			s= new JPanel();
			s.setOpaque(false);
			s.setLayout(new GridLayout(8,1,10,20));
			JLabel header = new JLabel("BattleFight");
			header.setHorizontalAlignment(JLabel.CENTER);
			header.setFont(new Font("Vivaldi", Font.ITALIC, 65));
			header.setForeground(Color.white);
			s.add(header);
			JButton one = new JButton("Campaign");
			one.addActionListener(new Listener());
			s.add(one);
			JButton two = new JButton("Battle");
			two.addActionListener(new FreeorRanked());
			s.add(two);
			// JButton eight = new JButton("Two Player Battle");
//  			eight.addActionListener(new DeathMatch2Listener());
//    		s.add(eight);
			JButton three = new JButton("Instructions");
			three.addActionListener(new IListener());
			s.add(three);
			JButton four = new JButton("Quit");
			four.addActionListener(new QuitListener());
			s.add(four);
			add(s, BorderLayout.CENTER);
			
			temp=new JPanel();
			temp.setBackground(Color.black);
			JButton back=new JButton("Back");
			back.addActionListener(new ResetListener());
			temp.add(back);
			
			
			
			selectDifficulty=new JPanel();
			selectDifficulty.setBackground(Color.black);
			selectDifficulty.setLayout(new GridLayout(5,1,40,40));
		   JButton easy=new JButton("Easy");
			easy.addActionListener(new CampaignDifficultyListener(0));
 			JButton med=new JButton("Medium");
 			med.addActionListener(new CampaignDifficultyListener(8));
 			JButton hard=new JButton("Hard");
 			hard.addActionListener(new CampaignDifficultyListener(13));
			easy.setBackground(Color.black);
			med.setBackground(Color.black);
			hard.setBackground(Color.black);
			easy.setForeground(Color.green);
			med.setForeground(Color.green);
			hard.setForeground(Color.green);
			selectDifficulty.add(easy);
			selectDifficulty.add(med);
			selectDifficulty.add(hard);
			scores=new JButton("High Scores");
			scores.setBackground(Color.black);
			scores.setForeground(Color.green);
			added1=added2=false;
			selectDifficulty.add(scores);
			
			selectDifficulty2=new JPanel();
			selectDifficulty2.setBackground(Color.black);
			selectDifficulty2.setLayout(new GridLayout(5,1,40,40));
			JButton easy1=new JButton("Easy");
			easy1.addActionListener(new DeathMatch(100,5,34,2,100,false));
			JButton med1=new JButton("Medium");
			med1.addActionListener(new DeathMatch(400,6,29,4,400,false));
			JButton hard1=new JButton("Hard");
			hard1.addActionListener(new DeathMatch(850,10,22,5,850,false));
			easy1.setBackground(Color.black);
			med1.setBackground(Color.black);
			hard1.setBackground(Color.black);
			easy1.setForeground(Color.green);
			med1.setForeground(Color.green);
			hard1.setForeground(Color.green);
			selectDifficulty2.add(easy1);
			selectDifficulty2.add(med1);
			selectDifficulty2.add(hard1);
			
			//scores2=new JButton("Skill Levels");
 			//scores2.setBackground(Color.black);
 			//scores2.setForeground(Color.green);
 			//scores2.addActionListener(new AccountListenerDeathmatch(0));
 			//selectDifficulty2.add(scores2);
			
			pick=new JPanel();
			pick.setBackground(Color.black);
			pick.setLayout(new GridLayout(5,1,40,40));
			JButton easy2=new JButton("One Player");
			easy2.addActionListener(new ListenerCampaign(1));
			JButton med2=new JButton("Two Player");
			med2.addActionListener(new ListenerCampaign(2));
			easy2.setBackground(Color.black);
			med2.setBackground(Color.black);
			easy2.setForeground(Color.green);
			med2.setForeground(Color.green);
			pick.add(easy2);
			pick.add(med2);
			//pick.add(scores);
			
			pick4=new JPanel();
			pick4.setBackground(Color.black);
			pick4.setLayout(new GridLayout(5,1,40,40));
			JButton easy5=new JButton("One Player");
			easy5.addActionListener(new ListenerDeathmatch(1));
			JButton med4=new JButton("Two Player");
			med4.addActionListener(new ListenerDeathmatch(2));
			easy5.setBackground(Color.black);
			med4.setBackground(Color.black);
			easy5.setForeground(Color.green);
			med4.setForeground(Color.green);
			pick4.add(easy5);
			pick4.add(med4);
			
			picker=new JPanel();
			picker.setBackground(Color.black);
			picker.setLayout(new GridLayout(5,1,40,40));
			JButton easy9=new JButton("Free Play");
			easy9.addActionListener(new DeathMatchListener(false));
			JButton med9=new JButton("Ranked Play");
			med9.addActionListener(new DeathMatchListener(true));
			easy9.setBackground(Color.black);
			med9.setBackground(Color.black);
			easy9.setForeground(Color.green);
			med9.setForeground(Color.green);
			picker.add(easy9);
			picker.add(med9);
			
			accounts=new JPanel();
			accounts.setBackground(Color.black);
			accounts.setLayout(new GridLayout(5,1,40,40));
			JButton newA=new JButton("New");
			newA.addActionListener(new DeathMatch(8,5,34,2,0,true));/////////////////////Write New Listener
			JButton oldA=new JButton("Load");
			oldA.addActionListener(new AccountDeathMatch());/////////////////////Write New Listener
			newA.setBackground(Color.black);
			oldA.setBackground(Color.black);
			newA.setForeground(Color.green);
			oldA.setForeground(Color.green);
			scores3=new JButton("Skill Levels");
 			scores3.setBackground(Color.black);
 			scores3.setForeground(Color.green);
 			scores3.addActionListener(new AccountListenerDeathmatch(0));
 			accounts.add(newA);
			accounts.add(oldA);
			accounts.add(scores3);
			
			in=new JPanel();
			in.setOpaque(false);
			instructions=new JLabel("Arrow Keys to move, Slash to shoot, period and comma to change weapons");
			instructions.setForeground(Color.ORANGE);
			instructions.setFont(new Font("Serif", Font.BOLD, 16));
			instructions2=new JLabel("2 Player: WASD to move,space to shoot,Q and E to change weapons");
			instructions2.setForeground(Color.ORANGE);
			instructions2.setFont(new Font("Serif", Font.BOLD, 16));
			in.add(instructions);
			in.add(instructions2);
			
			JButton back1=new JButton("Back");
			back1.addActionListener(new BackListener());
			selectDifficulty.add(back1);
			
			JButton back2=new JButton("Back");
			back2.addActionListener(new BackListener());
			selectDifficulty2.add(back2);
			
			JButton back3=new JButton("Back");
			back3.addActionListener(new BackListener());
			pick.add(back3);
			
			JButton back4=new JButton("Back");
			back4.addActionListener(new BackListener());
			pick4.add(back4);
			
			JButton back5=new JButton("Back");
			back5.addActionListener(new BackListener());
			accounts.add(back5);
			
			JButton back6=new JButton("Back");
			back6.addActionListener(new BackListener());
			picker.add(back6);
			
			buff=new BufferedImage(FRAME, FRAME, BufferedImage.TYPE_INT_RGB);
         g=buff.getGraphics();
         robot = new ImageIcon("pics/Robots.jpg");
         g.drawImage(robot.getImage(),0,0,FRAME,FRAME,null);
			
         setFocusable(true);
			 
			
      }
		public void paintComponent(Graphics a){
			a.drawImage(buff, 0, 0, getWidth(), getHeight(), null); 
      }
   	private class Listener implements ActionListener{//select the players
			public void actionPerformed(ActionEvent e){
				remove(s);
				add(pick,BorderLayout.CENTER);
				pick.setVisible(true);
				validate();
				paintComponent(g);
			}
		}
		private class ListenerCampaign implements ActionListener{//select the difficulty
			int myX=0;
			public ListenerCampaign(int x){
				myX=x;
			}
			public void actionPerformed(ActionEvent e){
				players=myX;
				
				scores.addActionListener(new AccountListener(myX));
				// if(added1)
// 					scores.removeActionListener(AL1);
// 				else if(added2)
// 					scores.removeActionListener(AL2);
// 				if(players==1){
// 					AL1=new AccountListener(1);
// 					scores.addActionListener(AL1);
// 					added1=true;
// 				}
// 				else{
// 					AL2=new AccountListener(2);
// 					scores.addActionListener(AL2);
// 					added2=true;
// 				}
				remove(pick);
				add(selectDifficulty,BorderLayout.CENTER);
				selectDifficulty.setVisible(true);
				validate();
				repaint();	 
			}
		}
		private class CampaignDifficultyListener implements ActionListener{//start the game
			int myX=0;
			public CampaignDifficultyListener(int x){
				myX=x;
			}
			public void actionPerformed(ActionEvent e){
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
				Object source = e.getSource();
	            if (source instanceof Component) {
               	Component c = (Component) source;
               	Frame frame1 = JOptionPane.getFrameForComponent(c);
              		 if(frame1 != null){
               		frame1.dispose();
							frame1.setVisible(false);
						}
					}

////////////////THIS CODE WAS BORROWED//////////////http://www.dreamincode.net/forums/topic/162705-dispose-a-frame-from-an-inner-class/////
         		JFrame frame = new JFrame("BattleFight");
					if(players==1)
         			frame.setSize(920,960); 
					else 
						frame.setSize(1040,960);  
        		 	frame.setLocation(200, 0);
        			frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
					if(players==1)
       		   	frame.setContentPane(new GameOne(myX));
					else
						frame.setContentPane(new GameTwo(myX));
        			frame.setVisible(true);
			}
		}
		private class FreeorRanked implements ActionListener{//select the players
			public void actionPerformed(ActionEvent e){
				remove(s);
				add(picker,BorderLayout.CENTER);
				picker.setVisible(true);
				validate();
				repaint();
			}
		}
		private class RankedListener implements ActionListener{//select the players
			public void actionPerformed(ActionEvent e){
				remove(picker);
				add(accounts,BorderLayout.CENTER);
				accounts.setVisible(true);
				validate();
				repaint();
			}
		}
		private class DeathMatchListener implements ActionListener{//select the players
			boolean myB;
			public DeathMatchListener(boolean b){
				myB=b;
				
			}
			public void actionPerformed(ActionEvent e){
				ranked=myB;
				remove(picker);
				if(ranked){
				players=1;
				add(accounts,BorderLayout.CENTER);
 				accounts.setVisible(true);
 				}
				else{
				add(pick4,BorderLayout.CENTER);
				pick4.setVisible(true);
				}
				validate();
				repaint();
			}
		}
		private class ListenerDeathmatch implements ActionListener{//select the difficulty
			int myX=0;
			public ListenerDeathmatch(int x){
				myX=x;
			}
			public void actionPerformed(ActionEvent e){
				players=myX;
				remove(pick4);
				if(!ranked){
					add(selectDifficulty2,BorderLayout.CENTER);
					selectDifficulty2.setVisible(true);
				}
				else{
					add(accounts,BorderLayout.CENTER);
 					accounts.setVisible(true);
				}
				validate();
				repaint();	 
			}
		}
		private class AccountDeathMatch implements ActionListener{//select the difficulty
			public void actionPerformed(ActionEvent e){
				try{
				if(players==1)
					scanman=new Scanner(new File("Pics/Dd.txt"));
				int rows=20;
				String[] names=new String[rows];
				String[] passes=new String[rows];
				int[] skills=new int[rows];
				for(int x=0;x<20;x++){
					skills[x]=scanman.nextInt();
					names[x]=scanman.next();
					passes[x]=scanman.next();
				}
				boolean found=false;
				int pos=0;
				String name=JOptionPane.showInputDialog("Enter Name");
				for(int f=0;f<rows;f++){
					if(names[f].equalsIgnoreCase(name)){
						pos=f;
						found=true;
					}
				}
				if(!found){	
					JOptionPane.showMessageDialog(null, "Not Found", "message", JOptionPane.INFORMATION_MESSAGE); 
					return;
				}
				found=false;
				String pass=JOptionPane.showInputDialog("Enter Password");
				if(passes[pos].equalsIgnoreCase(pass))
						found=true;
				if(!found){	
					JOptionPane.showMessageDialog(null, "Not Found", "message", JOptionPane.INFORMATION_MESSAGE); 
					return;
				}
				name=name.substring(0,1).toUpperCase()+name.substring(1).toLowerCase();
				JOptionPane.showMessageDialog(null, "Welcome "+name+"\nYour Skill Level is "+skills[pos], "message", JOptionPane.INFORMATION_MESSAGE); 
				
				////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
				Object source = e.getSource();
	            if (source instanceof Component) {
               	Component c = (Component) source;
               	Frame frame1 = JOptionPane.getFrameForComponent(c);
              		if(frame1 != null){
                  	frame1.dispose();
							frame1.setVisible(false);
						}
					}

////////////////THIS CODE WAS BORROWED//////////////http://www.dreamincode.net/forums/topic/162705-dispose-a-frame-from-an-inner-class/////
         		JFrame frame = new JFrame("BattleFight");
					if(players==1){
         		frame.setSize(920,960);   
        		 	frame.setLocation(200, 50);
        			frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
       		   frame.setContentPane(new Deathmatch(skills[pos],true,name));
					}
					// else{
// 					frame.setSize(1440,960);   
//         		 	frame.setLocation(0,20);
//         			frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        		   frame.setContentPane(new Deathmatch2(0,0,0,0,true));
// 					}
        			frame.setVisible(true);
					
				}
				catch(Exception f){
				}
			}
		}
		private class DeathMatch implements ActionListener{//start the game
			int myX=0;
			int myY=0;
			int myC=0;
			int myF=0;
			int myH=0;
			boolean myB=false;
			public DeathMatch(int x, int y,int c, int f, int h,boolean b){
				myX=x;
				myY=y;
				myC=c;
				myF=f;
				myH=h;
				myB=b;
				myB=ranked;
			}
			public void actionPerformed(ActionEvent e){
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
				Object source = e.getSource();
	            if (source instanceof Component) {
               	Component c = (Component) source;
               	Frame frame1 = JOptionPane.getFrameForComponent(c);
              		if(frame1 != null){
                  	frame1.dispose();
							frame1.setVisible(false);
						}
					}

////////////////THIS CODE WAS BORROWED//////////////http://www.dreamincode.net/forums/topic/162705-dispose-a-frame-from-an-inner-class/////
         		JFrame frame = new JFrame("BattleFight");
					if(players==1){
         		frame.setSize(920,960);   
        		 	frame.setLocation(200, 50);
        			frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
       		   frame.setContentPane(new Deathmatch(myH,ranked,null));
					}
					else{
					frame.setSize(1440,960);   
        		 	frame.setLocation(0,20);
        			frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
       		   frame.setContentPane(new Deathmatch2(myX));
					}
        			frame.setVisible(true);
			}
		}
		private class AccountListener implements ActionListener{//start the game
			int myPlayers=0;
			public AccountListener(int x){
				myPlayers=x;
			}
			public void actionPerformed(ActionEvent e){
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
				Object source = e.getSource();
	            if (source instanceof Component) {
               	Component c = (Component) source;
               	Frame frame1 = JOptionPane.getFrameForComponent(c);
              		if(frame1 != null){
                  	frame1.dispose();
							frame1.setVisible(false);
						}
					}

////////////////THIS CODE WAS BORROWED//////////////http://www.dreamincode.net/forums/topic/162705-dispose-a-frame-from-an-inner-class/////
         		JFrame frame = new JFrame("BattleFight");
         		frame.setSize(920,800);  
        		 	frame.setLocation(200, 10);
        			frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
       		   frame.setContentPane(new Account(myPlayers));
        			frame.setVisible(true);
			}
		}
		private class AccountListenerDeathmatch implements ActionListener{//start the game
			int myPlayers=0;
			public AccountListenerDeathmatch(int x){
				myPlayers=x;
			}
			public void actionPerformed(ActionEvent e){
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
				Object source = e.getSource();
	            if (source instanceof Component) {
               	Component c = (Component) source;
               	Frame frame1 = JOptionPane.getFrameForComponent(c);
              		if(frame1 != null){
                  	frame1.dispose();
							frame1.setVisible(false);
						}
					}

////////////////THIS CODE WAS BORROWED//////////////http://www.dreamincode.net/forums/topic/162705-dispose-a-frame-from-an-inner-class/////
         		JFrame frame = new JFrame("BattleFight");
         		frame.setSize(920,800);  
        		 	frame.setLocation(200, 10);
        			frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
       		   frame.setContentPane(new DAccount(players));
        			frame.setVisible(true);
			}
		}
		private class IListener implements ActionListener{
			public void actionPerformed(ActionEvent e){
				ImageIcon west = new ImageIcon("pics/WildWest.jpg");
				g.drawImage(west.getImage(),0,0,800,800,null);
				s.setVisible(false);
				add(temp, BorderLayout.SOUTH);
				temp.setVisible(true);
				remove(s);
				
				add(in, BorderLayout.CENTER);
				in.setVisible(true);
				repaint();
			}
		}
		private class QuitListener implements ActionListener{
			public void actionPerformed(ActionEvent e){
				System.exit(0);
			}
		}
		private class ResetListener implements ActionListener{
			public void actionPerformed(ActionEvent e){
				remove(in);
				in.setVisible(false);
				add(s,BorderLayout.CENTER);
				 s.setVisible(true);
				 g.drawImage(robot.getImage(),0,0,800,800,null);
				 temp.setVisible(false);
				 instructions.setVisible(false);
			}
			
		}
		
		private class BackListener implements ActionListener{
			public void actionPerformed(ActionEvent e){
				 accounts.setVisible(false);
				 selectDifficulty.setVisible(false);
				 selectDifficulty2.setVisible(false);
				 pick.setVisible(false);
				 pick4.setVisible(false);
				 picker.setVisible(false);
				 add(s);
				 validate();
				 g.drawImage(robot.getImage(),0,0,800,800,null);
			}
		}
		
   }