
	import java.awt.*;
   public class Rectangle{
		int myX;
		int myY;
		public Rectangle(int x,int y){
			myX=x;
			myY=y;
		}
		public void draw(Graphics b){
			b.fillRect(myX,myY,50,50);
		}
   }