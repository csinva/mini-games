	import java.awt.*;
   public class Box extends Rectangle{
		private int myLifeSpan, on,mytype;//tells what the box gives you
		final int LIFESPAN=2000;
      public Box(int x, int y, int l){
			super(x,y,l,0,new Color(255,0,0,150));
			myLifeSpan=LIFESPAN;
			on=0;
			mytype=0;
      }
		 public Box(int x, int y, int l, int t){
			super(x,y,l,0,new Color(255,0,0,150));
			myLifeSpan=LIFESPAN;
			on=0;
			mytype=t;
      }
		public int getLifeSpan(){
			return myLifeSpan;
		}
		public void setLifeSpan(int s){
			myLifeSpan=s;
		}
		public void setOn(int b){
			on=b;
		}
		public int getOn(){
			return on;
		}
      public void draw(Graphics b){
		if(mytype==0){
			myLifeSpan--;
			if(myLifeSpan<0)
				on=2;
			if(myLifeSpan>LIFESPAN*.95)
				b.setColor(new Color(255,0,0,150));
			else if(myLifeSpan>LIFESPAN*.9)
				b.setColor(new Color(255,0,0,200));
			else if(myLifeSpan>LIFESPAN*.3)
				b.setColor(Color.red);
			else if(myLifeSpan>LIFESPAN*.15)
				b.setColor(new Color(255,0,0,200));
			else
				b.setColor(new Color(255,0,0,150));
				
			b.fillRect(getX(),getY(),getLength(),getLength());
		}
		else{
			b.setColor(Color.white);
			b.fillRect(getX(),getY(),getLength(),getLength());
			b.setColor(Color.red);
			b.fillRect(getX()+(getLength()/2)-2,getY()+2,5,getLength()-4);
			b.fillRect(getX()+2,getY()+getLength()/2-2,getLength()-4,4);
		}
      }
   }