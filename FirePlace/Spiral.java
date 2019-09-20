   import java.awt.*;
   import java.util.*;
   public class Spiral extends Attack{
      Rectangle one;
      Rectangle two;
      public Spiral(int x, int y){	
         super(x,y);
         myColor = Color.black;
         BasicRect rectOne = new BasicRect(myX-10,myY);
         rectOne.myWidth = 10;
         rectOne.myLength = 5;
         rectOne.possibleDirections.remove(3);
         BasicRect rectTwo = new BasicRect(myX,myY-10);
         rectTwo.myLength = 10;
         rectTwo.myWidth = 5;
         rectTwo.possibleDirections.remove(5);
         BasicRect rectThree= new BasicRect(myX+10,myY);
         rectThree.myLength = 5;
         rectThree.myWidth = 10;
         rectThree.possibleDirections.remove(7);
         BasicRect rectFour = new BasicRect(myX,myY+10);
         rectFour.myLength = 10;
         rectFour.myWidth = 5;
         rectFour.possibleDirections.remove(1);
         rects.add(rectOne);
         rects.add(rectTwo);
         rects.add(rectThree);
         rects.add(rectFour);
			lifetime = 200;
      }
      public void act(){
         lifetime--;
         if(lifetime>80){
            for(int x = 0;x<7;x+=2){
               BasicRect r = (BasicRect)(rects.get(x));
               int dir = -1;
               while(dir == -1){
                  for(Integer i: r.possibleDirections){
                     if(Math.random()<.2)
                        dir = i;
                  }
               }
               int dirNot = dir-4;
               if(dirNot<0)
                  dirNot+=8;
               if(r.possibleDirections.contains(dirNot))
                  r.possibleDirections.remove(dirNot);
               int bx=r.myX;
               int by=r.myY;
               switch(dir){
                  case 1:
                     by-=10;
                     break;
                  case 3:
                     bx+=10;
                     break;
                  case 5: 
                     by+=10;
                     break;
                  case 7:
                     bx-=10;
                     break;
               }
               BasicRect b= new BasicRect(bx,by);
               b.possibleDirections = new HashSet<Integer>();
               for(int j:r.possibleDirections){
                  b.possibleDirections.add(j);
               }
               rects.add(0,b);
            }
         }		
      }
   }