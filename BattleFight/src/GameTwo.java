   import javax.swing.*;
   import java.awt.*;
   import java.awt.event.*;
   import java.awt.image.*;
   public class GameTwo extends JPanel{
		BufferedImage buff, radar;
		Graphics g, m;
		Rectangle p, p2;
		Scoreboard s;
		Scoreboard2 s1;
		ScoreboardRight s2,s3;
		Enemy[] enemies=new Enemy[50];
		boolean u,r,d,l,move,slash,u2,r2,d2,l2,move2,space,added,reached=false;
		boolean kListenerOn,p2ListenerOn=true;
		boolean pListenerOn=true;
		
		int length=20;
		int level,therandom=0;
		AccountListener AL;
		JButton scores;
		int kills,numRemaining,numAlive,slower,slower2,pslower,pauser,gM,counter3,negative,score,startLevel=0;
		double hits, shots=0;
		JLabel[] labels=new JLabel[5];
		int[] labelCounters=new int[5];
		int numBullets=3;
		int numUBullets=100;
		int numEBullets=10;
		int numRBullets=50;
		int boxTime=0;
		Bullet[] bullets=new Bullet[numBullets];
		Bullet[] eBullets=new Bullet[numEBullets];
		Bullet[] uBullets=new Bullet[numUBullets];
		Bullet[] rBullets=new Bullet[numRBullets];
		
		Bullet[] bullets2=new Bullet[numBullets];
		Bullet[] eBullets2=new Bullet[numEBullets];
		Bullet[] uBullets2=new Bullet[numUBullets];
		Bullet[] rBullets2=new Bullet[numRBullets];
		
		int[] boxStopTime=new int[2];
		Color tr=new Color(244,161,19,175);
		int[][] map=new int[800][800];
		Box[] boxes=new Box[2];
		JLabel label, label2;
		JPanel panel;
		Timer t;
      public GameTwo(int a){
		startLevel=level=a;
			for(int x=0;x<numBullets;x++){
				bullets[x]=new Bullet(0,0,6,20,Color.black,1);
				bullets2[x]=new Bullet(0,0,6,20,Color.black,1);
			}
			for(int x=0;x<numEBullets;x++){
				eBullets[x]=new Bullet(0,0,6,20,Color.black,1);
				eBullets2[x]=new Bullet(0,0,6,20,Color.black,1);
			}
			for(int x=0;x<numUBullets;x++){
				uBullets[x]=new Bullet(0,0,6,40,Color.black,1);
				uBullets[x].setOn(0);
				uBullets2[x]=new Bullet(0,0,6,40,Color.black,1);
				uBullets2[x].setOn(0);
			}
			for(int x=0;x<numRBullets;x++){
				rBullets[x]=new Bullet(0,0,8,5,Color.black,1);
				rBullets[x].setOn(0);
				rBullets[x].setRocket(true);
				rBullets2[x]=new Bullet(0,0,8,5,Color.black,1);
				rBullets2[x].setOn(0);
				rBullets2[x].setRocket(true);
			}
			for(int x=0;x<5;x++){
				labels[x]=new JLabel("");
				labels[x].setVisible(false);
				labels[x].setForeground(Color.green);
			}
			boxes[0]=new Box(80,80,15);
			boxes[1]=new Box(720,720,15);
			boxStopTime[0]=0;
			boxStopTime[1]=0;
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
			
			t = new Timer(15,new TimerListener());
         t.start();
         addKeyListener(new Key());
         setFocusable(true);
			
			p=new Rectangle(40,40,20,2,Color.green,40);
			p2=new Rectangle(40,80,20,2,new Color(253,0,219),40);
			p2.draw(g);
			p.draw(g);
			
			setLayout(null);
			JButton reset=new JButton("Reset");
			reset.setHorizontalAlignment(SwingConstants.LEFT);
			reset.addActionListener(new ResetListener());
			add(reset);
			reset.setBounds(820,850,70,30);
			reset.setFocusable(false);
			
			JButton back=new JButton("Menu");
			back.setHorizontalAlignment(SwingConstants.LEFT);
			back.addActionListener(new BackListener());
			add(back);
			back.setBounds(820,890,65,30);
			back.setFocusable(false);
			
			JButton pause=new JButton("| |");
			pause.addActionListener(new PauseListener());
			add(pause);
			pause.setForeground(Color.yellow);
			pause.setBackground(Color.black);
			pause.setBounds(10,890,70,30);
			pause.setFocusable(false);
			
			scores=new JButton("High Scores");
			scores.setFocusable(false);
			scores.setBounds(20,100,800,200);
			
			s=new Scoreboard();
			add(s);
			s.setBounds(120,860,800,150);
			
			s2=new ScoreboardRight();
			add(s2);
			s2.setBounds(920,0,120,960);
			
			s3=new ScoreboardRight();
			add(s3);
			s3.setBounds(0,0,120,960);
			
			s1=new Scoreboard2();
			add(s1);
			s1.setBounds(0,0,920,60);
			
						
			panel=new JPanel();
			add(panel);
			panel.setBounds(120,60,800,800);
			panel.setForeground(tr);
			panel.setBackground(tr);
			panel.setVisible(false);
			JLabel resume=new JLabel("Press r to resume");
			panel.add(resume);
			JButton play=new JButton("Resume");
			play.addActionListener(new ResumeListener());
			panel.add(play);
			panel.add(scores);
			play.setFocusable(false);
			
			label=new JLabel("You lose");
			add(label);
			label.setBounds(170,50,750,750);
			label.setFont(new Font("Vivaldi",Font.ITALIC,170));
			label.setForeground(Color.orange);
			label.setVisible(false);
			
			label2=new JLabel("Reviving partner...");
			add(label2);
			label2.setBounds(300,60,750,100);
			label2.setFont(new Font("Serif",Font.ITALIC,80));
			label2.setForeground(Color.red);
			label2.setVisible(false);
      }
		//////////////////////////////////////////////////////////////////////////////////////////LISTENERS
		private class PauseListener implements ActionListener{
			public void actionPerformed(ActionEvent e){
					pause();
			}
		}
		private class ResumeListener implements ActionListener{
			public void actionPerformed(ActionEvent e){
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
		private class ResetListener implements ActionListener{
			public void actionPerformed(ActionEvent e){
					added=false;
					scores.removeActionListener(AL);
					level=startLevel;
					preLevel();
					nextLevel();
					resume();
					u=l=r=d=space=u2=l2=r2=d2=slash=false;
					kListenerOn=pListenerOn=p2ListenerOn=true;
					slower=0;
					slower2=0;
					kills=0;
					p.setX(420);
					p.setY(400);
					p.setColor(Color.green);
					p2.setX(380);
					p2.setY(400);
					p.setAlive(true);
					p2.setAlive(true);
					p2.setColor(Color.green);
					label.setVisible(false);
					p.setDir(3);
					p.setHealth(40);
					p2.setDir(3);
					p2.setHealth(40);
			}
			
		}
		private class TimerListener implements ActionListener{
         public void actionPerformed(ActionEvent e){
				drawBackground(level);
				
				if(move==true){
					setDirection();
					moveRect(p);
					p.toggle();
				}
				if(move2==true){
					setDirection();
					moveRect(p2);
					p2.toggle();
				}
				if(slash){
					if(p.getShot()==p.getShotFrequency()){
								p.setShot(0);
								shoot(p);
					}
				}
				if(space){
					if(p2.getShot()==p2.getShotFrequency()){
								p2.setShot(0);
								shoot(p2);
					}
				}
				p.setShot(p.getShot()+1);
				p2.setShot(p2.getShot()+1);
			   slower++;
				if(slower==8){
					moveEnemies();
					slower=0;
					for(int x=0;x<numBullets;x++){
						if(bullets[x].getOn()==2)
							bullets[x].setOn(0);
					}
				}
				slower2++;
				if(slower2==65){
					if(p.getHealth()<40)
						p.setHealth(p.getHealth()+1);
					if(p2.getHealth()<40)
						p2.setHealth(p2.getHealth()+1);
					slower2=0;
				}
				
				if(p.getAlive()){
					p.draw(g);
					manageShooting(p);
				}
				if(p2.getAlive()){
					p2.draw(g);
					manageShooting(p2);
				}
				for(int x=0;x<numRemaining;x++){
						if(enemies[x].getAlive()){
							enemies[x].draw(g);
								if(collided(enemies[x],p)&&!enemies[x].isAShooter()){
									if(enemies[x].getAttacking()>0&&enemies[x].getAttacking()<35&&p.getAlive()){
										p.setHealth(p.getHealth()-10);
										p.react(enemies[x].getDir());
										moveRect(p);
										enemies[x].setAttacking(0);
									}
									else if(enemies[x].getAttacking()==0)
										enemies[x].attack();
								}
								if(collided(enemies[x],p2)&&!enemies[x].isAShooter()){
									if(enemies[x].getAttacking()>0&&enemies[x].getAttacking()<35&&p2.getAlive()){
										p2.setHealth(p2.getHealth()-10);
										p2.react(enemies[x].getDir());
										moveRect(p2);
										enemies[x].setAttacking(0);
									}
									else if(enemies[x].getAttacking()==0)
										enemies[x].attack();
								}
						}
				}
				
				
				for(int x=0;x<numEBullets;x++){
					if(eBullets[x].getOn()==1){
						moveBullet(eBullets[x]);
						eBullets[x].draw(g);
							if(p.getAlive())
								shot(p,eBullets[x]);
							if(p2.getAlive())
								shot(p2,eBullets[x]);
					}
				}
				update();
				if(!p.getAlive()){
					counter3++;
					g.setColor(new Color(255-counter3,counter3,100));
					g.fillRect(180,100,4*counter3,20);
					if(counter3<170){
						p.setX(p2.getX());
						p.setY(p2.getY());
					}
					else if(counter3==175){
						do{
						negative=(int)(Math.random()*2);
						if(negative==0)
							negative=-1;
						p.setX(p2.getX()+(int)(Math.random()*50+30)*negative);
						negative=(int)(Math.random()*2);
						if(negative==0)
							negative=-1;
						p.setY(p2.getY()+(int)(Math.random()*50+60)*negative);
						}while(p.getX()<25||p.getX()>750||p.getY()<25||p.getY()>740);
					}
					else if(counter3>175&&counter3<200)
						drawShaded(p,(counter3-175)*8);
					else if(counter3==200){
						p.setAlive(true);
						p.setHealth(50);
						label2.setVisible(false);
						counter3=0;
					}
				}
				else if(!p2.getAlive()){
					counter3++;
					g.setColor(new Color(255-counter3,counter3,100));
					g.fillRect(180,100,4*counter3,20);
					if(counter3<150){
						p2.setX(p.getX());
						p2.setY(p.getY());
					}
					else if(counter3==175){
						do{
						negative=(int)(Math.random()*2);
						if(negative==0)
							negative=-1;
						p2.setX(p.getX()+(int)(Math.random()*50+30)*negative);
						negative=(int)(Math.random()*2);
						if(negative==0)
							negative=-1;
						p2.setY(p.getY()+(int)(Math.random()*50+50)*negative);
						}while(p2.getX()<25||p2.getX()>750||p2.getY()<25||p2.getY()>740);
					}
					else if(counter3>175&&counter3<200)
						drawShaded(p2,(counter3-175)*8);
					else if(counter3==200){
						p2.setAlive(true);
						p2.setHealth(40);
						label2.setVisible(false);
						counter3=0;
					}
				}
				
				if(p.getHealth()<1){
					die(p);
					p.setX(p2.getX());
				}
				if(p2.getHealth()<1){
					die(p2);
					p2.setX(p.getX());
				}
				if(numAlive==0){
					drawBackground(level+1);
					if(p.getAlive())
						p.draw(g);
					if(p2.getAlive())
						p2.draw(g);
					preLevel();
					if(pauser==1)
						therandom=(int)(Math.random()*3);
					pauser++;
					if(pauser==180){
						pauser=0;
						label.setVisible(false);
						nextLevel();
					}
				}
				
					
				boxTime++;
				manageBoxes(boxTime);
				manageLabels();
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
					case KeyEvent.VK_SLASH:slash=false;break;
					case KeyEvent.VK_W:u2=false;break;
					case KeyEvent.VK_A:l2=false;break;
					case KeyEvent.VK_S:d2=false;break;
					case KeyEvent.VK_D:r2=false;break;
				 }
				 if(!u&&!r&&!d&&!l)
				 	move=false;
				if(!u2&&!r2&&!d2&&!l2)
				 	move2=false;
         }
         public void keyPressed(KeyEvent e){
			if(e.getKeyCode()==KeyEvent.VK_R)
				resume();
				if(pListenerOn){
				switch(e.getKeyCode()){
					case KeyEvent.VK_UP: 
						u=true;move=true;break;
				 	case KeyEvent.VK_RIGHT:
						r=true;move=true;break;
				 	case KeyEvent.VK_DOWN:
						d=true;move=true;break;
				 	case KeyEvent.VK_LEFT:
						l=true;move=true;break;
					case KeyEvent.VK_PERIOD:
						gM=p.getGun()+1;
						if(gM>3)
							gM=1;
						p.setGun(gM);break;
					case KeyEvent.VK_COMMA:
						gM=p.getGun()-1;
						if(gM<1)
							gM=3;
						p.setGun(gM);break;
					case KeyEvent.VK_SLASH:
						slash=true;break;
				}
				}
				if(p2ListenerOn){
				switch(e.getKeyCode()){
					case KeyEvent.VK_SPACE:
						space=true;break;
					case KeyEvent.VK_W:
						u2=true;move2=true;break;
					case KeyEvent.VK_A:
						l2=true;move2=true;break;
					case KeyEvent.VK_S:
						d2=true;move2=true;break;
					case KeyEvent.VK_D:
						r2=true;move2=true;break;
					case KeyEvent.VK_Q:
						gM=p2.getGun()-1;
						if(gM<1)
							gM=3;
						p2.setGun(gM);break;
					case KeyEvent.VK_E:
						gM=p2.getGun()+1;
						if(gM>3)
							gM=1;
						p2.setGun(gM);break;
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
						
						
					if(u2&&r2)
						p2.setDir(2);
					else if(d2&&r2)
						p2.setDir(4);
					else if(d2&&l2)
						p2.setDir(6);
					else if(u2&&l2)
						p2.setDir(8);
					else if(r2&&l2){
						if(p2.getDir()==3){
							p2.setDir(7);
							r=false;
						}
						else{
							p2.setDir(3);
							l=false;
						}
					}
					else if(u2&&d2){
						if(p2.getDir()==1){
							p2.setDir(5);
							u2=false;
						}
						else{
							p2.setDir(1);
							d2=false;
						}
					}
					else if(u2)
						p2.setDir(1);
					else if(r2)
						p2.setDir(3);
					else if(d2)
						p2.setDir(5);
					else if(l2)
						p2.setDir(7);
		}
      public boolean shot(Rectangle one, Bullet two){////////////////////////////////////////////////COLLIDE METHODS
			if((Math.abs(two.getX()-(one.getX()+length/2))<=length/2+two.getLength())&&
			Math.abs(two.getY()-(one.getY()+25+two.getLength()/2))<=25+two.getLength()/2){
			if(two.isABullet()&&!two.isGrenade()&&!two.isExploding())
				two.setOn(2);
			one.react();
				if(one.isEnemy()){
					one.setHealth(one.getHealth()-1);
					one.react(two.getDir());
					hits++;
					if(one.getHealth()<=0){
					one.setAlive(false);
					numAlive--;
					kills++;
					}
				}
				else
					one.setHealth(one.getHealth()-10);
				return true;
			}	
			return false;
      }
		public boolean collided(Enemy one, Rectangle two){
			if(Math.abs((one.getX()+length/2)-(two.getX()+length/2))<=length&&Math.abs(one.getY()-two.getY())<=50){
				return true;
				}
			return false;
		}
		public boolean collided(Box box,Rectangle two){
			if(Math.abs((two.getX()+length/2)-(box.getX()+box.getLength()/2))<=length/2+box.getLength()/2&&
				Math.abs((two.getY()+length/2-box.getLength()/2)-box.getY())<=25+box.getLength()/2){
				return true;
				}
			return false;
		}
		
		public void manageBoxes(int time){////////////////////////////////////////////MANAGING BOXES AND LABELS
			for(int x=0;x<2;x++){
				if(boxes[x].getOn()==1){
					boxes[x].draw(g);
					if(collided(boxes[x],p)){
						boxes[x].setOn(2);
							if(p.getHealth()<35){
								p.setHealth(40);
								addLabel("+Health",boxes[x].getX(),boxes[x].getY());
							}
							else{
								int count=0;
								for(int b=0;b<numUBullets;b++){
									if(uBullets[b].getOn()==0)
										count++;
								}
								if(count<numUBullets*.5){
									for(int b=0;b<numUBullets;b++)
										uBullets[b].setOn(0);
									addLabel("+Uzi Ammo",boxes[x].getX(),boxes[x].getY());
								}
								else{
									if(Math.random()>.5&&count<numUBullets){
										for(int b=0;b<numUBullets;b++)
											uBullets[b].setOn(0);
										addLabel("+Uzi Ammo",boxes[x].getX(),boxes[x].getY());
									}
								}								
						  }								
					}
					if(collided(boxes[x],p2)){
						boxes[x].setOn(2);
							if(p2.getHealth()<35){
								p2.setHealth(40);
								addLabel("+Health",boxes[x].getX(),boxes[x].getY());
							}
							else{
								int count=0;
								for(int b=0;b<numUBullets;b++){
									if(uBullets2[b].getOn()==0)
										count++;
								}
								if(count<numUBullets*.5){
									for(int b=0;b<numUBullets;b++)
										uBullets2[b].setOn(0);
									addLabel("+Uzi Ammo",boxes[x].getX(),boxes[x].getY());
								}
								else{
									if(Math.random()>.5&&count<numUBullets){
										for(int b=0;b<numUBullets;b++)
											uBullets2[b].setOn(0);
										addLabel("+Uzi Ammo",boxes[x].getX(),boxes[x].getY());
									}
								}								
						  }								
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
					labels[c].setBounds(x,y,80,25);
					labels[c].setForeground(Color.green);
					add(labels[c]);
					labels[c].setVisible(true);
					labelCounters[c]=40;
					break;
				}
			}
		}
		public void addLabel(String text, int x, int y,int a){
			for(int c=0;c<5;c++){
				if(!labels[c].isVisible()){
					labels[c]=new JLabel(text);
					labels[c].setFont(new Font("Serif",Font.BOLD,60));
					labels[c].setBounds(x,y,700,100);
					labels[c].setForeground(Color.green);
					add(labels[c]);
					labels[c].setVisible(true);
					labelCounters[c]=60;
					break;
				}
			}
		}
		
		public void pause(){
			kListenerOn=false;///////////////////////////////////switch for focusable
			t.stop();
			panel.setVisible(true);
		}
		public void resume(){
					panel.setVisible(false);
					kListenerOn=true;
					t.restart();
		}
      public boolean moveRect(Rectangle a){	///////////////////////////////////////MOVING AND SHOOTING
													
				int oldX=a.getX();
				int oldY=a.getY();
				a.move();
				int newX=a.getX();
				int newY=a.getY();
			try{
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
					return false;
				}
			}
			catch(Exception E){}
			return true;
      }
		
		public void moveBullet(Bullet a){
				int oldX=a.getX();
				int oldY=a.getY();
				
				a.move();
				
				int newX=a.getX();
				int newY=a.getY();
			if(a.isABullet()){
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
			else{//////////////////////////////////necessary?
				if(map[newY][newX-1]==1||
					map[newY][newX+19]==1||
					map[newY+47][newX-1]==1||map[newY+47][newX+19]==1
					){
					a.setX(oldX);
					a.setY(oldY);
				}
			}
		}
		public void moveEnemies(){
			for(int x=0;x<numRemaining;x+=2){
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
				if(enemies[x].isAShooter()&&enemies[x].getShot()==enemies[x].getShotFrequency()){
					shoot(enemies[x]);
					enemies[x].setShot(0);
				}
				moveRect(enemies[x]);
				
				if(oldX==enemies[x].getX()&&oldY==enemies[x].getY()){//if it's in the same spot	
					if(enemies[x].getX()>600)
						enemies[x].setDir(5);		
					else if(enemies[x].getX()>350)
						enemies[x].setDir(7);
					else if(enemies[x].getX()>200)
						enemies[x].setDir(1);
					else
						enemies[x].setDir(1);	
					moveRect(enemies[x]);	
				}
			}
			}
			
			
			for(int x=1;x<numRemaining;x+=2){
			if(enemies[x].getAlive()&&enemies[x].getAttacking()==0){
				int oldX=enemies[x].getX();
				int oldY=enemies[x].getY();
			
				if(p2.getX()<enemies[x].getX()){//if p2layer is on left
					if(enemies[x].getY()>p2.getY()){//if p2layer is above
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
				else{									//if p2layer is on the right
					if(enemies[x].getY()>p2.getY()){//if p2layer is above
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
			if(p2.getX()<200&&enemies[x].getX()<200||//for columns that are op2en
				p2.getX()>200&p2.getX()<350&&enemies[x].getX()>200&&enemies[x].getX()<350||
				p2.getX()>350&&p2.getX()<600&&p2.getY()<600&&enemies[x].getX()>350&&enemies[x].getX()<600&&enemies[x].getY()<600||
				
				p2.getY()<200&&p2.getX()<600&&enemies[x].getY()<200&&enemies[x].getX()<600|//for rows that are op2en
				p2.getY()>600&&p2.getX()>200&&enemies[x].getY()>600&&enemies[x].getX()>200){
				if(Math.abs(p2.getX()-enemies[x].getX())<8){ //this can be extended so it works for diagonals
				double random=Math.random();
				if(random<.7){
					if(p2.getY()>enemies[x].getY())
						enemies[x].setDir(5);
					if(p2.getY()<enemies[x].getY())
						enemies[x].setDir(1);
				}
				else if(random<.85)
					enemies[x].setDir(3);
				else
					enemies[x].setDir(7);
				}
				
				if(Math.abs(p2.getY()-enemies[x].getY())<8){
				double rando=Math.random();
				if(rando<.7){
					if(p2.getX()>enemies[x].getX())
						enemies[x].setDir(3);
					if(p2.getX()<enemies[x].getX())
						enemies[x].setDir(5);
				}
				else if(rando<.85)
					enemies[x].setDir(1);
				else
					enemies[x].setDir(5);
				}
			}
				if(enemies[x].isAShooter()&&enemies[x].getShot()==enemies[x].getShotFrequency()){
					shoot(enemies[x]);
					enemies[x].setShot(0);
				}
				moveRect(enemies[x]);
				
				if(oldX==enemies[x].getX()&&oldY==enemies[x].getY()){//if it's in the same sp2ot	
					if(enemies[x].getX()>600)
						enemies[x].setDir(5);		
					else if(enemies[x].getX()>350)
						enemies[x].setDir(7);
					else if(enemies[x].getX()>200)
						enemies[x].setDir(1);
					else
						enemies[x].setDir(1);	
					moveRect(enemies[x]);	
				}
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
			switch(k.getGun()){
			case 1:
				for(int x=0;x<numBullets;x++){
					if(bullets[x].getOn()==0){
						bullets[x].setOn(1);
						b=x;
						shots++;
						break;
					}
				}
				bullets[b].setX(fireX);
				bullets[b].setY(fireY);
				bullets[b].setDir(k.getDir());
				bullets[b].setColor(k.getBulletColor());break;
			case 2:
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
				uBullets[b].setColor(k.getBulletColor());break;
			case 3:
				for(int x=0;x<numRBullets;x++){
					if(rBullets[x].getOn()==0){
						rBullets[x].setOn(1);
						b=x;
						shots++;
						break;
					}
				}
				rBullets[b].setX(fireX);
				rBullets[b].setY(fireY);
				rBullets[b].setDir(k.getDir());
				rBullets[b].setColor(k.getBulletColor());break;
			}
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
			eBullets[b].setSpeed(6);
			eBullets[b].setLength(7);
		}
			
      }
					
      public void drawBackground(int lev){///////////////////////////////////////////////////BACKGROUND AND LEVELS
			ImageIcon i=new ImageIcon("slash.jpg");
			switch(lev){
			case 0:
			case 1:
				i=new ImageIcon("pics/sky.jpg");
				g.setColor(new Color(0,0,1));break;
			case 2:
				i=new ImageIcon("pics/city.jpg");
				g.setColor(Color.green.darker());
				tr=new Color(21,183,90,180);break;
			case 3:
				i=new ImageIcon("pics/fish.jpg");
				g.setColor(new Color(244,161,19));break;
			case 4:
				i=new ImageIcon("pics/cheetah.jpg");///////////find new
				g.setColor(Color.green.darker());break;
			case 5:
				i=new ImageIcon("pics/flowers.jpg");
				g.setColor(new Color(242,170,170));break;
			case 7:
				i=new ImageIcon("pics/cheetah.jpg");
				g.setColor(Color.green.darker());break;
			default:
				i=new ImageIcon("pics/flowers.jpg");
				g.setColor(new Color(244,161,19));break;	
			}
			g.drawImage(i.getImage(),0,0,800,800,null);
			g.fillRect(0,0,800,length);
			g.fillRect(0,0,length,800);
			g.fillRect(0,800-length,800,800);
			g.fillRect(800-length,0,800,800);
			
			g.fillRect(800/4,800/4,length,800*3/4);
			g.fillRect(800*3/4,0,length,800*3/4);
			g.fillRect(800/2,800*3/4-length,800/4,length);
			
			
      }
		
		public void preLevel(){//to make something translucent, make the fourth argument less than 255
		score=kills*100+(level-1)*100;
		if(score<0)
			score=0;
		s1.update(level+1,score);
		if(pauser/30%2==1){//adds blinks
			label.setText("Level "+(level+1));
			label.setVisible(true);
			switch(level+1){
			case 1:numRemaining=10;
				for(int x=0;x<numRemaining;x++){
					enemies[x]=new Enemy(700,x*60+25,20,5,Color.gray);
					enemies[x].draw(g,tr);
					enemies[x].setAlive(false);
				}break;
			case 2:numRemaining=15;
				for(int x=0;x<numRemaining;x++){
					enemies[x]=new Enemy(680+x*2,2*25+20,20,5,Color.gray);
					enemies[x].draw(g,tr);
					enemies[x].setAlive(false);
				}break;
			case 3:numRemaining=22;
				for(int x=0;x<11;x++){
					enemies[x]=new Enemy(40,x*8+25,20,5,Color.gray);
					enemies[x].draw(g,tr);
					enemies[x].setAlive(false);
				}
				for(int x=11;x<numRemaining;x++){
					enemies[x]=new Enemy(680+x*2,(x-11)*30+400,20,5,Color.gray);
					enemies[x].draw(g,tr);
					enemies[x].setAlive(false);
				}break;
			case 4:numRemaining=8;
				for(int x=0;x<6;x++){
					enemies[x]=new Enemy(40,x*30+500,20,5,Color.gray);
					enemies[x].draw(g,tr);
					enemies[x].setAlive(false);
				}
				enemies[6]=new Shooter(40,700,20,4,Color.black);
				enemies[7]=new Shooter(700,50,20,4,Color.black);
				enemies[6].draw(g,tr);
				enemies[7].draw(g,tr);
				enemies[6].setAlive(false);
				enemies[7].setAlive(false);break;
			case 5:numRemaining=5;
				for(int x=0;x<6;x++){
					enemies[x]=new Shooter(40,x*30+500,20,4,Color.black);
					enemies[x].draw(g,tr);
					enemies[x].setAlive(false);
				}break;
			case 6:numRemaining=10;
				for(int x=0;x<4;x++){
					enemies[x]=new Enemy(25+x*100,30,20,5,Color.gray);
				}
				for(int x=4;x<8;x++){
					enemies[x]=new Enemy(230+(x-4)*100,730,20,5,Color.gray);
				}
				enemies[8]=new Shooter(40,700,20,4,Color.black);
				enemies[9]=new Shooter(700,50,20,4,Color.black);
				for(int x=0;x<10;x++){
					enemies[x].draw(g,tr);
					enemies[x].setAlive(false);
				}break;
			case 7:numRemaining=12;
				for(int x=2;x<10;x++){
					enemies[x]=new Enemy(700,x*60+25,20,5,Color.gray,3);
					enemies[x].draw(g,tr);
					enemies[x].setAlive(false);
				}
				enemies[0]=new Enemy(700,8*60+25,20,11,Color.blue);
				enemies[1]=new Enemy(700,9*60+25,20,11,Color.blue);
				enemies[0].draw(g,tr);
				enemies[1].draw(g,tr);
				enemies[0].setAlive(false);
				enemies[1].setAlive(false);
				enemies[10]=new Enemy(100,8*60+25,20,11,Color.blue);
				enemies[11]=new Enemy(100,9*60+25,20,11,Color.blue);
				enemies[10].draw(g,tr);
				enemies[11].draw(g,tr);
				enemies[10].setAlive(false);
				enemies[11].setAlive(false);
				break;
			case 8:numRemaining=27;
				for(int y=0;y<10;y++){
					enemies[y]=new Shooter(300,40*y+40,20,4,Color.black);
				}
				for(int y=0;y<10;y++){
					enemies[y+10]=new Enemy(40,40*y+40,20,4,Color.gray);
				}
				for(int y=0;y<10;y++){
					enemies[y+20]=new Enemy(700,40*y+40,20,4,Color.gray);
				}
				for(int x=0;x<30;x++){
					enemies[x].draw(g, tr);
					enemies[x].setAlive(false);
				}
			case 9:numRemaining=30;
				for(int y=0;y<10;y++){
					enemies[y]=new Shooter(300,40*y+40,20,4,Color.black);
				}
				for(int y=0;y<10;y++){
					enemies[y+10]=new Enemy(40,40*y+40,20,11,Color.blue);
				}
				for(int y=0;y<10;y++){
					enemies[y+20]=new Enemy(700,40*y+40,20,11,Color.blue);
				}
				for(int x=0;x<30;x++){
					enemies[x].draw(g, tr);
					enemies[x].setAlive(false);
				}break;
			case 10:numRemaining=10;
				for(int y=0;y<10;y++){
					enemies[y]=new Enemy(40,40*y+40,20,7,Color.white,20);
					enemies[y].setAlive(false);
					enemies[y].draw(g,tr);
				}addLabel("+UZI INFINITY",300,100,-1);
				addLabel("+Rockets",250,500,-1);break;
			case 11:numRemaining=30;
				for(int y=0;y<10;y++){
					enemies[y]=new Shooter(300,40*y+40,20,4,Color.black);
				}
				for(int y=0;y<10;y++){
					enemies[y+10]=new Enemy(40,40*y+40,20,11,Color.blue);
				}
				for(int y=0;y<10;y++){
					enemies[y+20]=new Enemy(700,40*y+40,20,7,Color.white,20);
				}
				for(int x=0;x<30;x++){
					enemies[x].draw(g, tr);
					enemies[x].setAlive(false);
				}break;
			case 12:numRemaining=40;
				for(int y=0;y<10;y++){
					enemies[y]=new Enemy(400,40*y+40,20,7,Color.white,20);
				}
				for(int y=0;y<10;y++){
					enemies[y+10]=new Enemy(40,40*y+40,20,11,Color.blue);
				}
				for(int y=0;y<10;y++){
					enemies[y+20]=new Enemy(700,40*y+40,20,7,Color.white,20);
				}
				for(int x=0;x<5;x++){
					enemies[x+30]=new Enemy(30+x*40,60,20,11,Color.gray);
					enemies[x+35]=new Enemy(400+x*40,650,20,11,Color.gray);
				}
				for(int x=0;x<40;x++){
					enemies[x].draw(g, tr);
					enemies[x].setAlive(false);
				}break;
			case 13:numRemaining=1;
				enemies[0]=new Enemy(400,400,60,7,Color.white,80);
				enemies[0].setAlive(false);
				enemies[0].draw(g,tr);break;
			case 14:numRemaining=30;
				for(int y=0;y<10;y++){
					enemies[y]=new Shooter(300,40*y+40,20,8,Color.black);
				}
				for(int y=0;y<10;y++){
					enemies[y+10]=new Enemy(40,40*y+40,20,11,Color.white,20);
				}
				for(int y=0;y<10;y++){
					enemies[y+20]=new Enemy(700,40*y+40,20,11,Color.white,20);
				}
				for(int x=0;x<30;x++){
					enemies[x].draw(g, tr);
					enemies[x].setAlive(false);
				}break;
			case 20:numRemaining=1;
				enemies[0]=new Enemy(400,400,60,12,Color.white,200);
				enemies[0].draw(g);break;
			default:
				switch(therandom){
				case 0:numRemaining=22;
					for(int x=0;x<11;x++){
						enemies[x]=new Enemy(40,x*8+25,20,5,Color.white,22+(level-14)*3);
						enemies[x].draw(g,tr);
						enemies[x].setAlive(false);
					}
					for(int x=11;x<22;x++){
						enemies[x]=new Enemy(680+x*2,(x-11)*30+400,20,11+(level-14)*3,Color.blue);
						enemies[x].draw(g,tr);
						enemies[x].setAlive(false);
					}break;
				case 1:numRemaining=30;
					for(int y=0;y<10;y++)
						enemies[y]=new Shooter(300,40*y+40,20,7+level-14,Color.black);
					for(int y=0;y<10;y++)
						enemies[y+10]=new Enemy(40,40*y+40,20,11+level-14,Color.blue);
					for(int y=0;y<10;y++)
						enemies[y+20]=new Enemy(700,40*y+40,20,7+level-14,Color.white,20+level-14);
					for(int x=0;x<30;x++){
						enemies[x].draw(g, tr);
						enemies[x].setAlive(false);
					}break;
				case 2:numRemaining=40;
					for(int y=0;y<10;y++){
						enemies[y]=new Enemy(400,40*y+40,20,7+level-14,Color.white,20+level-14);
					}
					for(int y=0;y<10;y++){
						enemies[y+10]=new Enemy(40,40*y+40,20,11+(level-14)*3,Color.blue);
					}
					for(int y=0;y<10;y++){
						enemies[y+20]=new Enemy(700,40*y+40,20,7,Color.white,20+level-14);
					}
					for(int x=0;x<5;x++){
						enemies[x+30]=new Enemy(30+x*40,60,20,11,Color.gray);
						enemies[x+35]=new Enemy(400+x*40,650,20,11,Color.gray);
					}
					for(int x=0;x<40;x++){
						enemies[x].draw(g, tr);
						enemies[x].setAlive(false);
					}break;
				}
				break;
		}
		}
			repaint();
		}
		public void nextLevel(){
			level++;
			switch(level){
			case 1:numRemaining=numAlive=10;//lined up on the right
				for(int x=0;x<numRemaining;x++){
					enemies[x]=new Enemy(700,x*60+25,20,7,Color.gray);
					enemies[x].draw(g);
				}break;
			case 2:numRemaining=numAlive=15;//in the top right corner
				for(int x=0;x<numRemaining;x++){
					enemies[x]=new Enemy(680+x*2,2*25+20,20,7,Color.gray);
					enemies[x].draw(g);
				}break;
			case 3: numRemaining=numAlive=22;//on both sides
				for(int x=0;x<11;x++){
					enemies[x]=new Enemy(40,x*8+25,20,7,Color.gray);
					enemies[x].draw(g);
				}
				for(int x=11;x<numRemaining;x++){
					enemies[x]=new Enemy(680+x*2,(x-11)*30+400,20,7,Color.gray);
					enemies[x].draw(g);
				}break;
			case 4: numRemaining=numAlive=8;//introducing shooters
				for(int x=0;x<6;x++){
					enemies[x]=new Enemy(40,x*30+500,20,7,Color.gray);
					enemies[x].draw(g);
				}
				enemies[6]=new Shooter(40,700,20,4,Color.black);
				enemies[7]=new Shooter(700,50,20,4,Color.black);
				enemies[6].draw(g);
				enemies[7].draw(g);break;
			case 5: numRemaining=numAlive=5;//overload of shooters
				for(int x=0;x<6;x++){
					enemies[x]=new Shooter(40,x*30+500,20,4,Color.black);
					enemies[x].draw(g);
				}break;
			case 6: numRemaining=numAlive=10;//shooters and enemies
				for(int x=0;x<4;x++){
					enemies[x]=new Enemy(25+x*100,30,20,7,Color.gray);
				}
				for(int x=4;x<8;x++){
					enemies[x]=new Enemy(230+(x-4)*100,730,20,7,Color.gray);
				}
				enemies[8]=new Shooter(40,700,20,4,Color.black);
				enemies[9]=new Shooter(700,50,20,4,Color.black);break;
			case 7:numRemaining=numAlive=12;//introducing speed
				for(int x=2;x<10;x++){
					enemies[x]=new Enemy(700,x*60+25,20,7,Color.gray,5);
					enemies[x].draw(g);
				}
				enemies[0]=new Enemy(700,8*60+25,20,12,Color.blue);
				enemies[1]=new Enemy(700,9*60+25,20,12,Color.blue);
				enemies[0].draw(g);
				enemies[1].draw(g);
				enemies[10]=new Enemy(100,8*60+25,20,14,Color.blue);
				enemies[11]=new Enemy(100,9*60+25,20,14,Color.blue);
				enemies[10].draw(g);
				enemies[11].draw(g);
				break;
			case 8: numRemaining=numAlive=30;//3 columns
				for(int y=0;y<10;y++){
					enemies[y]=new Shooter(300,40*y+40,20,4,Color.black);
				}
				for(int y=0;y<10;y++){
					enemies[y+10]=new Enemy(40,40*y+40,20,7,Color.gray);
				}
				for(int y=0;y<10;y++){
					enemies[y+20]=new Enemy(700,40*y+40,20,7,Color.gray);
				}
				for(int x=0;x<30;x++)
					enemies[x].draw(g);break;	
			case 9: numRemaining=numAlive=30;	//speed
				for(int y=0;y<10;y++){
					enemies[y]=new Shooter(300,40*y+40,20,4,Color.black);
				}
				for(int y=0;y<10;y++){
					enemies[y+10]=new Enemy(40,40*y+40,20,13,Color.blue);
				}
				for(int y=0;y<10;y++){
					enemies[y+20]=new Enemy(700,40*y+40,20,13,Color.blue);
				}
				for(int x=0;x<30;x++)
					enemies[x].draw(g);break;
			case 10: numRemaining=numAlive=10;
				for(int y=0;y<10;y++){
					enemies[y]=new Enemy(40,40*y+40,20,11,Color.white,20);
					enemies[y].draw(g);
				}reached=true;
				for(int x=0;x<numRBullets;x++)
					rBullets[x].setOn(0);
				p.setGun(3);
			case 11: numRemaining=numAlive=30;
				for(int y=0;y<10;y++){
					enemies[y]=new Shooter(300,40*y+40,20,4,Color.black);
				}
				for(int y=0;y<10;y++){
					enemies[y+10]=new Enemy(40,40*y+40,20,11,Color.blue);
				}
				for(int y=0;y<10;y++){
					enemies[y+20]=new Enemy(700,40*y+40,20,7,Color.white,20);
				}
				for(int x=0;x<30;x++){
					enemies[x].draw(g);
				}
			case 12:numRemaining=numAlive=40;
				for(int y=0;y<10;y++){
					enemies[y]=new Enemy(300,40*y+40,20,7,Color.white,20);
				}
				for(int y=0;y<10;y++){
					enemies[y+10]=new Enemy(40,40*y+40,20,11,Color.blue);
				}
				for(int y=0;y<10;y++){
					enemies[y+20]=new Enemy(700,40*y+40,20,7,Color.white,20);
				}
				for(int x=0;x<5;x++){
					enemies[x+30]=new Enemy(30+x*40,60,20,11,Color.gray);
					enemies[x+35]=new Enemy(400+x*40,650,20,11,Color.gray);
				}
				for(int x=0;x<40;x++){
					enemies[x].draw(g);
				}break;
			case 13:numRemaining=numAlive=1;
				enemies[0]=new Enemy(400,400,60,7,Color.white,200);
				enemies[0].draw(g);break;
			case 14:numRemaining=numAlive=30;
				for(int y=0;y<10;y++){
					enemies[y]=new Shooter(300,40*y+40,20,8,Color.black);
				}
				for(int y=0;y<10;y++){
					enemies[y+10]=new Enemy(40,40*y+40,20,11,Color.white,20);
				}
				for(int y=0;y<10;y++){
					enemies[y+20]=new Enemy(700,40*y+40,20,11,Color.white,20);
				}
				for(int x=0;x<30;x++){
					enemies[x].draw(g);
				}break;
			case 20:numRemaining=numAlive=1;
				enemies[0]=new Enemy(400,400,60,12,Color.white,200);
				enemies[0].draw(g);break;
			default:
				switch(therandom){
				case 0:numRemaining=numAlive=22;
					for(int x=0;x<11;x++){
						enemies[x]=new Enemy(40,x*8+25,20,5,Color.white,22+(level-14)*3);
						enemies[x].draw(g);
					}
					for(int x=11;x<numRemaining;x++){
						enemies[x]=new Enemy(680+x*2,(x-11)*30+400,20,11+(level-14)*3,Color.blue);
						enemies[x].draw(g);
					}break;
				case 1:
					numRemaining=numAlive=30;
					for(int y=0;y<10;y++)
						enemies[y]=new Shooter(300,40*y+40,20,7+level-14,Color.black);
					for(int y=0;y<10;y++)
						enemies[y+10]=new Enemy(40,40*y+40,20,11+level-14,Color.blue);
					for(int y=0;y<10;y++)
						enemies[y+20]=new Enemy(700,40*y+40,20,7+level-14,Color.white,20+level-14);
					for(int x=0;x<30;x++){
						enemies[x].draw(g);
					}break;
				case 2:numRemaining=numAlive=40;
					for(int y=0;y<10;y++){
						enemies[y]=new Enemy(400,40*y+40,20,7+level-14,Color.white,20+level-14);
					}
					for(int y=0;y<10;y++){
						enemies[y+10]=new Enemy(40,40*y+40,20,11+(level-14)*3,Color.blue);
					}
					for(int y=0;y<10;y++){
						enemies[y+20]=new Enemy(700,40*y+40,20,7,Color.white,20+level-14);
					}
					for(int x=0;x<5;x++){
						enemies[x+30]=new Enemy(30+x*40,60,20,11,Color.gray);
						enemies[x+35]=new Enemy(400+x*40,650,20,11,Color.gray);
					}
					for(int x=0;x<40;x++){
						enemies[x].draw(g);
					}break;
				}
				break;
		}
		}
		
		public void drawShaded(Rectangle o, int x){
			o.draw(g,x);
		}
		public void die(Rectangle o){//////////////////////////////////////////////////////////////////////////////MISC
			o.setAlive(false);
			label2.setVisible(true);
			if(!p.getAlive()&&!p2.getAlive())
				lose();
		}
		public void lose(){
			pause();
			numAlive=-1;
			label.setFont(new Font("Serif",Font.ITALIC,75));
			label.setForeground(Color.black);
			label.setText("You Lose - Score: "+score);
 			label.setVisible(true);
 			kListenerOn=false;
			pListenerOn=false;
			p2ListenerOn=false;
			if(!added){
				AL=new AccountListener(score);
				scores.addActionListener(AL);
				added=true;
			}
			t.stop();
		}
		private class AccountListener implements ActionListener{//start the game
			int myX=score;
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
       		   frame.setContentPane(new Account(2,myX));
        			frame.setVisible(true);
			}
		}
      public void paintComponent(Graphics a){
        a.drawImage(buff, 120, 60, 800, 800, null);
      }
		public void update(){					
		s.update(numAlive, kills, p.getHealth(), level, hits/shots);
		score=kills*100+(level-1)*100;
		if(score<0)
			score=0;
		s1.update(level,score);
		int uziAmmo=0;
			for(int x=0;x<numUBullets;x++){
				if(uBullets[x].getOn()==0)
					uziAmmo++;
			}
		int rAmmo=0;
			for(int x=0;x<numRBullets;x++){
				if(rBullets[x].getOn()==0)
					rAmmo++;
			}
		int uziAmmo2=0;
			for(int x=0;x<numUBullets;x++){
				if(uBullets2[x].getOn()==0)
					uziAmmo2++;
			}
		int rAmmo2=0;
			for(int x=0;x<numRBullets;x++){
				if(rBullets2[x].getOn()==0)
					rAmmo2++;
			}
		s2.update(p.getGun(),uziAmmo,0, rAmmo,level);
		s3.update(p2.getGun(),uziAmmo2,0,rAmmo2,level);
		}
		public void manageShooting(Rectangle o){
			if(o.getGun()==1){
				for(int x=0;x<numBullets;x++){
					if(bullets[x].getOn()==1){
						moveBullet(bullets[x]);
						bullets[x].draw(g);
							for(int a=0;a<numRemaining;a++){
								if(bullets[x].getOn()==1){
									if(enemies[a].getAlive()){
										shot(enemies[a],bullets[x]);
									}	
								}
							}
					}
				}
				}
				else if(o.getGun()==2){
				for(int x=0;x<numUBullets;x++){
					if(uBullets[x].getOn()==1){
						moveBullet(uBullets[x]);
						uBullets[x].draw(g);
							for(int a=0;a<numRemaining;a++){
								if(uBullets[x].getOn()==1){
									if(enemies[a].getAlive()){
										shot(enemies[a],uBullets[x]);
									}	
								}
							}
					}
				}
				}
				
				else if(o.getGun()==3){
				for(int x=0;x<numRBullets;x++){
					if(rBullets[x].getOn()==1){
						if(!rBullets[x].isExploding())
							moveBullet(rBullets[x]);
						rBullets[x].draw(g);
							for(int a=0;a<numRemaining;a++){
								if(rBullets[x].getOn()==1){
									if(enemies[a].getAlive()){
										if(shot(enemies[a],rBullets[x])){
											rBullets[x].setExploding(true);
											rBullets[x].setOn(1);
										}
									}	
								}
							}
					}
				}
				}
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
				for(int a=199;a<800;a++)
					map[a][199+y]=1;
				for(int a=0;a<600;a++)
					map[a][599+y]=1;
				for(int a=399;a<600;a++)
					map[579+y][a]=1;
			}
		}
   }