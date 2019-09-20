   import java.awt.*;
   import java.util.*;
   public class Fire extends Attack{
   	// Rectangle one;
   // 		Rectangle two;
      int LIFETIME;
      public Fire(int x, int y){	
         super(x,y);
      	//lifetime = 225;
         LIFETIME=lifetime;
         myColor = new Color(255,0,0,lifetime);//Color.red;
         BasicRect rectOne = new BasicRect(myX-30,myY-30);
         rectOne.myWidth = 90;//Helper is 30 x 30
         rectOne.myLength = 90;
         rects.add(rectOne);
      }
      
      public void act(){
         lifetime--;	
         if(lifetime>LIFETIME/2){
            if(lifetime%2==0){
               rects.get(0).myX--;
               rects.get(0).myY--;
            }
            rects.get(0).myWidth++;
            rects.get(0).myLength++;
         }
         else{
				if(lifetime%2==0){
               rects.get(0).myX++;
               rects.get(0).myY++;
            }
            rects.get(0).myWidth--;
            rects.get(0).myLength--;
         }
         if(lifetime>=40)
            myColor = new Color(255,0,0,lifetime);
      }
   }