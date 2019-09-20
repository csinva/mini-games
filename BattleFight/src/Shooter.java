import java.awt.*;
public class Shooter extends Enemy{
	int myStep;
	public Shooter(int x, int y, int l, int speed,Color c){
			super(x,y,l,speed,c);
			if(Math.random()<.5)
				setDir(7);
			else
				setDir(5);
			setBulletColor(c);
			setShooter(true);
			setShotFrequency(50);
			setShot((int)(Math.random()*getShotFrequency()));
      }
	
		
	public void draw(Graphics b){
			setShot(getShot()+1);
			
			//health bar
			b.setColor(Color.green);
			int width=getLength()*getHealth()/getHEALTH();
			if(width<getLength()*.5)
				b.setColor(Color.red);
			b.fillRect(getX(),getY()-10,width,5);
			
			//head and body
			b.setColor(getColor());
			b.fillRect(getX(), getY(), getLength(), getLength());
			b.fillRect(getX()+7,getY()+10,6,26);
         
			//eyes
			switch(getDir()){
			case 4:
			case 3:
			b.setColor(Color.red);
			b.fillRect(getX()+12,getY()+1,4,5);
			b.setColor(Color.black);
			b.fillRect(getX()+15,getY()+2,3,3);break;
			case 5:
			b.setColor(Color.red); //left eye
			b.fillRect(getX()+2,getY()+1,6,5);
			b.setColor(Color.black);
			b.fillRect(getX()+4,getY()+2,3,3);
			b.setColor(Color.red);  //right eye
			b.fillRect(getX()+12,getY()+1,6,5);
			b.setColor(Color.black);
			b.fillRect(getX()+13,getY()+2,3,3);break;
			case 6:
			case 7:
			b.setColor(Color.red);
			b.fillRect(getX()+4,getY()+1,4,5);
			b.setColor(Color.black);
			b.fillRect(getX()+2,getY()+2,3,3);break;
			}
			
			//guns
			b.setColor(Color.black);
			switch(getDir()){
				case 1:b.fillRect(getX()+8,getY()-4,4,5);break;
				case 3:b.fillRect(getX()+15,getY()+22,12,4);
						 b.fillRect(getX()+15,getY()+22,8,6);
						 b.fillRect(getX()+15,getY()+22,3,9);break;
				case 5:b.fillRect(getX()+8,getY()+20,4,5);break;
				case 7:b.fillRect(getX()-7,getY()+22,12,4);
						 b.fillRect(getX()-3,getY()+22,8,6);
						 b.fillRect(getX()+3,getY()+22,3,9);break;
				case 2:int a[]={getX()+15,getX()+22,getX()+22,getX()+15};
						 int q[]={getY()+18,getY()+14,getY()+20,getY()+24};
						 b.fillRect(getX()+15,getY()+18,3,9);
						 b.fillPolygon(a,q,4);break;
				case 4:int n[]={getX()+15,getX()+22,getX()+22,getX()+15};
						 int h[]={getY()+24,getY()+29,getY()+35,getY()+30};
						  b.fillPolygon(n,h,4);break; 
				case 6:int m[]={getX()+5,getX()-2,getX()-2,getX()+5};
						 int k[]={getY()+24,getY()+29,getY()+35,getY()+30};
						  b.fillPolygon(m,k,4);break;
				case 8:int z[]={getX()+5,getX()-2,getX()-2,getX()+5};
						 int x[]={getY()+18,getY()+14,getY()+20,getY()+24};
						 b.fillRect(getX()+2,getY()+18,3,9);
						 b.fillPolygon(z,x,4);break;
			}
				
			//legs
			myStep++;
			b.setColor(getColor());
			int[] leftLegX={getX()+7,getX()+12,getX()+5,getX()};
			int[] leftLegY={getY()+31,getY()+36,getY()+46,getY()+39};
			int[] rightLegX={getX()+13,getX()+8,getX()+15,getX()+20};
			int[] rightLegY={getY()+31,getY()+36,getY()+46,getY()+41};
			
			if(myStep/8%2==1){
			b.fillPolygon(leftLegX,leftLegY,4);
			b.fillPolygon(rightLegX,rightLegY,4);
			}
			else
			b.fillRect(getX()+7,getY()+36,6,14);
		}
}