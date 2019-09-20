   import java.awt.*;
   import javax.swing.*;
   public class Player extends Rectangle{
      int lifetime;
      public Player(int x,int y){
         super(x,y);
         i=new ImageIcon("pics/Player.jpg");
         myColor=Color.green;
         myWidth = myLength = 15;
         lifetime=255;
      }
      public void draw(Graphics g){
         if(i==null){		
            myColor = new Color(0,255,0,lifetime);
				if(lifetime<22){
					 myColor = new Color(255,255,255);
				}
				lifetime--;
            g.setColor(myColor);
            g.fillRect(myX,myY,myWidth,myLength);
         }
         else{
            g.drawImage(i.getImage(),myX,myY,myWidth,myLength,null);
         }
      }
   }