   import java.awt.*;
   public class Helper extends Rectangle{
      public int attackType;
      public Helper(int DIMENSION){
         super((int)(Math.random()*(DIMENSION-30)),(int)(Math.random()*(DIMENSION-30)));	
         attackType = (int)(Math.random()*29);
         if(attackType<7)
            attackType=1;
         else if(attackType<14)
            attackType=2;
         else if(attackType<21)
            attackType=3;
         else if(attackType<22)
            attackType=4;
			else if(attackType<29)
				attackType=5;
      	//attackType=5;
         if(attackType==1)
            myColor = Color.cyan;
         else if(attackType==2)
            myColor = Color.black;
         else if(attackType==3)
            myColor = new Color(255,0,0,200);//Color.red;
         else if(attackType==4)
            myColor = Color.white;
			else if(attackType==5)
            myColor = Color.green;
         myLength = 30;
         myWidth = 30;
         if(attackType==4){
            myLength = 50;
            myWidth = 50;
         }
      }
   }