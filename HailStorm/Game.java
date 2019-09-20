   import javax.swing.*;import java.awt.*;import java.awt.event.*;import java.awt.image.*;
   import java.text.*;import java.io.*;import java.util.ArrayList;
   public class Game extends JPanel{
      public Graphics g;
      public final int GAMESPEED = 10, DIMENSION = 800;
      public int time = 0;
      public boolean l,d,r,u=false;
      Timer t;
      public BufferedImage buff;
      ArrayList<Rectangle> rects = new ArrayList<Rectangle>();
		ArrayList<Rectangle> enemies = new ArrayList<Rectangle>();
      Player p = new Player(400,400);
		JLabel score;
      public Game(){
			score = new JLabel("Score: "+time);
			score.setForeground(Color.white);
			add(score);
			score.setBounds(300,50,200,50);
         t = new Timer(GAMESPEED,new TimerListener());
         t.start();
         buff=new BufferedImage(800,800,BufferedImage.TYPE_INT_RGB);
         g=buff.getGraphics();
         
         rects.add(p);
         for(int x=0;x<3;x++)
           // rects.add(new Helper(DIMENSION));
         setFocusable(true);
         addKeyListener(new Key());
         addMouseListener(new Mouse());
      }
      public void paintComponent(Graphics g){
         g.drawImage(buff, 0, 0, 800, 800, null);
      }
      private class TimerListener implements ActionListener{
         public void actionPerformed(ActionEvent e){
            drawBackground(time++);
            setDirection();
            for(Rectangle r:rects){
               move(r);
               r.draw(g);
            }
				for(Rectangle r:enemies){
					r.move();
					r.draw(g);
				}
            checkCollisions(enemies);
            spawnEnemies();
				score();
            repaint();
         }
      }
		public void move(Rectangle r){
			int oldX = r.myX;
			int oldY = r.myY;
			r.move();
			if(r.myX<0||r.myX>DIMENSION-r.myLength)
				r.myX=oldX;
			if(r.myY<0||r.myY>DIMENSION-r.myLength*3-4)
				r.myY=oldY;
		}
      public void drawBackground(int time){///////////////////////////////////////////////////BACKGROUND AND LEVELS
         ImageIcon i=new ImageIcon("pics/storm.jpg");
         switch(time){
            // case 0:
//             case 1:
//                i=new ImageIcon("pics/sky.jpg");
//                g.setColor(new Color(0,0,1));
//                break;
//             case 2:
//                i=new ImageIcon("pics/city.jpg");
//                g.setColor(Color.green.darker());
//                break;
//             case 3:
//                i=new ImageIcon("pics/space.jpg");
//                g.setColor(new Color(244,161,19));
//                break;
//             case 4:
//                i=new ImageIcon("pics/cheetah.jpg");
//                g.setColor(Color.green.darker());
//                break;
//             case 5:
//                i=new ImageIcon("pics/flowers.jpg");
//                g.setColor(new Color(242,170,170));
//                break;
//             case 7:
//                i=new ImageIcon("pics/cheetah.jpg");
//                g.setColor(Color.green.darker());
//                break;
            default:
               i=new ImageIcon("pics/storm.jpg");
               g.setColor(new Color(244,161,19));
               break;	
         }
         g.drawImage(i.getImage(),0,0,DIMENSION,DIMENSION,null);
      }
      private class Key extends KeyAdapter{///////////////////////////////////////////////KEYS
         public void keyPressed(KeyEvent e){
            switch(e.getKeyCode()){
               case KeyEvent.VK_UP: 
                  u=true;
                  break;
               case KeyEvent.VK_RIGHT:
                  r=true;
                  break;
               case KeyEvent.VK_DOWN:
                  d=true;
                  break;
               case KeyEvent.VK_LEFT:
                  l=true;
                  break;
            }
         }
         public void keyReleased(KeyEvent e)
         {
            switch(e.getKeyCode()){
               case KeyEvent.VK_UP:u=false;
                  break;
               case KeyEvent.VK_RIGHT:r=false;
                  break;
               case KeyEvent.VK_DOWN:d=false;
                  break;
               case KeyEvent.VK_LEFT:l=false;
                  break;
            }
         }
      }
      public void setDirection(){
         if(!u&&!r&&!l&&!d)
            p.myDir = -1;
         if(u&&r)
            p.myDir = (2);
         else if(d&&r)
            p.myDir = (4);
         else if(d&&l)
            p.myDir = (6);
         else if(u&&l)
            p.myDir = (8);
         else if(r&&l){
            if(p.myDir ==3){
               p.myDir = (7);
               r=false;
            }
            else{
               p.myDir = (3);
               l=false;
            }
         }
         else if(u&&d){
            if(p.myDir ==1){
               p.myDir = (5);
               u=false;
            }
            else{
               p.myDir = (1);
               d=false;
            }
         }
         else if(u)
            p.myDir = (1);
         else if(r)
            p.myDir = (3);
         else if(d)
            p.myDir = (5);
         else if(l)
            p.myDir = (7);
      }
      public void checkCollisions(ArrayList<Rectangle> enemies){
      	for(Rectangle r: enemies){
				if(collision(r,p)||collision(p,r))
					endGame();
			}
      }
      public boolean collision(Rectangle one, Rectangle two){
			int xDif = Math.abs(two.myX-one.myX);
			int yDif = Math.abs(two.myY-one.myY);
			if(one.myX<two.myX){
				if(one.myY<two.myY){
					if(xDif<one.myLength&&yDif<one.myLength)
						return true;
				}
				else{
					if(xDif<one.myLength&&yDif<two.myLength)
						return true;
				}
			} 
         return false;
      }
      public void spawnEnemies(){
         if(time%50==0){
            for(int x=0;x<(int)(time*.004)+6*(time%3);x++){
               enemies.add(new Enemy(DIMENSION));
            }
         }
      }
      public void endGame(){
         setFocusable(false);
         g.setColor(new Color(244,161,19,175));
         g.fillRect(0,0,DIMENSION,DIMENSION);
         JLabel l = new JLabel("Game Over     Score: "+time);
         l.setForeground(Color.white);
         l.setFont(new Font("Serif",Font.BOLD,32));
         add(l);
         l.setBounds(DIMENSION/2-200,DIMENSION/2-100,400,200);
         repaint();
         t.stop();
      }
		public void score(){
			score.setText("Score: "+time);
		}
      private class Mouse extends MouseAdapter{
         public void mouseClicked(MouseEvent e){
         	
         }
      }
   }
  
