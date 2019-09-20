   import java.awt.*;
   import java.util.*;
   public class Killer extends Attack{
   	// Rectangle one;
   // 		Rectangle two;
      public Killer(int x, int y){	
         super(x,y);
      }
      
      public void act(){
         lifetime--;	
      }
   }