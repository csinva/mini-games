   import java.awt.*;
   import java.util.*;
   public class Timebomb extends Attack{
   	// Rectangle one;
   // 		Rectangle two;
      public Timebomb(int x, int y){	
         super(x,y);
			lifetime=3;
			myColor = Color.white;
			BasicRect rectOne = new BasicRect(0,0);
			rectOne.myWidth=800;
			rectOne.myLength=800;
			rects.add(rectOne);
      }
   }