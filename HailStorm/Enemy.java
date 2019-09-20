   import java.awt.*;
   public class Enemy extends Rectangle{
      public Enemy(int DIMENSION){
         super(0,0);
         myX = (int)(-10+Math.random()*(DIMENSION+10));
         myY=-700+100+(int)(Math.random()*(DIMENSION-myLength));		
			myColor = Color.blue;	
      }
   }