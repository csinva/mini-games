   import javax.swing.*;
   import java.awt.*;
   import java.awt.event.*;
   import java.awt.image.*;
	import java.io.*;
	import java.util.*;
	
   public class Account extends JPanel{
		JPanel s;
		int players=1;
		int pos;
      public Account(int j){
			players=j;
			JLabel label;
			setLayout(new BorderLayout());
			if(j==1)
				label=new JLabel("One Player High Scores");
			else
 				label=new JLabel("Two Player High Scores");
			label.setFont(new Font("Serif",Font.BOLD,75));
			add(label,BorderLayout.NORTH);
			s=new JPanel();
			add(s,BorderLayout.CENTER);
			setBackground(Color.white);
			try{
			Scanner scanman;
			if(j==1)
				scanman=new Scanner(new File("Pics/d.txt"));
			else
				scanman=new Scanner(new File("Pics/d2.txt"));
				int rows=0;
				while(scanman.hasNextLine()){
					rows++;
					scanman.nextLine();
				}
				String[] names=new String[rows];
				int[] scores=new int[rows];
				scanman.close();
			Scanner scans;
			if(j==1)
				scans=new Scanner(new File("Pics/d.txt"));
			else
				scans=new Scanner(new File("Pics/d2.txt"));
				for(int x=0;x<rows;x++){
					String a=scans.nextLine();
					int y=a.indexOf('/');
					scores[x]=Integer.parseInt(a.substring(0,y));
					names[x]=a.substring(y+1);
				}
				scans.close();
				s.setLayout(new GridLayout(rows+1,3));
				for(int x=0;x<rows;x++){
					JLabel t=new JLabel(""+(x+1));
					t.setFont(new Font("Serif",Font.BOLD, 30));
					s.add(t);
					JLabel t1=new JLabel(names[x]);
					t1.setFont(new Font("Serif",Font.BOLD, 30));
					s.add(t1);
					JLabel t2=new JLabel(""+scores[x]);
					t2.setFont(new Font("Serif",Font.BOLD, 30));
					s.add(t2);
				}
					JButton back1=new JButton("Back");
					back1.addActionListener(new BackListener());
					add(back1,BorderLayout.SOUTH);
				save(scores,names);
			}
			catch(Exception e){
				System.out.println("can't be found");
			}
      }
		public Account(int j,int score){
			players=j;
			setBackground(Color.white);
			setLayout(new BorderLayout());
			JLabel label;
			if(j==1)
				label=new JLabel("One Player High Scores");
			else
 				label=new JLabel("Two Player High Scores");
			label.setFont(new Font("Serif",Font.BOLD,75));
			add(label,BorderLayout.NORTH);
			s=new JPanel();
			add(s,BorderLayout.CENTER);
			try{
				Scanner scanman;
			if(j==1)
				scanman=new Scanner(new File("Pics/d.txt"));
			else
				scanman=new Scanner(new File("Pics/d2.txt"));
				int rows=0;
				while(scanman.hasNextLine()){
					rows++;
					scanman.nextLine();
				}
				String[] names=new String[rows+1];
				int[] scores=new int[rows+1];
				scanman.close();
					Scanner scans;
			if(j==1)
				scans=new Scanner(new File("Pics/d.txt"));
			else
				scans=new Scanner(new File("Pics/d2.txt"));
				for(int x=0;x<rows;x++){
					String a=scans.nextLine();
					int y=a.indexOf('/');
					scores[x]=Integer.parseInt(a.substring(0,y));
					names[x]=a.substring(y+1);
				}
				int temp=scores[rows];
				String temp2=names[rows];
				scores[rows]=score;
				names[rows]="blank";
				scans.close();
				s.setLayout(new GridLayout(rows+2,3));
				System.out.println("no prob");
				sort(scores,names);
				System.out.println("No Sorting error");
				if(score>=scores[19]){
					System.out.println("Yes!!!");
					String w=JOptionPane.showInputDialog("You set a High Score!  Please Enter Your name");
					if(w.length()<1)
						w="unknown";
					for(int x=0;x<names.length;x++){
						if(names[x].equals("blank")){
							names[x]=w;
							pos=x;
						}
					}
					display(scores,names,rows,s);
				}
				else{
					System.out.println("Ok...");
					scores[rows]=temp;
					names[rows]=temp2;
					display(scores,names,rows,s);
				}
				
				
					JButton back1=new JButton("Back");
					back1.addActionListener(new BackListener());
					add(back1,BorderLayout.SOUTH);
				System.out.println("Almost done");
				save(scores,names);
				System.out.println("Saved");
			}
			catch(Exception e){
				System.out.println("can't be found");
			}
		}
		public void sort(int[] x,String[] s){
			int maxPos;
         for(int k = 0; k < x.length; k++)
         {
            maxPos = findMax(x, x.length - k - 1);
				swap(x, maxPos, x.length - k - 1);
				swap(s, maxPos, x.length - k - 1);
         }
		}
		public void save(int[] x,String[] s){
			sort(x,s);
			try{
				PrintStream out;
				if(players==1)
					out=new PrintStream(new File("Pics/d.txt"));
				else
					out=new PrintStream(new File("Pics/d2.txt"));
				for(int a=0;a<20;a++){
					out.print(x[a]);
					out.print("/");
					out.print(s[a]);
					out.println();
				}
			}
			catch(Exception e){
				System.out.println("can't save");
			}
		}
		public void display(int[] f,String[] s, int r,JPanel q){
			for(int x=0;x<r;x++){
					JLabel t=new JLabel(""+(x+1));
					t.setFont(new Font("Serif",Font.BOLD, 30));
					q.add(t);
					JLabel t1=new JLabel(s[x]);
					t1.setFont(new Font("Serif",Font.BOLD, 30));
					q.add(t1);
					JLabel t2=new JLabel(""+f[x]);
					t2.setFont(new Font("Serif",Font.BOLD, 30));
					q.add(t2);
					if(x==pos){
						t.setForeground(Color.green);
						t2.setForeground(Color.green);
						t1.setForeground(Color.green);
					}
				}
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
		public int[] swap(int[] array, int a, int b){
			int temp=array[a];
			array[a]=array[b];
			array[b]=temp;
			return array;
		}
		public String[] swap(String[] array, int a, int b){
			String temp=array[a];
			array[a]=array[b];
			array[b]=temp;
			return array;
		}
		private class BackListener implements ActionListener{
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
					JFrame frame = new JFrame("Battle");
         		frame.setSize(800,835);   
        		 	frame.setLocation(200, 50);
        			frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
       		  	frame.setContentPane(new Menu());
        			frame.setVisible(true);
			}
		}
   }