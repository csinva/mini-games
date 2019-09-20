   import java.awt.*;
   import javax.swing.*;
   public class Enemy extends Rectangle{
      public int startDir;
      public int toggle;
      public Enemy(int DIMENSION){
         super(0,0);
         i=new ImageIcon("pics/polar bear.jpg");//http://www.speedofanimals.com/animals/polar_bear
         mySpeed = 2;
         myColor = Color.red;	
         startDir = (int)(Math.random()*4+1);
         myX = (int)(-10+Math.random()*(DIMENSION+10));
         myY=100+(int)(Math.random()*(DIMENSION-myLength-100));	
         switch(startDir){
            case 1:
               myY-=(int)(DIMENSION*1.2);
               break;
            case 2:
               myX+=(int)(DIMENSION*1.2);
               break;
            case 3:
               myY+=(int)(DIMENSION*1.2);
               break;
            case 4:
               myX-=(int)(DIMENSION*1.2);
               break;
         }
      }
      public void draw(Graphics g){
         toggle++;
			String filename = "pics/bear";
			
         if(myDir>5)
				filename+="L";
        else
            filename+="R";
		  if(toggle/4%2==1)
				filename+="1.png";
			else
				filename+="2.png";
			i=new ImageIcon(filename);
         g.drawImage(i.getImage(),myX,myY,myWidth,myLength,null);
      }
   }