   import javax.swing.*;
   import java.awt.*;
   import java.awt.event.*;
   import java.awt.image.*;
	import java.text.*;
	import java.io.*;
	import java.util.Scanner;
   public class Deathmatch extends JPanel{
		BufferedImage buff, radar;
		Graphics g, m;
		Rectangle p;
		ScoreboardMatch s;
		ScoreboardRightMatch s2;
		Enemy[] enemies=new Enemy[5];
		boolean u,r,d,l,move,space,grenade,ranked,added=false;
		AccountListener AL;
		boolean kListenerOn=true;
		int length=20;
		int level,deaths,grenadeCount,boxTime,eSpeed,eHealth,eNum,eFrequency,numKillStreaks,killStreak,skill,pShotFrequency=0;
		int kills,numRemaining,numAlive,slower,slower2,pslower,pauser,theCounter=0;
		JButton scores;
		int startTime=19;
		int time=startTime+4;
		int accuracy=4;/////////////////////////////////////change based on comp speed
		double hits, shots=0;
		JLabel[] labels=new JLabel[5];
		int[] labelCounters=new int[5];
		int numUBullets=300;
		int numEBullets=30;
		String name;
		int numGrenades=30;
		int[] eKills={0,0,0,0,0,0};
		int[] eDeaths={0,0,0,0,0,0};
		Color[] eCols={Color.red,Color.cyan,new Color(238,207,12),Color.black,new Color(230,12,238),Color.green};
		Bullet[] eBullets=new Bullet[numEBullets];
		Bullet[] uBullets=new Bullet[numUBullets];
		Grenade[] grenades=new Grenade[numGrenades];
		Color tr=new Color(244,161,19,175);
		int[][] map=new int[800][800];
		ScoreboardEnd sE;
		int numBoxes=4;
		int[] boxStopTime=new int[numBoxes];
		Box[] boxes=new Box[numBoxes];
		JLabel label, label2;
		JPanel panel, screen;
		Timer t, timer;
		//Ball ball;
      public Deathmatch(int skillLevel,boolean b,String fjk){//speed was 10 health was 6
			name=fjk;
			ranked=b;
			skill=skillLevel;
			pShotFrequency=5+(skillLevel/60);
			eNum=2+skillLevel/300;
			eSpeed=6+(skillLevel+150)/300;
			eHealth=(5+skillLevel+75)/100+2;
			eFrequency=37-((skillLevel+30)/80);
			screen = new JPanel();
			screen.setOpaque(true);
			Color mhz=new Color(244,0,255,255);
			screen.setBackground(mhz);
			screen.setBounds(0,0,800,1200);
			screen.setVisible(true);
			add(screen);
			for(int x=0;x<numEBullets;x++)	
				eBullets[x]=new Bullet(0,0,6,20,Color.black,1);
			for(int x=0;x<numUBullets;x++){
				uBullets[x]=new Bullet(0,0,6,40,Color.black,1);
				uBullets[x].setOn(0);
			}
			for(int x=0;x<numGrenades;x++)
				grenades[x]=new Grenade(0,0,0,10,Color.black,1);
			for(int x=0;x<5;x++){
				labels[x]=new JLabel("");
				labels[x].setVisible(false);
				labels[x].setForeground(Color.green);
			}
			boxes[0]=new Box(330,385,15,1);
			boxes[1]=new Box(450,385,15,1);
			boxes[2]=new Box(330,500,15,1);
			boxes[3]=new Box(450,500,15,1);
			for(int x=0;x<4;x++){
				boxStopTime[x]=0;
				boxes[x].setOn(1);
			}
         buff=new BufferedImage(800,800,BufferedImage.TYPE_INT_RGB);
			g=buff.getGraphics();
			
			radar=new BufferedImage(1000,1000,BufferedImage.TYPE_INT_RGB);
			m=buff.getGraphics();
			m.setColor(Color.black);
			m.fillRect(0,0,1000,1000);
			setFocusable(true);
			setGrid();
			drawBackground(level);
			repaint();
			
			p=new Rectangle(40,40,20,3,Color.green);
			p.setGun(2);
			p.setShotFrequency(pShotFrequency);
			for(int x=0;x<numUBullets;x++)
				uBullets[x].setOn(0);
			p.setShot(0);
			p.draw(g);
			
			setLayout(null);
			JButton reset=new JButton("Reset");
			reset.setHorizontalAlignment(SwingConstants.LEFT);
			reset.addActionListener(new ResetListener());
			reset.setFocusable(false);
			add(reset);
			reset.setBounds(700,850,70,30);
			
			JButton back=new JButton("Menu");
			back.setHorizontalAlignment(SwingConstants.LEFT);
			back.addActionListener(new BackListener());
			back.setFocusable(false);
			add(back);
			back.setEnabled(true);
			back.setBounds(700,890,65,30);
			
			JButton pause=new JButton("| |");
			pause.addActionListener(new PauseListener());
			pause.setFocusable(false);
			add(pause);
			pause.setForeground(Color.yellow);
			pause.setBackground(Color.black);
			pause.setBounds(10,890,70,30);
			
			s=new ScoreboardMatch(0);
			add(s);
			s.setBounds(0,860,800,150);
			
			s2=new ScoreboardRightMatch();
			add(s2);
			s2.setBounds(800,0,120,960);
			
			panel=new JPanel();
			add(panel);
			panel.setBounds(0,0,800,1000);
			panel.setForeground(tr);
			panel.setBackground(tr);
			panel.setVisible(false);
			JLabel resume=new JLabel("Press Button to Keep Playing");
			resume.setForeground(Color.green);
			panel.add(resume);
			JButton play=new JButton("Resume");
			play.addActionListener(new ResumeListener());
			panel.add(play);
			
			scores=new JButton("High Scores");
			scores.setFocusable(false);
			scores.setBounds(20,100,800,200);
			panel.add(scores);
				
			timer = new Timer(1000,new ClockListener());
         timer.start();
			
         addKeyListener(new Key());
         setFocusable(true);
			
			label=new JLabel("Game Over");
			add(label);
			label.setBounds(50,50,1000,750);
			label.setFont(new Font("Times New Roman",Font.ITALIC,400));
			label.setForeground(Color.white);
			label.setVisible(false);
			firstLevel();			
			
			if(!ranked)
				scores.setEnabled(false);
			//ball = new Ball(400,400,50,Color.black,15,15);
      }
		//////////////////////////////////////////////////////////////////////////////////////////LISTENERS
		private class PauseListener implements ActionListener{
			public void actionPerformed(ActionEvent e){
					pause();
			}
		}
		private class ResumeListener implements ActionListener{
			public void actionPerformed(ActionEvent e){
					if(time<=0)
						label.setVisible(true);
					resume();
			}
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
		private class ClockListener implements ActionListener{
			public void actionPerformed(ActionEvent e){
					time--;
				if(time>startTime){
					label.setFont(new Font("Times New Roman",Font.ITALIC,500));
					label.setForeground(Color.white);
					screen.add(label);
					label.setVisible(true);
					label.setText(""+(time-startTime));
				}
				else if(time==startTime){
					label.setVisible(false);
					label.setFont(new Font("Vivaldi",Font.ITALIC,170));
					label.setForeground(Color.orange);
					t = new Timer(15,new TimerListener());
        			t.start();
					die();
					deaths--;
				}
				else if(time>startTime-5){
					DecimalFormat d = new DecimalFormat("00");
					int display=startTime-5;
					label.setText(""+display/60+":"+d.format(display%60)+"remaining");
					label.setVisible(true);
				}
				else if(time<30&&time>27){
					label.setText("30 secs left!");
					label.setVisible(true);
				}
				else if(time<=0){
					label.setVisible(true);
				}
				else{
					label.setText("Game Over");
					label.setVisible(false);
				}
				if(time<=startTime)
					s.update(time,skill);
				   if(time==0)
 						end();
			}
		}
		private class ResetListener implements ActionListener{
			public void actionPerformed(ActionEvent e){
					added=false;
					scores.removeActionListener(AL);
					level=0;
					kills=deaths=0;
					u=r=d=l=false;
					kListenerOn=true;
					slower=slower2=kills=deaths=killStreak=0;
					p.setX(40);
					p.setY(40);
					label.setVisible(false);
					p.setHealth(100);
					firstLevel();
					time=startTime+4;
					timer.restart();
					Color mhz=new Color(244,0,255,255);
					screen.setBackground(mhz);
					screen.setVisible(true);
					resume();
					if(t!=null)
					t.stop();
					if(sE!=null)
						panel.remove(sE);
					for(int x=0;x<eKills.length;x++){
						eKills[x]=0;
						eDeaths[x]=0;
					}
					if(label2!=null)
						label2.setVisible(false);
			}
			
		}
		private class TimerListener implements ActionListener{
         public void actionPerformed(ActionEvent e){
			p.setShotFrequency(pShotFrequency);
				if(move==true){
					setDirection();
					moveRect(p);
					p.toggle();
				}
				if(space){
					if(p.getShot()==p.getShotFrequency()){
								p.setShot(0);
								shoot(p);
					}
				}
				p.setShot(p.getShot()+1);
			   slower++;
				if(slower==8){
					moveEnemies();
					slower=0;
				}
				slower2++;
				if(slower2==65){
					if(p.getHealth()<100)
						p.setHealth(p.getHealth()+1);
					slower2=0;
				}
				if(p.getAlive()){
					drawBackground(level);
					p.draw(g);
				}
				else
					drawBackground(level,-1);
				
				for(int x=0;x<numRemaining;x++){
						if(enemies[x].getAlive())
							enemies[x].draw(g);
				}
				
				for(int x=0;x<numUBullets;x++){
					if(uBullets[x].getOn()==1){
						moveBullet(uBullets[x]);
						uBullets[x].draw(g);
							if(uBullets[x].getOn()==1){
								for(int a=0;a<numRemaining;a++){
									if(enemies[a].getAlive())
										shot(enemies[a],uBullets[x],a);
									
								}
							}
					}
				}
				
			for(int y=0;y<numRemaining;y++){
				for(int x=0;x<numEBullets;x++){
					if(eBullets[x].getOn()==1){
						moveBullet(eBullets[x]);
						eBullets[x].draw(g);
						if(p.getAlive())
							shot(p,eBullets[x],numRemaining);
						if(y!=eBullets[x].getShooter())
							shot(enemies[y],eBullets[x],y);
					}
				}
			}
			 for(int x=0;x<numGrenades;x++){
					if(grenades[x].getOn()==1){
						moveGrenade(grenades[x]);
						grenades[x].draw(g);
							for(int a=0;a<numRemaining;a++){
								if(enemies[a].getAlive()&&grenades[x].getLifeTime()>140)
									shot(enemies[a],grenades[x],a);
							}
						if(grenades[x].getLifeTime()==200)//when it is done exploding
							grenades[x].setOn(2);
					}
			 }
				update();
				// if(numAlive<numRemaining)
// 					nextLevel();
				boxTime++;
				manageBoxes(boxTime);
				manageLabels();
				if(slower2%accuracy==0)
					//ball();
			//	ball.draw(g);
			;
			if(!p.getAlive()){
				theCounter+=4;
				
				if(theCounter>=255){
					theCounter=0;
					p.setAlive(true);
					screen.setVisible(false);
					kListenerOn=true;
				}
				else{
					screen(theCounter);
					p.draw(g,theCounter);
					g.setColor(Color.black);
					int backwards=255-theCounter;
					g.drawOval(p.getX()-backwards/2+10,p.getY()-backwards/2+15,backwards,backwards);
					backwards=backwards*3;
					g.drawOval(p.getX()-backwards/2+10,p.getY()-backwards/2+15,backwards,backwards);
					
					g.drawLine(p.getX()-backwards/2+10,p.getY()+15,p.getX()+backwards/2+10,p.getY()+15);
					g.drawLine(p.getX()+10,p.getY()-backwards/2,p.getX()+10,p.getY()+backwards/2+30);
				}
			}
			if(time<=0){
					add(label);
					label.setVisible(true);
			}
				repaint();
			}	
		}
		private class Key extends KeyAdapter{///////////////////////////////////////////////KEYS
			public void keyReleased(KeyEvent e)
         {
				 switch(e.getKeyCode()){
					case KeyEvent.VK_UP:u=false;break;
				 	case KeyEvent.VK_RIGHT:r=false;break;
				 	case KeyEvent.VK_DOWN:d=false;break;
				 	case KeyEvent.VK_LEFT:l=false;break;
					case KeyEvent.VK_SPACE:space=false;break;
					case KeyEvent.VK_SLASH:space=false;break;
					case KeyEvent.VK_G:grenade=false;break;
				 }
				 if(!u&&!r&&!d&&!l)
				 	move=false;
         }
         public void keyPressed(KeyEvent e){
			if(kListenerOn){ 
				switch(e.getKeyCode()){
					case KeyEvent.VK_UP: 
						u=true;move=true;break;
				 	case KeyEvent.VK_RIGHT:
						r=true;move=true;break;
				 	case KeyEvent.VK_DOWN:
						d=true;move=true;break;
				 	case KeyEvent.VK_LEFT:
						l=true;move=true;break;
					case KeyEvent.VK_SPACE:
						space=true;break;
					case KeyEvent.VK_SLASH:
						space=true;break;
					case KeyEvent.VK_G:
						shootGrenade(p);break;
				}
				
			}
			}
		}
		public void setDirection(){
			if(u&&r)
						p.setDir(2);
					else if(d&&r)
						p.setDir(4);
					else if(d&&l)
						p.setDir(6);
					else if(u&&l)
						p.setDir(8);
					else if(r&&l){
						if(p.getDir()==3){
							p.setDir(7);
							r=false;
						}
						else{
							p.setDir(3);
							l=false;
						}
					}
					else if(u&&d){
						if(p.getDir()==1){
							p.setDir(5);
							u=false;
						}
						else{
							p.setDir(1);
							d=false;
						}
					}
					else if(u)
						p.setDir(1);
					else if(r)
						p.setDir(3);
					else if(d)
						p.setDir(5);
					else if(l)
						p.setDir(7);
		}
      public boolean shot(Rectangle one, Bullet two, int eNum){////////////////////////////////////////////////COLLIDE METHODS
			if((Math.abs(two.getX()-(one.getX()+length/2))<=length/2+two.getLength())&&
			Math.abs(two.getY()-(one.getY()+25+two.getLength()/2))<=25+two.getLength()/2){
			if(two.isABullet()&&!two.isGrenade()&&!two.isExploding())
				two.setOn(2);
			one.react();
				if(one.isEnemy()){
					one.setHealth(one.getHealth()-1);
						int save=one.getDir();
						one.setDir(two.getDir());
						moveRect(one);
						moveRect(one);
						one.setDir(save);
					hits++;
					if(two.getShooter()!=100)
						one.setHealth(one.getHealth()-6);
					if(one.getHealth()<=0){
						one.setAlive(false);
						numAlive--;
						nextLevel();
						eDeaths[eNum]++;
						if(two.getShooter()==100){
							kills++;
							killStreak++;
							addLabel("+Kills",one.getX(),one.getY());
							
							int k=killStreak/4;
							if(k>0&&killStreak%4==0){
								if(k==1)
									numKillStreaks++;
								addLabel("+KILLSTREAK x "+k,one.getX()-200,one.getY());/////////////fix this
							}
						}
						else{
							eKills[two.getShooter()]++;
						}
					}
				}
				else{
					if(!two.isGrenade())
						one.setHealth(one.getHealth()-20);
					else if(two.isGrenadable()){
						one.setHealth(one.getHealth()-30);
						two.setGrenadable(false);
					}
					if(p.getHealth()<=0){
						die();
						eKills[two.getShooter()]++;
					}
				}
				return true;
			}	
			return false;
      }
		public boolean collided(Enemy one){
			if(Math.abs((one.getX()+length/2)-(p.getX()+length/2))<=length&&Math.abs(one.getY()-p.getY())<=50){
				return true;
				}
			return false;
		}
		public boolean collided(Box box){
			if(Math.abs((p.getX()+length/2)-(box.getX()+box.getLength()/2))<=length/2+box.getLength()/2&&
				Math.abs((p.getY()+length/2-box.getLength()/2)-box.getY())<=25+box.getLength()/2){
				return true;
				}
			return false;
		}
		// public void ball(){
// 			int random=(int)(Math.random()*255);
// 			int random1=(int)(Math.random()*255);
// 			int random2=(int)(Math.random()*255);
// 			ball.setColor(new Color(random,random1,random2));
// 			ball.move(780,780);
// 		}
		public void manageBoxes(int time){////////////////////////////////////////////MANAGING BOXES AND LABELS
			for(int x=0;x<numBoxes;x++){
				if(boxes[x].getOn()==1){
					boxes[x].draw(g);
					if(collided(boxes[x])){
						boxes[x].setOn(2);
								p.setHealth(100);
								addLabel("+Health",boxes[x].getX(),boxes[x].getY());
							
															
					}
				}
				else if(boxes[x].getOn()==2){
					boxStopTime[x]=time;
					boxes[x].setOn(0);
				}
				else if(time-boxStopTime[x]==500)
					boxes[x].setOn(1);
				else if(boxes[x].getLifeSpan()<0){
					boxStopTime[x]=time;
					boxes[x].setOn(1);
					boxes[x].setLifeSpan(2000);
				}
			}
		}
		public void manageLabels(){
			for(int x=0;x<5;x++){
				if(labels[x].isVisible()){
					labelCounters[x]--;
					if(labelCounters[x]==0)
						labels[x].setVisible(false);
				}
			}
		}
		public void addLabel(String text, int x, int y){
			for(int c=0;c<5;c++){
				if(!labels[c].isVisible()){
					labels[c]=new JLabel(text);
					labels[c].setFont(new Font("Serif",Font.BOLD,40));
					labels[c].setBounds(x,y,500,65);
					if(text.equalsIgnoreCase("+deaths"))
						labels[c].setForeground(Color.red);
					else
						labels[c].setForeground(Color.green);
					add(labels[c]);
					labels[c].setVisible(true);
					labelCounters[c]=40;
					break;
				}
			}
		}
		public void pause(){
			kListenerOn=false;///////////////////////////////////switch for focusable
			if(t!=null)
				t.stop();
			timer.stop();
			panel.setVisible(true);
			panel.setFocusable(false);
		}
		public void resume(){
			panel.setVisible(false);
			kListenerOn=true;
			if(t!=null)
			t.start();
			timer.start();
			setFocusable(true);
		}
      public void moveRect(Rectangle a){	///////////////////////////////////////MOVING AND SHOOTING
													
				int oldX=a.getX();
				int oldY=a.getY();
				a.move();
				int newX=a.getX();
				int newY=a.getY();
				
				if(map[newY-1][newX-1]==1||
					map[newY-1][newX+19]==1||
					map[newY+49][newX-1]==1||
					map[newY+15][newX+19]==1||
					map[newY+15][newX-1]==1||
					map[newY+34][newX+19]==1||
					map[newY+34][newX-1]==1||
					map[newY+49][newX+19]==1){
					a.setX(oldX);
					a.setY(oldY);
			}
      }
		public void moveBullet(Bullet a){
				int oldX=a.getX();
				int oldY=a.getY();
				
				a.move();
				
				int newX=a.getX();
				int newY=a.getY();
				if(a.getX()>775||a.getX()<20||a.getY()>775||a.getY()<=20)
					if(a.isRocket())
						a.setExploding(true);
					else 
						a.setOn(2);
				else if(map[newY-1][newX-10]==1||
					map[newY-1][newX+length-10]==1||
					map[newY+length][newX-10]==1||
					map[newY+length][newX+length-10]==1
					)
					if(a.isRocket())
						a.setExploding(true);
					else 
						a.setOn(2);
		}
		public void moveGrenade(Grenade a){
				grenadeCount++;
				int oldX=a.getX();
				int oldY=a.getY();
				int l=a.getLifeTime();
				switch(a.getDIR()){
					case 1:
					case 5:
						if(l<15){
							a.setSpeed(5);
							a.move();
						}
						else if(l<25){
							a.setSpeed(4);
							a.move();
						}
						else if(l<35){
							a.setSpeed(3);
							a.move();
						}
						else if(l<45){
							a.setSpeed(4);
							a.move();
						}break;
					case 3:
						if(l<15){
							if(grenadeCount%2==0)
								a.setDir(2);
							else
								a.setDir(3);
							a.setSpeed(5);
							a.move();
						}
						else if(l<20){
							a.setDir(3);
							a.setSpeed(4);
							a.move();
						}
						else if(l<50){
							if(grenadeCount%2==0)
								a.setDir(4);
							else
								a.setDir(3);
							a.setSpeed(3);
							a.move();
						}break;
					case 7:
						if(l<15){
							if(grenadeCount%2==0)
								a.setDir(8);
							else
								a.setDir(7);
							a.setSpeed(5);
							a.move();
						}
						else if(l<20){
							a.setDir(7);
							a.setSpeed(4);
							a.move();
						}
						else if(l<50){
							if(grenadeCount%2==0)
								a.setDir(6);
							else
								a.setDir(7);
							a.setSpeed(3);
							a.move();
						}break;
					case 2:
						if(l<15){
							if(grenadeCount%2==0)
								a.setDir(1);
							else
								a.setDir(2);
							a.setSpeed(5);
							a.move();
						}
						else if(l<20){
							a.setDir(2);
							a.setSpeed(4);
							a.move();
						}
						else if(l<50){
							if(grenadeCount%2==0)
								a.setDir(3);
							else
								a.setDir(4);
							a.setSpeed(3);
							a.move();
						}break;
					case 8:
						if(l<15){
							if(grenadeCount%2==0)
								a.setDir(1);
							else
								a.setDir(8);
							a.setSpeed(5);
							a.move();
						}
						else if(l<20){
							a.setDir(8);
							a.setSpeed(4);
							a.move();
						}
						else if(l<50){
							if(grenadeCount%2==0)
								a.setDir(7);
							else
								a.setDir(6);
							a.setSpeed(3);
							a.move();
						}break;
					case 4:
						if(l<15){
							if(grenadeCount%2==0)
								a.setDir(3);
							else
								a.setDir(4);
							a.setSpeed(5);
							a.move();
						}
						else if(l<20){
							a.setDir(4);
							a.setSpeed(4);
							a.move();
						}break;
					case 6:
						if(l<15){
							if(grenadeCount%2==0)
								a.setDir(7);
							else
								a.setDir(6);
							a.setSpeed(5);
							a.move();
						}
						else if(l<20){
							a.setDir(6);
							a.setSpeed(4);
							a.move();
						}break;
				}
				
				int newX=a.getX();
				int newY=a.getY();
				
				if(a.getX()>775||a.getX()<20||a.getY()>775||a.getY()<20){
					a.setX(oldX);
					a.setY(oldY);
				}
				else if(map[newY-1][newX-1]==1||
					map[newY-1][newX+length-1]==1||
					map[newY+length][newX-1]==1||
					map[newY+length][newX+length-1]==1
					){
					a.setX(oldX);
					a.setY(oldY);
					}
		}
		public void moveEnemies(){
			for(int x=0;x<numRemaining;x++){
			if(enemies[x].getAlive()&&enemies[x].getAttacking()==0){
				int oldX=enemies[x].getX();
				int oldY=enemies[x].getY();
			
				if(p.getX()<enemies[x].getX()){//if player is on left
					if(enemies[x].getY()>p.getY()){//if player is above
						if(Math.random()<.5)
							enemies[x].setDir(8);
						else
							enemies[x].setDir(7);
							
					}
					else{
						if(Math.random()<.5)
							enemies[x].setDir(6);
						else
							enemies[x].setDir(7);
					}
				}
				else{									//if player is on the right
					if(enemies[x].getY()>p.getY()){//if player is above
						if(Math.random()<.5)
							enemies[x].setDir(2);
						else
							enemies[x].setDir(3);
					}
					else{
						if(Math.random()<.5)
							enemies[x].setDir(4);
						else
							enemies[x].setDir(3);
					}
				}
			if(p.getX()<200&&enemies[x].getX()<200||//for columns that are open
				p.getX()>200&p.getX()<350&&enemies[x].getX()>200&&enemies[x].getX()<350||
				p.getX()>350&&p.getX()<600&&p.getY()<600&&enemies[x].getX()>350&&enemies[x].getX()<600&&enemies[x].getY()<600||
				
				p.getY()<200&&p.getX()<600&&enemies[x].getY()<200&&enemies[x].getX()<600|//for rows that are open
				p.getY()>600&&p.getX()>200&&enemies[x].getY()>600&&enemies[x].getX()>200){
				if(Math.abs(p.getX()-enemies[x].getX())<8){ //this can be extended so it works for diagonals
				double random=Math.random();
				if(random<.7){
					if(p.getY()>enemies[x].getY())
						enemies[x].setDir(5);
					if(p.getY()<enemies[x].getY())
						enemies[x].setDir(1);
				}
				else if(random<.85)
					enemies[x].setDir(3);
				else
					enemies[x].setDir(7);
				}
				
				if(Math.abs(p.getY()-enemies[x].getY())<8){
				double rando=Math.random();
				if(rando<.7){
					if(p.getX()>enemies[x].getX())
						enemies[x].setDir(3);
					if(p.getX()<enemies[x].getX())
						enemies[x].setDir(5);
				}
				else if(rando<.85)
					enemies[x].setDir(1);
				else
					enemies[x].setDir(5);
				}
			}
				moveRect(enemies[x]);
				
			}
			if(enemies[x].isAShooter()&&enemies[x].getShot()>=enemies[x].getShotFrequency()){/////////////////maybe not
					shoot(enemies[x], x);
					enemies[x].setShot(0);
			}
			}
		}
      public void shoot(Rectangle k){
		
			int fireX=k.getX()+length/2;
			int fireY=k.getY();
			switch(k.getDir()){
				case 2:
				case 8:fireY=fireY+13;
				break;
				case 4:
				case 6:fireY=fireY+25;
				break;
				case 3:
				case 7:fireY=fireY+20;
				break;
				case 1:
				case 5:fireX=fireX-3;
				break;				
			}
				int b=0;
		if(!k.isAShooter()){
				for(int x=0;x<numUBullets;x++){
					if(uBullets[x].getOn()==0){
						uBullets[x].setOn(1);
						b=x;
						shots++;
						break;
					}
				}
				uBullets[b].setX(fireX);
				uBullets[b].setY(fireY);
				uBullets[b].setDir(k.getDir());
				uBullets[b].setColor(k.getBulletColor());
		}
		if(k.isAShooter()){
			for(int x=0;x<numEBullets;x++){
				if(eBullets[x].getOn()!=1){
					eBullets[x].setOn(1);
					b=x;
					break;
				}
			}
			eBullets[b].setX(fireX);
			eBullets[b].setY(fireY);
			eBullets[b].setDir(k.getDir());
			eBullets[b].setColor(k.getBulletColor());
			eBullets[b].setSpeed(1);
			eBullets[b].setLength(7);
		}
			
      }
		public void shootGrenade(Rectangle k){
			int fireX=k.getX()+length/2;
			int fireY=k.getY();
			switch(k.getDir()){
				case 2:
				case 8:fireY=fireY+13;
				break;
				case 4:
				case 6:fireY=fireY+25;
				break;
				case 3:
				case 7:fireY=fireY+20;
				break;
				case 1:
				case 5:fireX=fireX-3;
				break;				
			}
			int b=0;
			for(int x=0;x<numGrenades;x++){
				if(grenades[x].getOn()==0){
					grenades[x].setOn(1);
					b=x;
					break;
				}
			}
			grenades[b].setX(fireX);
			grenades[b].setY(fireY);
			grenades[b].setDir(k.getDir());
			grenades[b].setDIR(k.getDir());
			grenades[b].setSpeed(6);
			grenades[b].setLength(7);
			moveGrenade(grenades[b]);
		}
		public void shoot(Rectangle k, int e){
		
			int fireX=k.getX()+length/2;
			int fireY=k.getY();
			switch(k.getDir()){
				case 2:
				case 8:fireY=fireY+13;
				break;
				case 4:
				case 6:fireY=fireY+25;
				break;
				case 3:
				case 7:fireY=fireY+20;
				break;
				case 1:
				case 5:fireX=fireX-3;
				break;				
			}
				int b=0;
		if(k.isAShooter()){
			for(int x=0;x<numEBullets;x++){
				if(eBullets[x].getOn()!=1){
					eBullets[x].setOn(1);
					b=x;
					break;
				}
			}
			eBullets[b].setX(fireX);
			eBullets[b].setY(fireY);
			eBullets[b].setDir(k.getDir());
			eBullets[b].setColor(k.getBulletColor());
			eBullets[b].setSpeed(1);
			eBullets[b].setLength(7);
			eBullets[b].setShooter(e);
		}
			
      }
      public void drawBackground(int lev){///////////////////////////////////////////////////BACKGROUND AND LEVELS
			ImageIcon i;
			switch(eNum){
				case 1:
				case 2:i=new ImageIcon("pics/sky.jpg");break;
				case 3:
				case 4:i=new ImageIcon("pics/tiger.jpg");break;
				case 5:i=new ImageIcon("pics/Desert.jpg");break;
				default:i=new ImageIcon("pics/fire.jpg");break;
			}
			g.setColor(new Color(0,0,1));
			g.drawImage(i.getImage(),0,0,800,800,null);
			g.fillRect(0,0,800,length);
			g.fillRect(0,0,length,800);
			g.fillRect(0,800-length,800,800);
			g.fillRect(800-length,0,800,800);			
      }
		public void drawBackground(int lev, int x){
			g.setColor(Color.white);
			g.fillRect(0,0,800,800);
			g.setColor(new Color(0,0,1));
			g.fillRect(0,0,800,length);
			g.fillRect(0,0,length,800);
			g.fillRect(0,800-length,800,800);
			g.fillRect(800-length,0,800,800);			
      }
		public void firstLevel(){
			level++;
			numRemaining=numAlive=eNum;//lined up on the right
				int myX=50;
				int myY=50;
				for(int x=0;x<numRemaining;x++){
						myX=(int)(Math.random()*760+20);
						myY=(int)(Math.random()*760+20);
					while(myX<50||myX>750||myY<50||myY>730){
						myX=(int)(Math.random()*730+30);
						myY=(int)(Math.random()*650+30);
					}
					enemies[x]=new Shooter(myX,myY,20,eSpeed,eCols[x]);
					enemies[x].setHealth(eHealth);
					enemies[x].setHEALTH(eHealth);
					enemies[x].setShotFrequency(eFrequency);
					enemies[x].draw(g);
				}
		}
		public void nextLevel(){
				int myX=0;
				int myY=0;
				for(int x=0;x<numRemaining;x++){
					if(!enemies[x].getAlive()){
						myX=(int)(Math.random()*760+20);
						myY=(int)(Math.random()*760+20);
					while(myX<50||myX>750||myY<50||myY>730){
						myX=(int)(Math.random()*730+30);
						myY=(int)(Math.random()*650+30);
					}
					enemies[x]=new Shooter(myX,myY,20,eSpeed,eCols[x]);
					enemies[x].setShotFrequency(eFrequency);
					enemies[x].setHealth(eHealth);
					enemies[x].setHEALTH(eHealth);
					enemies[x].draw(g);
					}
				}
		}
		
		public void die(){//////////////////////////////////////////////////////////////////////////////MISC
			int myX=(int)(Math.random()*760+20);
			int myY=(int)(Math.random()*760+20);
				while(myX<50||myX>750||myY<50||myY>730){
						myX=(int)(Math.random()*730+30);
						myY=(int)(Math.random()*650+30);
					}
			p=new Rectangle(myX,myY,20,3,Color.green);
			p.setGun(2);
			p.setShotFrequency(pShotFrequency);
			p.setGun(2);
			p.setBulletColor(Color.green);
			for(int x=0;x<numUBullets;x++)
				uBullets[x].setOn(0);
			for(int x=0;x<numGrenades;x++){
				if(grenades[x].getOn()!=1)
					grenades[x].setOn(0);
			}
			p.setShot(0);
			p.draw(g);
			deaths++;
			addLabel("+deaths",p.getX(),p.getY());
			killStreak=0;
			screen(0);
			screen.setVisible(true);
			p.setAlive(false);
			u=d=l=r=space=false;
			kListenerOn=false;
		}
		public void screen(int t){
			screen.setBackground(new Color(244,t,255,200));
		}
		public void end(){//////////////////////////////////////////////////////////////////////////////MISC
			screen.setVisible(false);
			label.setText("Game Over");
			label.setVisible(true);
			panel.setBackground(Color.black);
			panel.setVisible(true);
			panel.add(label);
			eKills[numRemaining]=kills;
			eDeaths[numRemaining]=deaths;
			sE=new ScoreboardEnd();
			int change=sE.update(kills,numKillStreaks,deaths,eKills,eDeaths,numRemaining,eCols);
			skill+=change;
			if(skill>1000)
				skill=1000;
			if(skill<0)
				skill=0;
			if(change>-1)
				label2=new JLabel("Skill+"+change+" = "+skill);
			else
				label2=new JLabel("Skill"+change+" = "+skill);
			label2.setBounds(50,400,1000,200);
			label2.setFont(new Font("Times New Roman",Font.ITALIC,75));
			label2.setForeground(Color.green);
			label2.setVisible(true);
			panel.add(label2);
			eNum=2+skill/300;
			eSpeed=6+(skill+150)/300;
			eHealth=(5+skill+75)/100+2;
			eFrequency=37-((skill+30)/80);
			sE.setBackground(tr);
			panel.add(sE);
			kListenerOn=false;
			t.stop();
			timer.stop();
			if(!added){
				AL=new AccountListener(skill);
				scores.addActionListener(AL);
				added=true;
			}
			s.update(time,skill);
		}
		private class AccountListener implements ActionListener{//start the game
			int myX=0;
			public AccountListener(int f){
				myX=f;
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
         		frame.setSize(920,960);  
        		 	frame.setLocation(200, 0);
        			frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
       		   frame.setContentPane(new DAccount(1,skill));
        			frame.setVisible(true);
			}
		}
      public void paintComponent(Graphics a){
		int scale=2;
		int bx=0;
		int by=0;
			a.drawImage(radar, -200,-200,10000,1000,null);
			bx=(160-p.getX())*scale;
			if(bx>0)
				bx=0;
			if(bx<-785)
				bx=-785;
			by=(160-p.getY())*scale;
			if(by>0)
				by=0;
			if(by<-785)
				by=-785;
			a.drawImage(buff,bx,by,800*scale,800*scale,null);
			//g.setColor(new Color(0,0,1,225));
			//g.fillRect(0,0,800,800);
			//a.drawImage(buff,600,600,200,200,null);
         //a.drawImage(buff, 0, 60, 800, 800, null);
      }
		
		public void update(){					
		s.update(time,skill);
		int score=kills*100+(level-1)*100;
		if(score<0)
			score=0;
		s2.update();
		}
				
		public void setGrid(){
			for(int x=0;x<800;x++){
				for(int y=0;y<800;y++){
					map[x][y]=0;
				}
			}
			
			for(int y=0;y<20;y++){
				for(int x=0;x<800;x++){
					map[y][x]=1; //top
					map[x][y]=1; //left
					map[799-y][x]=1;  //bottom
					map[x][799-y]=1; //right
				}
			}
		}
   }