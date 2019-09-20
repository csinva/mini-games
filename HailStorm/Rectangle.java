   import java.awt.*;
   public class Rectangle{
      public int myX, myY, myDir, myHealth, mySpeed, myLength;
      public Color myColor;
      public boolean alive;
      public Rectangle(int x,int y){
         myX=x;
         myY=y;
         myDir = 5;
         myHealth = 20;
         mySpeed = 3;
         myLength = 10;
         myColor = Color.black;
         alive = true;
      }
      public void draw(Graphics g){
         g.setColor(myColor);
         g.fillRect(myX,myY,myLength,myLength);
      }
      public void move(){
         switch(myDir){
            case -1:
               break;
            case 1:myY = myY -mySpeed;
               break;
            case 2:myY = (int)(myY -mySpeed*.7);
               myX = (int)(myX+mySpeed*.7);
               break;
            case 3:myX = myX+mySpeed;
               break;
            case 4:myX = (int)(myX+mySpeed*.7);
               myY = (int)(myY +mySpeed*.7);
               break;
            case 5:myY = myY +mySpeed;
               break;
            case 6:myY = (int)(myY +mySpeed*.7);
               myX = (int)(myX-mySpeed*.7);
               break;
            case 7:myX = myX-mySpeed;
               break;
            case 8:myX = (int)(myX-mySpeed*.7);
               myY = (int)(myY -mySpeed*.7);
               break;
         }
      }	
   }