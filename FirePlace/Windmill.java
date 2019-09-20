   import java.awt.*;
   import java.util.*;
   public class Windmill extends Attack{
   	// Rectangle one;
   // 		Rectangle two;
      public Windmill(int x, int y){	
         super(x,y);
         myColor = new Color(0,255,255,lifetime);//Color.cyan;
         BasicRect rectOne = new BasicRect(myX-10,myY);
         rectOne.myWidth = 30;
         rectOne.myLength = 5;
         rects.add(rectOne);
         BasicRect rectTwo = new BasicRect(myX,myY-10);
         rectTwo.myLength = 30;
         rectTwo.myWidth = 5;
         rects.add(rectTwo);
         lifetime=300;
      	// one = rectOne;
      // 			two = rectTwo;		
      }
      
      public void act(){
         lifetime--;
         if(lifetime>=40)
            if(lifetime<=255)
               myColor = new Color(0,255,255,lifetime);
            else
               myColor = new Color(0,255,255,255);
         if(lifetime>0){
            rects.get(0).myWidth+=20;
            rects.get(1).myLength+=20;
         }
         rects.get(0).myX-=10;
         rects.get(1).myY-=10;			
      }
   }