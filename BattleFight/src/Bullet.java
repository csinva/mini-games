	import java.awt.*;
   public class Bullet extends Rectangle{
		private int on, time, incrementer, shooter;
		private boolean exploding, rocket;
      public Bullet(int x, int y, int l, int s, Color c, int d){
			super(x,y,l,s,c);
			setDir(d);
			on=0;
			int time=0;
			incrementer=5;
			setBullet(true);
			setGrenade(false);
			exploding=false;
			rocket=false;
			shooter=100;
      }
   	public int getOn(){//0 is not used, 1 is shot, 2 is hit
			return on;
		}
		public int getShooter(){
			return shooter;
		}
		public boolean isExploding(){
			return exploding;
		}
		public boolean isRocket(){
			return rocket;
		}
		public void setRocket(boolean r){
			rocket=r;
		}
		public void setExploding(boolean e){
			exploding=e;
		}
		public void setOn(int o){
			on=o;
		}
		public void setShooter(int s){
			shooter=s;
		}
      public void draw(Graphics b){
		if(!exploding){
			b.setColor(getColor());
			b.fillRect(getX(), getY(), getLength(), getLength());
      }
		else{
		time++;
			int negative=(int)(Math.random()*2);
			if(negative==0)
				negative=-1;
			int random=(int)(Math.random()*5)*negative;
			int random2=(int)(Math.random()*5+6*negative);
			int random3=(int)(Math.random()*5+9)*negative;
			int random3_2=(int)(Math.random()*5+9)*negative;
			int random3_3=(int)(Math.random()*5+9)*negative;
			int random3_4=(int)(Math.random()*5+9)*negative;
			int random4=(int)(Math.random()*5+14)*negative;
			int random4_2=(int)(Math.random()*5+14)*negative;
			int random4_3=(int)(Math.random()*5+14)*negative;
			int random4_4=(int)(Math.random()*5+14)*negative;
			int random5=(int)(Math.random()*5+17)*negative;
			int random5_2=(int)(Math.random()*5+17)*negative;
			int random5_3=(int)(Math.random()*5+17)*negative;
			int random5_4=(int)(Math.random()*5+17)*negative;
			
			if(time<=incrementer){///////////exploding//1
				b.setColor(new Color(255,188,98));
				b.fillOval(getX(),getY(),16,16);
				
				b.fillOval(getX()+random,getY()-6,13,13);
				b.fillOval(getX(),getY()+random,13,13);
				setLength(13);
			}
			else if(time<=incrementer*2){//2
				b.setColor(new Color(255,178,98));
				b.fillOval(getX(),getY(),16,16);
				
				b.fillOval(getX()+random,getY()-6,13,13);
				b.fillOval(getX(),getY()+random,13,13);//
				
				b.fillOval(getX()+random2,getY()-6,14,14);
				b.fillOval(getX(),getY()+random2,14,14);
				setLength(19);
			}
			else if(time<=incrementer*3){//3
				b.setColor(new Color(255,168,98));
				b.fillOval(getX(),getY(),16,16);
				
				b.fillOval(getX()+random,getY()-6,13,13);
				b.fillOval(getX(),getY()+random,13,13);
				
				b.fillOval(getX()+random2,getY()-6,14,14);
				b.fillOval(getX(),getY()+random2,14,14);//
				
				b.fillOval(getX()+random3,getY()-8,15,15);
				b.fillOval(getX()+random,getY()+random3,15,15);
				setLength(25);
			}
			else if(time<=incrementer*4){//4
				b.setColor(new Color(255,158,98));
				b.fillOval(getX(),getY(),16,16);
				
				b.fillOval(getX()+random,getY()-6,13,13);
				b.fillOval(getX(),getY()+random,13,13);
				
				b.fillOval(getX()+random2,getY()-8,14,14);
				b.fillOval(getX(),getY()+random2,14,14);
				
				b.fillOval(getX()+random3,getY()-9,15,15);
				b.fillOval(getX()+random,getY()+random3,15,15);//
				
				b.fillOval(getX()+random3_2,getY()-10,15,15);
				b.fillOval(getX()+6,getY()+random3_2,15,15);
				
				b.fillOval(getX()+random3_3,getY()-11,15,15);
				b.fillOval(getX()-8,getY()+random3_3,15,15);
				
				b.fillOval(getX()+random3_4,getY()-12,15,15);
				b.fillOval(getX(),getY()+random3_4,15,15);
				setLength(50);
			}
			else if(time<=incrementer*5){//5
				b.setColor(new Color(255,148,98));
				b.fillOval(getX(),getY(),16,16);
				
				b.fillOval(getX()+random,getY()-6,13,13);
				b.fillOval(getX(),getY()+random,13,13);
				
				b.fillOval(getX()+random2,getY()-6,14,14);
				b.fillOval(getX(),getY()+random2,14,14);
				
				b.fillOval(getX()+random3,getY()-6,15,15);
				b.fillOval(getX()+random,getY()+random3,15,15);
				
				b.fillOval(getX()+random3_2,getY()-6,15,15);
				b.fillOval(getX()+6,getY()+random3_2,15,15);
				
				b.fillOval(getX()+random3_3,getY()-6,15,15);
				b.fillOval(getX()-8,getY()+random3_3,15,15);
				
				b.fillOval(getX()+random3_4,getY()-6,15,15);
				b.fillOval(getX(),getY()+random3_4,15,15);//
				
				b.fillOval(getX()+random4,getY()-11,15,15);
				b.fillOval(getX()-11,getY()+random4,15,15);
				
				b.fillOval(getX()+random4_2,getY()-5,15,15);
				b.fillOval(getX()-5,getY()+random3_2,15,15);
				
				b.fillOval(getX()+random4_3,getY()+2,15,15);
				b.fillOval(getX()+2,getY()+random3_3,15,15);
				
				b.fillOval(getX()+random4_4,getY()+7,15,15);
				b.fillOval(getX()+9,getY()+random4_4,15,15);
				setLength(65);
			}
			else if(time<=incrementer*6){//6
				b.setColor(new Color(255,138,98));
				b.fillOval(getX(),getY(),16,16);
				
				b.fillOval(getX()+random,getY()-6,13,13);
				b.fillOval(getX(),getY()+random,13,13);
				
				b.fillOval(getX()+random2,getY()-6,14,14);
				b.fillOval(getX(),getY()+random2,14,14);
				
				b.fillOval(getX()+random3,getY()-6,15,15);
				b.fillOval(getX()+random,getY()+random3,15,15);
				
				b.fillOval(getX()+random3_2,getY()-6,15,15);
				b.fillOval(getX()+6,getY()+random3_2,15,15);
				
				b.fillOval(getX()+random3_3,getY()-6,15,15);
				b.fillOval(getX()-8,getY()+random3_3,15,15);
				
				b.fillOval(getX()+random3_4,getY()-6,15,15);
				b.fillOval(getX(),getY()+random3_4,15,15);
				
				b.fillOval(getX()+random4,getY()-11,15,15);
				b.fillOval(getX()-11,getY()+random4,15,15);
				
				b.fillOval(getX()+random4_2,getY()-5,15,15);
				b.fillOval(getX()-5,getY()+random3_2,15,15);
				
				b.fillOval(getX()+random4_3,getY()+2,15,15);
				b.fillOval(getX()+2,getY()+random3_3,15,15);
				
				b.fillOval(getX()+random4_4,getY()+7,15,15);
				b.fillOval(getX()+9,getY()+random4_4,15,15);//
				
				b.fillOval(getX()+random5,getY()-15,15,15);
				b.fillOval(getX()-13,getY()+random5,15,15);
				
				b.fillOval(getX()+random5_2,getY()-8,15,15);
				b.fillOval(getX()-8,getY()+random5_2,15,15);
				
				b.fillOval(getX()+random5_3,getY()+5,15,15);
				b.fillOval(getX()+7,getY()+random5_3,15,15);
				
				b.fillOval(getX()+random5_4,getY()+12,15,15);
				b.fillOval(getX()+11,getY()+random5_4,15,15);
				setLength(75);
			}
			else{
				on=2;
				setExploding(false);
				setLength(6);
				time=0;
			}
      }
		}
	}