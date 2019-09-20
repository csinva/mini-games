   import javax.swing.*;import java.awt.*;import java.awt.event.*;import java.awt.image.*;
   import java.text.*;import java.io.*;import java.util.ArrayList;
   public class Game extends JPanel{
      public Graphics g,blackG;
      public final int GAMESPEED = 12, DIMENSION = 800;
      public int time = 0,numKilled =0;
      public boolean l,d,r,u,kill=false;
      Timer t;
      public BufferedImage buff, black;
      ArrayList<Helper> helpers = new ArrayList<Helper>();
      ArrayList<Enemy> enemies = new ArrayList<Enemy>();
      ArrayList<Attack> attacks = new ArrayList<Attack>();
      Player p = new Player(400,400);
      JButton reset;
      JLabel score, label, score2;
      public Game(){
         score = new JLabel("Score: "+time);
         score.setForeground(Color.black);
         score.setFont(new Font("Serif",Font.BOLD,22));
         add(score);
         score.setBounds(200,10,400,100);
         score2 = new JLabel("Score: "+time);
         score2.setForeground(Color.black);
         score2.setFont(new Font("Serif",Font.BOLD,28));
         add(score2);
         score2.setBounds(200,50,400,100);
         t = new Timer(GAMESPEED,new TimerListener());
         t.start();
         buff=new BufferedImage(800,800,BufferedImage.TYPE_INT_RGB);
         black = new BufferedImage(10,10,BufferedImage.TYPE_INT_RGB);
         blackG = black.getGraphics();
         blackG.setColor(Color.black);
         blackG.fillRect(0,0,10,10);
         g=buff.getGraphics();
         //helpers.add(p);
         for(int x=0;x<4;x++)
            helpers.add(new Helper(DIMENSION));
         setFocusable(true);
         addKeyListener(new Key());
         addMouseListener(new Mouse());
      	
         setLayout(null);
         reset=new JButton("Reset");
         reset.setHorizontalAlignment(SwingConstants.LEFT);
         reset.addActionListener(new ResetListener());
         add(reset);
         reset.setBounds(DIMENSION/2-35,DIMENSION/2+175,70,30);
         reset.setVisible(false);
      }
      private class TimerListener implements ActionListener{
         public void actionPerformed(ActionEvent e){
            drawBackground(time++);
         	
            for(Rectangle h:helpers)//Helpers
               h.draw(g);
            checkHelperCollisions(helpers);	
         	
            for(int x=0;x<attacks.size();x++){
               Attack a = attacks.get(x);//Rectangle a:attacks){//Attacks
               a.act();
               if(a.lifetime<0)
                  attacks.remove(x);
               a.draw(g);
            }
            if(p.lifetime<=0){
               kill=false;
               p.i=new ImageIcon("pics/Player.jpg");
            }
            checkAttackCollisions(attacks,enemies);
         	
            setDirectionPlayer();//Player
            movePlayer(p);
            p.draw(g);
         	
            for(Enemy r:enemies){//Enemies
               setDirectionEnemy(r);
               if(time%2==0)
                  r.act();
               r.draw(g);
            }
            checkEnemyCollisions(enemies);
            spawnEnemies();
         	
            if(time<510){
               g.setColor(new Color(255,255,255,255-time/2));
               g.fillRect(0,0,800,800);
            }
            score();
            repaint();
         }
         
      }
      public void paintComponent(Graphics g){
         int scale=2;
         int bx=0;
         int by=0;
        // g.drawImage(radar, -200,-200,10000,1000,null);
         bx=(160-p.myX)*scale;
         if(bx>0)
            bx=0;
         if(bx<-785)
            bx=-785;
         by=(160-p.myY)*scale;
         if(by>0)
            by=0;
         if(by<-785)
            by=-785;
         g.drawImage(buff,bx,by,800*scale,800*scale,null);
      	//g.drawImage(black,DIMENSION,0,10,DIMENSION,null);
      	//g.drawImage(black,0,DIMENSION,DIMENSION,10,null);
      }
      
      public void movePlayer(Rectangle r){
         int oldX = r.myX;
         int oldY = r.myY;
         r.act();
         if(r.myX<0||r.myX>DIMENSION-r.myLength)
            r.myX=oldX;
         if(r.myY<0||r.myY>DIMENSION-r.myLength)
            r.myY=oldY;
      }
      public void setDirectionEnemy(Enemy e){
         switch(e.startDir){
            case 1: e.myDir = 5;
               if(e.myY>=10)
                  e.startDir = -1;
               break;
            case 2: e.myDir = 7;
               if(e.myX<=DIMENSION)
                  e.startDir = -1;
               break;
            case 3: e.myDir = 1;
               if(e.myY<=DIMENSION)
                  e.startDir = -1;
               break;
            case 4: e.myDir = 3;
               if(e.myX>=10)
                  e.startDir = -1;
               break;
            default:
            
               if(p.myX<e.myX){//if player is on left
                  int xDif = Math.abs(p.myX - e.myX);
                  if(e.myY>p.myY){//if player is above
                     if(Math.random()<.5)
                        e.myDir = (8);
                     else
                        e.myDir = (7);
                     if(xDif < 5&&Math.random()<.8)
                        e.myDir = 1;
                  }
                  else{//if player is below
                     if(Math.random()<.5)
                        e.myDir = (6);
                     else
                        e.myDir = (7);
                     if(xDif < 5&&Math.random()<.8)
                        e.myDir = 5;
                  }
               }
               else{									//if player is on the right
                  if(e.myY>p.myY){//if player is above
                     if(Math.random()<.5)
                        e.myDir = (2);
                     else
                        e.myDir = (3);
                  }
                  else{//if player is below
                     if(Math.random()<.5)
                        e.myDir = (4);
                     else
                        e.myDir = (3);
                  }
               }
         	
         	
         }
      }
   	// public void moveEnemy(Enemy r){
   // 			
   // 		}
      public void drawBackground(int time){///////////////////////////////////////////////////BACKGROUND AND LEVELS
         ImageIcon i=new ImageIcon("pics/polar bear.jpg");
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
               i=new ImageIcon("pics/desert.jpg");
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
      public void setDirectionPlayer(){
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
      public void checkAttackCollisions(ArrayList<Attack> attacks,ArrayList<Enemy> enemies){
         for(Attack a:attacks){
            for(int x =0;x<enemies.size();x++){
               Enemy e = enemies.get(x);
               for(Rectangle r:a.rects){
                  if(collision(r,e)||collision(e,r)){
                     enemies.remove(x);
                     numKilled++;
                     break;
                  }
               }
               // if(a instanceof Windmill){
            //                   if(collision(e,((Windmill)a).one)||collision(((Windmill)a).one,e)
            //                   ||collision(e,((Windmill)a).two)||collision(((Windmill)a).two,e)){
            //                      enemies.remove(x);
            // 							numKilled++;
            //                   }
            //                } 
            }
         }
      }
      public void checkHelperCollisions(ArrayList<Helper> helpers){
         for(int x = 0;x<helpers.size();x++){
            Helper r = helpers.get(x);
            if(collision(r,p)||collision(p,r)){
               helpers.remove(x);
               if(r.attackType==1)
                  attacks.add(new Windmill(r.myX,r.myY));
               else if(r.attackType==2)
                  attacks.add(new Spiral(r.myX,r.myY));
               else if(r.attackType==3)
                  attacks.add(new Fire(r.myX,r.myY));
               else if(r.attackType==4){
                  g.fillRect(0,0,DIMENSION,DIMENSION);
                  for(int y =0;y<enemies.size();y++){//Enemy e:enemies){
                     Enemy e = enemies.get(y);
                     e.mySpeed--;
                     if(e.mySpeed<0)
                        e.mySpeed=0;
                     if(e.mySpeed==0&&(e.myX<0&&e.myY<0||e.myY>DIMENSION)||(e.myX>DIMENSION&&e.myY<0||e.myY>DIMENSION)){
                        enemies.remove(y);
                        y--;
                     }
                  }
               }
               else if(r.attackType==5){
                  p.i = null;//new ImageIcon("pics/fire.jpg");
               	//attacks.add(new Killer(0,0));
                  p.lifetime=255;
                  kill=true;
               }
               helpers.add(new Helper(DIMENSION));
            }
         }
      }
      public void checkEnemyCollisions(ArrayList<Enemy> enemies){
         for(int x=0;x<enemies.size();x++){
            Rectangle r = enemies.get(x);//Rectangle r: enemies){
            if(collision(r,p)||collision(p,r))
               if(kill){
                  enemies.remove(x);
                  x--;
                  numKilled++;
               }
               else
                  endGame();
         }
      }
      public boolean collision(Rectangle one, Rectangle two){
         int xDif = Math.abs(two.myX-one.myX);
         int yDif = Math.abs(two.myY-one.myY);
         if(one.myX<two.myX){
            if(one.myY<two.myY){
               if(xDif<one.myWidth&&yDif<one.myLength)
                  return true;
            }
            else{
               if(xDif<one.myWidth&&yDif<two.myLength)
                  return true;
            }
         } 
         return false;
      }
      public void spawnEnemies(){
         if(time%100==0){
            for(int x=0;x<(int)(time*.0004)+6*(time%3);x++){
               enemies.add(new Enemy(DIMENSION));
            }
         }
      }
      
      public void score(){
         score.setText("Time: "+time);
         score2.setText("numKilled: "+numKilled);
      }
      private class Mouse extends MouseAdapter{
         public void mouseClicked(MouseEvent e){
         	
         }
      }
      public void endGame(){
         //setFocusable(false);
         g.setColor(new Color(244,161,19,175));
         g.fillRect(0,0,DIMENSION,DIMENSION);
         label = new JLabel("Game Over     numKilled: "+numKilled);
         label.setForeground(Color.white);
         label.setFont(new Font("Serif",Font.BOLD,32));
         add(label);
         score.setVisible(false);
         score2.setVisible(false);
         label.setBounds(DIMENSION/2-200,DIMENSION/2-100,650,200);
         reset.setVisible(true);
         repaint();
         t.stop();
      }
      private class ResetListener implements ActionListener{
         public void actionPerformed(ActionEvent e){
            time = 0;
            remove(label);
            score.setVisible(true);
            score2.setVisible(true);
            while(attacks.size()!=0)
               attacks.remove(0);
            while(enemies.size()!=0)
               enemies.remove(0);
            while(helpers.size()!=0)
               helpers.remove(0);
            for(int x=0;x<4;x++)
               helpers.add(new Helper(DIMENSION));
            p = new Player(400,400);
            reset.setFocusable(false);
            reset.setVisible(false);
            numKilled = 0;
            requestFocusInWindow();
            t.start();
         }
      	
      }
   }
  
