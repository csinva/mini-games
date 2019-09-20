   import java.awt.*;
	import java.util.*;
	public abstract class Attack extends Rectangle{
		int lifetime;
		ArrayList<Rectangle> rects;
		public Attack(int x, int y){	
			super(x,y);
			lifetime = 255;
			myColor = Color.white;
			rects = new ArrayList<Rectangle>();
		}
		public void draw(Graphics g){
         g.setColor(myColor);
         for(Rectangle one:rects)
            g.fillRect(one.myX,one.myY,one.myWidth,one.myLength);
      	//g.fillRect(two.myX,two.myY,two.myWidth,two.myLength);
      }
		 public void act(){
         lifetime--;
      }
	}