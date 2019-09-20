
	import java.awt.*;
   public class Enemy extends Rectangle{
		int myStep, count;
      public Enemy(int x, int y, int l, int speed,Color c){
			super(x,y,l,speed,c);
			setDir(5);
			setEnemy(true);
			myStep=7;
			setHealth(2);
			setHEALTH(2);
      }
		public Enemy(int x, int y, int l, int speed,Color c, int h){
			super(x,y,l,speed,c);
			setDir(5);
			setEnemy(true);
			myStep=7;
			setHealth(h);
			setHEALTH(h);
      }
		public boolean canShoot(){
			return false;
		}
      public void draw(Graphics b){
		
			//health bar
			b.setColor(Color.green);
			b.fillRect(getX(),getY()-10,getLength()*getHealth()/getHEALTH(),5);
			//head and body
			b.setColor(getColor());
			if(count>0){//in case you were hit
				count--;
				b.setColor(Color.red);
			}
			b.fillRect(getX(),getY(),getLength(),getLength());
			b.fillRect(getX()+7,getY()+10,6,26);
         
			//eyes
			switch(getDir()){
			case 4:
			case 3:
			b.setColor(Color.white);
			b.fillRect(getX()+12,getY()+1,4,5);
			b.setColor(Color.black);
			b.fillRect(getX()+15,getY()+2,3,3);break;
			case 5:
			b.setColor(Color.white); //left eye
			b.fillRect(getX()+2,getY()+1,6,5);
			b.setColor(Color.black);
			b.fillRect(getX()+4,getY()+2,3,3);
			b.setColor(Color.white);  //right eye
			b.fillRect(getX()+12,getY()+1,6,5);
			b.setColor(Color.black);
			b.fillRect(getX()+13,getY()+2,3,3);break;
			case 6:
			case 7:
			b.setColor(Color.white);
			b.fillRect(getX()+4,getY()+1,4,5);
			b.setColor(Color.black);
			b.fillRect(getX()+2,getY()+2,3,3);break;
			}
			//arms
		if(getAttacking()>0)
			setAttacking(getAttacking()-1);
		if(getAttacking()>40){
			b.setColor(Color.red);
			b.fillRect(getX(),getY()-8,5,8);
			b.fillRect(getX()+15,getY()-8,5,8);
		}
		else if(getAttacking()>20){
			b.setColor(Color.red);
			switch(getDir()){
			case 1:
				b.fillRect(getX(),getY()-7,5,7);
				b.fillRect(getX()+15,getY()-7,5,7);break;
			case 2:
			case 3:
				int a[]={getX()+15,getX()+22,getX()+22,getX()+15};
				int q[]={getY()+18,getY()+14,getY()+20,getY()+24};
				b.fillRect(getX()+15,getY()+18,3,9);
				b.fillPolygon(a,q,4);break;
			case 4:
				int n[]={getX()+15,getX()+22,getX()+22,getX()+15};
				int h[]={getY()+24,getY()+29,getY()+35,getY()+30};
				b.fillPolygon(n,h,4);break;
			case 5:
				b.fillRect(getX(),getY()+28,5,8);
				b.fillRect(getX()+15,getY()+28,5,8);break;
			case 6:
				int m[]={getX()+5,getX()-2,getX()-2,getX()+5};
				int k[]={getY()+24,getY()+29,getY()+35,getY()+30};
				b.fillPolygon(m,k,4);break;
			case 7:
			case 8:
				int z[]={getX()+5,getX()-2,getX()-2,getX()+5};
				int x[]={getY()+18,getY()+14,getY()+20,getY()+24};
				b.fillRect(getX()+2,getY()+18,3,9);
			   b.fillPolygon(z,x,4);break;
			}
		}
		else if(getAttacking()>0){
			b.setColor(Color.red);
			switch(getDir()){
			case 1:
				b.fillRect(getX(),getY()-6,5,6);
				b.fillRect(getX()+15,getY()-6,5,6);break;
			case 2:
				int a[]={getX()+15,getX()+22,getX()+22,getX()+15};
				int q[]={getY()+18,getY()+14,getY()+20,getY()+24};
				b.fillRect(getX()+15,getY()+18,3,9);
				b.fillPolygon(a,q,4);break;
			case 3:
				b.fillRect(getX()+getLength(),getY()+25,8,5);break;
			case 4:
				int n[]={getX()+15,getX()+22,getX()+22,getX()+15};
				int h[]={getY()+24,getY()+29,getY()+35,getY()+30};
				b.fillPolygon(n,h,4);break;
			case 5:
				b.fillRect(getX(),getY()+28,5,8);
				b.fillRect(getX()+15,getY()+28,5,8);break;
			case 6:
				int m[]={getX()+5,getX()-2,getX()-2,getX()+5};
				int k[]={getY()+24,getY()+29,getY()+35,getY()+30};
				b.fillPolygon(m,k,4);break;
			case 7:
				b.fillRect(getX()-8,getY()+25,8,5);break;
			case 8:
				int z[]={getX()+5,getX()-2,getX()-2,getX()+5};
				int x[]={getY()+18,getY()+14,getY()+20,getY()+24};
				b.fillRect(getX()+2,getY()+18,3,9);
			   b.fillPolygon(z,x,4);break;
			}
		}
			//legs
			myStep++;
			b.setColor(Color.blue);
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
		public void draw(Graphics b, Color c){///////////////////////////////////////////////
			//head and body
			b.setColor(c);
			b.fillRect(getX(),getY(),getLength(),getLength());
			b.fillRect(getX()+7,getY()+10,6,26);
         
			//eyes
			switch(getDir()){
			case 4:
			case 3:
			b.fillRect(getX()+12,getY()+1,4,5);
			b.fillRect(getX()+15,getY()+2,3,3);break;
			case 5:
			b.fillRect(getX()+2,getY()+1,6,5);
			b.fillRect(getX()+4,getY()+2,3,3);
			b.fillRect(getX()+12,getY()+1,6,5);
			b.fillRect(getX()+13,getY()+2,3,3);break;
			case 6:
			case 7:
			b.fillRect(getX()+4,getY()+1,4,5);
			b.fillRect(getX()+2,getY()+2,3,3);break;
			}
			//arms
		if(getAttacking()>0)
			setAttacking(getAttacking()-1);
		if(getAttacking()>40){
			b.setColor(Color.red);
			b.fillRect(getX(),getY()-8,5,8);
			b.fillRect(getX()+15,getY()-8,5,8);
		}
		else if(getAttacking()>20){
			switch(getDir()){
			case 1:
				b.fillRect(getX(),getY()-7,5,7);
				b.fillRect(getX()+15,getY()-7,5,7);break;
			case 2:
			case 3:
				int a[]={getX()+15,getX()+22,getX()+22,getX()+15};
				int q[]={getY()+18,getY()+14,getY()+20,getY()+24};
				b.fillRect(getX()+15,getY()+18,3,9);
				b.fillPolygon(a,q,4);break;
			case 4:
				int n[]={getX()+15,getX()+22,getX()+22,getX()+15};
				int h[]={getY()+24,getY()+29,getY()+35,getY()+30};
				b.fillPolygon(n,h,4);break;
			case 5:
				b.fillRect(getX(),getY()+28,5,8);
				b.fillRect(getX()+15,getY()+28,5,8);break;
			case 6:
				int m[]={getX()+5,getX()-2,getX()-2,getX()+5};
				int k[]={getY()+24,getY()+29,getY()+35,getY()+30};
				b.fillPolygon(m,k,4);break;
			case 7:
			case 8:
				int z[]={getX()+5,getX()-2,getX()-2,getX()+5};
				int x[]={getY()+18,getY()+14,getY()+20,getY()+24};
				b.fillRect(getX()+2,getY()+18,3,9);
			   b.fillPolygon(z,x,4);break;
			}
		}
		else if(getAttacking()>0){
			switch(getDir()){
			case 1:
				b.fillRect(getX(),getY()-6,5,6);
				b.fillRect(getX()+15,getY()-6,5,6);break;
			case 2:
				int a[]={getX()+15,getX()+22,getX()+22,getX()+15};
				int q[]={getY()+18,getY()+14,getY()+20,getY()+24};
				b.fillRect(getX()+15,getY()+18,3,9);
				b.fillPolygon(a,q,4);break;
			case 3:
				b.fillRect(getX()+getLength(),getY()+25,8,5);break;
			case 4:
				int n[]={getX()+15,getX()+22,getX()+22,getX()+15};
				int h[]={getY()+24,getY()+29,getY()+35,getY()+30};
				b.fillPolygon(n,h,4);break;
			case 5:
				b.fillRect(getX(),getY()+28,5,8);
				b.fillRect(getX()+15,getY()+28,5,8);break;
			case 6:
				int m[]={getX()+5,getX()-2,getX()-2,getX()+5};
				int k[]={getY()+24,getY()+29,getY()+35,getY()+30};
				b.fillPolygon(m,k,4);break;
			case 7:
				b.fillRect(getX()-8,getY()+25,8,5);break;
			case 8:
				int z[]={getX()+5,getX()-2,getX()-2,getX()+5};
				int x[]={getY()+18,getY()+14,getY()+20,getY()+24};
				b.fillRect(getX()+2,getY()+18,3,9);
			   b.fillPolygon(z,x,4);break;
			}
		}
			//legs
			myStep++;
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
		public void attack(){
			setAttacking(60);
		}
		public void react(){
			count=3;
		}
   }