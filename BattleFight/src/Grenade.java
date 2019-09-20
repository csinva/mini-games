	import java.awt.*;
   public class Grenade extends Bullet{
		final int LIFETIME=140;//when it should start exploding
		int DIR=1;
		int lifeTime;
		final int incrementer=8;
      public Grenade(int x, int y, int l, int s, Color c, int d){
			super(x,y,l,s,c,d);
			lifeTime=0;
			setGrenade(true);
			setGrenadable(true);
      }
		public int getLifeTime(){
			return lifeTime;
		}
		public int getDIR(){
			return DIR;
		}
		public void setDIR(int x){
			DIR=x;
		}
      public void draw(Graphics b){
		lifeTime++;			
			if(lifeTime<=LIFETIME){
				b.setColor(Color.black);
				b.fillOval(getX(),getY(),10,10);
			}
		else{
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
			negative=(int)(Math.random()*2);
			int random4_2=(int)(Math.random()*5+14)*negative;
			int random4_3=(int)(Math.random()*5+14)*negative;
			int random4_4=(int)(Math.random()*5+14)*negative;
			int random5=(int)(Math.random()*5+17)*negative;
			int random5_2=(int)(Math.random()*5+17)*negative;
			int random5_3=(int)(Math.random()*5+17)*negative;
			int random5_4=(int)(Math.random()*5+17)*negative;
			
			if(lifeTime<=LIFETIME+incrementer){///////////exploding//1
				b.setColor(new Color(255,188,98));
				b.fillOval(getX(),getY(),16,16);
				
				b.fillOval(getX()+random,getY()-6,13,13);
				b.fillOval(getX(),getY()+random,13,13);
				setLength(13);
			}
			else if(lifeTime<=LIFETIME+incrementer*2){//2
				b.setColor(new Color(255,178,98));
				b.fillOval(getX(),getY(),16,16);
				
				b.fillOval(getX()+random,getY()-6,13,13);
				b.fillOval(getX(),getY()+random,13,13);//
				
				b.fillOval(getX()+random2,getY()-6,14,14);
				b.fillOval(getX(),getY()+random2,14,14);
				setLength(19);
			}
			else if(lifeTime<=LIFETIME+incrementer*3){//3
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
			else if(lifeTime<=LIFETIME+incrementer*4){//4
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
			else if(lifeTime<=LIFETIME+incrementer*5){//5
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
			else if(lifeTime<=LIFETIME+incrementer*6){//6
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
		}
      }
	}